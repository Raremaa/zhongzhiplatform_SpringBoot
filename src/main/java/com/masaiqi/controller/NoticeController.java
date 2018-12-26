package com.masaiqi.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.masaiqi.entity.Notice;
import com.masaiqi.entity.Project;
import com.masaiqi.entity.ProjectUser;
import com.masaiqi.entity.User;
import com.masaiqi.json.JsonResult;
import com.masaiqi.service.INoticeService;
import com.masaiqi.service.IProjectUserService;
import com.masaiqi.service.IUserService;
import com.masaiqi.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
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
public class NoticeController {

    @Autowired
    INoticeService noticeService;

    @Autowired
    IUserService userService;

    @Autowired
    IProjectUserService projectUserService;

    /**
     *
     */
    @RequestMapping(value = "${vue-url.getNotice}",method = RequestMethod.POST)
    public JsonResult getNotice(@RequestBody Project project)throws Exception{
        if(project == null){
            return new JsonResult("信息为空！");
        }
        Notice notice = (Notice) noticeService.getObj(new QueryWrapper<Notice>().eq("projectId",project.getId()));
        List<Notice> lists = new ArrayList<>(0);
        if(notice != null){
            lists.add(notice);
        }
        return new JsonResult(lists);
    }

    /**
     *
     */
    @RequestMapping(value = "${vue-url.updateNotice}",method = RequestMethod.POST)
    public JsonResult updateNotice(@RequestBody Notice notice)throws Exception{
        if(notice == null){
            return new JsonResult("信息为空！");
        }
        if(notice.getId() == null){
            noticeService.save(notice);
        }else {
            noticeService.updateById(notice);
        }
        WebSocketServer webSocketServer = new WebSocketServer();
        List<ProjectUser> lists = projectUserService.list(new QueryWrapper<ProjectUser>().eq("projectId",notice.getProjectId()));
        lists.forEach(obj ->{
            User temp = userService.getById(obj.getUserId());
            if(temp.getAuthority() == 3){
                try {
                    webSocketServer.sendtoUser("项目组发布了新的公告："+notice.getInfo(),temp.getId().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return new JsonResult(true,null,null);
    }
}
