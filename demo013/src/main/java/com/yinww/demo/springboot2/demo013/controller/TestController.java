package com.yinww.demo.springboot2.demo013.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.yinww.demo.springboot2.demo013.domain.CountryRegion;
import com.yinww.demo.springboot2.demo013.handler.PinyinTool;
import com.yinww.demo.springboot2.demo013.handler.RegionHandler;

@RestController
public class TestController {
    
    @GetMapping({"/", ""})
    public Object test() throws Exception {
        List<CountryRegion> zhCountryRegions = new ArrayList<>();
        List<CountryRegion> enCountryRegions = new ArrayList<>();
        String zhFile = "C:/Users/yinww/git/demo-springboot2/demo013/src/main/resources/LocList_zh.xml";
        parseXml(zhCountryRegions, zhFile);
        String enFile = "C:/Users/yinww/git/demo-springboot2/demo013/src/main/resources/LocList_en.xml";
        parseXml(enCountryRegions, enFile);
        
        Map<String, String> englishMap = getEnglishMap(enCountryRegions);
        for (CountryRegion countryRegion : zhCountryRegions) {
            String code = countryRegion.getCode();
            String englishName = englishMap.get(code);
            countryRegion.setEnglishName(englishName);
        }
        saveCountryRegion(zhCountryRegions);
        return "ok";
    }
    
    private Map<String, String> getEnglishMap(List<CountryRegion> enCountryRegions) {
        Map<String, String> result = new HashMap<>();
        for (CountryRegion countryRegion : enCountryRegions) {
            result.put(countryRegion.getCode(), countryRegion.getName());
        }
        return result;
    }

    private void parseXml(List<CountryRegion> countryRegions, String file) {
        try {
            XMLReader parser = XMLReaderFactory.createXMLReader();
            parser.setContentHandler(new RegionHandler(countryRegions));
            InputStream in = new FileInputStream(file);
            InputSource inputSource = new InputSource(in);
            inputSource.setEncoding("UTF-8");
            parser.parse(inputSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void saveCountryRegion(List<CountryRegion> countryRegions) {
        try {
            File f=new File("E:\\CountryRegion.txt");
            FileWriter fw = new FileWriter(f, true);
            StringBuilder sb = new StringBuilder();
            PinyinTool pinyinTool = new PinyinTool();
            for (int i = 0; i < countryRegions.size(); i++) {
                sb.delete(0, sb.length()); // 清空sb
                sb.append("insert into bs_country_region(id, name, english_name, code, pinyin, pinyin_short, order_no, continent_id) values('");

                CountryRegion countryRegion = countryRegions.get(i);
                int index = i + 4; // 中国香港、澳门、台湾不在里面
                sb.append(index);
                sb.append("', '");
                String name = countryRegion.getName();
                String pinYin = pinyinTool.toPinYin(name);
                String pinYinShort = pinyinTool.toPinYinShort(name);
                sb.append(name);
                sb.append("', '");
                String englishName = countryRegion.getEnglishName();
                sb.append(englishName.replace("'", "\\'"));
                sb.append("', '");
                sb.append(countryRegion.getCode());
                sb.append("', '");
                sb.append(pinYin);
                sb.append("', '");
                sb.append(pinYinShort);
                sb.append("', ");
                sb.append(index);
                sb.append(", '');\n");
                fw.write(sb.toString());
            }
            fw.flush();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
