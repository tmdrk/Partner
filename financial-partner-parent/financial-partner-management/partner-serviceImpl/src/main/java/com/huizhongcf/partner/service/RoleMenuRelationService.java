/* 
 * Copyright (C) 2006-2014 亿谱汇投资管理（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: RoleMenuRelationService.java 
 *
 * Created: [2014-11-17 下午04:29:39] by ydw 
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

import com.huizhongcf.partner.model.RoleMenuRelation;

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

public interface RoleMenuRelationService {

	/**
	 * 
	 * Description: 保存菜单角色关系
	 *
	 * @param 
	 * @return void
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-10 上午11:13:44
	 */
	public void addRoleMenuRelation(RoleMenuRelation roleMenuRelation, String[] roleId);
}
