package com.yinww.demo.springboot2.demo006.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yinww.demo.springboot2.demo006.domain.User;
import com.yinww.demo.springboot2.demo006.service.UserService;

@RestController
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @ResponseBody
    @RequestMapping("add")
    public Object addUser() {
        for (int i = 0; i < 25; i++) {
            User user = new User();
            user.setId(new Long(i));
            user.setName("name_" + i);
            userService.addUser(user);
        }
        return "ok";
    }
    
    @ResponseBody
    @RequestMapping("get")
    public Object getUsers() {
        List<User> users = userService.getUsers();
        return users;
    }

}
