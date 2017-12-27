/* 
 * Copyright (C) 2006-2013 普信恒业科技发展（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: myAccessDecisionManager.java 
 *
 * Created: [2013-11-4 下午10:13:50] by lijie
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

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

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

public class MyAccessDecisionManager implements AccessDecisionManager {
	
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
		if(configAttributes == null) {
			return;
		}
		//所请求的资源拥有的权限(一个资源对多个权限)
		Iterator<ConfigAttribute> iterator = configAttributes.iterator();
		while(iterator.hasNext()) {
			ConfigAttribute configAttribute = iterator.next();
			//访问所请求资源所需要的权限
			String needPermission = configAttribute.getAttribute();
			//用户所拥有的权限authentication
			for(GrantedAuthority ga : authentication.getAuthorities()) {
				if(needPermission.equals(ga.getAuthority())) {
					return;
				}
			}
		}
		//没有权限让我们去捕捉
		throw new AccessDeniedException(" 没有权限访问！");
	}

	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}
	
}

