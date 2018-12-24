package com.masaiqi.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.masaiqi.entity.Team;
import com.masaiqi.entity.User;
import com.masaiqi.json.JsonResult;
import com.masaiqi.service.ITeamService;
import com.masaiqi.service.IUserService;
import com.masaiqi.service.impl.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 团队表 前端控制器
 * </p>
 *
 * @author masaiqi
 * @since 2018-10-22
 */
@RestController
public class TeamController {

    @Autowired
    ITeamService teamService = new TeamServiceImpl();

    @Autowired
    IUserService userService;

    /**
     * 根据id查询团队信息
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "${vue-url.getTeamDate}",method = RequestMethod.POST)
    public JsonResult getById(@RequestBody Map map)throws Exception{
        if(map == null){
            return new JsonResult("未传入team信息！");
        }
        if(map.get("teamId") == null){
            return new JsonResult("未传入team信息！");
        }
        JsonResult<Team> jsonResult = new JsonResult(true,"",null);
        jsonResult.setDate(teamService.getById((Serializable) map.get("teamId")));
        return jsonResult;
    }

    /**
     * 修改团队信息
     * @param team 修改完成的团队信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "${vue-url.updateTeamInf}",method = RequestMethod.POST)
    public JsonResult update(@RequestBody Team team)throws Exception{
        teamService.updateById(team);
        return new JsonResult(true,"",null);
    }

    /**
     * 解散团队
     * @param map 团队编号
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "${vue-url.dissmissTeamInf}",method = RequestMethod.POST)
    public JsonResult dismiss(@RequestBody Map map)throws Exception{
        if(map == null){
            return new JsonResult("未传入数据!");
        }
        if(map.get("id") == null){
            return new JsonResult("未传入数据!");
        }
        teamService.dismiss((Integer) map.get("id"));
        return new JsonResult(true,"成功",null);
    }

    /**
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "${vue-url.getTeam}",method = RequestMethod.GET)
    public JsonResult getTeam()throws Exception{
        List<Team> lists = teamService.list(new QueryWrapper<Team>().orderByAsc("Leader"));
        lists.forEach(obj ->{
            User user = userService.getById(obj.getLeader());
            obj.setTeamLeaderName(user.getName());
        });
        return new JsonResult(lists);
    }

    /**
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "${vue-url.deleteTeam}",method = RequestMethod.POST)
    public JsonResult deleteTeam(@RequestBody Team team)throws Exception{
        if(team == null){
            return new JsonResult("null");
        }
        userService.remove(new QueryWrapper<User>().eq("teamId",team.getId()));
        teamService.removeById(team.getId());
        return new JsonResult(true,null,null);
    }
}
