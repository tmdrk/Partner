package com.huizhongcf.partner.api.controller;

import java.util.HashMap;
import java.util.Map;

import com.hui.zhong.cf.service.partner.service.InterfaceLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.json.ParseException;
import com.hui.zhong.cf.service.partner.model.Partner;
import com.hui.zhong.cf.service.partner.service.PartnerOrderService;
import com.hui.zhong.cf.service.partner.service.PartnerService;
import com.huizhongcf.util.CommonUtil;
import com.huizhongcf.util.ReturnMsgData;

/**
 * 
 *
 * Description: 汇中网对外接口
 *
 * @author baohongjian
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年12月12日    bao       1.0        1.0 Version 
 * </pre>
 */
@Controller
@RequestMapping("/partner")
public class PartnerController {

	private static final Logger LOG = LoggerFactory.getLogger(PartnerController.class);

	@Autowired 
	private PartnerService partnerservice;
	@Autowired
	private PartnerOrderService partnerOrderService;//合伙人订单service
	@Autowired
	private InterfaceLogService interfaceLogService;//日志表Service
	@ResponseBody
	@RequestMapping("/find")
	 public ReturnMsgData find(){
		 LOG.info("运行");
		 ReturnMsgData date = new ReturnMsgData("00000","成功");
		 return date;
	 } 

	 /**
	  * 
	  * Description: 汇中网向合伙人同步注册用户
	  *
	  * @param 
	  * @return Object
	  * @throws ParseException 
	  * @throws 
	  * @Author bao
	  * Create Date: 2017年12月11日 下午4:39:12
	  */

	@RequestMapping(value="/register")
	@ResponseBody
	public ReturnMsgData register() throws ParseException{
		String body = CommonUtil.getBody();
		LOG.info("汇中网推送注册用户信息,包体如下:body="+body);
		ReturnMsgData data = partnerservice.SaveRegister(body);
		return data;
	}


	   /**
	   * 
	   * Description: 汇中网向合伙人同步用户实名信息
	   *
	   * @param 
	   * @return Object
	   * @throws 
	   * @Author bao
	   * Create Date: 2017年12月11日 下午5:09:38
	   */
		@RequestMapping("/userRealInfo")
		@ResponseBody
		public Object userRealInfo(){
			String body = CommonUtil.getBody();
			LOG.info("(partner-api服务，更新用户实名认证信息)userRealInfo方法，汇中网推送的用户实名信息body："+body);
			/**参数是否为空，是否符合要求，api服务和dubbo服务不做验证**/
			ReturnMsgData returnMsgData = partnerOrderService.updateUserRealInfo(body);
			if(returnMsgData!=null){
				LOG.info("(partner-api服务，更新用户实名认证信息)userRealInfo方法，dubbo服务返回结果："+returnMsgData.toString());
			}else{
				LOG.info("(partner-api服务，更新用户实名认证信息)userRealInfo方法，dubbo服务返回结果："+null);
			}
			return returnMsgData;
		}


	    /**
	    * Description: 汇中网向合伙人同步订单信息
	    * @param
	    * @return Object
	    * @throws 
	    * @Author bao
	    * Create Date: 2017年12月11日 下午5:11:51
	    */
		@RequestMapping("/orderInfo")
		@ResponseBody
		public Object orderInfo(){
			String body = CommonUtil.getBody();
			LOG.info("(partner-api服务，批量保存订单)orderInfo方法，汇中网推送的订单数据body："+body);
			/**参数是否为空，是否符合要求，api服务和dubbo服务不做验证**/
			ReturnMsgData returnMsgData = partnerOrderService.insertOrderInfo(body);
			if(returnMsgData!=null){
				LOG.info("(partner-api服务，批量保存订单)orderInfo方法，dubbo服务返回结果："+returnMsgData.toString());
			}else{
				LOG.info("(partner-api服务，批量保存订单)orderInfo方法，dubbo服务返回结果："+null);
			}
			return returnMsgData;
		}

		/**
		  *
		  * Description: 汇中网向合伙人同步订单状态
		  *
		  * @param
		  * @return Object
		  * @throws
		  * @Author bao
		  * Create Date: 2017年12月11日 下午5:12:52
		 */
		@RequestMapping("/orderStatus")
		@ResponseBody
		public Object orderStatus(){
			String body = CommonUtil.getBody();
			LOG.info("(partner-api服务，批量更改订单状态)orderStatus方法，汇中网推送的订单数据body："+body);
			/**参数是否为空，是否符合要求，api服务和dubbo服务不做验证**/
			ReturnMsgData returnMsgData = partnerOrderService.updateOrderStatus(body);
			if(returnMsgData!=null){
				LOG.info("(partner-api服务，批量更改订单状态)orderStatus方法，dubbo服务返回结果："+returnMsgData.toString());
			}else{
				LOG.info("(partner-api服务，批量更改订单状态)orderStatus方法，dubbo服务返回结果："+null);
			}
			return returnMsgData;
		}

}
