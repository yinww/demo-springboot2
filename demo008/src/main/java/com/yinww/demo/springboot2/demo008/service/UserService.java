package com.yinww.demo.springboot2.demo008.service;

import java.util.List;

import com.yinww.demo.springboot2.demo008.domain.User;

public interface UserService {
    
    void addUser(User user);
    
    List<User> getUsers();

}
