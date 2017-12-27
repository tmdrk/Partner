package com.hui.zhong.cf.mobile.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hui.zhong.cf.mobile.constants.Constants;
import com.hui.zhong.cf.mobile.dao.CommissionLiquidationMapper;
import com.hui.zhong.cf.mobile.dao.LoginUserMapper;
import com.hui.zhong.cf.mobile.model.CommissionLiquidation;
import com.hui.zhong.cf.mobile.model.PageModel;
import com.hui.zhong.cf.mobile.service.CommissionLiquidationService;
import com.huizhongcf.util.DateUtil;
import com.huizhongcf.util.MoneyUtil;
import com.huizhongcf.util.StringUtil;

@Service("commissionLiquidationService")
public class CommissionLiquidationServiceImpl implements CommissionLiquidationService{
	private Logger logger = LoggerFactory.getLogger(CommissionLiquidationServiceImpl.class);
	@Autowired
	private CommissionLiquidationMapper commissionLiquidationMapper;
	@Autowired
	private LoginUserMapper loginUserMapper;
	@Override
	public List<String> queryUnsettlementMonthList(Map<String, Object> paraMap) {
		logger.info("CommissionLiquidationServiceImpl-->queryUnsettlementMonthList 查询待结算月份集合开始...");
		List<String> retList = new ArrayList<String>();
		retList.add(DateUtil.formatDate(new Date(),"yyyy-MM"));
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("liquidationStatus", Constants.liquidation_status_10);
		Integer userId = Integer.parseInt(paraMap.get("userId").toString());
		pMap.put("userId", userId);
		List<CommissionLiquidation> list = commissionLiquidationMapper.queryCommissionLiquidationList(pMap);
		for(CommissionLiquidation commissionLiquidation:list){
			if(!retList.contains(commissionLiquidation.getLiquidationMonth())){
				retList.add(commissionLiquidation.getLiquidationMonth());
			}
		}
		return retList;
	}
	
