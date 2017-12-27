/* 
 * Copyright (C) 2006-2013 达飞普惠财富投资管理（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: CtrlFilter.java 
 *
 * Created: [2013-11-14 下午4:01:55] by jie 
 *
 * $Id$
 * 
 * $Revision$
 *
 * $Author$
 *
 * $Date$
 *
 * ============================================================ 
 * 
 * ProjectName: dfd 
 * 
 * Description: 
 * 
 * ==========================================================*/
package com.huizhongcf.partner.admin.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.huizhongcf.partner.admin.constant.Constants;
import com.huizhongcf.partner.service.ControlService;


/** 
 *
 * Description:
 *
 * @author lijie
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2013-11-14      lijie       1.0         1.0 Version 
 * </pre>
 */
public class ControlFilter implements Filter{

	@Autowired
	private ControlService controlService; 

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest _request = (HttpServletRequest)request;
		HttpServletResponse _response = (HttpServletResponse)response;
		HttpSession session = _request.getSession(false);
		if(null != session){
			Object o = session.getAttribute(Constants.CTRL_NAME);
			if(null == o){
				session.setAttribute(Constants.CTRL_NAME, controlService.findRoleByCtrl());
			}
		}
		chain.doFilter(_request, _response);
	}

	public void init(FilterConfig arg0) throws ServletException {}

	public void destroy() {}
}
