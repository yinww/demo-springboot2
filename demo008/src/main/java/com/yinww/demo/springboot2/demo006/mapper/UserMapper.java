package com.yinww.demo.springboot2.demo006.mapper;

import java.util.List;

import com.yinww.demo.springboot2.demo006.domain.User;

public interface UserMapper {

    void addUser(User user);

    List<User> getUsers();

}
