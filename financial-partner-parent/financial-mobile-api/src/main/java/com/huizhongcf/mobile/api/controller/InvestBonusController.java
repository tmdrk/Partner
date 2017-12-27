package com.huizhongcf.mobile.api.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hui.zhong.cf.mobile.model.LoginUser;
import com.hui.zhong.cf.mobile.service.CommissionLiquidationService;
import com.hui.zhong.cf.mobile.service.InvestBonusService;
import com.hui.zhong.cf.mobile.service.InvestService;
import com.hui.zhong.cf.mobile.service.LoginUserService;
import com.huizhongcf.mobile.api.Interceptor.AccessRequired;
import com.huizhongcf.mobile.api.framework.BaseController;
import com.huizhongcf.util.BigDecimalUtil;
import com.huizhongcf.util.MathUtil;

/**
 * 投资统计和投资奖励。
 * 
 * @author zhangfei
 * @date 2017年12月12日
 */
@Controller
@RequestMapping("/investBonus")
public class InvestBonusController extends BaseController {
	@Autowired
	private InvestService investService;
	@Autowired
	private LoginUserService loginUserService;
	@Autowired
	private InvestBonusService investBonusService;
	@Autowired
	private CommissionLiquidationService commissionLiquidationService;
	
	@RequestMapping("/init")
	@ResponseBody
	@AccessRequired(login=true)
	public Object generalPartner() {
		Map<String, Object> body = new HashMap<String, Object>();
		
		LoginUser loginUser = getSession();
		Integer partnerUserId = loginUser.getUserId();// 合伙人用户id，从当前登录用户获取
		// 判断用户类型
		String userType = null;
		if ("20".equals(loginUser.getUserType())) {// 团队管理者
			userType = "20";
		} else if ("20".equals(loginUser.getUserRole())) {// 客户
			userType = "00";
		} else {// 一般合伙人
			userType = "10";
		}
		body.put("userType", userType);
		body.put("userId", partnerUserId);
		
		if ("00".equals(userType)) {
			body.put("myInvestInfo", new HashMap<String, Object>(0));// 我的出借信息
			body.put("mySubordinateInvestInfo", new HashMap<String, Object>(0));// 我的下级出借信息
			body.put("otherSubordinateInvestInfo", new HashMap<String, Object>(0));// 其他下级出借信息
			body.put("myBonusInfo", new HashMap<String, Object>(0));// 我的奖励信息
			return renderSuccess(body);
		}
				
		// 1. 我的出借
		Map<String, Object> myInvestMap = new HashMap<String, Object>();
		// 累计出借
		Map<String, Object> addupInvestInfo = investService.getAddupInvestInfo(partnerUserId);
		double addupInvestAmt = MapUtils.getDoubleValue(addupInvestInfo, "totalInvestAmount", 0d);
		myInvestMap.put("addupInvestAmount", transformMoneyUnit(addupInvestAmt));// 累计出借，单位：万元
		myInvestMap.put("addupInvestNum", MapUtils.getInteger(addupInvestInfo, "totalInvestNum", 0));// 累计出单
		
		// 本月我的出借
		Map<String, Object> myInvestInfoOfTM = investService.getPartnerInvestInfoOfThisMonth(partnerUserId);
		double myInvestAmtOfTM = MapUtils.getDoubleValue(myInvestInfoOfTM, "totalInvestAmount", 0d);
		myInvestMap.put("myInvestAmountOfThisMonth", transformMoneyUnit(myInvestAmtOfTM));// 本月我的出借，单位：万元
		myInvestMap.put("myInvestNumOfThisMonth", MapUtils.getInteger(myInvestInfoOfTM, "totalInvestNum", 0));// 本月我的出单
		
		// 本月客户出借
		Map<String, Object> customerInvestInfoOfTM = investService.getCustomerInvestInfoOfThisMonth(partnerUserId);
		double customerInvestAmtOfTM = MapUtils.getDoubleValue(customerInvestInfoOfTM, "totalInvestAmount", 0d);
		myInvestMap.put("customerInvestAmountOfThisMonth", transformMoneyUnit(customerInvestAmtOfTM));// 本月客户出借，单位：万元
		myInvestMap.put("customerInvestNumOfThisMonth", MapUtils.getInteger(customerInvestInfoOfTM, "totalInvestNum", 0));// 本月客户出单
		myInvestMap.put("investAmountOfThisMonth", transformMoneyUnit(BigDecimalUtil.add(myInvestAmtOfTM, customerInvestAmtOfTM)));// 本月出借
		
		body.put("myInvestInfo", myInvestMap);// 我的出借信息
		
		// 2. 我的下级出借
		Map<String, Object> mySubInvestMap = new HashMap<String, Object>();
		// 直接下级合伙人user_id列表
		List<Integer> subPartnerUserIdList = loginUserService.getDirectSubordinatePartnerUserIds(partnerUserId);
		if (CollectionUtils.isEmpty(subPartnerUserIdList)) {
			mySubInvestMap.put("investAmountOfThisMonth", "0.00");// 本月出借，单位：万元
			mySubInvestMap.put("partnerInvestAmountOfThisMonth", "0.00");// 本月合伙人出借，单位：万元
			mySubInvestMap.put("customerInvestAmountOfThisMonth", "0.00");// 本月客户出借，单位：万元
			mySubInvestMap.put("addupInvestAmount", "0.00");// 累计出借，单位：万元
			mySubInvestMap.put("addupPartnerInvestAmount", "0.00");// 累计合伙人出借，单位：万元
			mySubInvestMap.put("addupCustomerInvestAmount", "0.00");// 累计客户出借，单位：万元
		} else {
			Integer[] subPartnerUserIdArr = subPartnerUserIdList.toArray(new Integer[subPartnerUserIdList.size()]);
			Map<String, Object> subPartnerInvestInfoOfTM = investService.getPartnerInvestInfoOfThisMonth(subPartnerUserIdArr);
			// 我的下级出借 - 本月合伙人出借
			double subPartnerInvestAmtOfTM = MapUtils.getDoubleValue(subPartnerInvestInfoOfTM, "totalInvestAmount", 0d);
			mySubInvestMap.put("partnerInvestAmountOfThisMonth", transformMoneyUnit(subPartnerInvestAmtOfTM));// 本月合伙人出借，单位：万元
			
			Map<String, Object> subPartnerCustomerInvestInfoOfTM = investService.getCustomerInvestInfoOfThisMonth(subPartnerUserIdArr);
			// 我的下级出借 - 本月客户出借
			double subPartnerCustomerInvestAmtOfTM = MapUtils.getDoubleValue(subPartnerCustomerInvestInfoOfTM, "totalInvestAmount", 0d);
			mySubInvestMap.put("customerInvestAmountOfThisMonth", transformMoneyUnit(subPartnerCustomerInvestAmtOfTM));// 本月客户出借，单位：万元
			mySubInvestMap.put("investAmountOfThisMonth", 
					transformMoneyUnit(BigDecimalUtil.add(subPartnerInvestAmtOfTM, subPartnerCustomerInvestAmtOfTM)));// 本月出借，单位：万元
			
			Map<String, Object> subPartnerAddupInvestInfo = investService.getPartnerAddupInvestInfo(subPartnerUserIdArr);
			// 我的下级出借 - 累计合伙人出借
			double subPartnerAddupInvestAmt = MapUtils.getDoubleValue(subPartnerAddupInvestInfo, "totalInvestAmount", 0d);
			mySubInvestMap.put("addupPartnerInvestAmount", transformMoneyUnit(subPartnerAddupInvestAmt));// 累计合伙人出借，单位：万元
			
			Map<String, Object> subPartnerCustomerAddupInvestInfo = investService.getCustomerAddupInvestInfo(subPartnerUserIdArr);
			// 我的下级出借 - 累计客户出借
			double subPartnerCustomerAddupInvestAmt = MapUtils.getDoubleValue(subPartnerCustomerAddupInvestInfo, "totalInvestAmount", 0d);
			mySubInvestMap.put("addupCustomerInvestAmount", transformMoneyUnit(subPartnerCustomerAddupInvestAmt));// 累计客户出借，单位：万元
			mySubInvestMap.put("addupInvestAmount", 
					transformMoneyUnit(BigDecimalUtil.add(subPartnerAddupInvestAmt, subPartnerCustomerAddupInvestAmt)));// 累计出借，单位：万元
		}
		
		body.put("mySubordinateInvestInfo", mySubInvestMap);// 我的下级出借信息
		
		if (!"20".equals(userType)) {
			body.put("otherSubordinateInvestInfo", new HashMap<String, Object>(0));// 其他下级出借信息
		} else {
			// 3. 其他下级出借
			Map<String, Object> otherSubInvestMap = new HashMap<String, Object>();
			// 其他下级合伙人user_id列表
			List<Integer> otherSubPartnerUserIdList = loginUserService.getOtherSubordinatePartnerUserIds(partnerUserId);
			if (CollectionUtils.isEmpty(otherSubPartnerUserIdList)) {
				otherSubInvestMap.put("investAmountOfThisMonth", "0.00");// 本月出借，单位：万元
				otherSubInvestMap.put("partnerInvestAmountOfThisMonth", "0.00");// 本月合伙人出借，单位：万元
				otherSubInvestMap.put("customerInvestAmountOfThisMonth", "0.00");// 本月客户出借，单位：万元
				otherSubInvestMap.put("addupInvestAmount", "0.00");// 累计出借，单位：万元
				otherSubInvestMap.put("addupPartnerInvestAmount", "0.00");// 累计合伙人出借，单位：万元
				otherSubInvestMap.put("addupCustomerInvestAmount", "0.00");// 累计客户出借，单位：万元
			} else {
				Integer[] otherSubPartnerUserIdArr = otherSubPartnerUserIdList.toArray(new Integer[otherSubPartnerUserIdList.size()]);
				Map<String, Object> otherSubPartnerInvestInfoOfTM = investService.getPartnerInvestInfoOfThisMonth(otherSubPartnerUserIdArr);
				// 其他下级出借 - 本月合伙人出借
				double otherSubPartnerInvestAmtOfTM = MapUtils.getDoubleValue(otherSubPartnerInvestInfoOfTM, "totalInvestAmount", 0d);
				otherSubInvestMap.put("partnerInvestAmountOfThisMonth", transformMoneyUnit(otherSubPartnerInvestAmtOfTM));// 本月合伙人出借，单位：万元
				
				Map<String, Object> otherSubPartnerCustomerInvestInfoOfTM = investService.getCustomerInvestInfoOfThisMonth(otherSubPartnerUserIdArr);
				// 其他下级出借 - 本月客户出借
				double otherSubPartnerCustomerInvestAmtOfTM = MapUtils.getDoubleValue(otherSubPartnerCustomerInvestInfoOfTM, "totalInvestAmount", 0d);
				otherSubInvestMap.put("customerInvestAmountOfThisMonth", transformMoneyUnit(otherSubPartnerCustomerInvestAmtOfTM));// 本月客户出借，单位：万元
				otherSubInvestMap.put("investAmountOfThisMonth", 
						transformMoneyUnit(BigDecimalUtil.add(otherSubPartnerInvestAmtOfTM, otherSubPartnerCustomerInvestAmtOfTM)));// 本月出借，单位：万元
				
				Map<String, Object> otherSubPartnerAddupInvestInfo = investService.getPartnerAddupInvestInfo(otherSubPartnerUserIdArr);
				// 其他下级出借 - 累计合伙人出借
				double otherSubPartnerAddupInvestAmt = MapUtils.getDoubleValue(otherSubPartnerAddupInvestInfo, "totalInvestAmount", 0d);
				otherSubInvestMap.put("addupPartnerInvestAmount", transformMoneyUnit(otherSubPartnerAddupInvestAmt));// 累计合伙人出借，单位：万元
				
				Map<String, Object> otherSubPartnerCustomerAddupInvestInfo = investService.getCustomerAddupInvestInfo(otherSubPartnerUserIdArr);
				// 其他下级出借 - 累计客户出借
				double otherSubPartnerCustomerAddupInvestAmt = MapUtils.getDoubleValue(otherSubPartnerCustomerAddupInvestInfo, "totalInvestAmount", 0d);
				otherSubInvestMap.put("addupCustomerInvestAmount", transformMoneyUnit(otherSubPartnerCustomerAddupInvestAmt));// 累计客户出借，单位：万元
				otherSubInvestMap.put("addupInvestAmount", 
						transformMoneyUnit(BigDecimalUtil.add(otherSubPartnerAddupInvestAmt, otherSubPartnerCustomerAddupInvestAmt)));// 累计出借，单位：万元
			}
			
			body.put("otherSubordinateInvestInfo", otherSubInvestMap);// 其他下级出借信息
		}
		
		// 4. 我的奖励
		Map<String, Object> myBonusMap = new HashMap<String, Object>();
		// 本月预计出借奖励
		BigDecimal expectedInvestBonusOfTM = investBonusService.getPartnerInvestBonusOfThisMonth(partnerUserId);
		// 本月预计差额奖励
		BigDecimal expectedDiffBonusOfTM = investBonusService.getPartnerDiffBonusOfThisMonth(partnerUserId);
		// 本月预计服务奖励
		BigDecimal expectedServiceBonusOfTM = BigDecimal.ZERO;
		if ("20".equals(userType)) {// 团队管理者
			expectedServiceBonusOfTM = investBonusService.getServiceBonusOfThisMonth(partnerUserId);
		}
		BigDecimal expectedBonusOfThisMonth = expectedInvestBonusOfTM.add(expectedDiffBonusOfTM).add(expectedServiceBonusOfTM);
		myBonusMap.put("expectedBonusOfThisMonth", MathUtil.formatDecimal(expectedBonusOfThisMonth));// 本月预计获得奖励
		
		Map<String, Object> settleInfo = commissionLiquidationService.commissionLiquidationStat(partnerUserId);
		// 本月之前的待结算
		double unLiquidationAmount = MapUtils.getDoubleValue(settleInfo, "unLiquidationAmount", 0d);
		// 待结算
		double notSettledAmount = BigDecimalUtil.add(expectedBonusOfThisMonth.doubleValue(), unLiquidationAmount);
		// 已结算
		double liquidationAmount = MapUtils.getDoubleValue(settleInfo, "liquidationAmount", 0d);
		myBonusMap.put("notSettledAmount", MathUtil.formatDecimal(new BigDecimal(notSettledAmount)));// 待结算金额
		myBonusMap.put("alreadySettledAmount", MathUtil.formatDecimal(new BigDecimal(liquidationAmount)));// 已结算金额
		myBonusMap.put("addupBonus", 
				MathUtil.formatDecimal(new BigDecimal(BigDecimalUtil.add(notSettledAmount, liquidationAmount))));// 累计获得奖励
		
		body.put("myBonusInfo", myBonusMap);
		
		return renderSuccess(body);
	}
	
	/**
	 * 将金额的单位由元转换为万元，并进行格式化
	 * 
	 * @param money
	 * @return
	 */
	private String transformMoneyUnit(double money) {
		return MathUtil.formatDecimal(new BigDecimal(BigDecimalUtil.div(money, 10000d, 2)));
	}
}
