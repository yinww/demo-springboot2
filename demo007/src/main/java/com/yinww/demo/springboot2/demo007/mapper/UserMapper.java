package com.yinww.demo.springboot2.demo007.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yinww.demo.springboot2.demo007.domain.User;

@Mapper
public interface UserMapper {

    void addUser(User user);

}
