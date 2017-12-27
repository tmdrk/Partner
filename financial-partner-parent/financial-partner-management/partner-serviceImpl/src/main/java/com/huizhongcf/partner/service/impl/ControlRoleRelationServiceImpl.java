/* 
 * Copyright (C) 2006-2014 亿谱汇投资管理（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: ControlRoleRelationServiceImpl.java 
 *
 * Created: [2014-11-17 下午04:21:39] by ydw 
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

import com.huizhongcf.partner.dao.ControlRoleRelationMapper;
import com.huizhongcf.partner.model.ControlRoleRelation;
import com.huizhongcf.partner.service.ControlRoleRelationService;

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
@Service("controlRoleRelationService")
public class ControlRoleRelationServiceImpl implements ControlRoleRelationService{
	
	@Autowired
	private ControlRoleRelationMapper controlRoleRelationMapper;

	@Override
	public void addCtrlRoleRelation(ControlRoleRelation controlRoleRelation,String[] roleId) {
		// 数据库已经有该控件相关的角色信息,先删除原有的纪录,然后插入新记录
		controlRoleRelationMapper.deleteCtrlRoleRelationByCtrlId(controlRoleRelation.getControlId());
		for(int i = 0; i < roleId.length; i++){
			controlRoleRelation.setRoleId(Integer.parseInt(roleId[i]));
			controlRoleRelationMapper.insertSelective(controlRoleRelation);
		}
	}
}
