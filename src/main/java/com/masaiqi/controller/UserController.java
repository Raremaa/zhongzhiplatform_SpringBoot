package com.masaiqi.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.masaiqi.entity.ProjectUser;
import com.masaiqi.entity.User;
import com.masaiqi.json.JsonResult;
import com.masaiqi.kit.ConstantKit;
import com.masaiqi.kit.Md5TokenGenerator;
import com.masaiqi.kit.StringKit;
import com.masaiqi.model.ReqModel.ReqUser;
import com.masaiqi.model.ResModel.ResLogin;
import com.masaiqi.model.ResModel.ResUser;
import com.masaiqi.service.IProjectUserService;
import com.masaiqi.service.IUserService;
import com.masaiqi.service.impl.ProjectUserServiceImpl;
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
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author masaiqi
 * @since 2018-10-22
 */
@RestController
@RequestMapping("/userAction")
@Api("用户控制层")
public class UserController {

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
    @RequestMapping(value = "login",method = RequestMethod.POST)
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
     * 获取用户信息
     * @param reqUser
     * @return token
     * @throws Exception
     */
    @RequestMapping(value = "getUserInfo",method = RequestMethod.GET)
    public JsonResult getUserInfo(ReqUser reqUser) throws Exception{
        JsonResult<ResUser> jsonResult = new JsonResult<>();
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
                        projectUsers.forEach(obj -> projectIds.add(obj.getProjectId()));
                        String access = "";
                        switch (user.getAuthority()){
                            case 0: access = "admin"; break;
                            case 1: access = "team_member"; break;
                            case 2: access = "project_leader"; break;
                            default: access = "unknown";break;
                        }
                        ResUser resUser = ResUser.builder()
                                .token(reqUser.getToken())
                                .user_id(user.getId())
                                .no(user.getNo())
                                .name(user.getName())
                                .phone(user.getPhone())
                                .email(user.getEmail())
                                .pictureUrl(user.getPictureUrl())
                                .introduction(user.getIntroduction())
                                .teamId(user.getTeamId())
                                .access(access)
                                .projectId(projectIds)
                                .build();
                        jsonResult.setDate(resUser);
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
}
