package com.yinww.demo.springboot2.demo002.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.yinww.demo.springboot2.demo002.validator.IDConstraint;

public class User {

	@NotBlank(message = "{error.username}")
	private String username;

	@NotBlank(message = "{error.password}")
	private String password;

	@Email(message = "{error.email}")
	private String email;
	
	@IDConstraint(required = false, message = "{error.idcard}")
	private String idCard;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
}
