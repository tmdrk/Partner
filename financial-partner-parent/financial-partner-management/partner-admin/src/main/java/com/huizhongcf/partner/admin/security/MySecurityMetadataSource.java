/* 
 * Copyright (C) 2006-2013 普信恒业科技发展（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: MySecurityMetadataSource.java 
 *
 * Created: [2013-11-4 下午10:06:06] by lijie
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.huizhongcf.partner.dao.MenuMapper;
import com.huizhongcf.partner.model.Menu;

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

//1 加载资源与权限的对应关系
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	
	@Autowired
	private MenuMapper menuMapper;
	
	public MySecurityMetadataSource() {
		super();
	}

	//由spring调用
	public MySecurityMetadataSource(MenuMapper menuMapper) {
		loadResourceDefine();
	}
	
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}
	//加载所有资源与权限的关系
	private void loadResourceDefine() {
		if(resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			List<Menu> menus = menuMapper.findAllRetEntityNoPage(null);
			for (Menu menu : menus) {
				Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
				//以权限名封装为Spring的security Object
				ConfigAttribute configAttribute = new SecurityConfig(menu.getNameEn());
				configAttributes.add(configAttribute);
				resourceMap.put(menu.getMenuUrl(), configAttributes);
			}
		}
	}
	//返回所请求资源所需要的权限
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		if(resourceMap == null) {
			loadResourceDefine();
		}
		return resourceMap.get(requestUrl);
	}
}

