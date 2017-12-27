package com.huizhongcf.partner.service;

import java.util.List;
import java.util.Map;

import com.huizhongcf.util.PageModel;
import com.huizhongcf.util.ReturnMsgData;

public interface CommissionLiquidationService {
	/**
	 * 分页结算单记录列表
	 * @param map
	 * @returnPageModel
	 */	
	PageModel getCommissionLiquidationList(Map<String, Object> map);
	
	/**
	 * 
	 * Description: 结算单结算
	 * @param commission_fee_id 
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author lixiaoshuai
	 * Create Date: 2017年12月9日 15:37:33
	 */
	int updateCommissionFeePay(Map<String, Object> requestMap);
	/**
	 * 
	 * Description: 结算单批量结算
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author lixiaoshuai
	 * Create Date: 2017年12月9日 15:37:33
	 */
	String commissionFeePayMany(String body);
	/**
	 * 
	 * Description: 导出结算单列表
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author lixiaoshuai
	 * Create Date: 2017年12月9日 15:37:33
	 */
	List<Map<String, Object>> exportCommissionLiquidationList(Map<String, Object> paramsCondition);
	/**
	 * 
	 * Description: 根据ID查询结算单
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author lixiaoshuai
	 * Create Date: 2017年12月9日 15:37:33
	 */
	Map<String, Object> getCommissionLiquidationById(Integer commissionLiquidationId);
}
