package com.huizhongcf.partner.admin.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huizhongcf.partner.admin.baseweb.BaseController;
import com.huizhongcf.partner.admin.baseweb.DataMsg;
import com.huizhongcf.partner.admin.constant.Constants;
import com.huizhongcf.partner.model.LoginUser;
import com.huizhongcf.partner.service.LoginUserService;
import com.huizhongcf.util.DateTimeUtil;
import com.huizhongcf.util.ExportExcel;
import com.huizhongcf.util.PageModel;
import com.huizhongcf.util.StringUtil;

/**
 * 
 * Description: 登录用户 Controller
 *
 * @author haochunhe
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年12月6日    	haochunhe      	  1.0       1.0 Version 
 * </pre>
 */
@Controller
@RequestMapping("/loginuser")
public class LoginUserController extends BaseController{

	@Autowired
	private LoginUserService loginUserService;
	/**
	 * 页面跳转到登录用户列表
	 * @param refreshTag
	 * @param messageCode
	 * @param model
	 * @return
	 */
	@RequestMapping("/toLoginUserListPage")
	public String toLoginUserListPage(String refreshTag,String messageCode,Model model) {
		showMessageAlert(refreshTag,messageCode,model);
		return "app/biz/loginuser/login_user_list";
	}
	
	/**
	 * 用户数据分页列表
	 * @param request
	 * @param dataMsg
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/loginUsers")
	public DataMsg LoginUserList(HttpServletRequest request,DataMsg dataMsg) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageNo", Integer.valueOf(request.getParameter("page")));
			map.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			String realName = StringUtil.trim(request.getParameter("realName"));
			if (StringUtil.isNotBlank(realName)) {
				map.put("realName", realName);
			}
			String username = StringUtil.trim(request.getParameter("username"));
			if (StringUtil.isNotBlank(username)) {
				map.put("username", username);
			}
			String userRole = StringUtil.trim(request.getParameter("userRole"));
			if (StringUtil.isNotBlank(userRole)) {
				map.put("userRole", userRole);
			}
			String userStatus = StringUtil.trim(request.getParameter("userStatus"));
			if (StringUtil.isNotBlank(userStatus)) {
				map.put("userStatus", userStatus);
			}
			String userType = StringUtil.trim(request.getParameter("userType"));
			if (StringUtil.isNotBlank(userType)) {
				map.put("userType", userType);
			}
			String organizationChannelId = StringUtil.trim(request.getParameter("organizationChannelId"));
			if (StringUtil.isNotBlank(organizationChannelId)) {
				map.put("organizationChannelId", organizationChannelId);
			}
			String organizationAreaId = StringUtil.trim(request.getParameter("organizationAreaId"));
			if (StringUtil.isNotBlank(organizationAreaId)) {
				map.put("organizationAreaId", organizationAreaId);
			}
			String invitationCode = StringUtil.trim(request.getParameter("invitationCode"));
			if (StringUtil.isNotBlank(invitationCode)) {
				String partnerCode = invitationCode.substring(0,3);
				if("301".equals(partnerCode)){
					map.put("partnerRecommendCode", invitationCode);
				}else{
					map.put("customerRecommendCode", invitationCode);
				}
			}
			String parentName = StringUtil.trim(request.getParameter("parentName"));
			if (StringUtil.isNotBlank(parentName)) {
				map.put("parentName", parentName);
			}
			String parentRealName = StringUtil.trim(request.getParameter("parentRealName"));
			if (StringUtil.isNotBlank(parentRealName)) {
				map.put("parentRealName", parentRealName);
			}
			String parentUsername = StringUtil.trim(request.getParameter("parentUsername"));
			if (StringUtil.isNotBlank(parentUsername)) {
				map.put("parentUsername", parentUsername);
			}
			String parentUserRole = StringUtil.trim(request.getParameter("parentUserRole"));
			if (StringUtil.isNotBlank(parentUserRole)) {
				map.put("parentUserRole", parentUserRole);
			}
			String min_change_partner_time = StringUtil.trim(request.getParameter("min_change_partner_time"));
			if (StringUtil.isNotBlank(min_change_partner_time)) {
				map.put("min_change_partner_time", min_change_partner_time);
			}
			String max_change_partner_time = StringUtil.trim(request.getParameter("max_change_partner_time"));
			if (StringUtil.isNotBlank(max_change_partner_time)) {
				map.put("max_change_partner_time", max_change_partner_time);
			}
			String min_regist_time = StringUtil.trim(request.getParameter("min_regist_time"));
			if (StringUtil.isNotBlank(min_regist_time)) {
				map.put("min_regist_time", min_regist_time);
			}
			String max_regist_time = StringUtil.trim(request.getParameter("max_regist_time"));
			if (StringUtil.isNotBlank(max_regist_time)) {
				map.put("max_regist_time", max_regist_time);
			}
			PageModel pageModel = loginUserService.getLoginUser(map);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}
	
	/**
	 * 
	 * Description: 跳转到修改员工页面
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author haochunhe
	 * Create Date: 2017-12-7 上午09:33:32
	 */
	@RequestMapping(value="/toEditLoginUser/{userId}")
	public String toEditLoginUser(@PathVariable Integer userId,Model model) {
		try {
			Map<String,Object> loginUser = loginUserService.selectByUserId(userId);
			model.addAttribute("loginUser", loginUser);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return "common/exception";
		}
		return "app/biz/loginuser/user_login_edit";
	}
	
