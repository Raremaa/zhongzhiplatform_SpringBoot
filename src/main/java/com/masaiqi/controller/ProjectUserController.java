package com.masaiqi.controller;


import com.alibaba.fastjson.JSON;
import com.masaiqi.entity.ProjectUser;
import com.masaiqi.entity.User;
import com.masaiqi.json.JsonResult;
import com.masaiqi.model.ReqModel.ReqUserProjejct;
import com.masaiqi.service.IProjectUserService;
import com.masaiqi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 项目表-用户表关联表 前端控制器
 * </p>
 *
 * @author masaiqi
 * @since 2018-10-22
 */
@RestController
public class ProjectUserController {

    @Autowired
    IProjectUserService projectUserService;

    @Autowired
    IUserService userService;

    /**
     * 分配团队成员（自动添加团队拥有者）
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "${vue-url.addUserProject}",method = RequestMethod.POST)
    public JsonResult addUserProject(@RequestBody Map map)throws Exception{
        List users = (List) map.get("userList");
        List projectUserList = (List) users.get(0);
        projectUserList.forEach(obj ->{
            ProjectUser projectUser = JSON.parseObject(JSON.toJSONString(obj),ProjectUser.class);
            projectUserService.save(projectUser);
        });
        return new JsonResult(true,"",null);
    }
}
