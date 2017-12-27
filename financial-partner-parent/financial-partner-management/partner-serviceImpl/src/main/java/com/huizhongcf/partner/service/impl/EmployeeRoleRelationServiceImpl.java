/* 
 * Copyright (C) 2006-2014 亿谱汇投资管理（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: EmployeeRoleRelationServiceImpl.java 
 *
 * Created: [2014-11-17 下午04:24:17] by ydw 
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

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huizhongcf.partner.dao.EmployeeRoleRelationMapper;
import com.huizhongcf.partner.model.EmployeeRoleRelation;
import com.huizhongcf.partner.service.EmployeeRoleRelationService;
import com.huizhongcf.util.StringUtil;

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
@Service("employeeRoleRelationService")
public class EmployeeRoleRelationServiceImpl implements EmployeeRoleRelationService{
	
	@Autowired
	private EmployeeRoleRelationMapper employeeRoleRelationMapper;

	@Override
	public void addEmpRoleRelation(String empIds, String roleIds, Integer creator) {
		if(StringUtil.isNotBlank(empIds) && StringUtil.isNotBlank(roleIds)){
			String[] empIdArray = empIds.split(",");
			String[] roleIdArray = roleIds.split(",");
			EmployeeRoleRelation err = null;
			for(int i = 0; i < empIdArray.length; i++){
				for (int j = 0; j < roleIdArray.length; j++) {
					err = new EmployeeRoleRelation();
					err.setEmployeeId(Integer.valueOf(empIdArray[i]));
					err.setRoleId(Integer.valueOf(roleIdArray[j]));
					err.setCreateTime(new Date());
					err.setCreator(creator);
					employeeRoleRelationMapper.insertSelective(err);
				}
			}
		}
	}

	@Override
	public void editEmpRoleRelation(Integer employeeId, String roleIds,Integer creator) {
		employeeRoleRelationMapper.deleteRoleByEmployeeId(employeeId);//先删除后插入
		if(StringUtil.isNotBlank(roleIds) && employeeId != null){
			String[] roleIdArray = roleIds.split(",");
			EmployeeRoleRelation err = null;
			for(int i = 0; i < roleIdArray.length; i++){
				err = new EmployeeRoleRelation();
				err.setEmployeeId(employeeId);
				err.setRoleId(Integer.valueOf(roleIdArray[i]));
				err.setCreateTime(new Date());
				err.setCreator(creator);
				employeeRoleRelationMapper.insertSelective(err);
			}
		}
	}

}
