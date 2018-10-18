package com.masaiqi.controller;

import com.masaiqi.entity.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testboot")
public class TestController {
    @RequestMapping("getUser")
    public Test getUser() {
        Test user = new Test();
        user.setName("test");
        return user;
    }
}
