/* 
 * Copyright (C) 2006-2014 亿谱汇投资管理（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: WorkGroupService.java 
 *
 * Created: [2014-11-17 下午04:31:01] by ydw 
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

import com.huizhongcf.partner.model.WorkGroup;

/** 
 *
 * Description: 工作组接口
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

public interface WorkGroupService {
	
	/**
	 * 
	 * Description: 插入工作组
	 *
	 * @param workGroups
	 * @param employeeId
	 * @return void
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-2-18 下午05:55:02
	 */
	public void insertWorkGroups(List<WorkGroup> workGroups,Integer employeeId);
	
	 /**
     * 
     * Description: 根据员工id查找拥有的工作组信息
     *
     * @param 
     * @return List<Map<String,Object>>
     * @throws 
     * @Author ydw
     * Create Date: 2014-2-19 下午01:16:13
     */
    public List<Map<String, Object>> findWorkGroupInfoByEmpId(Integer employeeId);

}
