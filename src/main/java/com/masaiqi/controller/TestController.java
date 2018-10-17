package com.masaiqi.controller;

import com.masaiqi.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testboot")
public class TestController {
    @RequestMapping("getUser")
    public User getUser() {
        User user = new User();
        user.setName("test");
        return user;
    }
}