	@Override
	public Map<String, Object> queryUnsettlementInfo(Map<String, Object> queryMap) {
		logger.info("CommissionLiquidationServiceImpl-->queryUnsettlementInfo 查询待结算信息开始...");
		Map<String, Object> retMap = new HashMap<String, Object>();
		Map<String, Object> loanMap = new HashMap<String, Object>();
		Map<String, Object> diffMap = new HashMap<String, Object>();
		Map<String, Object> serviceMap = new HashMap<String, Object>();
		Integer userId = Integer.parseInt(queryMap.get("userId").toString());
//		String userRole = queryMap.get("userRole").toString();
		String userType = queryMap.get("userType").toString();
		String liquidationMonth = queryMap.get("settlementMonth").toString(); //待结算月份
		Map<String, Object> paraMap = new HashMap<String, Object>(); 
		paraMap.put("userId", userId);
		boolean isNowMonth = liquidationMonth.equals(DateUtil.formatDate(new Date(),"yyyy-MM")); //是否是当月
		boolean showDiffBonus = true;
		boolean showServiceBonus = true;
		if(isNowMonth){
			//查询用户直属下级合伙人数
			paraMap.put("subUserRole", Constants.login_user_role_10);
			int count = loginUserMapper.countByCondition(paraMap);
			paraMap.remove("subUserRole");
			if(count==0){
				showDiffBonus = false;
			}
			if(!userType.equals(Constants.login_user_type_20)){
				showServiceBonus = false;
			}
		}else{
			//查询非本月待结算信息，根据差额奖金和服务奖金是否为零，选择是否前台显示
			paraMap.put("liquidationMonth", liquidationMonth);
			List<CommissionLiquidation> list = commissionLiquidationMapper.queryCommissionLiquidationList(paraMap);
			if(list.size()<0){
				throw new RuntimeException("查询待结算信息-->获取"+liquidationMonth+"月用户["+userId+"]未结算信息为空！");
			}
			paraMap.remove("liquidationMonth");
			CommissionLiquidation commissionLiquidation = list.get(0);
			if(commissionLiquidation.getDiffBonus().toString().equals("0.00")){
				showDiffBonus = false;
			}
			if(commissionLiquidation.getServiceBonus().toString().equals("0.00")){
				showServiceBonus = false;
			}
		}
		paraMap.put("loanDateStart", DateUtil.parseDate(liquidationMonth,"yyyy-MM")); //获取该月份第一天时间
		paraMap.put("loanDateEnd", DateUtil.addMonth(DateUtil.parseDate(liquidationMonth,"yyyy-MM"),1)); //获取该月份下月第一天时间
		//查询我的月度累计出借金额(万元) 我的月度累计折标金额(万元)
		Map<String, Object> resultMap = commissionLiquidationMapper.queryMonthAmount(paraMap);
		loanMap.put("monthLoanAmount", MoneyUtil.kiloFormatDiv(resultMap.get("monthLoanAmount"),10000));
		loanMap.put("monthDiscountAmount", MoneyUtil.kiloFormatDiv(resultMap.get("monthDiscountAmount"),10000));
		if(showDiffBonus){
			diffMap.put("monthLoanAmount", MoneyUtil.kiloFormatDiv(resultMap.get("monthLoanAmount"),10000));
			diffMap.put("monthDiscountAmount", MoneyUtil.kiloFormatDiv(resultMap.get("monthDiscountAmount"),10000));
		}
		if(showServiceBonus){
			serviceMap.put("monthLoanAmount", MoneyUtil.kiloFormatDiv(resultMap.get("monthLoanAmount"),10000));
			serviceMap.put("monthDiscountAmount", MoneyUtil.kiloFormatDiv(resultMap.get("monthDiscountAmount"),10000));
		}
		//我的下级月度累计出借金额(万元) 我的下级月度累计折标金额(万元)
		resultMap = commissionLiquidationMapper.querySubMonthAmount(paraMap);
		loanMap.put("mySubordinateMonthLoanAmount", MoneyUtil.kiloFormatDiv(resultMap.get("mySubordinateMonthLoanAmount"),10000));
		loanMap.put("mySubordinateMonthDiscountAmount", MoneyUtil.kiloFormatDiv(resultMap.get("mySubordinateMonthDiscountAmount"),10000));
		if(showServiceBonus){
			serviceMap.put("mySubordinateMonthLoanAmount", MoneyUtil.kiloFormatDiv(resultMap.get("mySubordinateMonthLoanAmount"),10000));
			serviceMap.put("mySubordinateMonthDiscountAmount", MoneyUtil.kiloFormatDiv(resultMap.get("mySubordinateMonthDiscountAmount"),10000));
		}
		//出借奖金系数 差额奖金系数 服务奖金系数
		List<Map<String,Object>> BonusConfigList = commissionLiquidationMapper.queryBonusConfigList(null);
		String percent = "%";
		for(Map<String,Object> map:BonusConfigList){
			if(map.get("bonusType").equals(Constants.bonus_type_10)){
				loanMap.put("loanBonusCoefficient", map.get("bonusOneCoefficient")+percent);
			}else if(map.get("bonusType").equals(Constants.bonus_type_20)){
				if(showDiffBonus){
					diffMap.put("diffBonusCoefficient", map.get("bonusOneCoefficient")+percent);
				}
			}else if(map.get("bonusType").equals(Constants.bonus_type_30)){
				if(showServiceBonus){
					serviceMap.put("serviceBonusCoefficient", map.get("bonusOneCoefficient")+percent);
				}
			}
		}
		//预计获得出借奖金(元) 预计获得差额奖金(元) 预计获得服务奖金(元)
		if(liquidationMonth.equals(DateUtil.formatDate(new Date(),"yyyy-MM"))){
			//如果查询的是当月奖金，查询invest_bonus表
			resultMap = commissionLiquidationMapper.queryExpectLoanBonusAmount(paraMap);
			if(null==resultMap){
				logger.error(liquidationMonth+"该月份对应用户投资奖励表信息不存在！");
				throw new RuntimeException(liquidationMonth+"该月份对应用户投资奖励表信息不存在！");
			}
			loanMap.put("expectLoanBonus", MoneyUtil.kiloFormat(resultMap.get("expectLoanBonus")));
			if(showDiffBonus){
				resultMap = commissionLiquidationMapper.queryExpectDiffBonusAmount(paraMap);
				diffMap.put("expectDiffBonus", MoneyUtil.kiloFormat(resultMap.get("expectDiffBonus")));
			}
			if(showServiceBonus){
				resultMap = commissionLiquidationMapper.queryExpectServiceBonusAmount(paraMap);
				serviceMap.put("expectServiceBonus", MoneyUtil.kiloFormat(resultMap.get("expectServiceBonus")));
			}
		}else{
			//如果查询的是当月之前的奖金，则查询结算表
			paraMap.put("liquidationMonth", liquidationMonth);
			resultMap = commissionLiquidationMapper.queryPreExpectBonusAmount(paraMap);
			if(null==resultMap){
				logger.error(liquidationMonth+"该月份对应结算表信息不存在！");
				throw new RuntimeException(liquidationMonth+"该月份结算表信息不存在！");
			}
			paraMap.remove("liquidationMonth");
			loanMap.put("expectLoanBonus", MoneyUtil.kiloFormat(resultMap.get("expectLoanBonus")));
			if(showDiffBonus){
				diffMap.put("expectDiffBonus", MoneyUtil.kiloFormat(resultMap.get("expectDiffBonus")));
			}
			if(showServiceBonus){
				serviceMap.put("expectServiceBonus", MoneyUtil.kiloFormat(resultMap.get("expectServiceBonus")));
			}
		}
		
		//其他下级月度累计出借金额(万元) 其他下级月度累计折标金额(万元)
		if(showServiceBonus){
			resultMap = commissionLiquidationMapper.queryOtherSubMonthAmount(paraMap);
			serviceMap.put("otherSubordinateMonthLoanAmount", MoneyUtil.kiloFormatDiv(resultMap.get("otherSubordinateMonthLoanAmount"),10000));
			serviceMap.put("otherSubordinateMonthDiscountAmount", MoneyUtil.kiloFormatDiv(resultMap.get("otherSubordinateMonthDiscountAmount"),10000));
		}
		retMap.put("loanBonus", loanMap);
		if(showDiffBonus){
			retMap.put("diffBonus", diffMap);
		}
		if(showServiceBonus){
			retMap.put("serviceBonus", serviceMap);
		}
		return retMap;
	}

