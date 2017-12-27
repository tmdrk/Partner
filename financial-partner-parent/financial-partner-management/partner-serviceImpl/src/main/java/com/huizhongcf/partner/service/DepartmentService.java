/* 
 * Copyright (C) 2006-2014 亿谱汇投资管理（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: DepartmentService.java 
 *
 * Created: [2014-11-17 下午04:22:16] by ydw 
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

import com.huizhongcf.partner.model.Department;

/** 
 *
 * Description: 部门管理service
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

public interface DepartmentService {
	
	/**
	 * 
	 * Description: 查询部门列表，用于部门管理页面
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-11-28 下午06:02:54
	 */
	public List<Map<String, Object>> findDepartmentList();
	
	/**
	 * 
	 * Description: 通过主键查询
	 *
	 * @param 
	 * @return Department
	 * @throws 
	 * @Author yaodawei
	 * Create Date: 2014-1-21 下午04:16:18
	 */
    public Department selectByPrimaryKey(Integer departmentId);
    
	/**
	 * 
	 * Description: 新增部门
	 *
	 * @param 
	 * @return Integer
	 * @throws 
	 * @Author yaodawei
	 * Create Date: 2014-1-21 下午03:33:29
	 */
	public Integer insertSelective(Department department);

	/**
	 * Description: 根据部门id更新信息
	 *
	 * @param department
	 * @Author JiangLong Cui
	 * @Create Date: 2013-11-7 下午1:42:34
	 */
	public Integer updateByPrimaryKeySelective(Department department);
	
	 /**
	  * 
	  * Description: 通过主键删除
	  *
	  * @param 
	  * @return int
	  * @throws 
	  * @Author ydw
	  * Create Date: 2014-12-1 上午11:18:42
	  */
    public int deleteByPrimaryKey(Integer departmentId);
    
    /**
     * 
     * Description: 查找员工列表    一级部门 财富管理部
     *
     * @param 
     * @return List<Map<String,Object>>
     * @throws 
     * @Author ydw
     * Create Date: 2014-12-1 下午01:49:40
     */
	public List<Map<String, Object>> findFirstDeptInfo();
	
	/**
	 * 
	 * Description: 查找所在团队 三级级联的第二级(营业部)或第三级(团队)
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-1 下午02:02:07
	 */
	public List<Map<String, Object>> findSubWorkDeptInfoByDeptId(Department department);
	
	/**
	 * 
	 * Description: 获得层级部门信息名称
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-2 上午11:58:46
	 */
	public String getLevelDeptInfo(Integer deptId);
	
	/**
	 * 
	 * Description: 查询部门信息用于给员工设置工作组 
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-2-19 上午10:28:26
	 */
	public List<Map<String, Object>> findDepartmentGroupList(Integer employeeId);

	/**
	 * Description: 查询业绩管理的二三级部门目标管理
	 * @param 
	 * @return String
	 * @throws 
	 * @Author Gaofeng
	 * Create Date:2017年8月8日
	*/
	public List<Map<String, Object>> findDepartmentTwoAndThree();
}
