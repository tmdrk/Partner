package com.huizhongcf.mobile.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hui.zhong.cf.mobile.model.LoginUser;
import com.hui.zhong.cf.mobile.model.PageModel;
import com.hui.zhong.cf.mobile.service.CommissionLiquidationService;
import com.huizhongcf.mobile.api.Interceptor.AccessRequired;
import com.huizhongcf.mobile.api.framework.BaseController;
import com.huizhongcf.mobile.api.utils.StringUtil;

@Controller
@RequestMapping("/commission")
public class CommissionLiquidationController extends BaseController{
	@Autowired
	private CommissionLiquidationService commissionLiquidationService;
	
	/**
	 * 查询待结算月份列表
	 * @return
	 * @author zhoujie
	 * @date 2017年12月14日 下午6:16:23
	 */
	@RequestMapping("/unsettlement/month")
	@ResponseBody
	@AccessRequired(login=true)
	public Object queryUnsettlementMonthList(){
		try {
			Map<String, Object> requestParam = getRequestParam();
			LoginUser loginUser = getSession();
			Integer userId = loginUser.getUserId();// 当前登录用户id
			requestParam.put("userId", userId);
			List<String> body = commissionLiquidationService.queryUnsettlementMonthList(requestParam);
			return renderSuccess(body);
		} catch (Exception e) {
			return renderError("500", "查询待结算月份列表异常");
		}
	}
	
	/**
	 * 查询待结算信息
	 * @return
	 * @author zhoujie
	 * @date 2017年12月15日 上午11:28:49
	 */
	@RequestMapping("/unsettlement/info")
	@ResponseBody
	@AccessRequired(login=true)
	public Object queryUnsettlementInfo(){
		try {
			Map<String, Object> requestParam = getRequestParam();
			if(StringUtil.isBlank(requestParam.get("settlementMonth"))){
				return renderError("10000", "参数错误! settlementMonth不能为空。");
			}
			LoginUser loginUser = getSession();
			Integer userId = loginUser.getUserId();// 当前登录用户id
			String userType = loginUser.getUserType();// 当前登录用户id
			requestParam.put("userId", userId);
			requestParam.put("userType", userType);
			Map<String, Object> body = commissionLiquidationService.queryUnsettlementInfo(requestParam);
			return renderSuccess(body);
		} catch (Exception e) {
			return renderError("500", "查询待结算信息异常");
		}
	}
	
	/**
	 * 分页查询已结算列表
	 * @return
	 * @author zhoujie
	 * @date 2017年12月15日 上午11:29:31
	 */
	@RequestMapping("/settlement")
	@ResponseBody
	@AccessRequired(login=true)
	public Object getCommissionLiquidationList(){
		try {
			Map<String, Object> requestParam = getRequestParam();
			LoginUser loginUser = getSession();
			Integer userId = loginUser.getUserId();// 当前登录用户id
			requestParam.put("userId", userId);
			String pageNo = StringUtil.isNotBlank(requestParam.get("pageNo"))?requestParam.get("pageNo").toString():"1";
			String pageSize = StringUtil.isNotBlank(requestParam.get("pageSize"))?requestParam.get("pageSize").toString():"10";
			requestParam.put("pageNo", Integer.parseInt(pageNo));
			requestParam.put("pageSize", Integer.parseInt(pageSize));
			PageModel body = commissionLiquidationService.getCommissionLiquidationList(requestParam);
			return renderSuccess(body);
		} catch (Exception e) {
			return renderError("500", "分页查询已结算列表异常");
		}
	}
	
	/**
	 * 按年查询已结算列表
	 * @return
	 * @author zhoujie
	 * @date 2017年12月21日 下午4:03:17
	 */
	@RequestMapping("/settlement/list")
	@ResponseBody
	@AccessRequired(login=true)
	public Object queryCommissionLiquidationYearList(){
		try {
			Map<String, Object> requestParam = getRequestParam();
			if(StringUtil.isBlank(requestParam.get("settlementYear"))){
				return renderError("10000", "参数错误! settlementYear不能为空。");
			}
			LoginUser loginUser = getSession();
			Integer userId = loginUser.getUserId();// 当前登录用户id
			requestParam.put("userId", userId);
			List<Map<String,Object>> body = commissionLiquidationService.queryCommissionLiquidationYearList(requestParam);
			return renderSuccess(body);
		} catch (Exception e) {
			return renderError("500", "查询已结算列表异常");
		}
	}
	
	/**
	 * 分页查询出借奖金明细列表
	 * @return
	 * @author zhoujie
	 * @date 2017年12月15日 上午11:33:10
	 */
	@RequestMapping("/loanBonus/detail")
	@ResponseBody
	@AccessRequired(login=true)
	public Object getLoanBunusDetailList(){
		try {
			Map<String, Object> requestParam = getRequestParam();
			if(StringUtil.isBlank(requestParam.get("settlementMonth"))){
				return renderError("10000", "参数错误! settlementMonth不能为空。");
			}
			if(StringUtil.isBlank(requestParam.get("type"))){
				return renderError("10000", "参数错误! type不能为空。");
			}
			LoginUser loginUser = getSession();
			Integer userId = loginUser.getUserId();// 当前登录用户id
			requestParam.put("userId", userId);
			String pageNo = StringUtil.isNotBlank(requestParam.get("pageNo"))?requestParam.get("pageNo").toString():"1";
			String pageSize = StringUtil.isNotBlank(requestParam.get("pageSize"))?requestParam.get("pageSize").toString():"10";
			requestParam.put("pageNo", Integer.parseInt(pageNo));
			requestParam.put("pageSize", Integer.parseInt(pageSize));
			PageModel body  = commissionLiquidationService.getLoanBunusDetailList(requestParam);
			return renderSuccess(body);
		} catch (Exception e) {
			return renderError("500", "分页查询出借奖金明细列表异常");
		}
	}
	
