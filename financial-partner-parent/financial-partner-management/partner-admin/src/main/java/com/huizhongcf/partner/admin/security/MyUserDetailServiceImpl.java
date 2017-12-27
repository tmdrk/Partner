/* 
 * Copyright (C) 2006-2013 普信恒业科技发展（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: MyUserDetailServiceImpl.java 
 *
 * Created: [2013-11-4 下午10:18:52] by lijie
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.huizhongcf.partner.dao.EmployeeMapper;
import com.huizhongcf.partner.dao.MenuMapper;
import com.huizhongcf.partner.dao.RoleMapper;
import com.huizhongcf.partner.model.Employee;
import com.huizhongcf.partner.model.Menu;
import com.huizhongcf.partner.model.Role;

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
 * 2013-11-4      lijie       1.0         1.0 Version 
 * </pre>
 */

public class MyUserDetailServiceImpl implements UserDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(MyUserDetailServiceImpl.class);
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Autowired
	private RoleMapper roleMapper; 
	
	@Autowired
	private MenuMapper menuMapper;
	
	//登录验证
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee employee = null;
		employee = employeeMapper.findByUsername(username);
		if(employee == null){  
			throw new UsernameNotFoundException("用户名"+username+"不存在");  
	    }  
		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(employee);
		boolean enables = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		
		//封装成spring security的user
		User userdetail = new User(username, employee.getPassword(), enables, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths);
		return userdetail;
	}
	
	//取得用户的权限
	@SuppressWarnings("deprecation")
	private Set<GrantedAuthority> obtionGrantedAuthorities(Employee employee) {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		List<Menu> menus = new ArrayList<Menu>();
		try {
			List<Role> roles = roleMapper.findRoleByUserId(employee.getEmployeeId());
			StringBuffer roleCodes = new StringBuffer();
			StringBuffer menuUrls = new StringBuffer();
			List<Integer> menuIdList = new ArrayList<Integer>();
			for(Role role : roles) {
				roleCodes.append(role.getRoleCode());
				roleCodes.append(",");
				menus.addAll(menuMapper.findMenuByRoleId(role.getRoleId()));
				authSet.add(new SimpleGrantedAuthority(role.getRoleCode()));
			}
			for(Menu menu : menus) {
				authSet.add(new SimpleGrantedAuthority(menu.getNameEn()));
				menuIdList.add(menu.getMenuId());
				menuUrls.append(menu.getMenuUrl());
				menuUrls.append(",");
			}
			logger.info("用户名：" + employee.getEmail());
			logger.info("用户角色：" + roleCodes.toString());
			logger.info("用户权限：" + menuUrls.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return authSet;
	}
}