	/**
	 * 
	 * Description: 修改员工信息
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author haochunhe
	 * Create Date: 2017-12-7 上午10:21:52
	 */
	@ResponseBody
	@RequestMapping("/updateLoginUser")
	public DataMsg updateLoginUser(@ModelAttribute LoginUser loginUser,HttpSession session,DataMsg dataMsg){
		try {
			loginUser.setOperator(getSystemCurrentUser(session).getEmployeeId());
			loginUser.setOperateTime(new Date());
			loginUserService.updateByPrimaryKeySelective(loginUser);
			dataMsg.setMessageCode("0003");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			dataMsg.setMessageCode("0004");
		}
		return dataMsg;
	}
	
	/**
	 * 页面跳转到异常用户列表
	 * @param refreshTag
	 * @param messageCode
	 * @param model
	 * @return
	 */
	@RequestMapping("/toExceptionLoginUser")
	public String toExceptionLoginUser(String refreshTag,String messageCode,Model model) {
		showMessageAlert(refreshTag,messageCode,model);
		return "app/biz/loginuser/exception_login_user";
	}
	
	/**
	 * 异常用户数据分页列表
	 * @param request
	 * @param dataMsg
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/exceptionLoginUserList")
	public DataMsg exceptionLoginUserList(HttpServletRequest request,DataMsg dataMsg) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageNo", Integer.valueOf(request.getParameter("page")));
			map.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			String realName = StringUtil.trim(request.getParameter("realName"));
			if (StringUtil.isNotBlank(realName)) {
				map.put("realName", realName);
			}
			String username = StringUtil.trim(request.getParameter("username"));
			if (StringUtil.isNotBlank(username)) {
				map.put("username", username);
			}
			String inputInvitationCode = StringUtil.trim(request.getParameter("inputInvitationCode"));//查询接口日志的起始时间
			if (StringUtil.isNotBlank(inputInvitationCode)) {
				map.put("inputInvitationCode", inputInvitationCode);
			}
			String min_change_partner_time = StringUtil.trim(request.getParameter("min_change_partner_time"));
			if (StringUtil.isNotBlank(min_change_partner_time)) {
				map.put("min_change_partner_time", min_change_partner_time);
			}
			String max_change_partner_time = StringUtil.trim(request.getParameter("max_change_partner_time"));
			if (StringUtil.isNotBlank(max_change_partner_time)) {
				map.put("max_change_partner_time", max_change_partner_time);
			}
			String min_regist_time = StringUtil.trim(request.getParameter("min_regist_time"));
			if (StringUtil.isNotBlank(min_regist_time)) {
				map.put("min_regist_time", min_regist_time);
			}
			String max_regist_time = StringUtil.trim(request.getParameter("max_regist_time"));
			if (StringUtil.isNotBlank(max_regist_time)) {
				map.put("max_regist_time", max_regist_time);
			}
			PageModel pageModel = loginUserService.getExceptionLoginUser(map);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}
	
	/**
	 * 
	 * Description: 跳转查看邀请用户页面
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author haochunhe
	 * Create Date: 2017-12-7 下午2:33:32
	 */
	@RequestMapping(value="/toFindLoginUser/{userId}")
	public String toFindLoginUser(@PathVariable Integer userId,Model model) {
		try {
			Map<String,Object> loginUser = loginUserService.selectByUserId(userId);
			model.addAttribute("loginUser", loginUser);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return "common/exception";
		}
		return "app/biz/loginuser/login_user_tail";
	}
	
