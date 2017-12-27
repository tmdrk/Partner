package com.huizhongcf.mobile.api.framework;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.alibaba.fastjson.JSON;

public class ExceptionResolver extends SimpleMappingExceptionResolver {

	private static final Log loger = LogFactory.getLog(ExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		loger.info("接口异常");
		ex.printStackTrace();
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		ResultBean resultBean = new ResultBean();
		resultBean.setErrorCode("500");
		resultBean.setErrorMsg("服务器异常");
		try {
			String jsonString = JSON.toJSONString(resultBean);
			response.getWriter().print(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
