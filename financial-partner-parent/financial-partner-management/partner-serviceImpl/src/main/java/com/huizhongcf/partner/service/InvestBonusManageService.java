package com.huizhongcf.partner.service;

import java.util.List;
import java.util.Map;

import com.huizhongcf.util.PageModel;
import com.huizhongcf.util.ReturnMsgData;

public interface InvestBonusManageService {
	/**
	 * 分页查询奖励单记录列表
	 * @param map
	 * @return PageModel
	 */	
	PageModel getInvestBonusList(Map<String, Object> paramsCondition);
	/**
	 * 查看订单详情
	 * @param map
	 * @return Map<String,Object>
	 */	
	Map<String,Object> getInvestDetail(Map<String, Object> paramsCondition);
	/**
	 * 导出奖励单记录列表
	 * @param map
	 * @return List<Map<String, Object>>
	 */	
	List<Map<String, Object>> exportInvestBonusList(Map<String, Object> paramsCondition);
	/**
	 * 奖励结算详情
	 * @param map
	 * @return PageModel
	 */	
	PageModel getCommissionLiquidationDetailList(Map<String, Object> paramsCondition);
	/**
	 * 导出奖励结算详情列表
	 * @param map
	 * @return List<Map<String, Object>>
	 */	
	List<Map<String, Object>> exportCommissionLiquidationDetailList(Map<String, Object> paramsCondition);
}
