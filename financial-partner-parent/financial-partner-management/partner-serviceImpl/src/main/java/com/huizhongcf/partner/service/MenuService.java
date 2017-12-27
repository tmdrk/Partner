/* 
 * Copyright (C) 2006-2014 亿谱汇投资管理（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: MenuService.java 
 *
 * Created: [2014-11-17 下午04:26:42] by ydw 
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

import com.huizhongcf.partner.model.Menu;


/** 
 *
 * Description: 菜单接口
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
public interface MenuService {
	
	/**
	 * 
	 * Description: 查询该用户拥有的菜单权限 
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-10 下午05:23:25
	 */
	public List<Map<String, Object>> findMenuByUserId(Integer userId);
	
	/**
	 * 
	 * Description: 根据父Id查找根菜单
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-10 下午05:33:11
	 */
	public List<Map<String, Object>> findRootMenuByPid(List<Integer> menuPid);

	/**
	 * 
	 * Description: 查询所有菜单
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-4 上午11:14:14
	 */
	public List<Map<String, Object>> findAllRetMapNoPage(Map<String, Object> paramsCondition);
	
	/**
	 * 
	 * Description: 查询所用root节点
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-4 下午01:12:55
	 */
	public List<Map<String, Object>> selectMenuRoot();
	
	/**
	 * 
	 * Description: 添加菜单
	 *
	 * @param 
	 * @return Integer
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-4 下午01:33:32
	 */
	public Integer addMenu(Menu menu);
	
	/**
	 * 
	 * Description: 修改菜单
	 *
	 * @param 
	 * @return Integer
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-4 下午01:33:41
	 */
	public Integer updateMenu(Menu menu);
	
	/**
	 * 
	 * Description: 检查菜单中文名称、英文名称是否重复
	 *
	 * @param 
	 * @return int
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-4 下午01:53:41
	 */
	public int checkMenuIsRepeat(Map<String, Object> paramsCondition);
	
	/**
	 * 
	 * Description: 根据主键删除菜单
	 *
	 * @param 
	 * @return int
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-4 下午02:18:49
	 */
	public int deleteMenu(Integer menuId);
	
	/**
	 * 
	 * Description: 菜单排序
	 *
	 * @param 
	 * @return void
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-4 下午04:24:40
	 */
	public void updateNodesSort(List<Map<String, Object>> nolesSortList);
	
}
