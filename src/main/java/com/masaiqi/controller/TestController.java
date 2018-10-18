package com.masaiqi.controller;

import com.masaiqi.dao.UserMapper;
import com.masaiqi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testboot")
public class TestController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping("getUser")
    public User getUser() {
        User user = userMapper.selectByPrimaryKey(1);
        return user;
    }
}
