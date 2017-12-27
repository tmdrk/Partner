/* 
 * Copyright (C) 2006-2014 亿谱汇投资管理（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: RoleController.java 
 *
 * Created: [2014-12-3 下午03:52:43] by ydw 
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
import com.huizhongcf.partner.model.Role;
import com.huizhongcf.partner.model.RoleMenuRelation;
import com.huizhongcf.partner.service.RoleMenuRelationService;
import com.huizhongcf.partner.service.RoleService;
import com.huizhongcf.util.PageModel;
import com.huizhongcf.util.StringUtil;

/** 
 *
 * Description: 角色Controller
 *
 * @author ydw
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014-12-3    ydw       1.0        1.0 Version 
 * </pre>
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController{
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RoleMenuRelationService roleMenuRelationService;
	
	/**
	 * 
	 * Description: 跳转到角色列表页面
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-3 下午03:57:18
	 */
	@RequestMapping(value="/toRoleList")
	public String toRoleList(String refreshTag,String messageCode,Model model) {
		showMessageAlert(refreshTag,messageCode,model);
		return "app/role/role_list";
	}
	
	/**
	 * 
	 * Description: 角色列表分页
	 *
	 * @param 
	 * @return DataMsg
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-3 下午04:11:55
	 */
	@ResponseBody
	@RequestMapping(value="/roles")
	public DataMsg pageList(HttpServletRequest request,DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("page")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			String roleCode = request.getParameter("roleCode");
			if (StringUtil.isNotBlank(roleCode)) {
				paramsCondition.put("roleCode", roleCode.trim());
			}
			String roleName = request.getParameter("roleName");
			if (StringUtil.isNotBlank(roleName)) {
				paramsCondition.put("roleName", roleName.trim());
			}
			PageModel pageModel = roleService.findAllByPage(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}
	
	/**
	 * 
	 * Description: 查询所有角色不分页
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-10 下午01:52:06
	 */
	@ResponseBody
	@RequestMapping(value="/findAllNoPage")
	public List<Map<String, Object>> findAllNoPage() {
		List<Map<String, Object>> roleList = null;
		try {
			roleList = roleService.findAllNoPage(null);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return roleList;
	}
	
	/**
	 * 
	 * Description: 跳转到角色录入页面
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-3 下午04:41:53
	 */
	@RequestMapping(value="/toAddRole")
	public String toAddRole() {
		return "app/role/role_add";
	}
	
	/**
	 * 
	 * Description: 添加角色
	 *
	 * @param 
	 * @return DataMsg
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-3 下午05:21:32
	 */
	@ResponseBody
	@RequestMapping(value="/doAddRole")
	public DataMsg doAddRole(@ModelAttribute Role role, DataMsg dataMsg,HttpSession session){
		try {
			role.setCreator(getSystemCurrentUser(session).getEmployeeId());
			role.setCreateTime(new Date());
			roleService.addRole(role);
			dataMsg.setMessageCode("0001");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			dataMsg.setMessageCode("0002");
		}
		return dataMsg;
	}

	/**
	 * 
	 * Description: 跳转到角色修改页面
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-3 下午04:42:09
	 */
	@RequestMapping(value="/toUpdateRole/{roleId}")
	public String toUpdateRole(@PathVariable Integer roleId,Model model){
		try {
			Role role = roleService.getRoleByRoleId(roleId);
			model.addAttribute("role", role);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return "common/exception";
		}
		return "app/role/role_edit";
	}
	
	/**
	 * 
	 * Description: 修改角色
	 *
	 * @param 
	 * @return DataMsg
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-3 下午05:21:32
	 */
	@ResponseBody
	@RequestMapping(value="/doUpdateRole")
	public DataMsg doUpdateRole(@ModelAttribute Role role, DataMsg dataMsg){
		try {
			roleService.editRole(role);
			dataMsg.setMessageCode("0003");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			dataMsg.setMessageCode("0004");
		}
		return dataMsg;
	}
	
	/**
	 * 
	 * Description: 查询所有角色，并且标识出当前员工拥有的角色
	 *
	 * @param 
	 * @return DataMsg
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-10 上午10:37:15
	 */
	@ResponseBody
	@RequestMapping(value="/authorizationEmpList/{employeeId}")
	public DataMsg authorizationEmpList(@PathVariable Integer employeeId,DataMsg dataMsg){
		try {
			List<Map<String, Object>> dataList = roleService.authorizationEmpList(employeeId);
			dataMsg.setRows(dataList);
		} catch(Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}
	
	/**
	 * 
	 * Description: 查询所有角色，并且标识出菜单拥有的角色
	 *
	 * @param 
	 * @return DataMsg
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-10 上午10:37:15
	 */
	@ResponseBody
	@RequestMapping(value="/authorizationMenuList/{menuId}")
	public DataMsg authorizationMenuList(@PathVariable Integer menuId,DataMsg dataMsg){
		try {
			List<Map<String, Object>> dataList = roleService.authorizationMenuList(menuId);
			dataMsg.setRows(dataList);
		} catch(Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}
	
	/**
	 * 
	 * Description: 查询所有角色，并且标识出控件按钮拥有的角色
	 *
	 * @param 
	 * @return DataMsg
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-9 下午03:43:05
	 */
	@ResponseBody
	@RequestMapping(value="/authorizationControlList/{controlId}")
	public DataMsg authorizationControlList(@PathVariable Integer controlId,DataMsg dataMsg){
		try {
			List<Map<String, Object>> dataList = roleService.authorizationControlList(controlId);
			dataMsg.setRows(dataList);
		} catch(Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}
	
	/**
	 * 
	 * Description: 给菜单授权
	 *
	 * @param 
	 * @return DataMsg
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-9 下午04:22:48
	 */
	@ResponseBody
	@RequestMapping(value="/doAuthorizeMenu")
	public DataMsg doAuthorizeMenu(@ModelAttribute RoleMenuRelation roleMenuRelation,@RequestParam String roleIds,DataMsg dataMsg,HttpSession session){
		try {
			roleMenuRelation.setCreator(getSystemCurrentUser(session).getEmployeeId());
			roleMenuRelation.setCreateTime(new Date());
			roleMenuRelationService.addRoleMenuRelation(roleMenuRelation, roleIds.split(","));
			dataMsg.setMessageCode("0014");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			dataMsg.setMessageCode("0015");
		}
		return dataMsg;
	}
}
