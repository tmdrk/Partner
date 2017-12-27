package com.hui.zhong.cf.mobile.dao;

import java.util.List;
import java.util.Map;

import com.hui.zhong.cf.mobile.model.Invest;
import org.apache.ibatis.annotations.Param;

public interface InvestMapper extends BaseMapper<Invest> {
	/**
	 *  查询合伙人本月的投资（出借）统计信息。
	 * 
	 * @param queryMap
	 * @return
	 */
	Map<String, Object> getPartnerInvestInfoOfThisMonth(Map<String, Object> queryMap);
	
	/**
	 * 查询合伙人自己的客户本月的投资（出借）统计信息。
	 * 
	 * @param queryMap
	 * @return
	 */
	Map<String, Object> getCustomerInvestInfoOfThisMonth(Map<String, Object> queryMap);
	
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
	Map<String, Object> getPartnerAddupInvestInfo(List<Integer> partnerUserIds);

	/**
	 * 查询给定的合伙人的客户的累计出借信息。
	 * 
	 * @param newArrayList
	 * @return
	 */
	Map<String, Object> getCustomerAddupInvestInfo(List<Integer> newArrayList);

	/**
	 * 查询订单列表
	 *
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> selectListByParam(Map<String, Object> param);
    /**
	 * 查询订单列表统计
	 *
	 * @param param
	 * @return
	 */
	Long selectListByParamCount(Map<String, Object> param);

	/**
	 *	查询订单详情
	 * @param investId
	 * @return
	 */
	Map<String, Object> selectInvest(@Param("investId") Integer investId,@Param("userId") Integer userId);

}
