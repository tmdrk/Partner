/* 
 * Copyright (C) 2006-2014 亿谱汇投资管理（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: RoleMenuRelationServiceImpl.java 
 *
 * Created: [2014-11-17 下午04:29:50] by ydw 
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huizhongcf.partner.dao.RoleMenuRelationMapper;
import com.huizhongcf.partner.model.RoleMenuRelation;
import com.huizhongcf.partner.service.RoleMenuRelationService;

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
@Service("roleMenuRelationService")
public class RoleMenuRelationServiceImpl implements RoleMenuRelationService{
	
	@Autowired
	private RoleMenuRelationMapper roleMenuRelationMapper;

	@Override
	public void addRoleMenuRelation(RoleMenuRelation roleMenuRelation,String[] roleId) {
		// 数据库已经有该菜单相关的角色信息,先删除原有的纪录,然后插入新记录
		roleMenuRelationMapper.deleteMenuRoleRelationByMenuId(roleMenuRelation.getMenuId());
		for(int i = 0; i < roleId.length; i++){
			roleMenuRelation.setRoleId(Integer.parseInt(roleId[i]));
			roleMenuRelationMapper.insertSelective(roleMenuRelation);
		}
	}

}
