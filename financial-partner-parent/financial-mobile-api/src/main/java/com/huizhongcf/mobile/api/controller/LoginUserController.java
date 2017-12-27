package com.huizhongcf.mobile.api.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hui.zhong.cf.mobile.model.LoginUser;
import com.hui.zhong.cf.mobile.model.PageModel;
import com.hui.zhong.cf.mobile.service.LoginUserService;
import com.huizhongcf.mobile.api.Interceptor.AccessRequired;
import com.huizhongcf.mobile.api.framework.BaseController;
import com.huizhongcf.util.DateTimeUtil;
import com.huizhongcf.util.MaskUtils;
import com.huizhongcf.util.MathUtil;

/**
 * 处理登录用户相关的操作。
 * 
 * @author zhangfei
 * @date 2017年12月8日
 */
@Controller
@RequestMapping("/loginUser")
public class LoginUserController extends BaseController {
	
	@Autowired
	private LoginUserService loginUserService;
	
	/**
	 * 用户-一般合伙人-我的客户，用户-团队管理者-我的客户。<br>
	 * 我的客户：该合伙人自己发展的客户。
	 * @return
	 */
	@RequestMapping("/myCustomer")
	@ResponseBody
	@AccessRequired(login=true)
	public Object myCustomer() {
		Map<String, Object> queryMap = getQueryMap();
		// 校验参数
		Integer pageNo = MapUtils.getInteger(queryMap, "pageNo");
		Integer pageSize = MapUtils.getInteger(queryMap, "pageSize");
		if (pageNo == null || pageSize == null || 
				pageNo.intValue() < 1 || pageSize.intValue() < 1) {
			return renderError("10000", "参数错误");
		}
		
		Map<String, Object> body = new HashMap<String, Object>();
		
		LoginUser loginUser = (LoginUser) queryMap.get("loginUser");
		// 判断用户类型
		String userType = null;
		if ("20".equals(loginUser.getUserType())) {// 团队管理者
			userType = "20";
		} else if ("20".equals(loginUser.getUserRole())) {// 客户
			userType = "00";
		} else {// 一般合伙人
			userType = "10";
		}
		body.put("userType", userType);
		queryMap.remove("loginUser");
		
		if ("00".equals(userType)) {
			body.put("myCustomerNum", 0);// 我的客户数
			body.put("mySubordinateNum", 0);// 我的下级数
			body.put("otherSubordinateNum", 0);// 其他下级数
			body.put("customerList", Collections.EMPTY_LIST);// 客户列表
			body.put("pageNo", pageNo);// 当前页
			body.put("nextPageNo", pageNo.intValue() + 1);// 下一页
			body.put("pageSize", pageSize);// 每页条数
			body.put("totalRecords", 0);// 总条数
			body.put("totalPages", 1);// 总页数
			return renderSuccess(body);
		}
		
		Integer partnerUserId = loginUser.getUserId();// 当前登录合伙人的user_id
		// 根据查询条件查询该合伙人的客户列表，包含投资统计信息
		PageModel customerPM = loginUserService.getMyCustomerWithInvestInfo(queryMap);
		body.put("myCustomerNum", loginUserService.getDirectCustomerCount(partnerUserId));// 我的客户数
		body.put("customerList", formatRetList(customerPM.getList()));// 客户列表
		body.put("pageNo", pageNo);// 当前页
		body.put("nextPageNo", pageNo.intValue() + 1);// 下一页
		body.put("pageSize", pageSize);// 每页条数
		body.put("totalRecords", customerPM.getTotalRecords());// 总条数
		body.put("totalPages", customerPM.getTotalPages());// 总页数
		
		// 该合伙人的所有下级合伙人user_id
		List<Integer> subPartnerUserIdList = loginUserService.getDirectSubordinatePartnerUserIds(partnerUserId);
		int subPartnerNum = CollectionUtils.isEmpty(subPartnerUserIdList) ? 0 : subPartnerUserIdList.size();
		if (subPartnerNum == 0) {
			body.put("mySubordinateNum", 0);// 我的下级数
		} else {
			Integer[] subPartnerUserIdArr = subPartnerUserIdList.toArray(new Integer[subPartnerNum]);
			Long subPartnerCustomerNum = loginUserService.getDirectCustomerCount(subPartnerUserIdArr);
			body.put("mySubordinateNum", subPartnerNum + subPartnerCustomerNum.intValue());// 我的下级数
		}
		
		if (!"20".equals(userType)) {// 不是团队管理者
			body.put("otherSubordinateNum", 0);// 其他下级数
		} else {// 团队管理者
			// 该合伙人的所有其他合伙人user_id
			List<Integer> otherSubPartnerUserIdList = loginUserService.getOtherSubordinatePartnerUserIds(partnerUserId);
			int otherSubPartnerNum = CollectionUtils.isEmpty(otherSubPartnerUserIdList) ? 0 : otherSubPartnerUserIdList.size();
			if (otherSubPartnerNum == 0) {
				body.put("otherSubordinateNum", 0);// 其他下级数
			} else {
				Integer[] otherSubPartnerUserIdArr = otherSubPartnerUserIdList.toArray(new Integer[otherSubPartnerNum]);
				Long otherSubPartnerCustomerNum = loginUserService.getDirectCustomerCount(otherSubPartnerUserIdArr);
				body.put("otherSubordinateNum", otherSubPartnerNum + otherSubPartnerCustomerNum.intValue());// 其他下级数
			}
		}
		
		return renderSuccess(body);
	}
	
	
	/**
	 * 用户-一般合伙人-我的下级-合伙人，用户-团队管理者-我的下级-合伙人。<br>
	 * 
	 * @return
	 */
	@RequestMapping("/mySubordinate/partner")
	@ResponseBody
	@AccessRequired(login=true)
	public Object mySubordinatePartner() {
		Map<String, Object> queryMap = getQueryMap();
		// 校验参数
		Integer pageNo = MapUtils.getInteger(queryMap, "pageNo");
		Integer pageSize = MapUtils.getInteger(queryMap, "pageSize");
		if (pageNo == null || pageSize == null || 
				pageNo.intValue() < 1 || pageSize.intValue() < 1) {
			return renderError("10000", "参数错误");
		}
		
		Map<String, Object> body = new HashMap<String, Object>();
		Integer partnerUserId = MapUtils.getInteger(queryMap, "partnerUserId");
		
		// 该合伙人的所有下级合伙人user_id
		List<Integer> subPartnerUserIdList = loginUserService.getDirectSubordinatePartnerUserIds(partnerUserId);
		if (CollectionUtils.isEmpty(subPartnerUserIdList)) {
			body.put("mySubordinateNum", 0);// 下级总数量
			body.put("partnerNum", 0);// 合伙人数量
			body.put("partnerCustomerNum", 0);// 合伙人客户数量
			body.put("partnerList", Collections.EMPTY_LIST);// 合伙人列表
			body.put("pageNo", pageNo);// 当前页
			body.put("nextPageNo", pageNo.intValue() + 1);// 下一页
			body.put("pageSize", pageSize);// 每页条数
			body.put("totalRecords", 0);// 总条数
			body.put("totalPages", 1);// 总页数
		} else {
			// 根据查询条件查询下级合伙人
			PageModel subPartnerPM = loginUserService.getDirectSubordinatePartnerWithInvestInfo(queryMap);
			List<Map<String, Object>> subPartnerList = subPartnerPM.getList();
			
			Integer[] subPartnerUserIdArr = subPartnerUserIdList.toArray(new Integer[subPartnerUserIdList.size()]);
			Long subPartnerCustomerNum = loginUserService.getDirectCustomerCount(subPartnerUserIdArr);
			body.put("mySubordinateNum", subPartnerUserIdList.size() + subPartnerCustomerNum.intValue());// 下级总数量
			body.put("partnerNum", subPartnerUserIdList.size());// 合伙人数量
			body.put("partnerCustomerNum", subPartnerCustomerNum);// 合伙人客户数量
			body.put("partnerList", formatRetList(subPartnerList));// 合伙人列表
			body.put("pageNo", pageNo);// 当前页
			body.put("nextPageNo", pageNo.intValue() + 1);// 下一页
			body.put("pageSize", pageSize);// 每页条数
			body.put("totalRecords", subPartnerPM.getTotalRecords());// 总条数
			body.put("totalPages", subPartnerPM.getTotalPages());// 总页数
		}
		
		return renderSuccess(body);
	}
	
