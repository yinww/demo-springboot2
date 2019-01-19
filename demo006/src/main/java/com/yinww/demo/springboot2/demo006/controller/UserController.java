package com.yinww.demo.springboot2.demo006.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinww.demo.springboot2.demo006.domain.User;
import com.yinww.demo.springboot2.demo006.service.UserService;

@RestController
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping("add")
    public Object addUser() {
        User user = new User();
        user.setId(new Long(1));
        user.setName("张三丰");
        userService.addUser(user);
        return "ok";
    }

}
