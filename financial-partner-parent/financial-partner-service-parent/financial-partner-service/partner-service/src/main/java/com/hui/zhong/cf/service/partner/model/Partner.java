package com.hui.zhong.cf.service.partner.model;

import java.io.Serializable;
import java.util.Date;

public class Partner implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long userId;
	private String username;
	private String realName;//真实姓名
	private String cardCode;//身份证
	private String channelCode; //渠道码,301：代表合伙人；601：代表客户
	private String recommendCode;//邀请码
	private Date registTime;//注册时间
	private String loginPwd;//密码
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getRecommendCode() {
		return recommendCode;
	}
	public void setRecommendCode(String recommendCode) {
		this.recommendCode = recommendCode;
	}
	public Date getRegistTime() {
		return registTime;
	}
	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getCardCode() {
		return cardCode;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	


}
