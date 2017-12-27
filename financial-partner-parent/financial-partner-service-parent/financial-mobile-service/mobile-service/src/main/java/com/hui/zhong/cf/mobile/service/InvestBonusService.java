package com.hui.zhong.cf.mobile.service;

import java.math.BigDecimal;

public interface InvestBonusService {
	/**
	 * 根据合伙人user_id查询该合伙人本月的差额奖励。<br>
	 * 差额奖励 = 该合伙人的差额奖励 + 合伙人直接客户的差额奖励。<br>
	 * 领取差额奖励有一个前提：该合伙人的下级合伙人数量必须大于0。
	 * 
	 * @param partnerUserId
	 * @return
	 */
	BigDecimal getPartnerDiffBonusOfThisMonth(Integer partnerUserId);
	
	/**
	 * 根据合伙人user_id查询该合伙人本月的出借奖励。<br>
	 * 出借奖励 = 该合伙人的出借奖励 + 该合伙人直接客户带来的出借奖励 
	 * 			 + 该合伙人直接下级合伙人带来的出借奖励 + 该合伙人直接下级合伙人的客户带来的出借奖励
	 * 
	 * @param partnerUserId
	 * @return
	 */
	BigDecimal getPartnerInvestBonusOfThisMonth(Integer partnerUserId);
	
	/**
	 * 根据团队管理者user_id查询其本月的服务奖励。
	 * 
	 * @param partnerUserId
	 * @return
	 */
	BigDecimal getServiceBonusOfThisMonth(Integer partnerUserId);
}
