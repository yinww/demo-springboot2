package com.yinww.demo.springboot2.demo006.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yinww.demo.springboot2.demo006.domain.User;

@Mapper
public interface UserMapper {

    void addUser(User user);

}