	/**
	 * 分页查询差额奖金明细列表
	 * @return
	 * @author zhoujie
	 * @date 2017年12月15日 上午11:33:10
	 */
	@RequestMapping("/diffBonus/detail")
	@ResponseBody
	@AccessRequired(login=true)
	public Object getDiffBunusDetailList(){
		try {
			Map<String, Object> requestParam = getRequestParam();
			if(StringUtil.isBlank(requestParam.get("settlementMonth"))){
				return renderError("10000", "参数错误! settlementMonth不能为空。");
			}
			LoginUser loginUser = getSession();
			Integer userId = loginUser.getUserId();// 当前登录用户id
			requestParam.put("userId", userId);
			String pageNo = StringUtil.isNotBlank(requestParam.get("pageNo"))?requestParam.get("pageNo").toString():"1";
			String pageSize = StringUtil.isNotBlank(requestParam.get("pageSize"))?requestParam.get("pageSize").toString():"10";
			requestParam.put("pageNo", Integer.parseInt(pageNo));
			requestParam.put("pageSize", Integer.parseInt(pageSize));
			PageModel body  = commissionLiquidationService.getDiffBunusDetailList(requestParam);
			return renderSuccess(body);
		} catch (Exception e) {
			return renderError("500", "分页查询差额奖金明细列表异常");
		}
	}
	
	/**
	 * 分页查询服务奖金明细列表
	 * @return
	 * @author zhoujie
	 * @date 2017年12月15日 上午11:33:10
	 */
	@RequestMapping("/serviceBonus/detail")
	@ResponseBody
	@AccessRequired(login=true)
	public Object getServiceBunusDetailList(){
		try {
			Map<String, Object> requestParam = getRequestParam();
			if(StringUtil.isBlank(requestParam.get("settlementMonth"))){
				return renderError("10000", "参数错误! settlementMonth不能为空。");
			}
			if(StringUtil.isBlank(requestParam.get("type"))){
				return renderError("10000", "参数错误! type不能为空。");
			}
			LoginUser loginUser = getSession();
			Integer userId = loginUser.getUserId();// 当前登录用户id
			requestParam.put("userId", userId);
			String pageNo = StringUtil.isNotBlank(requestParam.get("pageNo"))?requestParam.get("pageNo").toString():"1";
			String pageSize = StringUtil.isNotBlank(requestParam.get("pageSize"))?requestParam.get("pageSize").toString():"10";
			requestParam.put("pageNo", Integer.parseInt(pageNo));
			requestParam.put("pageSize", Integer.parseInt(pageSize));
			PageModel body  = commissionLiquidationService.getServiceBunusDetailList(requestParam);
			return renderSuccess(body);
		} catch (Exception e) {
			return renderError("500", "分页查询服务奖金明细列表异常");
		}
	}
	
	/**
	 * 查询出借奖金明细总数（包括我的和我的下级）
	 * @return
	 * @author zhoujie
	 * @date 2017年12月19日 下午4:58:08
	 */
	@RequestMapping("/loanBonus/count")
	@ResponseBody
	@AccessRequired(login=true)
	public Object getLoanBunusDetailCount(){
		try {
			Map<String, Object> requestParam = getRequestParam();
			if(StringUtil.isBlank(requestParam.get("settlementMonth"))){
				return renderError("10000", "参数错误! settlementMonth不能为空。");
			}
			LoginUser loginUser = getSession();
			Integer userId = loginUser.getUserId();// 当前登录用户id
			requestParam.put("userId", userId);
			Map<String, Object> body  = commissionLiquidationService.getLoanBunusDetailCount(requestParam);
			return renderSuccess(body);
		} catch (Exception e) {
			return renderError("500", "查询出借奖金明细总数异常");
		}
	}
	
	/**
	 * 查询服务奖金明细总数（包括我的,我的下级和其他下级）
	 * @return
	 * @author zhoujie
	 * @date 2017年12月19日 下午5:01:04
	 */
	@RequestMapping("/serviceBonus/count")
	@ResponseBody
	@AccessRequired(login=true)
	public Object getServiceBunusDetailCount(){
		try {
			Map<String, Object> requestParam = getRequestParam();
			if(StringUtil.isBlank(requestParam.get("settlementMonth"))){
				return renderError("10000", "参数错误! settlementMonth不能为空。");
			}
			LoginUser loginUser = getSession();
			Integer userId = loginUser.getUserId();// 当前登录用户id
			requestParam.put("userId", userId);
			Map<String, Object> body  = commissionLiquidationService.getServiceBunusDetailCount(requestParam);
			return renderSuccess(body);
		} catch (Exception e) {
			return renderError("500", "查询出借奖金明细总数异常");
		}
	}
	
	/**
	 * 查询已结算年份列表
	 * @return
	 * @author zhoujie
	 * @date 2017年12月21日 下午3:40:28
	 */
	@RequestMapping("/settlement/year")
	@ResponseBody
	@AccessRequired(login=true)
	public Object querySettlementYearList(){
		try {
			Map<String, Object> requestParam = getRequestParam();
			LoginUser loginUser = getSession();
			Integer userId = loginUser.getUserId();// 当前登录用户id
			requestParam.put("userId", userId);
			List<String> body = commissionLiquidationService.querySettlementYearList(requestParam);
			return renderSuccess(body);
		} catch (Exception e) {
			return renderError("500", "查询已结算年份列表异常");
		}
	}
}
