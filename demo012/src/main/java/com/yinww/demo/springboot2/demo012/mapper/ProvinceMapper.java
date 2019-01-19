package com.yinww.demo.springboot2.demo012.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yinww.demo.springboot2.demo012.domain.Province;

@Mapper
public interface ProvinceMapper {
    
    int saveProvince(Province province);

}