	/**
	 * 用户-一般合伙人-我的下级-合伙人客户，用户-团队管理者-我的下级-合伙人客户。<br>
	 * 
	 * @return
	 */
	@RequestMapping("/mySubordinate/partnerCustomer")
	@ResponseBody
	@AccessRequired(login=true)
	public Object mySubordinatePartnerCustomer() {
		Map<String, Object> queryMap = getQueryMap();
		// 校验参数
		Integer pageNo = MapUtils.getInteger(queryMap, "pageNo");
		Integer pageSize = MapUtils.getInteger(queryMap, "pageSize");
		if (pageNo == null || pageSize == null || 
				pageNo.intValue() < 1 || pageSize.intValue() < 1) {
			return renderError("10000", "参数错误");
		}
		
		Map<String, Object> body = new HashMap<String, Object>();
		Integer partnerUserId = MapUtils.getInteger(queryMap, "partnerUserId");
		
		// 该合伙人的所有下级合伙人user_id
		List<Integer> subPartnerUserIdList = loginUserService.getDirectSubordinatePartnerUserIds(partnerUserId);
		if (CollectionUtils.isEmpty(subPartnerUserIdList)) {
			body.put("mySubordinateNum", 0);// 下级总数量
			body.put("partnerNum", 0);// 合伙人数量
			body.put("partnerCustomerNum", 0);// 合伙人客户数量
			body.put("partnerCustomerList", Collections.EMPTY_LIST);// 合伙人客户列表
			body.put("pageNo", pageNo);// 当前页
			body.put("nextPageNo", pageNo.intValue() + 1);// 下一页
			body.put("pageSize", pageSize);// 每页条数
			body.put("totalRecords", 0);// 总条数
			body.put("totalPages", 1);// 总页数
		} else {
			Integer[] subPartnerUserIdArr = subPartnerUserIdList.toArray(new Integer[subPartnerUserIdList.size()]);
			// 该合伙人的所有下级合伙人的客户总数
			Long subPartnerCustomerNum = loginUserService.getDirectCustomerCount(subPartnerUserIdArr);
			
			queryMap.put("partnerUserIdList", subPartnerUserIdList);
			queryMap.remove("partnerUserId");
			PageModel subPartnerCustomerPM = loginUserService.getMyCustomerWithInvestInfo(queryMap);
			List<Map<String, Object>> subPartnerCustomerList = subPartnerCustomerPM.getList();
			
			body.put("mySubordinateNum", subPartnerUserIdList.size() + subPartnerCustomerNum.intValue());// 下级总数量
			body.put("partnerNum", subPartnerUserIdList.size());// 合伙人数量
			body.put("partnerCustomerNum", subPartnerCustomerNum);// 合伙人客户数量
			body.put("partnerCustomerList", formatRetListWithMask(subPartnerCustomerList));// 合伙人客户列表
			body.put("pageNo", pageNo);// 当前页
			body.put("nextPageNo", pageNo.intValue() + 1);// 下一页
			body.put("pageSize", pageSize);// 每页条数
			body.put("totalRecords", subPartnerCustomerPM.getTotalRecords());// 总条数
			body.put("totalPages", subPartnerCustomerPM.getTotalPages());// 总页数
		}
		
		return renderSuccess(body);
	}
	
