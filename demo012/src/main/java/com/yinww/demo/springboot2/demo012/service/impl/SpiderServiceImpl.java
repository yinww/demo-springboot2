package com.yinww.demo.springboot2.demo012.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yinww.demo.springboot2.demo012.domain.City;
import com.yinww.demo.springboot2.demo012.domain.Province;
import com.yinww.demo.springboot2.demo012.mapper.CityMapper;
import com.yinww.demo.springboot2.demo012.mapper.ProvinceMapper;
import com.yinww.demo.springboot2.demo012.service.SpiderService;

@Controller
public class SpiderServiceImpl implements SpiderService {
    
    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private ProvinceMapper provinceMapper;

    @Override
    public int saveProvince(Province province) {
        return provinceMapper.saveProvince(province);
    }

    @Override
    public void saveCity(City city) {
        cityMapper.saveCity(city);
    }

}
