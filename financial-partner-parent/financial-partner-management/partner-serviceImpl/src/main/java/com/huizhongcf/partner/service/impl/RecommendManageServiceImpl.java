package com.huizhongcf.partner.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huizhongcf.partner.dao.LoginUserMapper;
import com.huizhongcf.partner.service.RecommendManageService;
import com.huizhongcf.util.PageModel;

@Service("recommendManageService")
public class RecommendManageServiceImpl implements RecommendManageService {

	@Autowired
	private LoginUserMapper loginUserMapper;
	
	@Override
	public PageModel findAllRecommendCodePage(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paramsCondition.get("pageNo"));
		pageModel.setPageSize((Integer) paramsCondition.get("pageSize"));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> data = loginUserMapper.findAllRecommendCodeInfo(paramsCondition);
		Long totalRecords = loginUserMapper.findAllRecommendCodeCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}

	@Override
	public List<Map<String, Object>> exportAllRecommendCodeInfo(Map<String, Object> paramsCondition) {
		return loginUserMapper.exportAllRecommendCodeInfo(paramsCondition);
	}

	@Override
	public List<Map<String, Object>> exportSelectRecommendCodeInfo(Map<String, Object> paramsCondition) {
		return loginUserMapper.exportSelectRecommendCodeInfo(paramsCondition);
	}

}
