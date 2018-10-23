package com.masaiqi.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.masaiqi.entity.ProjectUser;
import com.masaiqi.entity.User;
import com.masaiqi.json.JsonResult;
import com.masaiqi.kit.ConstantKit;
import com.masaiqi.kit.Md5TokenGenerator;
import com.masaiqi.model.ReqModel.ReqUser;
import com.masaiqi.model.ResModel.ResUser;
import com.masaiqi.service.IProjectUserService;
import com.masaiqi.service.IUserService;
import com.masaiqi.service.impl.ProjectUserServiceImpl;
import com.masaiqi.service.impl.UserServiceImpl;
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
     * @return 用户登录信息
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public JsonResult login(ReqUser reqUser) throws Exception{
        JsonResult<ResUser> jsonResult = new JsonResult<>();
        User user = userService.getOne(new QueryWrapper<User>().lambda()
                .eq(User::getPhone, reqUser.getPhone())
                .eq(User::getPassword, reqUser.getPassword())
        );
        //登录成功 查询用户所属项目组id 计算token值并存储到redis当中
        if(user != null){
            List<ProjectUser> projectUsers = projectUserService.list(new QueryWrapper<ProjectUser>().lambda()
                    .eq(ProjectUser::getUserId,user.getId())
            );
            List<Integer> projectIds = new ArrayList<>(0);
            projectUsers.forEach(obj -> projectIds.add(obj.getProjectId()));
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
            ResUser resUser = ResUser.builder()
                    .user_id(user.getId())
                    .no(user.getNo())
                    .name(user.getName())
                    .phone(user.getPhone())
                    .email(user.getEmail())
                    .pictureUrl(user.getPictureUrl())
                    .introduction(user.getIntroduction())
                    .teamId(user.getTeamId())
                    .projectId(projectIds)
                    .token(token)
                    .build();
            if(!StringUtils.isEmpty(exceptionMsg)){
                jsonResult.setMsg(exceptionMsg);
            }else {
                jsonResult.setDate(resUser);
            }
        }else {
            jsonResult.setMsg("用户名或密码错误！");
        }
        return jsonResult;
    }
}
