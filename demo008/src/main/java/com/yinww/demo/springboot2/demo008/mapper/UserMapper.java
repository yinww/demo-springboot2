package com.yinww.demo.springboot2.demo008.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yinww.demo.springboot2.demo008.domain.User;

@Mapper
public interface UserMapper {

    void addUser(User user);

    List<User> getUsers();

}
