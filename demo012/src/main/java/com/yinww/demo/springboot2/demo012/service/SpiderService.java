package com.yinww.demo.springboot2.demo012.service;

import com.yinww.demo.springboot2.demo012.domain.City;
import com.yinww.demo.springboot2.demo012.domain.Province;

public interface SpiderService {

    int saveProvince(Province province);
    
    void saveCity(City city);
    
}
