package com.yinww.demo.springboot2.demo012.controller;

import java.io.File;
import java.io.FileWriter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinww.demo.springboot2.demo012.domain.City;
import com.yinww.demo.springboot2.demo012.domain.Province;
import com.yinww.demo.springboot2.demo012.service.SpiderService;
import com.yinww.demo.springboot2.demo012.util.HttpUtil;

@RestController
public class SpiderController {
    
    @Autowired
    private SpiderService spiderService;
    
    @GetMapping({"/", ""})
    public Object spider() throws Exception {
        String url = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/";
        String charset = "gb2312";
        Document rootDoc = HttpUtil.get(url, charset);
        if(rootDoc == null) {
            return 0;
        }

        File provinceFile = new File("E:\\province.txt");
        File cityFile = new File("E:\\city.txt");
        FileWriter provinceWriter = new FileWriter(provinceFile);
        FileWriter cityWriter = new FileWriter(cityFile);
        StringBuilder sb = new StringBuilder();
        PinyinTool pinyinTool = new PinyinTool();
        
        Element firstElement = rootDoc.getElementsByClass("center_list_contlist").get(0);
        String yearHref = firstElement.select("a").get(0).attr("href"); // 最近一个年份的省份链接
        Document doc = HttpUtil.get(yearHref, charset);
        
        // 遍历所有的省
        Elements provinceElements = doc.getElementsByClass("provincetr");
        for (Element element : provinceElements) {
            Elements aEles = element.select("a");
            for (Element aEle : aEles) {
                String name = aEle.text();
                String provincesHref = aEle.attr("href");
                String code = provincesHref.substring(0, provincesHref.indexOf("."));
                int index = yearHref.lastIndexOf("/") + 1;
                provincesHref = yearHref.substring(0, index) + provincesHref;
                Province province = new Province(name, code);
                spiderService.saveProvince(province);
                writeProvince(province, provinceWriter, sb, pinyinTool);
                getCites(provincesHref, charset, province.getId(), cityWriter, sb, pinyinTool);
            }
        }
        closeFile(provinceWriter);
        closeFile(cityWriter);
        
        return "spider crawl end.";
    }
    
    private void closeFile(FileWriter provinceWriter) {
        try {
            provinceWriter.flush();
            provinceWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeProvince(Province province, FileWriter provinceWriter, StringBuilder sb, PinyinTool pinyinTool) {
        try {
            sb.delete(0, sb.length()); // 清空sb
            sb.append("insert into bs_province(id, country_region_id, name, english_name, code, area_code, pinyin, pinyin_short, order_no) values('");

            int index = province.getId();
            sb.append(index);
            sb.append("', '1', '");
            String name = province.getName();
            String pinYin = pinyinTool.toPinYin(name);
            String pinYinShort = pinyinTool.toPinYinShort(name);
            String englishName = pinyinTool.toPinYinSub(name);
            sb.append(name);
            sb.append("', '");
            sb.append(englishName);
            sb.append("', '");
            sb.append(province.getCode());
            sb.append("', '', '");
            sb.append(pinYin);
            sb.append("', '");
            sb.append(pinYinShort);
            sb.append("', ");
            sb.append(index);
            sb.append(");\n");
            provinceWriter.write(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getCites(String url, String charset, int provinceId, FileWriter cityWriter, StringBuilder sb, PinyinTool pinyinTool) throws Exception {
        Document rootDoc = HttpUtil.get(url, charset);
        if(rootDoc != null) {
            Elements cityElements = rootDoc.getElementsByClass("citytr");
            for (Element cityElement : cityElements) {
                Element aEle = cityElement.select("a").get(1); // 第二个是市的名字
                String name = aEle.text();
                String cityHref = aEle.attr("href");
                if("市辖区".equals(name)) {
                    int index = url.lastIndexOf("/") + 1;
                    cityHref = url.substring(0, index) + cityHref;
                    getZhiCites(cityHref, charset, provinceId, cityWriter, sb, pinyinTool);
                } else {
                    City city = new City();
                    city.setName(name);
                    city.setProvinceId(provinceId);
                    int start = cityHref.lastIndexOf("/") + 1;
                    String code = cityHref.substring(start, cityHref.indexOf("."));
                    city.setCode(code);
                    spiderService.saveCity(city);
                    writeCity(city, cityWriter, sb, pinyinTool);
                }
            }
        }
    }

    private void getZhiCites(String url, String charset, int provinceId, FileWriter cityWriter, StringBuilder sb, PinyinTool pinyinTool) throws Exception {
        Document rootDoc = HttpUtil.get(url, charset);
        if(rootDoc != null) {
            Elements cityElements = rootDoc.getElementsByClass("countytr");
            for (Element cityElement : cityElements) {
                Element aEle = cityElement.select("a").get(1); // 第二个是市的名字
                String name = aEle.text();
                String cityHref = aEle.attr("href");
                City city = new City();
                city.setName(name);
                city.setProvinceId(provinceId);
                int start = cityHref.lastIndexOf("/") + 1;
                String code = cityHref.substring(start, cityHref.indexOf("."));
                city.setCode(code);
                spiderService.saveCity(city);
                writeCity(city, cityWriter, sb, pinyinTool);
            }
        }
    }

    private void writeCity(City city, FileWriter cityWriter, StringBuilder sb, PinyinTool pinyinTool) {
        try {
            sb.delete(0, sb.length()); // 清空sb
            sb.append("insert into bs_city(id, province_id, name, english_name, code, area_code, pinyin, pinyin_short, order_no) values('");

            int index = city.getId();
            sb.append(index);
            sb.append("', '");
            sb.append(city.getProvinceId());
            sb.append("', '");
            String name = city.getName();
            String pinYin = pinyinTool.toPinYin(name);
            String pinYinShort = pinyinTool.toPinYinShort(name);
            String englishName = pinyinTool.toPinYinSub(name);
            sb.append(name);
            sb.append("', '");
            sb.append(englishName);
            sb.append("', '");
            sb.append(city.getCode());
            sb.append("', '', '");
            sb.append(pinYin);
            sb.append("', '");
            sb.append(pinYinShort);
            sb.append("', ");
            sb.append(index);
            sb.append(");\n");
            cityWriter.write(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
