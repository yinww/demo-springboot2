package com.yinww.demo.springboot2.demo007.controller;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinww.demo.springboot2.demo007.domain.User;
import com.yinww.demo.springboot2.demo007.service.UserService;

@RestController
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private StringEncryptor stringEncryptor;
    
    @RequestMapping(value = "/encrypt/{value}")
    public String encrypt(@PathVariable String value) {
        String encrypt = stringEncryptor.encrypt(value);
        return encrypt;
    }
    
    @RequestMapping("add")
    public Object addUser() {
        User user = new User();
        user.setId(new Long(1));
        user.setName("张三丰");
        userService.addUser(user);
        return "ok";
    }

}
