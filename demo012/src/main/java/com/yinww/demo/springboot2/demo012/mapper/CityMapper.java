package com.yinww.demo.springboot2.demo012.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yinww.demo.springboot2.demo012.domain.City;

@Mapper
public interface CityMapper {

    void saveCity(City city);
    
}
