package com.huizhongcf.partner.dao;

import java.util.List;
import java.util.Map;

import com.huizhongcf.partner.model.CommissionLiquidation;

public interface CommissionLiquidationMapper extends BaseMapper<CommissionLiquidation>{
	/**
	 * 分页查询结算单列表
	 * @param paramsCondition
	 * @return Map
	 */	
	List<Map<String, Object>> getCommissionLiquidationList(Map<String, Object> paramsCondition);
	/**
	 * 分页查询结算单列表总数
	 * @param map
	 * @returnPageModel
	 */	
	Long getCommissionLiquidationCount(Map<String, Object> paramsCondition);
	
	/**
	 * 
	 * Description: 理财佣金待结算
	 * @param commission_fee_id 
	 *
	 * @param 
	 * @return Map<String,Object>
	 * @throws 
	 * @Author lixiaoshuai
	 * Create Date: 2017年12月9日 15:40:58
	 */
	int updateCommissionFeePay(Map<String, Object> requestMap);
	
	/**
	 * 
	 * Description: 理财佣金批量结算
	 *
	 * @param 
	 * @return int
	 * @throws 
	 * @Author lixiaoshuai
	 * Create Date: 2017年12月9日 15:41:08
	 */
	int updateCommissionFeePayMany(CommissionLiquidation settle);
	/**
	 * 导出结算单列表
	 * @param paramsCondition
	 * @return Map
	 */	
	List<Map<String, Object>> exportCommissionLiquidationList(Map<String, Object> paramsCondition);
	/**
	 * 根据ID查询结算单
	 * @param paramsCondition
	 * @return Map
	 */	
	Map<String, Object> getCommissionLiquidationById(Integer commissionLiquidationId);
}