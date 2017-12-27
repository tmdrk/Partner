package com.hui.zhong.cf.mobile.service;

import java.util.List;
import java.util.Map;

import com.hui.zhong.cf.mobile.model.PageModel;

public interface CommissionLiquidationService {
	/**
	 * 查询待结算月份列表
	 * @param paraMap
	 * @return
	 * @author zhoujie
	 * @date 2017年12月13日 上午10:40:50
	 */
	List<String> queryUnsettlementMonthList(Map<String,Object> paraMap);
	
	/**
	 * 查询待结算信息
	 * @param paraMap
	 * @return
	 * @author zhoujie
	 * @date 2017年12月13日 上午10:40:53
	 */
	Map<String, Object> queryUnsettlementInfo(Map<String, Object> paraMap);
	
	/**
	 * 分页查询已结算列表
	 * @param paraMap
	 * @return
	 * @author zhoujie
	 * @date 2017年12月14日 上午11:04:26
	 */
	PageModel getCommissionLiquidationList(Map<String,Object> paraMap);
	
	/**
	 * 分页查询出借奖金明细列表
	 * @param paraMap
	 * @return
	 * @author zhoujie
	 * @date 2017年12月14日 上午11:41:21
	 */
	PageModel getLoanBunusDetailList(Map<String,Object> paraMap);
	
	/**
	 * 分页查询差额奖金明细列表
	 * @param paraMap
	 * @return
	 * @author zhoujie
	 * @date 2017年12月14日 上午11:42:10
	 */
	PageModel getDiffBunusDetailList(Map<String,Object> paraMap);
	
	/**
	 * 分页查询服务奖金明细列表
	 * @param paraMap
	 * @return
	 * @author zhoujie
	 * @date 2017年12月14日 上午11:42:14
	 */
	PageModel getServiceBunusDetailList(Map<String,Object> paraMap);
	
	/**
	 * 结算统计
	 * @param userId 用户id
	 * @return map -->	unLiquidationAmount 待结算总额 
	 * 					liquidationAmount 已结算总额
	 * @author zhoujie
	 * @date 2017年12月15日 下午3:05:55
	 */
	Map<String, Object> commissionLiquidationStat(Integer userId);
	
	/**
	 * 查询出借奖金明细总数（包括我的和我的下级）
	 * @param paraMap
	 * @return map -->	myCount 我的 
	 * 					subCount 我的下级
	 * @author zhoujie
	 * @date 2017年12月19日 下午4:19:00
	 */
	Map<String, Object> getLoanBunusDetailCount(Map<String, Object> paraMap);
	
	/**
	 * 查询服务奖金明细总数（包括我的,我的下级和其他下级）
	 * @param paraMap
	 * @return map -->	myCount 我的 
	 * 					subCount 我的下级
	 * 					otherSubCount 其他下级
	 * @author zhoujie
	 * @date 2017年12月19日 下午4:19:06
	 */
	Map<String, Object> getServiceBunusDetailCount(Map<String, Object> paraMap);
	
	/**
	 * 查询已结算年份列表
	 * @param paraMap
	 * @return
	 * @author zhoujie
	 * @date 2017年12月21日 下午3:33:41
	 */
	List<String> querySettlementYearList(Map<String,Object> paraMap);
	
	/**
	 * 查询已结算列表
	 * @param paraMap
	 * @return
	 * @author zhoujie
	 * @date 2017年12月21日 下午3:51:35
	 */
	List<Map<String, Object>> queryCommissionLiquidationYearList(Map<String,Object> paraMap);
}
