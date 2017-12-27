/* 
 * Copyright (C) 2006-2013 达飞普惠财富投资管理（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: MyUsernamePasswordAuthenticationFilter.java 
 *
 * Created: [2013-11-5 下午3:52:48] by jie 
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

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.huizhongcf.partner.admin.constant.Constants;
import com.huizhongcf.partner.dao.EmployeeMapper;
import com.huizhongcf.partner.dao.EmployeeRoleRelationMapper;
import com.huizhongcf.partner.dao.WorkGroupMapper;
import com.huizhongcf.partner.model.Employee;
import com.huizhongcf.util.MD5Util;
import com.huizhongcf.util.PropertyUtil;
import com.huizhongcf.util.StringUtil;

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
 * 2013-11-5      lijie       1.0         1.0 Version 
 * </pre>
 */

public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	private static final Logger logger = LoggerFactory.getLogger(MyUsernamePasswordAuthenticationFilter.class);
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Autowired
	private WorkGroupMapper workGroupMapper;
	
	@Autowired
	private EmployeeRoleRelationMapper employeeRoleRelationMapper;
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}
		
		//检查验证码
		checkValidateCode(request);
		
		String username = obtainUsername(request);
		String password = MD5Util.md5(obtainPassword(request)+PropertyUtil.getInfo("md5Key"));
		
		//验证用户账号与密码是否对应
		Employee employee = employeeMapper.findByUsername(username.trim());
		
		if(employee != null && "0".equals(employee.getActivatedState())){
			throw new AuthenticationServiceException("该用户已被停用"); 
		}
		
		if(employee == null || !employee.getPassword().equals(password)) {
			throw new AuthenticationServiceException("用户名或密码错误"); 
		}
		//查找当前登录人拥有的工作部门id根据员工id
		List<Integer> workDeptIds = workGroupMapper.findWorkDeptIdByEmpId(employee.getEmployeeId());
		
		//查找当前登录人拥有的角色
		List<String> empRoles = employeeRoleRelationMapper.findEmpRoleByEmpId(employee.getEmployeeId());
		
		//UsernamePasswordAuthenticationToken实现 Authentication
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
		
		// Place the last username attempted into HttpSession for views
        HttpSession session = request.getSession(false); 
		
		// 允许子类设置详细属性
        setDetails(request, authRequest);
        session.setAttribute(Constants.WORK_DEPT_IDS, workDeptIds);
        session.setAttribute(Constants.SYSTEM_USER, employee);
        session.setAttribute(Constants.SYSTEM_USER_ROLES, empRoles);
        
        // 运行UserDetailsService的loadUserByUsername 再次封装Authentication
		return this.getAuthenticationManager().authenticate(authRequest);
	}
	
	protected void checkValidateCode(HttpServletRequest request) { 
		HttpSession session = request.getSession();
	    String sessionValidateCode = obtainSessionValidateCode(session); 
	    //让上一次的验证码失效
	    session.setAttribute(Constants.CHECK_CODE, null);
	    String validateCodeParameter = obtainValidateCodeParameter(request);  
	    if (StringUtil.isEmpty(validateCodeParameter)) {
	        throw new AuthenticationServiceException("请输入验证码");  
	    }  
	    if (!sessionValidateCode.equalsIgnoreCase(validateCodeParameter)) {
	        throw new AuthenticationServiceException("验证码错误");  
	    }  
	}
	
	private String obtainValidateCodeParameter(HttpServletRequest request) {
		Object obj = request.getParameter(Constants.CHECK_CODE);
		return null == obj ? "" : obj.toString();
	}

	protected String obtainSessionValidateCode(HttpSession session) {
		Object obj = session.getAttribute(Constants.CHECK_CODE);
		return null == obj ? "" : obj.toString();
	}

	@Override
	protected String obtainUsername(HttpServletRequest request) {
		Object obj = request.getParameter(USERNAME);
		return null == obj ? "" : obj.toString();
	}

	@Override
	protected String obtainPassword(HttpServletRequest request) {
		Object obj = request.getParameter(PASSWORD);
		return null == obj ? "" : obj.toString();
	}
}