	/**
	 * 用户-团队管理者-其他下级-合伙人。<br>
	 * 
	 * @return
	 */
	@RequestMapping("/otherSubordinate/partner")
	@ResponseBody
	@AccessRequired(login=true)
	public Object otherSubordinatePartner() {
		Map<String, Object> queryMap = getQueryMap();
		// 校验参数
		Integer pageNo = MapUtils.getInteger(queryMap, "pageNo");
		Integer pageSize = MapUtils.getInteger(queryMap, "pageSize");
		if (pageNo == null || pageSize == null || 
				pageNo.intValue() < 1 || pageSize.intValue() < 1) {
			return renderError("10000", "参数错误");
		}
		
		Map<String, Object> body = new HashMap<String, Object>();
		Integer partnerUserId = MapUtils.getInteger(queryMap, "partnerUserId");
		
		// 该合伙人的所有其他下级合伙人user_id
		List<Integer> otherSubPartnerUserIdList = loginUserService.getOtherSubordinatePartnerUserIds(partnerUserId);
		if (CollectionUtils.isEmpty(otherSubPartnerUserIdList)) {
			body.put("otherSubordinateNum", 0);// 其他下级总数量
			body.put("partnerNum", 0);// 合伙人数量
			body.put("partnerCustomerNum", 0);// 合伙人客户数量
			body.put("partnerList", Collections.EMPTY_LIST);// 合伙人列表
			body.put("pageNo", pageNo);// 当前页
			body.put("nextPageNo", pageNo.intValue() + 1);// 下一页
			body.put("pageSize", pageSize);// 每页条数
			body.put("totalRecords", 0);// 总条数
			body.put("totalPages", 1);// 总页数
		} else {
			// 根据查询条件查询其他下级合伙人
			PageModel otherSubPartnerPM = loginUserService.getOtherSubordinatePartnerWithInvestInfo(queryMap);
			List<Map<String, Object>> otherSubPartnerList = otherSubPartnerPM.getList();
			
			Integer[] otherSubPartnerUserIdArr = otherSubPartnerUserIdList.toArray(new Integer[otherSubPartnerUserIdList.size()]);
			// 该合伙人的所有其他下级合伙人的客户总数
			Long otherSubPartnerCustomerNum = loginUserService.getDirectCustomerCount(otherSubPartnerUserIdArr);
			body.put("otherSubordinateNum", otherSubPartnerUserIdList.size() + otherSubPartnerCustomerNum.intValue());// 下级总数量
			body.put("partnerNum", otherSubPartnerUserIdList.size());// 其他合伙人数量
			body.put("partnerCustomerNum", otherSubPartnerCustomerNum);// 其他合伙人客户数量
			body.put("partnerList", formatRetListWithMask(otherSubPartnerList));// 其他合伙人列表
			body.put("pageNo", pageNo);// 当前页
			body.put("nextPageNo", pageNo.intValue() + 1);// 下一页
			body.put("pageSize", pageSize);// 每页条数
			body.put("totalRecords", otherSubPartnerPM.getTotalRecords());// 总条数
			body.put("totalPages", otherSubPartnerPM.getTotalPages());// 总页数
		}
		
		return renderSuccess(body);
	}
	
