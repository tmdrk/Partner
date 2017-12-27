package com.huizhongcf.partner.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huizhongcf.util.PageModel;
import com.huizhongcf.util.ReturnMsgData;
import com.huizhongcf.partner.dao.InvestBonusMapper;
import com.huizhongcf.partner.dao.InvestMapper;
import com.huizhongcf.partner.service.InvestBonusManageService;
@Service("investBonusManageService")
public class InvestBonusManageServiceImpl implements InvestBonusManageService {
    private Logger logger = LoggerFactory.getLogger(InvestBonusManageServiceImpl.class);
	@Autowired
	private InvestBonusMapper investBonusMapper;
	@Autowired
	private InvestMapper investMapper;
	@Override
	public PageModel getInvestBonusList(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paramsCondition.get("pageNo"));
		pageModel.setPageSize((Integer) paramsCondition.get("pageSize"));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		
		List<Map<String,Object>> list = investBonusMapper.getInvestBonusList(paramsCondition);
		Long totalRecords = investBonusMapper.getInvestBonusCount(paramsCondition);
		pageModel.setList(list);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}
	@Override
	public Map<String,Object> getInvestDetail(Map<String, Object> paramsCondition) {
		return investMapper.getInvestDetail(paramsCondition);
	}
	@Override
	public List<Map<String, Object>> exportInvestBonusList(Map<String, Object> paramsCondition) {
		return investBonusMapper.exportInvestBonusList(paramsCondition);
	}
	@Override
	public PageModel getCommissionLiquidationDetailList(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paramsCondition.get("pageNo"));
		pageModel.setPageSize((Integer) paramsCondition.get("pageSize"));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		
		List<Map<String,Object>> list = investBonusMapper.getCommissionLiquidationDetailList(paramsCondition);
		Long totalRecords = investBonusMapper.getCommissionLiquidationDetailCount(paramsCondition);
		pageModel.setList(list);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}
	@Override
	public List<Map<String, Object>> exportCommissionLiquidationDetailList(Map<String, Object> paramsCondition) {
		return investBonusMapper.exportCommissionLiquidationDetailList(paramsCondition);
	}
}