	@Override
	public PageModel getCommissionLiquidationList(
			Map<String, Object> paraMap) {
		logger.info("CommissionLiquidationServiceImpl-->getCommissionLiquidationList 分页查询已结算列表开始...");
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paraMap.get("pageNo"));
		pageModel.setPageSize((Integer) paraMap.get("pageSize"));
		paraMap.put("startIndex", pageModel.getStartIndex());
		paraMap.put("endIndex", pageModel.getEndIndex());
		paraMap.put("liquidationStatus",Constants.liquidation_status_20);//查询已结算（已支付）
		List<Map<String,Object>> retList = commissionLiquidationMapper.getCommissionLiquidationList(paraMap);
		Long totalRecords = commissionLiquidationMapper.getCommissionLiquidationCount(paraMap);
		for(Map<String,Object> map:retList){
			map.put("loanAmount", MoneyUtil.kiloFormat(map.get("loanAmount")));
			map.put("discountAmount", MoneyUtil.kiloFormat(map.get("discountAmount")));
			map.put("bonusAmount", MoneyUtil.kiloFormat(map.get("bonusAmount")));
			map.put("loanBonus", MoneyUtil.kiloFormat(map.get("loanBonus")));
			if(map.get("diffBonus").toString().equals("0.00")){
				map.remove("diffBonus");
			}else{
				map.put("diffBonus", MoneyUtil.kiloFormat(map.get("diffBonus")));
			}
			if(map.get("serviceBonus").toString().equals("0.00")){
				map.remove("serviceBonus");
			}else{
				map.put("serviceBonus", MoneyUtil.kiloFormat(map.get("serviceBonus")));
			}
		}
		pageModel.setList(retList);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}

	@Override
	public PageModel getLoanBunusDetailList(Map<String, Object> paraMap) {
		logger.info("CommissionLiquidationServiceImpl-->getLoanBunusDetailList 分页查询出借奖金明细列表开始...");
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paraMap.get("pageNo"));
		pageModel.setPageSize((Integer) paraMap.get("pageSize"));
		paraMap.put("startIndex", pageModel.getStartIndex());
		paraMap.put("endIndex", pageModel.getEndIndex());
		String liquidationMonth = paraMap.get("settlementMonth").toString();
		paraMap.put("loanDateStart", DateUtil.parseDate(liquidationMonth,"yyyy-MM")); //获取该月份第一天时间
		paraMap.put("loanDateEnd", DateUtil.addMonth(DateUtil.parseDate(liquidationMonth,"yyyy-MM"),1)); //获取该月份下月第一天时间
		String isCurrentMonth = liquidationMonth.equals(DateUtil.formatDate(new Date(),"yyyy-MM"))?"1":"0";
		paraMap.put("isCurrentMonth", isCurrentMonth);//是否是当前月 1：是 0：否
		List<Map<String,Object>> retList = commissionLiquidationMapper.getLoanBunusDetailList(paraMap);
		Long totalRecords = commissionLiquidationMapper.getLoanBunusDetailCount(paraMap);
		for(Map<String,Object> map:retList){
			if(!paraMap.get("type").toString().equals("1")){
				map.put("loanerName", StringUtil.isNotBlanks(map.get("loanerName"))?StringUtil.getHideRealName(map.get("loanerName").toString()):"--");
			}
			if(isCurrentMonth.equals("1")){
				map.put("loanBonus", MoneyUtil.kiloFormat(map.get("loanBonus")));
			}else{
				map.put("loanBonus", MoneyUtil.kiloFormat("0.00"));
			}
			map.put("loanAmount", MoneyUtil.kiloFormat(map.get("loanAmount")));
			map.put("discountAmount", MoneyUtil.kiloFormat(map.get("discountAmount")));
		}
		pageModel.setList(retList);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}
	@Override
	public PageModel getDiffBunusDetailList(Map<String, Object> paraMap) {
		logger.info("CommissionLiquidationServiceImpl-->getDiffBunusDetailList 分页查询差额奖金明细列表开始...");
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paraMap.get("pageNo"));
		pageModel.setPageSize((Integer) paraMap.get("pageSize"));
		paraMap.put("startIndex", pageModel.getStartIndex());
		paraMap.put("endIndex", pageModel.getEndIndex());
		String liquidationMonth = paraMap.get("settlementMonth").toString();
		paraMap.put("loanDateStart", DateUtil.parseDate(liquidationMonth,"yyyy-MM")); //获取该月份第一天时间
		paraMap.put("loanDateEnd", DateUtil.addMonth(DateUtil.parseDate(liquidationMonth,"yyyy-MM"),1)); //获取该月份下月第一天时间
		String isCurrentMonth = liquidationMonth.equals(DateUtil.formatDate(new Date(),"yyyy-MM"))?"1":"0";
		paraMap.put("isCurrentMonth", isCurrentMonth);//是否是当前月 1：是 0：否
		List<Map<String,Object>> retList = commissionLiquidationMapper.getDiffBunusDetailList(paraMap);
		Long totalRecords = commissionLiquidationMapper.getDiffBunusDetailCount(paraMap);
		for(Map<String,Object> map:retList){
//			if(!paraMap.get("type").toString().equals("1")){
//				map.put("loanerName", StringUtil.isNotBlanks(map.get("loanerName"))?StringUtil.getHideRealName(map.get("loanerName").toString()):"--");
//			}
			if(isCurrentMonth.equals("1")){
				map.put("diffBonus", MoneyUtil.kiloFormat(map.get("diffBonus")));
			}else{
				map.put("diffBonus", MoneyUtil.kiloFormat("0.00"));
			}
			map.put("loanAmount", MoneyUtil.kiloFormat(map.get("loanAmount")));
			map.put("discountAmount", MoneyUtil.kiloFormat(map.get("discountAmount")));
		}

		pageModel.setList(retList);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}

	@Override
	public PageModel getServiceBunusDetailList(Map<String, Object> paraMap) {
		logger.info("CommissionLiquidationServiceImpl-->getServiceBunusDetailList 分页查询服务奖金明细列表开始...");
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paraMap.get("pageNo"));
		pageModel.setPageSize((Integer) paraMap.get("pageSize"));
		paraMap.put("startIndex", pageModel.getStartIndex());
		paraMap.put("endIndex", pageModel.getEndIndex());
		String liquidationMonth = paraMap.get("settlementMonth").toString();
		paraMap.put("loanDateStart", DateUtil.parseDate(liquidationMonth,"yyyy-MM")); //获取该月份第一天时间
		paraMap.put("loanDateEnd", DateUtil.addMonth(DateUtil.parseDate(liquidationMonth,"yyyy-MM"),1)); //获取该月份下月第一天时间
		String isCurrentMonth = liquidationMonth.equals(DateUtil.formatDate(new Date(),"yyyy-MM"))?"1":"0";
		paraMap.put("isCurrentMonth", isCurrentMonth);//是否是当前月 1：是 0：否
		List<Map<String,Object>> retList = commissionLiquidationMapper.getServiceBunusDetailList(paraMap);
		Long totalRecords = commissionLiquidationMapper.getServiceBunusDetailCount(paraMap);
		for(Map<String,Object> map:retList){
			if(!paraMap.get("type").toString().equals("1")){
				map.put("loanerName", StringUtil.isNotBlanks(map.get("loanerName"))?StringUtil.getHideRealName(map.get("loanerName").toString()):"--");
			}
			if(isCurrentMonth.equals("1")){
				map.put("serviceBonus", MoneyUtil.kiloFormat(map.get("serviceBonus")));
			}else{
				map.put("serviceBonus", MoneyUtil.kiloFormat("0.00"));
			}
			map.put("loanAmount", MoneyUtil.kiloFormat(map.get("loanAmount")));
			map.put("discountAmount", MoneyUtil.kiloFormat(map.get("discountAmount")));
		}
		pageModel.setList(retList);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}

	@Override
	public Map<String, Object> commissionLiquidationStat(Integer userId) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("unLiquidationAmount", 0.00);
		retMap.put("liquidationAmount", 0.00);
		List<Map<String, Object>> list = commissionLiquidationMapper.commissionLiquidationStat(userId);
		for(Map<String, Object> map:list){
			if(Constants.liquidation_status_10.equals(map.get("liquidationStatus"))){
				retMap.put("unLiquidationAmount", map.get("totalReward"));
			}else if(Constants.liquidation_status_20.equals(map.get("liquidationStatus"))){
				retMap.put("liquidationAmount", map.get("totalReward"));
			}
		}
		return retMap;
	}

	@Override
	public Map<String, Object> getLoanBunusDetailCount(
			Map<String, Object> paraMap) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		String liquidationMonth = paraMap.get("settlementMonth").toString();
		paraMap.put("loanDateStart", DateUtil.parseDate(liquidationMonth,"yyyy-MM")); //获取该月份第一天时间
		paraMap.put("loanDateEnd", DateUtil.addMonth(DateUtil.parseDate(liquidationMonth,"yyyy-MM"),1)); //获取该月份下月第一天时间
		paraMap.put("type", "1");	//我的
		Long myCount = commissionLiquidationMapper.getLoanBunusDetailCount(paraMap);
		paraMap.put("type", "2");	//我的下级
		Long subCount = commissionLiquidationMapper.getLoanBunusDetailCount(paraMap);
		retMap.put("myCount", myCount);
		retMap.put("subCount", subCount);
		return retMap;
	}

	@Override
	public Map<String, Object> getServiceBunusDetailCount(
			Map<String, Object> paraMap) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		String liquidationMonth = paraMap.get("settlementMonth").toString();
		paraMap.put("loanDateStart", DateUtil.parseDate(liquidationMonth,"yyyy-MM")); //获取该月份第一天时间
		paraMap.put("loanDateEnd", DateUtil.addMonth(DateUtil.parseDate(liquidationMonth,"yyyy-MM"),1)); //获取该月份下月第一天时间
		paraMap.put("type", "1");	//我的
		Long myCount = commissionLiquidationMapper.getServiceBunusDetailCount(paraMap);
		paraMap.put("type", "2");	//我的下级
		Long subCount = commissionLiquidationMapper.getServiceBunusDetailCount(paraMap);
		paraMap.put("type", "3");	//其他下级
		Long otherSubCount = commissionLiquidationMapper.getServiceBunusDetailCount(paraMap);
		retMap.put("myCount", myCount);
		retMap.put("subCount", subCount);
		retMap.put("otherSubCount", otherSubCount);
		return retMap;
	}

	@Override
	public List<String> querySettlementYearList(Map<String, Object> paraMap) {
		logger.info("CommissionLiquidationServiceImpl-->queryUnsettlementMonthList 查询已结算年份集合开始...");
		List<String> retList = new ArrayList<String>();
		retList.add(DateUtil.formatDate(new Date(),"yyyy"));
		paraMap.put("liquidationStatus", Constants.liquidation_status_20);
		List<CommissionLiquidation> list = commissionLiquidationMapper.queryCommissionLiquidationList(paraMap);
		for(CommissionLiquidation commissionLiquidation:list){
			if(!retList.contains(commissionLiquidation.getLiquidationMonth().split("-")[0])){
				retList.add(commissionLiquidation.getLiquidationMonth().split("-")[0]);
			}
		}
		return retList;
	}

	@Override
	public List<Map<String, Object>> queryCommissionLiquidationYearList(
			Map<String, Object> paraMap) {
		logger.info("CommissionLiquidationServiceImpl-->queryCommissionLiquidationList 查询已结算列表开始...");
		paraMap.put("liquidationStatus",Constants.liquidation_status_20);//查询已结算（已支付）
		List<Map<String,Object>> retList = commissionLiquidationMapper.queryCommissionLiquidationYearList(paraMap);
		for(Map<String,Object> map:retList){
			map.put("loanAmount", MoneyUtil.kiloFormat(map.get("loanAmount")));
			map.put("discountAmount", MoneyUtil.kiloFormat(map.get("discountAmount")));
			map.put("bonusAmount", MoneyUtil.kiloFormat(map.get("bonusAmount")));
			map.put("loanBonus", MoneyUtil.kiloFormat(map.get("loanBonus")));
			if(map.get("diffBonus").toString().equals("0.00")){
				map.remove("diffBonus");
			}else{
				map.put("diffBonus", MoneyUtil.kiloFormat(map.get("diffBonus")));
			}
			if(map.get("serviceBonus").toString().equals("0.00")){
				map.remove("serviceBonus");
			}else{
				map.put("serviceBonus", MoneyUtil.kiloFormat(map.get("serviceBonus")));
			}
		}
		return retList;
	}
}
