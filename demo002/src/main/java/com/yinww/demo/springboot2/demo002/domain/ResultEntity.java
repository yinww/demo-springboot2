package com.yinww.demo.springboot2.demo002.domain;

public class ResultEntity {

	private int status;
	private Object data;
	private String msg;
	
	public ResultEntity() {
	}
	
	public ResultEntity(int status) {
		this.status = status;
	}
	
	public ResultEntity(int status, Object data) {
		this.status = status;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
