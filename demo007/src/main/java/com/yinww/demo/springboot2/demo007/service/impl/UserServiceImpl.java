package com.yinww.demo.springboot2.demo007.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinww.demo.springboot2.demo007.domain.User;
import com.yinww.demo.springboot2.demo007.mapper.UserMapper;
import com.yinww.demo.springboot2.demo007.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

}
