package com.yinww.demo.springboot2.demo006.service;

import java.util.List;

import com.yinww.demo.springboot2.demo006.domain.User;

public interface UserService {
    
    void addUser(User user);
    
    List<User> getUsers();

}
