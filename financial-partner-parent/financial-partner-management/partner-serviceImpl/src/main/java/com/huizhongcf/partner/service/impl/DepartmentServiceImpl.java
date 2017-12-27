/* 
 * Copyright (C) 2006-2014 亿谱汇投资管理（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: DepartmentServiceImpl.java 
 *
 * Created: [2014-11-17 下午04:22:29] by ydw 
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

import com.huizhongcf.partner.dao.DepartmentMapper;
import com.huizhongcf.partner.model.Department;
import com.huizhongcf.partner.service.DepartmentService;

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
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService{
	
	@Autowired
	private DepartmentMapper departmentMapper;

	@Override
	public List<Map<String, Object>> findDepartmentList() {
		return departmentMapper.findDepartmentList();
	}

	@Override
	public Department selectByPrimaryKey(Integer departmentId) {
		return departmentMapper.selectByPrimaryKey(departmentId);
	}

	@Override
	public Integer insertSelective(Department department) {
		return departmentMapper.insertSelective(department);
	}

	@Override
	public Integer updateByPrimaryKeySelective(Department department) {
		return departmentMapper.updateByPrimaryKeySelective(department);
	}

	@Override
	public int deleteByPrimaryKey(Integer departmentId) {
		return departmentMapper.deleteByPrimaryKey(departmentId);
	}

	@Override
	public List<Map<String, Object>> findFirstDeptInfo() {
		return departmentMapper.findFirstDeptInfo();
	}

	@Override
	public List<Map<String, Object>> findSubWorkDeptInfoByDeptId(Department department) {
		return departmentMapper.findSubWorkDeptInfoByDeptId(department);
	}

	@Override
	public String getLevelDeptInfo(Integer deptId) {
		return departmentMapper.getLevelDeptInfo(deptId);
	}

	@Override
	public List<Map<String, Object>> findDepartmentGroupList(Integer employeeId) {
		return departmentMapper.findDepartmentGroupList(employeeId);
	}

	@Override
	public List<Map<String, Object>> findDepartmentTwoAndThree() {
		 
		return departmentMapper.findDepartmentTwoAndThree();
	}
}

