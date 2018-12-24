package com.masaiqi.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.masaiqi.entity.*;
import com.masaiqi.json.JsonResult;
import com.masaiqi.model.ReqModel.ReqUserProjejct;
import com.masaiqi.model.ResModel.ResTaskPlus;
import com.masaiqi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author masaiqi
 * @since 2018-10-22
 */
@RestController
public class ProjectController {

    @Autowired
    IProjectService projectService;

    @Autowired
    IUserService userService;

    @Autowired
    IProjectUserService projectUserService;

    @Autowired
    ITeamService teamService;

    @Autowired
    IAccessoryService accessoryService;

    /**
     *
     * 获取项目组信息
     */
    @RequestMapping(value = "${vue-url.getProject}",method = RequestMethod.POST)
    public JsonResult getProject(@RequestBody Project project)throws Exception {
        if(project == null){
            return new JsonResult("未传入信息");
        }
        List<Object> projects = projectService.listObjs(new QueryWrapper<Project>().eq("teamId",project.getTeamId()));
        projects.forEach(obj ->{
            if(obj instanceof Project){
                Integer id = ((Project) obj).getProjectLeader();
                User user = userService.getById(id);
                ((Project) obj).setProjectLeaderName(user.getName());
            }
        });
        return new JsonResult(projects);
    }

    /**
     *
     * 添加项目组信息
     */
    @RequestMapping(value = "${vue-url.addProject}",method = RequestMethod.POST)
    public JsonResult addProject(@RequestBody Project project)throws Exception {
        if(project == null){
            return new JsonResult("未传入信息");
        }
        projectService.save(project);
        ProjectUser projectUser = new ProjectUser();
        projectUser.setProjectId(project.getId());
        projectUser.setUserId(project.getProjectLeader());
        projectUserService.save(projectUser);
        User user =  userService.getById(project.getProjectLeader());
        user.setAuthority(2);
        userService.updateById(user);
        Team team = teamService.getById(project.getTeamId());
        ProjectUser temp = new ProjectUser();
        temp.setUserId(team.getLeader());
        temp.setProjectId(project.getId());
        projectUserService.save(temp);
        return new JsonResult(true,"",null);
    }

    /**
     * 删除项目信息
     * @param project
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "${vue-url.deleteProject}",method = RequestMethod.POST)
    public JsonResult deleteProject(@RequestBody Project project)throws Exception{
        if(project == null){
            return new JsonResult("未传入信息");
        }
        projectService.removeById(project.getId());
        projectUserService.remove(new QueryWrapper<ProjectUser>().lambda()
            .eq(ProjectUser::getProjectId,project.getId())
        );
        return new JsonResult(true,"",null);
    }

    /**
     * 修改项目信息
     * @param project
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "${vue-url.updateProject}",method = RequestMethod.POST)
    public JsonResult updateProject(@RequestBody Project project)throws Exception{
        if(project == null){
            return new JsonResult("未传入信息");
        }
        projectService.updateById(project);
        return new JsonResult(true,"",null);
    }

    /**
     *
     */
    @RequestMapping(value = "${vue-url.getProjectDetail}",method = RequestMethod.POST)
    public JsonResult getProjectDetail(@RequestBody Project project)throws Exception {
        if(project == null){
            return new JsonResult("未传入信息");
        }
        Project temp = projectService.getById(project.getId());
        List<Project> lists = new ArrayList<>(0);
        lists.add(temp);
        return new JsonResult(lists);
    }

    /**
     *
     */
    @RequestMapping(value = "${vue-url.updateProjectDetail}",method = RequestMethod.POST)
    public JsonResult updateProjectDetail(@RequestBody Project project)throws Exception {
        if(project == null){
            return new JsonResult("未传入信息");
        }
        projectService.updateById(project);
        return new JsonResult(true,null,null);
    }

}