	/**
	 * 查看邀请用户数据分页列表
	 * @param request
	 * @param dataMsg
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getLoginUserList")
	public DataMsg getLoginUserList(HttpServletRequest request,DataMsg dataMsg,Integer userId) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageNo", Integer.valueOf(request.getParameter("page")));
			map.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			map.put("userId", userId);
			String user_id = StringUtil.trim(request.getParameter("user_id"));
			if (StringUtil.isNotBlank(user_id)) {
				map.put("userId", user_id);
			}
			String realName = StringUtil.trim(request.getParameter("realName"));
			if (StringUtil.isNotBlank(realName)) {
				map.put("realName", realName);
			}
			String username = StringUtil.trim(request.getParameter("username"));
			if (StringUtil.isNotBlank(username)) {
				map.put("username", username);
			}
			String userStatus = StringUtil.trim(request.getParameter("userStatus"));
			if (StringUtil.isNotBlank(userStatus)) {
				map.put("userStatus", userStatus);
			}
			String min_change_partner_time = StringUtil.trim(request.getParameter("min_change_partner_time"));
			if (StringUtil.isNotBlank(min_change_partner_time)) {
				map.put("min_change_partner_time", min_change_partner_time);
			}
			String max_change_partner_time = StringUtil.trim(request.getParameter("max_change_partner_time"));
			if (StringUtil.isNotBlank(max_change_partner_time)) {
				map.put("max_change_partner_time", max_change_partner_time);
			}
			PageModel pageModel = loginUserService.getLoginUserList(map);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}
	
	/**
	 * 
	 * Description: 跳转查看下级用户页面
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author haochunhe
	 * Create Date: 2017-12-7 下午2:33:32
	 */
	@RequestMapping(value="/toFindLoginPartnerUser/{userId}")
	public String toFindLoginPartnerUser(@PathVariable Integer userId,Model model) {
		try {
			Map<String,Object> loginUser = loginUserService.selectByUserId(userId);
			model.addAttribute("loginUser", loginUser);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return "common/exception";
		}
		return "app/biz/loginuser/login_partner_user_tail";
	}
	
