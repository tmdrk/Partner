package com.huizhongcf.mobile.api.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hui.zhong.cf.mobile.model.LoginUser;
import com.hui.zhong.cf.mobile.service.IndexService;
import com.huizhongcf.mobile.api.Interceptor.AccessRequired;
import com.huizhongcf.mobile.api.framework.BaseController;

@Controller
@RequestMapping("/index")
public class IndexController extends BaseController{
	@Autowired
	private IndexService indexService;
	
	/**
	 * 首页用户数据统计
	 * @return
	 * @author zhoujie
	 * @date 2017年12月14日 下午5:20:26
	 */
	@RequestMapping("/stat")
	@ResponseBody
	@AccessRequired(login=true)
	public Object queryData(){
		try {
			Map<String, Object> requestParam = getRequestParam();
			LoginUser loginUser = getSession();
			Integer userId = loginUser.getUserId();// 当前登录用户id
			String userType = loginUser.getUserType();// 当前登录用户id
			requestParam.put("userId", userId);
			requestParam.put("userType", userType);
			Map<String,Object> body = indexService.queryData(requestParam);
			return renderSuccess(body);
		} catch (Exception e) {
			return renderError("500", "首页用户数据统计异常");
		}
	}
}
