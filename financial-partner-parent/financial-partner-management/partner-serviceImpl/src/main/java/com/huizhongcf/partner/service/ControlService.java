/* 
 * Copyright (C) 2006-2014 亿谱汇投资管理（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: ControlService.java 
 *
 * Created: [2014-11-17 下午04:18:47] by ydw 
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

import com.huizhongcf.partner.model.Control;

/** 
 *
 * Description: 控件service
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

public interface ControlService {

	/**
	 * 
	 * Description: 根据按钮获得角色
	 *
	 * @param 
	 * @return Map<String,String>
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-10 下午06:13:26
	 */
	public Map<String, String> findRoleByCtrl();
	
	/**
	 * 
	 * Description: 查询当前页控件列表信息
	 *
	 * @param 
	 * @return PageModel
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-8 下午05:25:42
	 */
	public List<Map<String, Object>> findAllNoPage(Map<String, Object> paramsCondition);
	
	/**
	 * 
	 * Description: 添加控件
	 *
	 * @param 
	 * @return Integer
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-9 下午01:49:25
	 */
	public Integer addControl(Control control); 
	
	/**
	 * 
	 * Description: 根据控件id查找控件
	 *
	 * @param 
	 * @return Control
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-9 下午01:51:07
	 */
	public Control getControlByControlId(Integer controlId);
	
	/**
	 * 
	 * Description: 修改控件
	 *
	 * @param 
	 * @return Integer
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-9 下午01:49:59
	 */
	public Integer editControl(Control control); 
	
	/**
	 * 
	 * Description: 查询页面信息和控件信息通过控件id
	 *
	 * @param 
	 * @return Map<String,Object>
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-9 下午02:59:15
	 */
	public Map<String, Object> findPageControlInfoByContolId(Integer controlId);
}
