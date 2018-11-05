package com.masaiqi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.masaiqi.entity.ProjectUser;
import com.masaiqi.entity.Team;
import com.masaiqi.entity.User;
import com.masaiqi.json.JsonResult;
import com.masaiqi.kit.ConstantKit;
import com.masaiqi.kit.Md5TokenGenerator;
import com.masaiqi.kit.StringKit;
import com.masaiqi.model.ReqModel.ReqUser;
import com.masaiqi.model.ResModel.ResLogin;
import com.masaiqi.model.ResModel.ResUser;
import com.masaiqi.model.ResModel.ResUserBuilder;
import com.masaiqi.service.IProjectUserService;
import com.masaiqi.service.ITeamService;
import com.masaiqi.service.IUserService;
import com.masaiqi.service.impl.ProjectUserServiceImpl;
import com.masaiqi.service.impl.TeamServiceImpl;
import com.masaiqi.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author masaiqi
 * @since 2018-10-22
 */
@RestController
@Api("用户控制层")
public class UserController {

    @Autowired
    ITeamService teamService = new TeamServiceImpl();

    @Autowired
    IUserService userService = new UserServiceImpl();

    @Autowired
    IProjectUserService projectUserService = new ProjectUserServiceImpl();

    @Autowired
    Md5TokenGenerator tokenGenerator;

    //从yml配置文件中读取redis配置
    @Value("${my-jedis.host}")
    private String jedis_host;
    @Value("${my-jedis.port}")
    private int jedis_port;

    /**
     * 登录
     * @param reqUser
     * @return token信息
     * @throws Exception
     */
    @RequestMapping(value = "${vue-url.login}",method = RequestMethod.POST)
    @ApiOperation(value="登录", notes="根据手机号和密码获取token信息")
    public JsonResult login(@RequestBody ReqUser reqUser) throws Exception{
        JsonResult<ResLogin> jsonResult = new JsonResult<>();
        User user = userService.getOne(new QueryWrapper<User>().lambda()
                .eq(User::getPhone, reqUser.getPhone())
                .eq(User::getPassword, reqUser.getPassword())
        );
        //登录成功 计算token值并存储到redis当中
        if(user != null){
            String exceptionMsg = "";
            String token = tokenGenerator.generate(user.getName(),user.getPassword());
            try(Jedis jedis = new Jedis(jedis_host, jedis_port)){
                jedis.set(user.getName(), token);
                jedis.expire(user.getName(), ConstantKit.TOKEN_EXPIRE_TIME);
                jedis.set(token, user.getName());
                jedis.expire(token, ConstantKit.TOKEN_EXPIRE_TIME);
                Long currentTime = System.currentTimeMillis();
                jedis.set(token + user.getName(),currentTime.toString());
            } catch (Exception e){
                exceptionMsg = e.toString();
            }
            ResLogin resLogin = ResLogin.builder()
                    .token(token)
                    .build();
            if(!StringUtils.isEmpty(exceptionMsg)){
                jsonResult.setMsg(exceptionMsg);
            }else {
                jsonResult.setDate(resLogin);
            }
        }else {
            jsonResult.setMsg("用户名或密码错误！");
        }
        return jsonResult;
    }

    /**
     * 注册
     * @param reqUser
     * @return token信息
     * @throws Exception
     */
    @RequestMapping(value = "${vue-url.register}", method = RequestMethod.POST)
    public JsonResult register(@RequestBody ReqUser reqUser) throws Exception{
        if(reqUser == null){
            return new JsonResult("您还未传入用户信息");
        }
        userService.register(reqUser);
        String exceptionMsg = "";
        String token = tokenGenerator.generate(reqUser.getName(),reqUser.getPassword());
        try(Jedis jedis = new Jedis(jedis_host, jedis_port)){
            jedis.set(reqUser.getName(), token);
            jedis.expire(reqUser.getName(), ConstantKit.TOKEN_EXPIRE_TIME);
            jedis.set(token, reqUser.getName());
            jedis.expire(token, ConstantKit.TOKEN_EXPIRE_TIME);
            Long currentTime = System.currentTimeMillis();
            jedis.set(token + reqUser.getName(),currentTime.toString());
        } catch (Exception e){
            exceptionMsg = e.toString();
        }
        if(!StringKit.isEmptyExcludeTrim(exceptionMsg)){
            return new JsonResult(exceptionMsg);
        }
        ResLogin resLogin = ResLogin.builder()
                .token(token)
                .build();
        JsonResult<ResLogin> jsonResult = new JsonResult<>();
        jsonResult.setDate(resLogin);
        return jsonResult;
    }

