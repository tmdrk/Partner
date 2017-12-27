package com.huizhongcf.mobile.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hui.zhong.cf.mobile.model.LoginUser;
import com.hui.zhong.cf.mobile.model.PageModel;
import com.hui.zhong.cf.mobile.service.CommissionLiquidationService;
import com.hui.zhong.cf.mobile.service.InvestBonusService;
import com.hui.zhong.cf.mobile.service.InvestService;
import com.hui.zhong.cf.mobile.service.LoginUserService;
import com.huizhongcf.mobile.api.Interceptor.AccessRequired;
import com.huizhongcf.mobile.api.framework.BaseController;
import com.huizhongcf.mobile.api.utils.StringUtil;
import com.huizhongcf.util.BigDecimalUtil;
import com.huizhongcf.util.MathUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 出借信息
 * @author xieang
 */
@Controller
@RequestMapping("/invest")
public class InvestController extends BaseController {
	@Autowired
	private InvestService investService;

	@RequestMapping("/list")
	@ResponseBody
	@AccessRequired(login=true)
	public Object list() {
		try {
			Map<String, Object> requestParam = getRequestParam();
			LoginUser loginUser = getSession();
			Integer userId = loginUser.getUserId();// 当前登录用户id


			if(requestParam.get("lender")!=null){
				switch (requestParam.get("lender").toString()){
					case "0":requestParam.put("userId",userId);
					case "1":requestParam.put("superiorRecommendCodeUserIdOrOnId",userId);
					case "2":requestParam.put("teamRecommendCodeUserIdAndNotIn",userId);
				}
			}else {
				return renderError("500", "参数错误");
			}
			String pageNo = StringUtil.isNotBlank(requestParam.get("pageNo"))?requestParam.get("pageNo").toString():"1";
			String pageSize = StringUtil.isNotBlank(requestParam.get("pageSize"))?requestParam.get("pageSize").toString():"10";
			requestParam.put("pageNo", Integer.parseInt(pageNo));
			requestParam.put("pageSize", Integer.parseInt(pageSize));
			PageModel body = investService.selectInvestListByParam(requestParam);
			Map<String,Object> bodyCount = investService.selectStatisticsByParam(requestParam);
			JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(body));
			jsonObject.putAll(bodyCount);
			return renderSuccess(jsonObject);
		} catch (Exception e) {
			return renderError("500", "分页查询已结算列表异常");
		}
	}

	@RequestMapping("/detail")
	@ResponseBody
	@AccessRequired(login=true)
	public Object detail() {
		try {
			Map<String, Object> requestParam = getRequestParam();
			LoginUser loginUser = getSession();
			Integer userId = loginUser.getUserId();// 当前登录用户id
			Integer investId = null;
			if(requestParam.get("investId")!=null){
				investId = Integer.parseInt(requestParam.get("investId").toString());
			}else {
				return renderError("500", "参数错误");
			}
			Map<String,Object> body = investService.selectInvest(investId,userId);
			return renderSuccess(body);
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("500", "获取详情出现异常");
		}
	}



}
