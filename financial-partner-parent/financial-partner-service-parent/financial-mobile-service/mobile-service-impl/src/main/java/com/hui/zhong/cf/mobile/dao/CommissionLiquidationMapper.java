package com.hui.zhong.cf.mobile.dao;

import java.util.List;
import java.util.Map;

import com.hui.zhong.cf.mobile.model.CommissionLiquidation;


public interface CommissionLiquidationMapper extends BaseMapper<CommissionLiquidation>{
	/**
	 * 根据条件查询结算单列表
	 * @param paraMap
	 * @return
	 * @author zhoujie
	 * @date 2017年12月13日 上午10:31:28
	 */
	List<CommissionLiquidation> queryCommissionLiquidationList(Map<String, Object> paraMap);

	/**
	 * 查询月度累计金额
	 * @param paraMap
	 * @return
	 * @author zhoujie
	 * @date 2017年12月13日 上午10:57:17
	 */
	Map<String, Object> queryMonthAmount(Map<String, Object> paraMap);

	/**
	 * 查询下级月度累计金额
	 * @param paraMap
	 * @return
	 * @author zhoujie
	 * @date 2017年12月13日 上午11:03:39
	 */
	Map<String, Object> querySubMonthAmount(Map<String, Object> paraMap);

	/**
	 * 查询奖金配置列表
	 * @param paraMap
	 * @return
	 * @author zhoujie
	 * @date 2017年12月13日 上午11:12:14
	 */
	List<Map<String, Object>> queryBonusConfigList(Map<String, Object> paraMap);

	/**
	 * 查询其他下级月度累计金额(当前月)
	 * @param paraMap
	 * @return
	 * @author zhoujie
	 * @date 2017年12月13日 上午11:49:07
	 */
	Map<String, Object> queryOtherSubMonthAmount(Map<String, Object> paraMap);

	/**
	 * 查询其他下级月度累计金额(非当前月)
	 * @param paraMap
	 * @return
	 * @author zhoujie
	 * @date 2017年12月13日 下午12:44:05
	 */
	Map<String, Object> queryPreExpectBonusAmount(Map<String, Object> paraMap);

	/**
	 * 查询预计获得出借奖金(元)
	 * @param paraMap
	 * @return
	 * @author zhoujie
	 * @date 2017年12月13日 下午3:47:25
	 */
	Map<String, Object> queryExpectLoanBonusAmount(Map<String, Object> paraMap);

	/**
	 * 查询预计获得差额奖金(元)
	 * @param paraMap
	 * @return
	 * @author zhoujie
	 * @date 2017年12月13日 下午3:47:31
	 */
	Map<String, Object> queryExpectDiffBonusAmount(Map<String, Object> paraMap);

	/**
	 * 预计获得服务奖金(元)
	 * @param paraMap
	 * @return
	 * @author zhoujie
	 * @date 2017年12月13日 下午3:47:31
	 */
	Map<String, Object> queryExpectServiceBonusAmount(
			Map<String, Object> paraMap);

	/**
	 * 分页查询结算列表
	 * @param paraMap
	 * @return
	 * @author zhoujie
	 * @date 2017年12月14日 上午11:08:16
	 */
	List<Map<String, Object>> getCommissionLiquidationList(
			Map<String, Object> paraMap);
	/**
	 * 查询结算列表总数
	 * @param paraMap
	 * @return
	 * @author zhoujie
	 * @date 2017年12月14日 上午11:33:16
	 */
	Long getCommissionLiquidationCount(Map<String, Object> paraMap);

	/**
	 * 分页查询出借奖金明细列表
	 * @param paraMap
	 * @return
	 * @author zhoujie
	 * @date 2017年12月14日 上午11:50:19
	 */
	List<Map<String, Object>> getLoanBunusDetailList(Map<String, Object> paraMap);

	/**
	 * 查询出借奖金明细总数
	 * @param paraMap
	 * @return
	 * @author zhoujie
	 * @date 2017年12月14日 上午11:50:53
	 */
	Long getLoanBunusDetailCount(Map<String, Object> paraMap);

	/**
	 * 分页查询差额奖金明细列表
	 * @param paraMap
	 * @return
	 * @author zhoujie
	 * @date 2017年12月14日 下午3:12:55
	 */
	List<Map<String, Object>> getDiffBunusDetailList(Map<String, Object> paraMap);

	/**
	 * 查询差额奖金明细总数
	 * @param paraMap
	 * @return
	 * @author zhoujie
	 * @date 2017年12月14日 下午3:13:11
	 */
	Long getDiffBunusDetailCount(Map<String, Object> paraMap);

	/**
	 * 分页查询服务奖金明细列表
	 * @param paraMap
	 * @return
	 * @author zhoujie
	 * @date 2017年12月14日 下午3:13:06
	 */
	List<Map<String, Object>> getServiceBunusDetailList(
			Map<String, Object> paraMap);

	/**
	 * 查询服务奖金明细总数
	 * @param paraMap
	 * @return
	 * @author zhoujie
	 * @date 2017年12月14日 下午3:13:14
	 */
	Long getServiceBunusDetailCount(Map<String, Object> paraMap);
	
	/**
	 * 结算统计
	 * @param userId
	 * @return 
	 * @author zhoujie
	 * @date 2017年12月15日 下午3:01:04
	 */
	List<Map<String, Object>> commissionLiquidationStat(Integer userId);

	/**
	 * 按年查询佣金已结算列表
	 * @param paraMap
	 * @return
	 * @author zhoujie
	 * @date 2017年12月21日 下午4:00:38
	 */
	List<Map<String, Object>> queryCommissionLiquidationYearList(
			Map<String, Object> paraMap);
}
