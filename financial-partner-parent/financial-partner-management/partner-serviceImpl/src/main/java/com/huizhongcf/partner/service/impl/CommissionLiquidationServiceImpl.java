package com.huizhongcf.partner.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huizhongcf.partner.dao.CommissionLiquidationMapper;
import com.huizhongcf.partner.model.CommissionLiquidation;
import com.huizhongcf.partner.service.CommissionLiquidationService;
import com.huizhongcf.util.PageModel;
@Service("commissionLiquidationService")
public class CommissionLiquidationServiceImpl implements CommissionLiquidationService {
    private Logger logger = LoggerFactory.getLogger(CommissionLiquidationServiceImpl.class);
	@Autowired
	private CommissionLiquidationMapper commissionLiquidationMapper;
	@Override
	public PageModel getCommissionLiquidationList(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paramsCondition.get("pageNo"));
		pageModel.setPageSize((Integer) paramsCondition.get("pageSize"));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		
		List<Map<String,Object>> list = commissionLiquidationMapper.getCommissionLiquidationList(paramsCondition);
		Long totalRecords = commissionLiquidationMapper.getCommissionLiquidationCount(paramsCondition);
		pageModel.setList(list);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}
	
	@Override
	public int updateCommissionFeePay(Map<String, Object> requestMap) {
		return commissionLiquidationMapper.updateCommissionFeePay(requestMap);
	}
	
	@Override
	public String commissionFeePayMany(String body) {
		String msg = null;
		int pay = 0;
		String[] datass = body.split(",");
		int[] result = new int[datass.length];
		for(int i = 0 ;i < datass.length; i++){
			result[i] = Integer.parseInt(datass[i]);
			CommissionLiquidation commissionLiquidation = new CommissionLiquidation();
			commissionLiquidation.setCommissionLiquidationId(result[i]);
			pay = commissionLiquidationMapper.updateCommissionFeePayMany(commissionLiquidation);
		}
		if (pay > 0) {
			msg = "1";
		}
		return msg;
	}
	
	@Override
	public List<Map<String, Object>> exportCommissionLiquidationList(Map<String, Object> paramsCondition) {
		return commissionLiquidationMapper.exportCommissionLiquidationList(paramsCondition);
	}

	@Override
	public Map<String, Object> getCommissionLiquidationById(Integer commissionLiquidationId) {
		return commissionLiquidationMapper.getCommissionLiquidationById(commissionLiquidationId);
	}
}
