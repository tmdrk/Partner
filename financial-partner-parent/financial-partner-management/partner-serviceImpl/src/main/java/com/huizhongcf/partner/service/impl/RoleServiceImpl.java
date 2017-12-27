/* 
 * Copyright (C) 2006-2014 亿谱汇投资管理（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: RoleServiceImpl.java 
 *
 * Created: [2014-11-17 下午04:29:03] by ydw 
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

package com.huizhongcf.partner.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huizhongcf.partner.dao.RoleMapper;
import com.huizhongcf.partner.model.Role;
import com.huizhongcf.partner.service.RoleService;
import com.huizhongcf.util.PageModel;

/** 
 *
 * Description: 
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
@Service("roleService")
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public PageModel findAllByPage(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paramsCondition.get("pageNo"));
		pageModel.setPageSize((Integer) paramsCondition.get("pageSize"));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> data = roleMapper.findAllRetMapByPage(paramsCondition);
		Long totalRecords = roleMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}
	
	@Override
	public List<Map<String, Object>> findAllNoPage(Map<String, Object> paramsCondition) {
		return roleMapper.findAllRetMapNoPage(paramsCondition);
	}

	@Override
	public Integer addRole(Role role) {
		return roleMapper.insertSelective(role);
	}

	@Override
	public Role getRoleByRoleId(Integer roleId) {
		return roleMapper.selectByPrimaryKey(roleId);
	}

	@Override
	public Integer editRole(Role role) {
		return roleMapper.updateByPrimaryKeySelective(role);
	}
	
	@Override
	public List<Map<String, Object>> authorizationEmpList(Integer employeeId) {
		return roleMapper.authorizationEmpList(employeeId);
	}

	@Override
	public List<Map<String, Object>> authorizationControlList(Integer controlId) {
		return roleMapper.authorizationControlList(controlId);
	}

	@Override
	public List<Map<String, Object>> authorizationMenuList(Integer menuId) {
		return roleMapper.authorizationMenuList(menuId);
	}
}
