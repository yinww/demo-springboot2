package com.yinww.demo.springboot2.demo003.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@RequestMapping("/")
	public String hello() {
		return "Hello SpringBoot";
	}

}
