package com.hui.zhong.cf.job.model;

import java.io.Serializable;

public class Job implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long userId;
	private String userName;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
