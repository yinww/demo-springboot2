package com.yinww.demo.springboot2.demo012.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HttpUtil {

    public static Document get(String url, String charset) throws IOException {
        String userAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36";
        URL url2 = new URL(url);
        HttpURLConnection connection = (HttpURLConnection)url2.openConnection();
        connection.setRequestMethod("GET");
        //是否允许缓存，默认true。
        connection.setUseCaches(Boolean.FALSE);
        //设置请求头信息
        connection.addRequestProperty("Connection", "close");
        connection.addRequestProperty("user-agent", userAgent);
        //设置连接主机超时（单位：毫秒）
        connection.setConnectTimeout(80000);
         //设置从主机读取数据超时（单位：毫秒）  
        connection.setReadTimeout(80000);
        //开始请求
        try {
            Document doc = Jsoup.parse(connection.getInputStream(), charset, url);
            return doc;
        } catch (Exception e) {
            System.out.println("parse error: " + url);
            e.printStackTrace();
        }
        return null;
    }
    
}
