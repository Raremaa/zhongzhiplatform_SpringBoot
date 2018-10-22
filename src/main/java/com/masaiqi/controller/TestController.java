package com.masaiqi.controller;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.masaiqi.entity.User;
import com.masaiqi.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testboot")
@Api("测试2类")
public class TestController {
    @Autowired
    UserMapper userMapper;

    @ApiOperation(value="更新信息", notes="根据url的id来指定更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long",paramType = "path"),
            @ApiImplicitParam(name = "user", value = "用户实体user", required = true, dataType = "User")
    })
    @RequestMapping(value = "getUser",method = RequestMethod.POST)
    public User getUser() {
//        jdk1.8新特性 lambda表达式测试
        LambdaQueryWrapper<User> lam = new QueryWrapper<User>().lambda();
        lam.eq(User::getName,"uzi");
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(User::getName,"uzi");
        //普通传值方式测试 condition测试
        User user = userMapper.selectOne(new QueryWrapper<User>().eq(false,"name","uzi"));
        return user;
    }
}
