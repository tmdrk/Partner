/* 
 * Copyright (C) 2006-2014 亿谱汇投资管理（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: WorkGroupServiceImpl.java 
 *
 * Created: [2014-11-17 下午04:31:11] by ydw 
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

import com.huizhongcf.partner.dao.WorkGroupMapper;
import com.huizhongcf.partner.model.WorkGroup;
import com.huizhongcf.partner.service.WorkGroupService;

/** 
 *
 * Description: 工作组接口实现类
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
@Service("workGroupService")
public class WorkGroupServiceImpl implements WorkGroupService{
	
	@Autowired
	private WorkGroupMapper workGroupMapper;
	
	@Override
	public void insertWorkGroups(List<WorkGroup> workGroups, Integer employeeId) {
		workGroupMapper.deleteWorkGroupInfoByEmpId(employeeId);//删除原先工作组
		for (WorkGroup workGroup : workGroups) {
			workGroupMapper.insertSelective(workGroup);
		}
	}

	@Override
	public List<Map<String, Object>> findWorkGroupInfoByEmpId(Integer employeeId) {
		return workGroupMapper.findWorkGroupInfoByEmpId(employeeId);
	}
}
