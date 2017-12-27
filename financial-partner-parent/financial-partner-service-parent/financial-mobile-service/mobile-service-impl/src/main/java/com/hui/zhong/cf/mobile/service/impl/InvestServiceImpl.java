package com.hui.zhong.cf.mobile.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hui.zhong.cf.mobile.constants.Constants;
import com.hui.zhong.cf.mobile.model.PageModel;
import com.huizhongcf.util.MoneyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.inject.internal.Lists;
import com.hui.zhong.cf.mobile.dao.InvestMapper;
import com.hui.zhong.cf.mobile.service.InvestService;
import com.huizhongcf.util.DateUtil;

@Service
public class InvestServiceImpl implements InvestService {
	@Autowired
	private InvestMapper investMapper;

    private Logger logger = LoggerFactory.getLogger(InvestServiceImpl.class);
	@Override
	public Map<String, Object> getPartnerInvestInfoOfThisMonth(Integer... partnerUserIds) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		List<Integer> partnerUserIdList = Lists.newArrayList(partnerUserIds);
		queryMap.put("partnerUserIdList", partnerUserIdList);// 合伙人user_id列表
		try {
			queryMap.put("monthBegin", DateUtil.getCurrentMonthFirstDayDate());// 当月第一天
		} catch (Exception e) {
			// not handle
		}
		
		return investMapper.getPartnerInvestInfoOfThisMonth(queryMap);
	}

	@Override
	public Map<String, Object> getCustomerInvestInfoOfThisMonth(Integer... partnerUserIds) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		List<Integer> partnerUserIdList = Lists.newArrayList(partnerUserIds);
		queryMap.put("partnerUserIdList", partnerUserIdList);// 合伙人user_id列表
		try {
			queryMap.put("monthBegin", DateUtil.getCurrentMonthFirstDayDate());// 当月第一天
		} catch (Exception e) {
			// not handle
		}
		
		return investMapper.getCustomerInvestInfoOfThisMonth(queryMap);
	}

	@Override
	public Map<String, Object> getAddupInvestInfo(Integer partnerUserId) {
		return investMapper.getAddupInvestInfo(partnerUserId);
	}

	@Override
	public Map<String, Object> getPartnerAddupInvestInfo(Integer... partnerUserIds) {
		return investMapper.getPartnerAddupInvestInfo(Lists.newArrayList(partnerUserIds));
	}

	@Override
	public Map<String, Object> getCustomerAddupInvestInfo(Integer... partnerUserIds) {
		return investMapper.getCustomerAddupInvestInfo(Lists.newArrayList(partnerUserIds));
	}

    @Override
    public PageModel selectInvestListByParam(Map<String, Object> paramMap) {
        logger.info("分页查询出借列表开始...");
        PageModel pageModel = new PageModel();
        pageModel.setPageNo((Integer) paramMap.get("pageNo"));
        pageModel.setPageSize((Integer) paramMap.get("pageSize"));
        paramMap.put("startIndex", pageModel.getStartIndex());
        paramMap.put("endIndex", pageModel.getEndIndex());
        List<Map<String,Object>> retList = investMapper.selectListByParam(paramMap);
        Long totalRecords = investMapper.selectListByParamCount(paramMap);
        pageModel.setList(retList);
        pageModel.setTotalRecords(totalRecords);
        return pageModel;
    }

    @Override
    public Map<String,Object> selectInvest(Integer investId, Integer userId) {
        logger.info("出借详情");
        Map<String,Object> investMap = investMapper.selectInvest(investId,userId);
        return investMap;
    }

    @Override
    public Map<String, Object> selectStatisticsByParam(Map<String, Object> paramMap) {
		logger.info("查询列表中的统计信息");
		Map<String,Object> returnMap = new HashMap<>();
		paramMap.put("investStatus",Constants.investStatus_10);
		returnMap.put("investStatusNum_10",investMapper.selectListByParamCount(paramMap));
		paramMap.put("investStatus",Constants.investStatus_20);
		returnMap.put("investStatusNum_20",investMapper.selectListByParamCount(paramMap));
		paramMap.put("investStatus",Constants.investStatus_30);
		returnMap.put("investStatusNum_30",investMapper.selectListByParamCount(paramMap));
		paramMap.remove("investStatus");
		switch (paramMap.get("lender").toString()){
			case "0":
				returnMap.put("myLendNum",investMapper.selectListByParamCount(paramMap));
				paramMap.put("superiorRecommendCodeUserIdOrOnId",paramMap.get("userId"));
				paramMap.remove("userId");
				returnMap.put("mySubordinateLendNum",investMapper.selectListByParamCount(paramMap));
				paramMap.put("teamRecommendCodeUserIdAndNotIn",paramMap.get("superiorRecommendCodeUserIdOrOnId"));
				paramMap.remove("superiorRecommendCodeUserIdOrOnId");
				returnMap.put("otherSubordinateLendNum",investMapper.selectListByParamCount(paramMap));
				break;
			case "1":
				returnMap.put("mySubordinateLendNum",investMapper.selectListByParamCount(paramMap));
				paramMap.put("teamRecommendCodeUserIdAndNotIn",paramMap.get("superiorRecommendCodeUserIdOrOnId"));
				paramMap.remove("superiorRecommendCodeUserIdOrOnId");
				returnMap.put("otherSubordinateLendNum",investMapper.selectListByParamCount(paramMap));
				paramMap.put("userId",paramMap.get("teamRecommendCodeUserIdAndNotIn"));
				paramMap.remove("teamRecommendCodeUserIdAndNotIn");
				returnMap.put("myLendNum",investMapper.selectListByParamCount(paramMap));
				break;
			case "2":
				returnMap.put("otherSubordinateLendNum",investMapper.selectListByParamCount(paramMap));
				paramMap.put("userId",paramMap.get("teamRecommendCodeUserIdAndNotIn"));
				paramMap.remove("teamRecommendCodeUserIdAndNotIn");
				returnMap.put("myLendNum",investMapper.selectListByParamCount(paramMap));
				paramMap.put("superiorRecommendCodeUserIdOrOnId",paramMap.get("userId"));
				paramMap.remove("userId");
				returnMap.put("mySubordinateLendNum",investMapper.selectListByParamCount(paramMap));
				break;
		}
        return returnMap;
    }

}
