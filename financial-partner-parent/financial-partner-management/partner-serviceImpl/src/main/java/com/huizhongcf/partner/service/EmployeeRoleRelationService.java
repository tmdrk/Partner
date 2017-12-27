/* 
 * Copyright (C) 2006-2014 亿谱汇投资管理（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: EmployeeRoleRelationService.java 
 *
 * Created: [2014-11-17 下午04:24:03] by ydw 
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

/** 
 *
 * Description: 员工角色关系service
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

public interface EmployeeRoleRelationService {
	
	/**
	 * 
	 * Description: 添加员工角色关系
	 *
	 * @param 
	 * @return void
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-10 下午02:20:28
	 */
	public void addEmpRoleRelation(String empIds, String roleIds, Integer creator);
	
	/**
	 * 
	 * Description: 修改员工角色关系
	 *
	 * @param 
	 * @return void
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-10 下午03:23:31
	 */
	public void editEmpRoleRelation(Integer employeeId,String roleIds, Integer creator);

}
