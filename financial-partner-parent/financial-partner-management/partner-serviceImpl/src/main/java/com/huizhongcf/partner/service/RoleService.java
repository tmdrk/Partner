/* 
 * Copyright (C) 2006-2014 亿谱汇投资管理（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: RoleService.java 
 *
 * Created: [2014-11-17 下午04:28:52] by ydw 
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
 * ProjectName: sping-mvc 
 * 
 * Description: 
 * 
 * ==========================================================*/

package com.huizhongcf.partner.service;

import java.util.List;
import java.util.Map;

import com.huizhongcf.partner.model.Role;
import com.huizhongcf.util.PageModel;


/** 
 *
 * Description: 角色service
 *
 * @author ydw
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014-11-17    ydw       1.0        1.0 Version 
 * </pre>
 */

public interface RoleService {
	
	/**
	 * 
	 * Description: 后台角色管理列表
	 *
	 * @param 
	 * @return PageModel
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-3 下午04:02:20
	 */
	public PageModel findAllByPage(Map<String, Object> paramsCondition);
	
	/**
	 * 
	 * Description: 查询所有角色不分页
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-10 下午01:47:13
	 */
	public List<Map<String, Object>> findAllNoPage(Map<String, Object> paramsCondition);
	
	/**
	 * 
	 * Description: 添加角色
	 *
	 * @param 
	 * @return Integer
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-3 下午05:19:38
	 */
	public Integer addRole(Role role);
	
	/**
	 * 
	 * Description: 根据roleId查找角色
	 *
	 * @param 
	 * @return Role
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-3 下午05:33:41
	 */
	public Role getRoleByRoleId(Integer roleId);
	
	/**
	 * 
	 * Description: 修改角色
	 *
	 * @param 
	 * @return Integer
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-11-21 下午05:48:24
	 */
	public Integer editRole(Role role);
	
	/**
	 * 
	 * Description: 查询所有角色，并且标识出当前员工拥有的角色
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-10 下午03:03:16
	 */
	public List<Map<String, Object>> authorizationEmpList(Integer employeeId);
	
	/**
	 * 
	 * Description: 查询所有角色，并且标识出菜单拥有的角色
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-10 上午10:33:54
	 */
	public List<Map<String, Object>> authorizationMenuList(Integer menuId);
	
	/**
	 * 
	 * Description: 查询所有角色，并且标识出控件按钮拥有的角色
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-9 下午03:40:01
	 */
	public List<Map<String, Object>> authorizationControlList(Integer controlId);

}