	/**
	 * 用户-团队管理者-其他下级-合伙人客户。
	 * 
	 * @return
	 */
	@RequestMapping("/otherSubordinate/partnerCustomer")
	@ResponseBody
	@AccessRequired(login=true)
	public Object otherSubordinatePartnerCustomer() {
		Map<String, Object> queryMap = getQueryMap();
		// 校验参数
		Integer pageNo = MapUtils.getInteger(queryMap, "pageNo");
		Integer pageSize = MapUtils.getInteger(queryMap, "pageSize");
		if (pageNo == null || pageSize == null || 
				pageNo.intValue() < 1 || pageSize.intValue() < 1) {
			return renderError("10000", "参数错误");
		}
		
		Map<String, Object> body = new HashMap<String, Object>();
		Integer partnerUserId = MapUtils.getInteger(queryMap, "partnerUserId");
		
		// 该合伙人的所有其他下级合伙人user_id
		List<Integer> otherSubPartnerUserIdList = loginUserService.getOtherSubordinatePartnerUserIds(partnerUserId);
		if (CollectionUtils.isEmpty(otherSubPartnerUserIdList)) {
			body.put("otherSubordinateNum", 0);// 下级总数量
			body.put("partnerNum", 0);// 合伙人数量
			body.put("partnerCustomerNum", 0);// 合伙人客户数量
			body.put("partnerCustomerList", Collections.EMPTY_LIST);// 合伙人客户列表
			body.put("pageNo", pageNo);// 当前页
			body.put("nextPageNo", pageNo.intValue() + 1);// 下一页
			body.put("pageSize", pageSize);// 每页条数
			body.put("totalRecords", 0);// 总条数
			body.put("totalPages", 1);// 总页数
		} else {
			Integer[] otherSubPartnerUserIdArr = otherSubPartnerUserIdList.toArray(new Integer[otherSubPartnerUserIdList.size()]);
			// 该合伙人的所有其他下级合伙人的客户总数
			Long otherSubPartnerCustomerNum = loginUserService.getDirectCustomerCount(otherSubPartnerUserIdArr);
			
			queryMap.put("partnerUserIdList", otherSubPartnerUserIdList);
			queryMap.remove("partnerUserId");
			PageModel otherSubPartnerCustomerPM = loginUserService.getMyCustomerWithInvestInfo(queryMap);
			List<Map<String, Object>> otherSubPartnerCustomerList = otherSubPartnerCustomerPM.getList();
			
			body.put("otherSubordinateNum", otherSubPartnerUserIdList.size() + otherSubPartnerCustomerNum.intValue());// 下级总数量
			body.put("partnerNum", otherSubPartnerUserIdList.size());// 合伙人数量
			body.put("partnerCustomerNum", otherSubPartnerCustomerNum);// 合伙人客户数量
			body.put("partnerCustomerList", formatRetListWithMask(otherSubPartnerCustomerList));// 合伙人客户列表
			body.put("pageNo", pageNo);// 当前页
			body.put("nextPageNo", pageNo.intValue() + 1);// 下一页
			body.put("pageSize", pageSize);// 每页条数
			body.put("totalRecords", otherSubPartnerCustomerPM.getTotalRecords());// 总条数
			body.put("totalPages", otherSubPartnerCustomerPM.getTotalPages());// 总页数
		}
		
		return renderSuccess(body);
	}
	
