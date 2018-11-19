package com.yinww.demo.springboot2.demo002.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinww.demo.springboot2.demo002.domain.ResultEntity;
import com.yinww.demo.springboot2.demo002.domain.User;

@Controller
public class TestController {

	@RequestMapping(value={"/", "index.html"})
	public String index() {
		return "index";
	}

	@RequestMapping(value="save")
	@ResponseBody
	public Object save(@Validated User user) {
		return new ResultEntity(HttpStatus.OK.value(), user);
	}
}
