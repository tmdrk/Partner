package hui.zhong.cf.partner.h5.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import hui.zhong.cf.partner.h5.constants.MobileApiUrls;
import hui.zhong.cf.partner.h5.framework.BaseController;
/**
 * 
 *<dl>
 *<dt>类名：AwardController.java</dt>
 *<dd>描述: ~奖励详情controller</dd> 
 *<dd>创建时间： 2017年12月21日 下午2:34:11</dd>
 *<dd>创建人： GuoDong</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年12月21日 下午2:34:11    GuoDong       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Controller
@RequestMapping("/award")
public class AwardController extends BaseController {
	
	
	@RequestMapping("/toAwardsDetail")
    public String toAwardsDetail() {
        logger.info("跳转到奖励详情页");
        return "awardsDetail";
    }
	@RequestMapping("/toLoanBonus")
	public String toLoanBonus() {
		logger.info("跳转到出借奖金明细页");
		return "loanBonus";
	}
	
	@RequestMapping("/toDifferenceBonus")
	public String toDifferenceBonus() {
		logger.info("跳转到差额奖金明细页");
		return "differenceBonus";
	}
	
	@RequestMapping("/toServiceBonus")
	public String toServiceBonus() {
		logger.info("跳转到奖励详情页");
		return "serviceBonus";
	}
	
	@RequestMapping("/toRule")
	public String toRule() {
		logger.info("跳转到奖励规则页");
		return "awardsDetail";
	}
	
	/**
	 * Description: 获取待结算月份列表
	 * @return
	 * @author guodong
	 */
	@RequestMapping("/getMonthList")
	@ResponseBody
    public Object getMonthList(Map <String,Object> jsonMap) {
		String url = String.format(MobileApiUrls.monthList, MOBILE_API_SERVER_DOMAIN);
		String loggerMsg = "待结算月份列表接口";
		Map <String,String> reqMap = new HashMap<String, String>(); 
		reqResult(url, loggerMsg, reqMap, jsonMap);
        return jsonMap;
    }
	
	
	@RequestMapping("/test")
	@ResponseBody
    public Object test(Map <String,Object> jsonMap) {
		jsonMap.put("test", "test");
        return jsonMap;
    }
	/**
	 * Description: 获取待结算列表
	 * @return
	 * @author guodong
	 */
	@RequestMapping("/getUnSettlementList")
	@ResponseBody
    public Object getWaitPayList(HttpServletRequest request,Map <String,Object> jsonMap) {
		String url = String.format(MobileApiUrls.unSettlementList, MOBILE_API_SERVER_DOMAIN);
		String loggerMsg = "待结算列表接口";
		Map <String,String> reqMap = new HashMap<String, String>(); 
		reqMap.put("settlementMonth", request.getParameter("settlementMonth"));
		reqResult(url, loggerMsg, reqMap, jsonMap);
        return jsonMap;
    }
	
	/**
	 * Description: 获取出借奖金明细
	 * @param request
	 * @param jsonMap
	 * @return
	 * @author guodong
	 */
	@RequestMapping("/getLoanBonusList")
	@ResponseBody
	public Object getLoanBonusList(HttpServletRequest request,Map <String,Object> jsonMap) {
		String url = String.format(MobileApiUrls.loanBonusDetail, MOBILE_API_SERVER_DOMAIN);
		String loggerMsg = "出借奖金明细接口";
		Map <String,String> reqMap = new HashMap<String, String>(); 
		reqMap.put("settlementMonth", request.getParameter("settlementMonth"));
		reqMap.put("type", request.getParameter("type"));
		reqMap.put("pageNo", request.getParameter("pageNo"));
		reqMap.put("pageSize", request.getParameter("pageSize"));
		reqResult(url, loggerMsg, reqMap, jsonMap);
		return jsonMap;
	}
	
	/**
	 * Description: 获取差额奖金明细
	 * @param request
	 * @param jsonMap
	 * @return
	 * @author guodong
	 */
	@RequestMapping("/getDiffBonusList")
	@ResponseBody
	public Object getDiffBonusList(HttpServletRequest request,Map <String,Object> jsonMap) {
		String url = String.format(MobileApiUrls.diffBonusDetail, MOBILE_API_SERVER_DOMAIN);
		String loggerMsg = "差额奖金明细接口";
		Map <String,String> reqMap = new HashMap<String, String>(); 
		reqMap.put("settlementMonth", request.getParameter("settlementMonth"));
		reqMap.put("pageNo", request.getParameter("pageNo"));
		reqMap.put("pageSize", request.getParameter("pageSize"));
		reqResult(url, loggerMsg, reqMap, jsonMap);
		return jsonMap;
	}
	
	/**
	 * Description: 获取服务奖金明细
	 * @param request
	 * @param jsonMap
	 * @return
	 * @author guodong
	 */
	@RequestMapping("/getServiceBonusList")
	@ResponseBody
	public Object getServiceBonusList(HttpServletRequest request,Map <String,Object> jsonMap) {
		String url = String.format(MobileApiUrls.serviceBonusDetail, MOBILE_API_SERVER_DOMAIN);
		String loggerMsg = "服务奖金明细接口";
		Map <String,String> reqMap = new HashMap<String, String>(); 
		reqMap.put("settlementMonth", request.getParameter("settlementMonth"));
		reqMap.put("type", request.getParameter("type"));
		reqMap.put("pageNo", request.getParameter("pageNo"));
		reqMap.put("pageSize", request.getParameter("pageSize"));
		reqResult(url, loggerMsg, reqMap, jsonMap);
		return jsonMap;
	}
	
	/**
	 * Description: 共用请求接口方法
	 * @param url 接口地址
	 * @param loggerMsg  日志信息
	 * @param reqMap 请求参数
	 * @param jsonMap	处理完的请求结果
	 * @author guodong
	 */
	public void reqResult(String url, String loggerMsg, Map<String, String> reqMap, Map<String, Object> jsonMap) {
		try {
			reqMap.put("token", getToken());
			logger.info(loggerMsg + "请求参数：" + JSON.toJSONString(reqMap));
			String resultJson = get(url, reqMap);
			logger.info(loggerMsg + "响应参数：" + resultJson);
			@SuppressWarnings("unchecked")
			Map <String, Object> resultMap = (Map<String, Object>) JSON.parse(resultJson);
			String errorCode = (String) resultMap.get("errorCode");
			jsonMap.put("code", errorCode);
			if("200".equals(errorCode)){
				Object object = resultMap.get("body");
				jsonMap.put("bodys", object);
			}
		} catch (Exception e) {
			jsonMap.put("code", 999);
			logger.info(loggerMsg +"调用报错");
			e.printStackTrace();
		}
	}

}
