package com.masaiqi.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.masaiqi.entity.Notice;
import com.masaiqi.entity.Project;
import com.masaiqi.json.JsonResult;
import com.masaiqi.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        return new JsonResult(true,null,null);
    }
}
