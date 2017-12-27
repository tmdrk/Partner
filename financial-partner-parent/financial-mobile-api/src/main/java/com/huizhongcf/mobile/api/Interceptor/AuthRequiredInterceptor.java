package com.huizhongcf.mobile.api.Interceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.huizhongcf.mobile.api.framework.BaseController;
import com.huizhongcf.mobile.api.framework.ResultBean;
import com.huizhongcf.mobile.api.utils.JedisClientUtil;

 
public class AuthRequiredInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private JedisClientUtil jedisClientUtil;
	private Logger logger = Logger.getLogger(AuthRequiredInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		   logger.info("线程id"+Thread.currentThread().getId()  +"请求地址:"+request.getRequestURI()+" method: "+request.getMethod()+"请求参数："+JSON.toJSONString(request.getParameterMap()));
			// 登录验证
			String sessionMobileApiKey = BaseController.SESSION_MOBILE_API_KEY;
			String token = request.getParameter("token");
			String key = sessionMobileApiKey + token;
			String sessionJSON = jedisClientUtil.get(key);
			boolean isLogin = false;
			if (StringUtils.isNotBlank(sessionJSON)) {
				// 重置 session 时间
				jedisClientUtil.set(key, sessionJSON, BaseController.SESSION_TOKEN_EXPIRE);
				isLogin = true;
			}
			if(!(handler instanceof HandlerMethod)) {
				response.sendRedirect("http://10.10.16.200:8080");
				return false;
			}
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			AccessRequired annotation = method.getAnnotation(AccessRequired.class);
			if (annotation != null) {
				// 验证登录
				if (annotation.login()) {
					if (!isLogin) {
						request.setCharacterEncoding("UTF-8");
						response.setHeader("Content-Type", "text/html;charset=UTF-8");
						response.setCharacterEncoding("UTF-8");
						
						ResultBean resultBean =  new ResultBean();
						resultBean.setErrorCode("9999");
						resultBean.setErrorMsg("登录超时，请重新登录");
						response.getWriter().print(JSON.toJSON(resultBean));
						return false;
					} 
				}
			}
			
		return true;
	}

}