	/**
	 * 查看下级用户数据分页列表
	 * @param request
	 * @param dataMsg
	 * @return dataMsg
	 * @Author haochunhe
	 * Create Date: 2017-12-8 下午2:33:32
	 */
	@ResponseBody
	@RequestMapping(value="/getLoginPartnerUserList")
	public DataMsg getLoginPartnerUserList(HttpServletRequest request,DataMsg dataMsg,Integer userId,String user_type) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageNo", Integer.valueOf(request.getParameter("page")));
			map.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			map.put("userId", userId);
			String user_id = StringUtil.trim(request.getParameter("user_id"));
			String user_type1 = StringUtil.trim(request.getParameter("user_type"));
			if (StringUtil.isNotBlank(user_type1)) {
				user_type = user_type1;
			}
			if (StringUtil.isNotBlank(user_id)) {
				map.put("userId", user_id);
			}
			String realName = StringUtil.trim(request.getParameter("realName"));
			if (StringUtil.isNotBlank(realName)) {
				map.put("realName", realName);
			}
			String username = StringUtil.trim(request.getParameter("username"));
			if (StringUtil.isNotBlank(username)) {
				map.put("username", username);
			}
			String userRole = StringUtil.trim(request.getParameter("userRole"));
			if (StringUtil.isNotBlank(userRole)) {
				map.put("userRole", userRole);
			}
			String userStatus = StringUtil.trim(request.getParameter("userStatus"));
			if (StringUtil.isNotBlank(userStatus)) {
				map.put("userStatus", userStatus);
			}
			String inviteType = StringUtil.trim(request.getParameter("inviteType"));
			if (StringUtil.isNotBlank(inviteType)) {
				map.put("inviteType", inviteType);
			}
			String min_change_partner_time = StringUtil.trim(request.getParameter("min_change_partner_time"));
			if (StringUtil.isNotBlank(min_change_partner_time)) {
				map.put("min_change_partner_time", min_change_partner_time);
			}
			String max_change_partner_time = StringUtil.trim(request.getParameter("max_change_partner_time"));
			if (StringUtil.isNotBlank(max_change_partner_time)) {
				map.put("max_change_partner_time", max_change_partner_time);
			}
			if(Constants.USER_TYPE.equals(user_type)){//一般合伙人
				PageModel pageModel = loginUserService.getLoginPartnerUserList(map);
				dataMsg.setTotal(pageModel.getTotalRecords());
				dataMsg.setRows(pageModel.getList());
			}
			if(Constants.USER_TYPE_PARTNER.equals(user_type)){//团队管理者
				PageModel pageModel = loginUserService.getTeamLoginPartnerUserList(map);
				dataMsg.setTotal(pageModel.getTotalRecords());
				dataMsg.setRows(pageModel.getList());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}
	
