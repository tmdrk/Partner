package com.huizhongcf.partner.dao;

import java.util.List;
import java.util.Map;

import com.huizhongcf.partner.model.CommissionLiquidation;
import com.huizhongcf.partner.model.InvestBonus;

public interface InvestBonusMapper extends BaseMapper<InvestBonus>{
	/**
	 * 分页查询奖励单记录列表
	 * @param paramsCondition
	 * @return Map
	 */	
	List<Map<String, Object>> getInvestBonusList(Map<String, Object> paramsCondition);
	/**
	 * 分页查询奖励单记录列表总数
	 * @param map
	 * @returnPageModel
	 */	
	Long getInvestBonusCount(Map<String, Object> paramsCondition);
	/**
	 * 导出奖励单记录列表
	 * @param paramsCondition
	 * @return Map
	 */	
	List<Map<String, Object>> exportInvestBonusList(Map<String, Object> paramsCondition);
	/**
	 * 奖励结算详情
	 * @param paramsCondition
	 * @return Map
	 */	
	List<Map<String, Object>> getCommissionLiquidationDetailList(Map<String, Object> paramsCondition);
	/**
	 * 奖励结算详情总条数
	 * @param map
	 * @returnPageModel
	 */	
	Long getCommissionLiquidationDetailCount(Map<String, Object> paramsCondition);
	/**
	 * 导出奖励结算详情
	 * @param paramsCondition
	 * @return Map
	 */	
	List<Map<String, Object>> exportCommissionLiquidationDetailList(Map<String, Object> paramsCondition);

}