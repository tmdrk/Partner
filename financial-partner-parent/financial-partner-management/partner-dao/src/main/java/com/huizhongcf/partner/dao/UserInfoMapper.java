package com.huizhongcf.partner.dao;

import java.util.Map;

import com.huizhongcf.partner.model.UserInfo;

public interface UserInfoMapper extends BaseMapper<UserInfo>{

	public Map<String,Object> getUserInfo(Map<String,Object> param);
}