package com.masaiqi.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.masaiqi.entity.Accessory;
import com.masaiqi.entity.CommitCheck;
import com.masaiqi.entity.Task;
import com.masaiqi.json.JsonResult;
import com.masaiqi.service.IAccessoryService;
import com.masaiqi.service.ICommitCheckService;
import com.masaiqi.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 附件表 前端控制器
 * </p>
 *
 * @author masaiqi
 * @since 2018-10-22
 */
@RestController
public class CommitCheckController {

    @Autowired
    ICommitCheckService commitCheckService;

    @Autowired
    ITaskService taskService;

    @Autowired
    IAccessoryService accessoryService;

    /**
     *
     */
    @RequestMapping(value = "${vue-url.check}",method = RequestMethod.POST)
    public JsonResult check(@RequestBody CommitCheck commitCheck)throws Exception{
        if(commitCheck == null){
            return new JsonResult("信息为空！");
        }
        commitCheck.setCheckTime(LocalDate.now());
        commitCheckService.updateById(commitCheck);
        Task task = taskService.getById(commitCheck.getTaskId());
        task.setUserStatus("未提交");
        taskService.updateById(task);
        if(commitCheck.getAccessory() != null){
            Accessory accessory = commitCheck.getAccessory();
            accessoryService.save(accessory);
            commitCheck.setAccessoryId(accessory.getId());
            commitCheckService.updateById(commitCheck);
        }
        return new JsonResult(true,null,null);
    }

    /**
     *
     */
    @RequestMapping(value = "${vue-url.getCheck}",method = RequestMethod.POST)
    public JsonResult getCheck(@RequestBody Task task)throws Exception{
        if(task == null){
            return new JsonResult("信息为空！");
        }
        List<CommitCheck> lists = commitCheckService.list(new QueryWrapper<CommitCheck>().eq("taskId",task.getId()).orderByDesc("time"));
        lists.forEach(obj ->{
            if(obj.getAccessoryId() != null){
                Accessory accessory = accessoryService.getById(obj.getAccessoryId());
                obj.setAccessoryUrl(accessory.getUrl());
            }
        });
        return new JsonResult(lists);
    }

}