	/**
	 * 从请求中获取查询条件。
	 * 
	 * @return
	 */
	private Map<String, Object> getQueryMap() {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		
		HttpServletRequest request = getRequest();
		LoginUser loginUser = getSession();
		Integer partnerUserId = loginUser.getUserId();// 当前登录用户id
		String queryCondition = request.getParameter("queryCondition");// 查询条件，手机号或者姓名
		
		queryMap.put("loginUser", loginUser);
		queryMap.put("partnerUserId", partnerUserId);
		if (StringUtils.isNotEmpty(queryCondition)) {
			queryMap.put("username", queryCondition);// t_login_user表的username字段
			queryMap.put("realName", queryCondition);// t_user_info表的real_name字段
		}
		queryMap.put("pageNo", request.getParameter("pageNo"));// 当前页码
		queryMap.put("pageSize", request.getParameter("pageSize"));// 每页条数
		return queryMap;
	}
	
	/**
	 * 对返回的List列表的一些字段进行格式化。
	 * 
	 * @param list
	 */
	private List<Map<String, Object>> formatRetList(List<Map<String, Object>> list) {
		if (list == null) {
			return Collections.emptyList();
		}
		if (CollectionUtils.isNotEmpty(list)) {
			for (Map<String, Object> map : list) {
				// 出借总额
				double totalInvestAmount = MapUtils.getDoubleValue(map, "totalInvestAmount", 0d);
				// 邀请时间
				Timestamp invitationTime = (Timestamp) MapUtils.getObject(map, "invitationTime");
				
				map.put("totalInvestAmount", MathUtil.formatDecimal(new BigDecimal(totalInvestAmount)));
				map.put("invitationTime", invitationTime == null ? "" : DateTimeUtil.getTimeNormalSprit(invitationTime));
			}
		}
		return list;
	}
	
	/**
	 * 对返回的List列表的一些字段进行格式化，同时对某些字段的值进行脱敏。
	 * 
	 * @param list
	 */
	private List<Map<String, Object>> formatRetListWithMask(List<Map<String, Object>> list) {
		if (list == null) {
			return Collections.emptyList();
		}
		if (CollectionUtils.isNotEmpty(list)) {
			for (Map<String, Object> map : list) {
				// 姓名
				String realName = MapUtils.getString(map, "realName", "");
				// 出借总额
				double totalInvestAmount = MapUtils.getDoubleValue(map, "totalInvestAmount", 0d);
				// 邀请时间
				Timestamp invitationTime = (Timestamp) MapUtils.getObject(map, "invitationTime");
				// 手机号
				String mobile = MapUtils.getString(map, "mobile", "");
				
				map.put("realName", MaskUtils.maskName(realName));
				map.put("totalInvestAmount", MathUtil.formatDecimal(new BigDecimal(totalInvestAmount)));
				map.put("invitationTime", invitationTime == null ? "" : DateTimeUtil.getTimeNormalSprit(invitationTime));
				map.put("mobile", MaskUtils.maskMobile(mobile));
			}
		}
		return list;
	}
}
