package com.hui.zhong.cf.mobile.dao;

import java.math.BigDecimal;
import java.util.Map;

import com.hui.zhong.cf.mobile.model.InvestBonus;

public interface InvestBonusMapper extends BaseMapper<InvestBonus> {
	/**
	 * 根据合伙人user_id查询该合伙人本月的差额奖励。<br>
	 * 差额奖励 = 该合伙人的差额奖励 + 合伙人直接客户的差额奖励
	 * 
	 * @param queryMap
	 * @return
	 */
	BigDecimal getPartnerDiffBonusOfThisMonth(Map<String, Object> queryMap);

	/**
	 * 根据合伙人user_id查询该合伙人本月的出借奖励。<br>
	 * 出借奖励 = 该合伙人的出借奖励 + 该合伙人直接客户带来的出借奖励 
	 * 			 + 该合伙人直接下级合伙人带来的出借奖励 + 该合伙人直接下级合伙人的客户带来的出借奖励
	 * 
	 * @param queryMap
	 * @return
	 */
	BigDecimal getPartnerInvestBonusOfThisMonth(Map<String, Object> queryMap);

	/**
	 * 根据团队管理者user_id查询其本月的服务奖励。
	 * 
	 * @param queryMap
	 * @return
	 */
	BigDecimal getServiceBonusOfThisMonth(Map<String, Object> queryMap);
}
