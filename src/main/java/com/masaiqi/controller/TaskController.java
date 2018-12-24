package com.masaiqi.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.masaiqi.entity.*;
import com.masaiqi.json.JsonResult;
import com.masaiqi.model.ReqModel.ReqProjejct;
import com.masaiqi.model.ReqModel.ReqTask;
import com.masaiqi.model.ResModel.ResTask;
import com.masaiqi.model.ResModel.ResTaskPlus;
import com.masaiqi.service.IAccessoryService;
import com.masaiqi.service.ICommitCheckService;
import com.masaiqi.service.ITaskService;
import com.masaiqi.service.IUserService;
import com.masaiqi.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 任务表 前端控制器
 * </p>
 *
 * @author masaiqi
 * @since 2018-10-22
 */
@RestController
public class TaskController {

    @Autowired
    ITaskService taskService;

    @Autowired
    IAccessoryService accessoryService;

    @Autowired
    IUserService userService;

    @Autowired
    ICommitCheckService commitCheckService;

    /**
     *
     * 获取任务信息
     * @param task
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "${vue-url.getTask}",method = RequestMethod.POST)
    public JsonResult getTask(@RequestBody Task task)throws Exception {
        if(task == null){
            return new JsonResult("未传入信息");
        }
        List<ResTask> lists = taskService.getTask(task);
        lists.forEach(obj ->{
            if(task.getUserStatus() == "已提交"){
                obj.setFlag(1);
            }
            if(obj.getAccessoryPersonId() != null){
                Accessory accessory = (Accessory) accessoryService.getObj(new QueryWrapper<Accessory>().eq("Id",obj.getAccessoryPersonId()));
                obj.setAccessoryPerson(accessory);
            }
        });
        JsonResult jsonResult = new JsonResult(lists);
//        String jsonStr = JSONObject.toJSONString(jsonResult);
//        WebSocketServer.sendInfo(jsonStr);
        return jsonResult;
    }

    /**
     *
     * 添加task信息
     * @param reqTask
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "${vue-url.addTask}",method = RequestMethod.POST)
    public JsonResult addTask(@RequestBody ReqTask reqTask)throws Exception {
        if(reqTask == null){
            return new JsonResult("未传入信息");
        }
        taskService.addTask(reqTask);
        return new JsonResult(true,null,null);
    }

    /**
     *
     * 修改task信息
     * @param reqTask
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "${vue-url.updateTask}",method = RequestMethod.POST)
    public JsonResult updateTask(@RequestBody ReqTask reqTask)throws Exception {
        if(reqTask == null){
            return new JsonResult("未传入信息");
        }
        Task task = new Task();
        task = reqTask.getTask();
        task.setId(reqTask.getId());
        Accessory ac =  reqTask.getAccessory();
        Accessory acP = reqTask.getAccessoryPerson();
        accessoryService.updateById(ac);
        accessoryService.save(acP);
        task.setAccessoryId(ac.getId());
        task.setAccessoryPersonId(acP.getId());
        taskService.updateById(task);
        CommitCheck commitCheck = new CommitCheck();
        commitCheck.setTime(LocalDate.now());
        commitCheck.setTaskStatus("未完成");
        commitCheck.setIsCheck(0);
        commitCheck.setTaskId(task.getId());
        commitCheckService.save(commitCheck);
        return new JsonResult(true,null,null);
    }

    /**
     * 查询task信息
     */
    @RequestMapping(value = "${vue-url.getTaskPlus}",method = RequestMethod.POST)
    public JsonResult getTaskPlus(@RequestBody Project project)throws Exception {
        if(project == null){
            return new JsonResult("未传入信息");
        }
        List<ResTaskPlus> resTaskPlusList = new ArrayList<>(0);
        List<Task> tasks = taskService.list(new QueryWrapper<Task>().lambda()
            .eq(Task::getProjectId,project.getId())
        );
        tasks.forEach(obj ->{
            ResTaskPlus resTaskPlus = new ResTaskPlus();
            //个人完成情况: 0-默认 1-准备做 2-已经完成
            if(obj.getPersonStatus() == 0){
                resTaskPlus.setPersonStatusFormat("默认");
            }else {
                if(obj.getPersonStatus() == 1){
                    resTaskPlus.setPersonStatusFormat("准备做");
                }else {
                    resTaskPlus.setPersonStatusFormat("已经完成");
                }
            }

            User user = userService.getById(obj.getUserId());
            Accessory accessory1 = (Accessory) accessoryService.getObj(new QueryWrapper<Accessory>().eq("id",obj.getAccessoryId()));
            Accessory accessory2 = (Accessory) accessoryService.getObj(new QueryWrapper<Accessory>().eq("id",obj.getAccessoryPersonId()));
            resTaskPlus.setUser(user);
            resTaskPlus.setAccessory(accessory1);
            resTaskPlus.setAccessoryPerson(accessory2);
            resTaskPlus.setTask(obj);
            resTaskPlus.setTaskName(obj.getName());
            resTaskPlus.setUserName(user == null ? null:user.getName());
            resTaskPlus.setTaskUserStatus(obj.getUserStatus());
            resTaskPlus.setTaskStatus(obj.getStatus());
            resTaskPlus.setAccessoryUrl(accessory1 == null ? null:accessory1.getUrl());
            resTaskPlus.setAccessoryPersonUrl(accessory2 == null ? null:accessory2.getUrl());
            resTaskPlus.setTaskMsg(obj.getMsg());
            resTaskPlus.setTaskId(obj.getId());
            resTaskPlusList.add(resTaskPlus);
        });
        return new JsonResult(resTaskPlusList);
    }

