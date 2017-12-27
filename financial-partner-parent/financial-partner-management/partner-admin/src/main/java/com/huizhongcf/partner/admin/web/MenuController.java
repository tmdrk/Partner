/* 
 * Copyright (C) 2006-2014 亿谱汇投资管理（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: MenuController.java 
 *
 * Created: [2014-11-18 上午09:53:06] by ydw 
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

package com.huizhongcf.partner.admin.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huizhongcf.partner.admin.baseweb.BaseController;
import com.huizhongcf.partner.admin.baseweb.DataMsg;
import com.huizhongcf.partner.model.Menu;
import com.huizhongcf.partner.service.MenuService;
import com.huizhongcf.util.StringUtil;

/** 
 *
 * Description: 菜单Controller
 *
 * @author ydw
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014-11-18    ydw       1.0        1.0 Version 
 * </pre>
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController{
	
	@Autowired
	private MenuService menuService;

	/**
	 * 
	 * Description: 跳转至菜单管理页面
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-4 上午10:46:36
	 */
	@RequestMapping(value="/toMenuList")
	public String list(String refreshTag,String messageCode,Model model) {
		showMessageAlert(refreshTag,messageCode,model);
		return "app/menu/menu_list";
	}
	
	/**
	 * 
	 * Description: 查询该用户拥有的菜单权限
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-4 上午10:50:30
	 */
	@ResponseBody
	@RequestMapping(value="/loadMenu")
	public List<Map<String, Object>> loadMenu(HttpSession session) {
		List<Map<String, Object>> rootMenuList = null;
		try {
			List<Map<String, Object>> nodeMenuList = menuService.findMenuByUserId(getSystemCurrentUser(session).getEmployeeId());
			
			if(nodeMenuList.size() > 0){
				List<Integer> parentIdList = new ArrayList<Integer>();//存放父节点list
				for (Map<String, Object> map : nodeMenuList) {
					parentIdList.add((Integer)map.get("parent_id"));
				}
				rootMenuList = menuService.findRootMenuByPid(parentIdList);
				for (Map<String, Object> map : nodeMenuList) {
					rootMenuList.add(map);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return rootMenuList;
	}
	
	/**
	 * 
	 * Description: 查询所有菜单
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-4 上午10:50:30
	 */
	@ResponseBody
	@RequestMapping(value="/menus")
	public List<Map<String, Object>> menuList() {
		List<Map<String, Object>> menuList = null;
		try {
			menuList = menuService.findAllRetMapNoPage(null);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return menuList;
	}
	
	/**
	 * 
	 * Description: 查询所用root节点
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-4 下午01:03:42
	 */
	@ResponseBody
	@RequestMapping(value="/selectMenuRoot")
	public List<Map<String, Object>> selectMenuRoot() {
		List<Map<String, Object>> rootList = null;
		try {
			rootList = menuService.selectMenuRoot();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return rootList;
	}
	
	/**
	 * 
	 * Description: 增加修改菜单
	 *
	 * @param 
	 * @return DataMsg
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-4 下午01:29:22
	 */
	@ResponseBody
	@RequestMapping(value="/addOrUpdateMenu")
	public DataMsg addOrUpdateMenu(@ModelAttribute Menu menu,DataMsg dataMsg,HttpSession session){
		try {
			if(menu.getMenuId() != null){
				//更新
				menuService.updateMenu(menu);
				dataMsg.setMessageCode("0003");
			}else{
				//新增
				menu.setCreateTime(new Date());
				menu.setCreator(getSystemCurrentUser(session).getEmployeeId());
				menuService.addMenu(menu);
				dataMsg.setMessageCode("0001");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			if(menu.getMenuId() != null){
				dataMsg.setMessageCode("0004");
			}else{
				dataMsg.setMessageCode("0002");
			}
		}
		return dataMsg;
	}
	
	/**
	 * 
	 * Description: 检查菜单中文名称、英文名称是否重复
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-4 下午01:59:41
	 */
	@ResponseBody
	@RequestMapping(value="/checkMenuIsRepeat")
	public DataMsg checkMenuIsRepeat(HttpServletRequest request,DataMsg dataMsg) {
		try {
			String menuId = request.getParameter("menuId");
			String nameZh = request.getParameter("nameZh");
			String nameEn = request.getParameter("nameEn");
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			Integer result = null;
			if(StringUtil.isNotBlank(menuId) && StringUtil.isNotBlank(nameZh)){
				paramsCondition.put("menuId", Integer.valueOf(menuId));
				paramsCondition.put("nameZh", nameZh);
			}else if(StringUtil.isBlank(menuId) && StringUtil.isNotBlank(nameZh)){
				paramsCondition.put("nameZh", nameZh);
			}else if(StringUtil.isNotBlank(menuId) && StringUtil.isNotBlank(nameEn)){
				paramsCondition.put("menuId", Integer.valueOf(menuId));
				paramsCondition.put("nameEn", nameEn);
			}else if(StringUtil.isBlank(menuId) && StringUtil.isNotBlank(nameEn)){
				paramsCondition.put("nameEn", nameEn);
			}
			result = menuService.checkMenuIsRepeat(paramsCondition);
			if(result > 0){
				dataMsg.setIsRepeat("repeat");
			}else{
				dataMsg.setIsRepeat("noRepeat");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}
	
	/**
	 * 
	 * Description: 删除菜单
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-4 下午02:22:08
	 */
	@ResponseBody
	@RequestMapping(value="/deleteMenu/{menuId}")
	public DataMsg deleteMenu(@PathVariable Integer menuId,DataMsg dataMsg) {
		try {
			menuService.deleteMenu(menuId);
			dataMsg.setMessageCode("0005");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			dataMsg.setMessageCode("0006");
		}
		return dataMsg;
	}
	
	/**
	 * 
	 * Description: 菜单排序 
	 *
	 * @param 
	 * @return DataMsg
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-4 下午04:22:56
	 */
	@ResponseBody
	@RequestMapping(value="/nodesSort")
	public DataMsg nodesSort(@RequestParam String nSParam,DataMsg dataMsg) {
		try {
			String[] nSParamArray = nSParam.split("[$]");
			List<Map<String, Object>> nolesSortList = new ArrayList<Map<String, Object>>();
			for(String nSParamSingle : nSParamArray){
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("menuId", nSParamSingle.split(",")[0]);
				map.put("menuSort", nSParamSingle.split(",")[1]);
				nolesSortList.add(map);
			}
			menuService.updateNodesSort(nolesSortList);
			dataMsg.setRequestState("true");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			dataMsg.setRequestState("false");
		}
		return dataMsg;
	}
}