    /**
     * 获取用户信息
     * @param reqUser
     * @return token
     * @throws Exception
     */
    @RequestMapping(value = "${vue-url.getUserInfo}",method = RequestMethod.GET)
    public JsonResult getUserInfo(ReqUser reqUser) throws Exception{
        JsonResult<ResUserBuilder> jsonResult = new JsonResult<>();
        try(Jedis jedis = new Jedis(jedis_host,jedis_port)){
                String userName =  jedis.get(reqUser.getToken());
                if (!StringKit.isEmptyExcludeTrim(userName)) {
                    User user = userService.getOne(new QueryWrapper<User>().lambda()
                            .eq(User::getName,userName)
                    );
                    //若用户存在 查询用户所在项目组id集合
                    if(user != null){
                        List<ProjectUser> projectUsers = projectUserService.list(new QueryWrapper<ProjectUser>().lambda()
                                .eq(ProjectUser::getUserId,user.getId())
                        );
                        List<Integer> projectIds = new ArrayList<>(0);
                        if (projectUsers != null) {
                            projectUsers.forEach(obj -> projectIds.add(obj.getProjectId()));
                        }
                        Team team = teamService.getOne(new QueryWrapper<Team>().eq("Leader",user.getId()));
                        String access = "";
                        switch (user.getAuthority()){
                            case 0: access = "admin"; break;
                            case 1: access = "team_leader"; break;
                            case 2: access = "project_leader"; break;
                            default: access = "team_member";break;
                        }
                        ResUserBuilder resUserBuilder = ResUserBuilder.builder()
                                .token(reqUser.getToken())
                                .user_id(user.getId())
                                .no(user.getNo())
                                .name(user.getName())
                                .phone(user.getPhone())
                                .email(user.getEmail())
                                .pictureUrl(user.getPictureUrl())
                                .introduction(user.getIntroduction())
                                .teamId(team == null ? null:team.getId())
                                .access(access)
                                .projectId(projectIds)
                                .build();
                        jsonResult.setDate(resUserBuilder);
                    }else {
                        jsonResult.setMsg("当前用户已注销,请重新登录！");
                    }
                }else {
                    jsonResult.setMsg("当前用户已注销,请重新登录！");
                }
        }catch (Exception e){
            jsonResult.setMsg(e.toString());
        }
        return jsonResult;
    }


    /**
     * 根据team表主键获取用户信息
     * @param map key:teamId value:team表主键
     * @return 用户信息
     * @throws Exception
     */
    @RequestMapping(value = "${vue-url.getByTeamId}",method = RequestMethod.POST)
    public JsonResult getByTeamId(@RequestBody Map map)throws Exception{
        if(map == null || 0 == map.size()){
            return new JsonResult<ResUser>("团队信息为空！");
        }
        if(map.get("teamId") == null){
            return new JsonResult<ResUser>("团队信息为空！");
        }
        List<ResUser> lists = userService.getUserByTeamId((Integer) map.get("teamId"));
        lists.forEach(obj -> {
            if(obj.getAuthority() == 1){
                obj.setAuthorityName("团队拥有者");
            }else {
                obj.setAuthorityName("团队成员");
            }
        });
        return new JsonResult(lists);
    }

    /**
     * 注册普通用户信息
     * @param reqUser
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "${vue-url.registerMember}",method = RequestMethod.POST)
    public JsonResult registerMember(@RequestBody ReqUser reqUser)throws Exception {
        if(reqUser == null){
            return new JsonResult("未传入用户信息！");
        }
        User user = new User();
        user.setNo(reqUser.getNo());
        user.setName(reqUser.getName());
        user.setAuthority(reqUser.getAuthority());
        user.setPhone(reqUser.getPhone());
        user.setEmail(reqUser.getEmail());
        user.setIntroduction(reqUser.getIntroduction());
        user.setTeamId(reqUser.getTeamId());
        userService.save(user);
        return new JsonResult(true,"成功！",null);
    }

    /**
     * 删除成员信息
     * @param map 格式为{"id" : "1,2,3,4"}
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "${vue-url.deleteMembers}",method = RequestMethod.POST)
    public JsonResult deleteMember(@RequestBody Map map)throws Exception {
        if(map == null || map.get("id") == null){
            return new JsonResult("未传入用户信息!");
        }
        String ids = (String) map.get("id");
        String[] idArray =  ids.split(",");
        Integer[] idInt = new Integer[idArray.length];
        //将string数组转化为int数组
        for(int i =0; i<idArray.length;i++){
            idInt[i] = Integer.valueOf(idArray[i]);
        }
        userService.remove(new QueryWrapper<User>().in("id",idInt));
        return new JsonResult(true,"成功",null);
    }

    /**
     * 修改用户信息
     * @param reqUser 可修改用户名 手机号 电子邮箱地址
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "${vue-url.updateMember}",method = RequestMethod.POST)
    public JsonResult updateMember(@RequestBody ReqUser reqUser)throws Exception{
        if (reqUser == null || reqUser.getId() == null){
            return new JsonResult("未传入用户信息！");
        }
        User user = userService.getById(reqUser.getId());
        if(user == null){
            return new JsonResult("待修改用户不存在！");
        }
        if(reqUser.getName() != null){
            user.setName(reqUser.getName());
        }
        if(reqUser.getPhone() != null){
            user.setPhone(reqUser.getPhone());
        }
        if(reqUser.getEmail() != null){
            user.setEmail(reqUser.getEmail());
        }
        userService.updateById(user);
        return new JsonResult(true,"成功",null);
    }
}
