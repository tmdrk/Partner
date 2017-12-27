package com.hui.zhong.cf.mobile.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hui.zhong.cf.mobile.dao.InvestBonusMapper;
import com.hui.zhong.cf.mobile.dao.LoginUserMapper;
import com.hui.zhong.cf.mobile.service.InvestBonusService;
import com.huizhongcf.util.DateUtil;

@Service
public class InvestBonusServiceImpl implements InvestBonusService {
	@Autowired
	private InvestBonusMapper investBonusMapper;
	@Autowired
	private LoginUserMapper loginUserMapper;
	
	@Override
	public BigDecimal getPartnerDiffBonusOfThisMonth(Integer partnerUserId) {
		List<Integer> subPartnerUserIdList = loginUserMapper.getDirectSubordinatePartnerUserIds(partnerUserId);
		if (CollectionUtils.isEmpty(subPartnerUserIdList)) {
			return BigDecimal.ZERO;
		}
		
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("partnerUserId", partnerUserId);
		try {
			queryMap.put("monthBegin", DateUtil.getCurrentMonthFirstDayDate());// 当月第一天
		} catch (Exception e) {
			// not handle
		}
		
		BigDecimal diffBonus = investBonusMapper.getPartnerDiffBonusOfThisMonth(queryMap);
		return diffBonus == null ? BigDecimal.ZERO : diffBonus;
	}

	@Override
	public BigDecimal getPartnerInvestBonusOfThisMonth(Integer partnerUserId) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("partnerUserId", partnerUserId);
		try {
			queryMap.put("monthBegin", DateUtil.getCurrentMonthFirstDayDate());// 当月第一天
		} catch (Exception e) {
			// not handle
		}
		
		BigDecimal investBonus = investBonusMapper.getPartnerInvestBonusOfThisMonth(queryMap);
		return investBonus == null ? BigDecimal.ZERO : investBonus;
	}

	@Override
	public BigDecimal getServiceBonusOfThisMonth(Integer partnerUserId) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("partnerUserId", partnerUserId);
		try {
			queryMap.put("monthBegin", DateUtil.getCurrentMonthFirstDayDate());// 当月第一天
		} catch (Exception e) {
			// not handle
		}
		
		BigDecimal serviceBonus = investBonusMapper.getServiceBonusOfThisMonth(queryMap);
		return serviceBonus == null ? BigDecimal.ZERO : serviceBonus;
	}
}
