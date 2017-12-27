/* 
 * Copyright (C) 2006-2014 亿谱汇投资管理（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: MenuServiceImpl.java 
 *
 * Created: [2014-11-17 下午04:26:56] by ydw 
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

import com.huizhongcf.partner.dao.MenuMapper;
import com.huizhongcf.partner.model.Menu;
import com.huizhongcf.partner.service.MenuService;

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
@Service("menuService")
public class MenuServiceImpl implements MenuService{

	@Autowired
	private MenuMapper menuMapper;
	

	@Override
	public List<Map<String, Object>> findMenuByUserId(Integer userId) {
		return menuMapper.findMenuByUserId(userId);
	}
	
	@Override
	public List<Map<String, Object>> findRootMenuByPid(List<Integer> menuPid) {
		return menuMapper.findRootMenuByPid(menuPid);
	}

	@Override
	public List<Map<String, Object>> findAllRetMapNoPage(Map<String, Object> paramsCondition) {
		return menuMapper.findAllRetMapNoPage(paramsCondition);
	}

	@Override
	public List<Map<String, Object>> selectMenuRoot() {
		return menuMapper.selectMenuRoot();
	}

	@Override
	public Integer addMenu(Menu menu) {
		return menuMapper.insertSelective(menu);
	}

	@Override
	public Integer updateMenu(Menu menu) {
		return menuMapper.updateByPrimaryKeySelective(menu);
	}

	@Override
	public int checkMenuIsRepeat(Map<String, Object> paramsCondition) {
		return menuMapper.checkMenuIsRepeat(paramsCondition);
	}

	@Override
	public int deleteMenu(Integer menuId) {
		return menuMapper.deleteByPrimaryKey(menuId);
	}

	@Override
	public void updateNodesSort(List<Map<String, Object>> nolesSortList) {
		for(Map<String, Object> map : nolesSortList){
			menuMapper.updateNodesSort(map);
		}
	}
}