    /**
     * 修改任务信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "${vue-url.updateProjectPlus}",method = RequestMethod.POST)
    public JsonResult updateProjectPlus(@RequestBody ResTaskPlus resTaskPlus)throws Exception{
        if(resTaskPlus == null){
            return new JsonResult("未传入信息");
        }
        accessoryService.updateById(resTaskPlus.getAccessory());
        taskService.updateById(resTaskPlus.getTask());
        return new JsonResult(true,"",null);
    }

    /**
     *
     */
    @RequestMapping(value = "${vue-url.deleteTask}",method = RequestMethod.POST)
    public JsonResult deleteTask(@RequestBody Task task)throws Exception{
        if(task== null){
            return new JsonResult("未传入信息");
        }
        if(task.getAccessoryId() != null){
            accessoryService.removeById(task.getAccessoryId());
        }
        if(task.getAccessoryPersonId() != null){
            accessoryService.removeById(task.getAccessoryPersonId());
        }
        taskService.removeById(task.getId());
        return new JsonResult(true,"",null);
    }

    /**
     *
     */
    @RequestMapping(value = "${vue-url.getTaskFinished}",method = RequestMethod.POST)
    public JsonResult getTaskFinished(@RequestBody User user)throws Exception{
        if(user == null){
            return new JsonResult("未传入信息");
        }
        List<Task> tasks = taskService.list(new QueryWrapper<Task>()
                .eq("userId",user.getId())
                .eq("status","已完成")
        );
        tasks.forEach(obj ->{
            List<CommitCheck> lists = commitCheckService.list(new QueryWrapper<CommitCheck>().eq("taskId",obj.getId()).orderByDesc("time"));
            Accessory temp0 = accessoryService.getById(obj.getAccessoryId());
            Accessory temp1 = accessoryService.getById(obj.getAccessoryPersonId());
            obj.setTime(lists.get(0).getTimeFormat());
            obj.setCheckTime(lists.get(0).getCheckTimeFormat());
            obj.setAccessoryUrl(temp0.getUrl());
            obj.setAccessoryPersonUrl(temp1.getUrl());
        });
        return new JsonResult(tasks);
    }

    /**
     *
     */
    @RequestMapping(value = "${vue-url.updatePersonStatus}",method = RequestMethod.POST)
    public JsonResult updatePersonStatus(@RequestBody ResTask resTask)throws Exception{
        if(resTask== null){
            return new JsonResult("未传入信息");
        }
        Task task = taskService.getById(resTask.getId());
        task.setPersonStatus(resTask.getPersonStatus());
        taskService.updateById(task);
        return new JsonResult(true,"",null);
    }
}
