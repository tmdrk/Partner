package com.hui.zhong.cf.mobile.service;

import com.hui.zhong.cf.mobile.model.PageModel;

import java.util.Map;

public interface InvestService {
	/**
	 * 查询合伙人本月的投资（出借）统计信息。
	 * 
	 * @param partnerUserIds
	 * @return
	 */
	Map<String, Object> getPartnerInvestInfoOfThisMonth(Integer... partnerUserIds);
	
	/**
	 * 查询合伙人自己的客户本月的投资（出借）统计信息。
	 * 
	 * @param partnerUserIds
	 * @return
	 */
	Map<String, Object> getCustomerInvestInfoOfThisMonth(Integer... partnerUserIds);
	
	/**
	 * 查询合伙人累计出借统计信息。<br>
	 * 累计出借=(从该用户注册开始截止到当前时间)该合伙人自己出借总额
	 * 			+ 该合伙人自己的客户（包含客户邀请的客户）出借总额
	 * 
	 * @param partnerUserId
	 * @return
	 */
	Map<String, Object> getAddupInvestInfo(Integer partnerUserId);
	
	/**
	 * 查询给定的合伙人的累计出借信息。
	 * 
	 * @param partnerUserIds
	 * @return
	 */
	Map<String, Object> getPartnerAddupInvestInfo(Integer... partnerUserIds);
	
	/**
	 * 查询给定的合伙人的客户的累计出借信息。
	 * 
	 * @param partnerUserIds
	 * @return
	 */
	Map<String, Object> getCustomerAddupInvestInfo(Integer... partnerUserIds);

	PageModel selectInvestListByParam(Map<String, Object> paramMap);

	Map<String,Object> selectInvest(Integer investId, Integer userId);

	Map<String, Object> selectStatisticsByParam(Map<String, Object> paramMap);
}
