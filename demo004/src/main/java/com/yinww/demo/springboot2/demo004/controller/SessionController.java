package com.yinww.demo.springboot2.demo004.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

    @RequestMapping(value = "/setsession")
    public Object setSession(@RequestParam(required=false) String value, HttpSession session) {
        session.setAttribute("value", value);
        return session.getId();
    }
    
    @RequestMapping(value = "/getsession")
    public Object getSession(HttpSession session) {
        Object value = session.getAttribute("value");
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", session.getId());
        map.put("value", value);
        return map;
    }

}