	/**
	 * 
	 * Description: 停用、启用
	 *
	 * @param 
	 * @return DataMsg
	 * @throws 
	 * @Author haochunhe
	 * Create Date: 2017-12-7 下午06:32:54
	 */
	@ResponseBody
	@RequestMapping(value="/disableOrEnabled")
	public DataMsg disableOrEnabled(@ModelAttribute LoginUser loginUser,HttpSession session,DataMsg dataMsg) {
		try {
			loginUser.setOperator(getSystemCurrentUser(session).getEmployeeId());
			loginUser.setOperateTime(new Date());
			// 执行
			loginUserService.updateByPrimaryKeySelective(loginUser);
			if(loginUser.getUserStatus().equals(Constants.USER_STATE_ENABLED)){
				dataMsg.setMessageCode("0008");
			}else if(loginUser.getUserStatus().equals(Constants.USER_STATE_DISABLE)){
				dataMsg.setMessageCode("0010");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			if(loginUser.getUserStatus().equals(Constants.USER_STATE_ENABLED)){
				dataMsg.setMessageCode("0009");
			}else if(loginUser.getUserStatus().equals(Constants.USER_STATE_DISABLE)){
				dataMsg.setMessageCode("0011");
			}
		}
		return dataMsg;
	}
	
	/**
	 * 
	 * Description: 组织架构分配
	 *
	 * @param 
	 * @return DataMsg
	 * @throws 
	 * @Author haochunhe
	 * Create Date: 2017-12-7 下午07:32:54
	 */
	@ResponseBody
	@RequestMapping(value="/addOrganization")
	public DataMsg addOrganization(HttpServletRequest request,HttpSession session,DataMsg dataMsg){
		try {
			String organization_channel_id = StringUtil.trim(request.getParameter("organization_channel_id"));
			String organization_area_id = StringUtil.trim(request.getParameter("organization_area_id"));
			String userIds = StringUtil.trim(request.getParameter("userIds"));
			if (StringUtil.isNotBlank(organization_channel_id)&&StringUtil.isNotBlank(organization_area_id)&&StringUtil.isNotBlank(userIds)) {
				loginUserService.addOrganization(organization_channel_id, organization_area_id, userIds);
			}
			dataMsg.setMessageCode("0003");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			dataMsg.setMessageCode("0004");
		}
		return dataMsg;
	}
	
	/**
	 * 
	 * Description: 用户数据导出功能
	 *
	 * @param 
	 * @param request
	 * @param response
	 * @param session
	 * @Author haochunhe
	 * Create Date: 2017-12-8 上午10:32:54
	 */
	@RequestMapping(value="/exportLoginUsers")
	public void exportLoginUsers(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String realName = StringUtil.trim(request.getParameter("realName"));
			if (StringUtil.isNotBlank(realName)) {
				map.put("realName", realName);
			}
			String username = StringUtil.trim(request.getParameter("username"));
			if (StringUtil.isNotBlank(username)) {
				map.put("username", username);
			}
			String userRole = StringUtil.trim(request.getParameter("userRole"));
			if (StringUtil.isNotBlank(userRole)) {
				map.put("userRole", userRole);
			}
			String userStatus = StringUtil.trim(request.getParameter("userStatus"));
			if (StringUtil.isNotBlank(userStatus)) {
				map.put("userStatus", userStatus);
			}
			String userType = StringUtil.trim(request.getParameter("userType"));
			if (StringUtil.isNotBlank(userType)) {
				map.put("userType", userType);
			}
			String organizationChannelId = StringUtil.trim(request.getParameter("organizationChannelId"));
			if (StringUtil.isNotBlank(organizationChannelId)) {
				map.put("organizationChannelId", organizationChannelId);
			}
			String organizationAreaId = StringUtil.trim(request.getParameter("organizationAreaId"));
			if (StringUtil.isNotBlank(organizationAreaId)) {
				map.put("organizationAreaId", organizationAreaId);
			}
			String invitationCode = StringUtil.trim(request.getParameter("invitationCode"));
			if (StringUtil.isNotBlank(invitationCode)) {
				String partnerCode = invitationCode.substring(0,3);
				if("301".equals(partnerCode)){
					map.put("partnerRecommendCode", invitationCode);
				}else{
					map.put("customerRecommendCode", invitationCode);
				}
			}
			String parentName = StringUtil.trim(request.getParameter("parentName"));
			if (StringUtil.isNotBlank(parentName)) {
				map.put("parentName", parentName);
			}
			String parentRealName = StringUtil.trim(request.getParameter("parentRealName"));
			if (StringUtil.isNotBlank(parentRealName)) {
				map.put("parentRealName", parentRealName);
			}
			String parentUsername = StringUtil.trim(request.getParameter("parentUsername"));
			if (StringUtil.isNotBlank(parentUsername)) {
				map.put("parentUsername", parentUsername);
			}
			String parentUserRole = StringUtil.trim(request.getParameter("parentUserRole"));
			if (StringUtil.isNotBlank(parentUserRole)) {
				map.put("parentUserRole", parentUserRole);
			}
			String min_change_partner_time = StringUtil.trim(request.getParameter("min_change_partner_time"));
			if (StringUtil.isNotBlank(min_change_partner_time)) {
				map.put("min_change_partner_time", min_change_partner_time);
			}
			String max_change_partner_time = StringUtil.trim(request.getParameter("max_change_partner_time"));
			if (StringUtil.isNotBlank(max_change_partner_time)) {
				map.put("max_change_partner_time", max_change_partner_time);
			}
			String min_regist_time = StringUtil.trim(request.getParameter("min_regist_time"));
			if (StringUtil.isNotBlank(min_regist_time)) {
				map.put("min_regist_time", min_regist_time);
			}
			String max_regist_time = StringUtil.trim(request.getParameter("max_regist_time"));
			if (StringUtil.isNotBlank(max_regist_time)) {
				map.put("max_regist_time", max_regist_time);
			}
			List<Map<String, Object>> result = loginUserService.exportLoginUsers(map);
			String title = "用户数据信息"+DateTimeUtil.getDateNormalString(new Date());
	        String[] rowsName = new String[]{"序号","用户姓名","用户手机号","用户角色","用户身份","所属渠道","所属区域","上级合伙人姓名","上级合伙人手机号","上级合伙人推荐码","邀请人姓名","邀请人角色","汇中网邀请码","合伙人平台注册时间","汇中网注册时间","用户状态"};
	        List<Object[]>  dataList = new ArrayList<Object[]>();
	        Object[] objs = null;
	        Map<String, Object> promotionMap = null;
	        for (int i = 0; i < result.size(); i++) {
	        	promotionMap = result.get(i);
	            objs = new Object[rowsName.length];
	            objs[0] = i+1;
	            objs[1] = promotionMap.get("real_name");
	            objs[2] = promotionMap.get("username");
	            objs[3] = promotionMap.get("user_role_name");
	            objs[4] = promotionMap.get("user_type_name");
	            objs[5] = promotionMap.get("organization_channel_id_name");
	            objs[6] = promotionMap.get("organization_area_id_name");
	            objs[7] = promotionMap.get("parent_name");
	            objs[8] = promotionMap.get("parent_username");
	            objs[9] = promotionMap.get("invitation_code");
	            objs[10] = promotionMap.get("parent_real_name");
	            objs[11] = promotionMap.get("parent_user_role_name");
	            objs[12] = promotionMap.get("input_invitation_code");
	            String partner_time = null;
	            if(promotionMap.get("partner_time") != null){
	            	Date partnerTime=(Date) promotionMap.get("partner_time");
	            	partner_time = DateTimeUtil.getTimeNormalString(partnerTime);
	            }
	            objs[13] = partner_time;
	            String regist_time = null;
	            if(promotionMap.get("regist_time") != null){
	            	Date registTime=(Date) promotionMap.get("regist_time");
	            	regist_time = DateTimeUtil.getTimeNormalString(registTime);
	            }
	            objs[14] = regist_time;
	            objs[15] = promotionMap.get("user_status_name");
	            dataList.add(objs);
	        }
	        ExportExcel ex = new ExportExcel(title, rowsName, dataList);
	        ex.export(response);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
	}
	
	/**
	 * 
	 * Description: 查看邀请用户数据导出操作
	 *
	 * @param 
	 * @param request
	 * @param response
	 * @param session
	 * @Author haochunhe
	 * Create Date: 2017-12-8 上午11:32:54
	 */
	@RequestMapping(value="/exportInviteLoginUser")
	public void exportInviteLoginUser(HttpServletRequest request,HttpServletResponse response,HttpSession session,Integer userId) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userId);
			String user_id = StringUtil.trim(request.getParameter("user_id"));
			if (StringUtil.isNotBlank(user_id)) {
				map.put("userId", user_id);
			}
			String realName = StringUtil.trim(request.getParameter("realName"));
			if (StringUtil.isNotBlank(realName)) {
				map.put("realName", realName);
			}
			String username = StringUtil.trim(request.getParameter("username"));
			if (StringUtil.isNotBlank(username)) {
				map.put("username", username);
			}
			String userStatus = StringUtil.trim(request.getParameter("userStatus"));
			if (StringUtil.isNotBlank(userStatus)) {
				map.put("userStatus", userStatus);
			}
			String min_change_partner_time = StringUtil.trim(request.getParameter("min_change_partner_time"));
			if (StringUtil.isNotBlank(min_change_partner_time)) {
				map.put("min_change_partner_time", min_change_partner_time);
			}
			String max_change_partner_time = StringUtil.trim(request.getParameter("max_change_partner_time"));
			if (StringUtil.isNotBlank(max_change_partner_time)) {
				map.put("max_change_partner_time", max_change_partner_time);
			}
			List<Map<String, Object>> result = loginUserService.exportInviteLoginUser(map);
			String title = "用户数据信息"+DateTimeUtil.getDateNormalString(new Date());
	        String[] rowsName = new String[]{"序号","邀请人姓名","邀请人手机号","邀请用户注册合伙人平台时间","邀请用户状态"};
	        List<Object[]>  dataList = new ArrayList<Object[]>();
	        Object[] objs = null;
	        Map<String, Object> promotionMap = null;
	        for (int i = 0; i < result.size(); i++) {
	        	promotionMap = result.get(i);
	            objs = new Object[rowsName.length];
	            objs[0] = i+1;
	            objs[1] = promotionMap.get("real_name");
	            objs[2] = promotionMap.get("username");
	            String partner_time = null;
	            if(promotionMap.get("partner_time") != null){
	            	Date partnerTime=(Date) promotionMap.get("partner_time");
	            	partner_time = DateTimeUtil.getTimeNormalString(partnerTime);
	            }
	            objs[3] = partner_time;
	           // objs[3] = promotionMap.get("partner_time");
	            objs[4] = promotionMap.get("user_status_name");
	            dataList.add(objs);
	        }
	        ExportExcel ex = new ExportExcel(title, rowsName, dataList);
	        ex.export(response);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
	}
	
	/**
	 * 
	 * Description: 导出查看下级用户数据操作
	 * @param 
	 * @param request
	 * @param response
	 * @param session
	 * @Author haochunhe
	 * Create Date: 2017-12-9 上午11:32:54
	 */
	@RequestMapping(value="/exportLoginParterUser")
	public void exportLoginParterUser(HttpServletRequest request,HttpServletResponse response,HttpSession session,Integer userId,String user_type) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userId);
			String user_id = StringUtil.trim(request.getParameter("user_id"));
			String user_type1 = StringUtil.trim(request.getParameter("user_type"));
			if (StringUtil.isNotBlank(user_type1)) {
				user_type = user_type1;
			}
			if (StringUtil.isNotBlank(user_id)) {
				map.put("userId", user_id);
			}
			String realName = StringUtil.trim(request.getParameter("realName"));
			if (StringUtil.isNotBlank(realName)) {
				map.put("realName", realName);
			}
			String username = StringUtil.trim(request.getParameter("username"));
			if (StringUtil.isNotBlank(username)) {
				map.put("username", username);
			}
			String userRole = StringUtil.trim(request.getParameter("userRole"));
			if (StringUtil.isNotBlank(userRole)) {
				map.put("userRole", userRole);
			}
			String userStatus = StringUtil.trim(request.getParameter("userStatus"));
			if (StringUtil.isNotBlank(userStatus)) {
				map.put("userStatus", userStatus);
			}
			String inviteType = StringUtil.trim(request.getParameter("inviteType"));
			if (StringUtil.isNotBlank(inviteType)) {
				map.put("inviteType", inviteType);
			}
			String min_change_partner_time = StringUtil.trim(request.getParameter("min_change_partner_time"));
			if (StringUtil.isNotBlank(min_change_partner_time)) {
				map.put("min_change_partner_time", min_change_partner_time);
			}
			String max_change_partner_time = StringUtil.trim(request.getParameter("max_change_partner_time"));
			if (StringUtil.isNotBlank(max_change_partner_time)) {
				map.put("max_change_partner_time", max_change_partner_time);
			}
			List<Map<String, Object>> result = new ArrayList<>();
			if(Constants.USER_TYPE.equals(user_type)){//一般合伙人
				result = loginUserService.exportLoginPartnerUserList(map);
			}
			if(Constants.USER_TYPE_PARTNER.equals(user_type)){//团队管理者
				result = loginUserService.exportTeamLoginPartnerUserList(map);
			}
			String title = "用户数据信息"+DateTimeUtil.getDateNormalString(new Date());
			String[] rowsName = new String[]{"序号","下级用户姓名","下级用户手机号","下级用户角色","邀请方式","合伙人姓名","合伙人手机号","合伙人推荐码","下级用户注册合伙人平台时间","下级用户状态"};
			List<Object[]>  dataList = new ArrayList<Object[]>();
			Object[] objs = null;
			Map<String, Object> promotionMap = null;
			for (int i = 0; i < result.size(); i++) {
				promotionMap = result.get(i);
				objs = new Object[rowsName.length];
				objs[0] = i+1;
				objs[1] = promotionMap.get("real_name");
				objs[2] = promotionMap.get("username");
				objs[3] = promotionMap.get("user_role_name");
				String invite_type = (String) promotionMap.get("invite_type");
				if("1".equals(invite_type)){
					objs[4] = "本人邀请";
				}else{
					objs[4] = "下级邀请";
				}
				objs[5] = promotionMap.get("parent_real_name");
				objs[6] = promotionMap.get("parent_username");
				objs[7] = promotionMap.get("invitation_code");
				String partner_time = null;
				if(promotionMap.get("partner_time") != null){
					Date partnerTime=(Date) promotionMap.get("partner_time");
					partner_time = DateTimeUtil.getTimeNormalString(partnerTime);
				}
		        objs[8] = partner_time;
				//objs[8] = promotionMap.get("partner_time");
				objs[9] = promotionMap.get("user_status_name");
				dataList.add(objs);
			}
			ExportExcel ex = new ExportExcel(title, rowsName, dataList);
			ex.export(response);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
	}
	
	/**
	 * 
	 * Description: 根据邀请人推荐码查询用户信息
	 * @param partnerRecommendCode
	 * @return
	 * @Author haochunhe
	 * Create Date: 2017-12-9 下午3:32:54
	 */
	@ResponseBody
	@RequestMapping("/getUserByPartnerRecommendCodeOrUsername")
	public Map<String,Object> getUserByPartnerRecommendCodeOrUsername(String partnerRecommendCode) {
		Map<String,Object> result = new HashMap<>();
		try {
			result = loginUserService.getUserByPartnerRecommendCodeOrUsername(partnerRecommendCode);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return result;
	}
	
	/**
	 * 
	 * Description: 变更用户关系
	 *
	 * @param request
	 * @return DataMsg
	 * @throws 
	 * @Author haochunhe
	 * Create Date: 2017-12-9 下午4:32:54
	 */
	@ResponseBody
	@RequestMapping(value="/updataLoginUserRelation")
	public DataMsg updataLoginUserRelation(HttpServletRequest request,HttpSession session,DataMsg dataMsg){
		try {
			String userIds = StringUtil.trim(request.getParameter("user_ids"));
			String user_id = StringUtil.trim(request.getParameter("user_id"));
			if (StringUtil.isNotBlank(userIds) && StringUtil.isNotBlank(user_id)) {
				loginUserService.updataLoginUserRelation(user_id, userIds);
			}
			dataMsg.setMessageCode("0003");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			dataMsg.setMessageCode("0004");
		}
		return dataMsg;
	}
	
	/**
	 * 文件上传数据导入
	 * @param file
	 * @param request
	 * @param response
	 * @return map
	 *  hch
	 * 2017-12-19 11:44:43
	 * @throws Exception 
	 */
	/*@RequestMapping("/doUpload")
	@ResponseBody
	public Map<String,Object> dataUpload(MultipartFile file, HttpServletRequest request,HttpSession session){
		Map<String, Object> map = new HashMap<>();
		try {
			map = loginUserService.uploadLoginUser(file, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}*/
}
