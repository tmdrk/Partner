package com.huizhongcf.mobile.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hui.zhong.cf.mobile.model.LoginUser;
import com.hui.zhong.cf.mobile.service.LoginUserService;
import com.huizhongcf.mobile.api.Interceptor.AccessRequired;
import com.huizhongcf.mobile.api.framework.BaseController;
import com.huizhongcf.mobile.api.utils.ConstantsUtils;
import com.huizhongcf.mobile.api.utils.ValidateUtils;
import com.huizhongcf.mobile.api.utils.VerifyCodeUtils;
import com.huizhongcf.util.NewSendMessageUtil;
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	@Autowired
	private LoginUserService loginUserService;
	/** 
	* @Description: 用户登录
	* @param @return     
	* @return Object    返回类型 
	* @author  shiyang
	* @date 2017年12月11日 上午11:34:10 
	*/
	@RequestMapping("/doLogin")
	@ResponseBody
	public Object doLogin(){
		Map<String, Object> requestParam = getRequestParam();
		String userName = String.valueOf(requestParam.get("userName"));
		String passWord = String.valueOf(requestParam.get("passWord"));
		HashMap<String,Object> body = new HashMap<String,Object>();
		LoginUser doLogin = loginUserService.doLogin(userName, passWord);
		if(doLogin != null){
			if("2".equals(doLogin.getUserStatus())){
				return renderError("100002", "用户禁止登录");
			}
			 String token = setSession(doLogin);
			 body.put("token", token);
			 return renderSuccess(body);
		}else{
			return renderError("100001", "用户名或密码错误");
		}
	}
	/** 
	* @Description: 检查用户是否存在 
	* @param @return     
	* @return Object    返回类型 
	* @author  shiyang
	* @date 2017年12月11日 上午11:34:23 
	*/
	@RequestMapping("/checkUserName")
	@ResponseBody
	public Object checkUserName(){
		Map<String, Object> requestParam = getRequestParam();
		String userName = String.valueOf(requestParam.get("userName"));
		boolean checkUserName = loginUserService.checkUserNameExist(userName);
		HashMap<String,Object> body = new HashMap<String,Object>();
		if(checkUserName){
			body.put("exist", "Y");
		}else{
			body.put("exist", "N");
		}
		return renderSuccess(body);
	}
	/** 
	* @Description: 注册 
	* @param @return     
	* @return Object    返回类型 
	* @author  shiyang
	* @date 2017年12月11日 上午11:35:24 
	*/
	@RequestMapping("/doRegister")
	@ResponseBody
	public Object doRegister(){
		Map<String, Object> requestParam = getRequestParam();
		String userName = String.valueOf(requestParam.get("userName"));
		if(!ValidateUtils.isMobile(userName)){
			return renderError("100104", "手机号码错误");
		}
		String passWord = String.valueOf(requestParam.get("passWord"));
		if(!ValidateUtils.checkPassWord(passWord)){
			return renderError("100103", "密码格式不正确");
		}
		String smsCode = String.valueOf(requestParam.get("smsCode"));
		String key = "10_"+userName;
		if(!(StringUtils.isNoneBlank(smsCode)&& smsCode.equals(jedisClientUtil.get(key)))){
			return renderError("100101", "短信验证码错误");
		}
		boolean checkUserName = loginUserService.checkUserNameExist(userName);
		HashMap<String,Object> body = new HashMap<String,Object>();
		if(checkUserName){
			return renderError("100102", "手机号码存在");
		} 
		String recommendCode = String.valueOf(requestParam.get("recommendCode"));
		LoginUser doRegister = loginUserService.doRegister(userName, passWord, recommendCode);
		if(null == doRegister) {
			return renderError("500", "注册失败");
		}
		jedisClientUtil.del(key);
		//设置session 返回token
		body.put("token", setSession(doRegister));
		return renderSuccess(body);
	}
	/** 
	* @Description: 忘记密码
	* @param @return     
	* @return Object    返回类型 
	* @author  shiyang
	* @date 2017年12月11日 上午11:35:32 
	*/
	@RequestMapping("/forgetPassWord")
	@ResponseBody
	public Object forgetPassWord(){
		Map<String, Object> requestParam = getRequestParam();
		String userName = String.valueOf(requestParam.get("userName"));
		String newPassWord = String.valueOf(requestParam.get("newPassWord"));
		if(!ValidateUtils.checkPassWord(newPassWord)){
			return renderError("100303", "新密码格式不正确");
		}
		String smsCode = String.valueOf(requestParam.get("smsCode"));
		String key = "11_"+userName;
		if(!(StringUtils.isNoneBlank(smsCode)&& smsCode.equals(jedisClientUtil.get(key)))){
			return renderError("100302", "短信验证码错误");
			
		}
		loginUserService.forgetPassWord(userName, newPassWord);
		jedisClientUtil.del(key);
		return renderSuccess(null);
	}
	
	/** 
	* @Description:  修改密码
	* @return     
	* @return Object   
	* @throws 
	* @author shiyang 
	* @date 2017年12月21日 下午2:48:40  
	*/
	@RequestMapping("/modifyPassWord")
	@ResponseBody
	@AccessRequired(login=true)
	public Object modifyPassWord(){
		Map<String, Object> requestParam = getRequestParam();
		String userName = getSession().getUsername();
		String newPassWord = String.valueOf(requestParam.get("newPassWord"));
		String oldPassWord = String.valueOf(requestParam.get("oldPassWord"));
		if(!ValidateUtils.checkPassWord(newPassWord)){
			return renderError("100303", "新密码格式不正确");
		}
		LoginUser doLogin = loginUserService.doLogin(userName, oldPassWord);
		if(null == doLogin) {
			renderError("100203", "原始密码错误");
		}
		loginUserService.forgetPassWord(userName, newPassWord);
//		jedisClientUtil.del(key);
		return renderSuccess(null);
	}
	
	/** 
	* @Description: 发送短信 type  10：注册11:忘记密码12：修改密码
	* @param @return     
	* @return Object    返回类型 
	* @author  shiyang
	* @date 2017年12月11日 上午11:53:26 
	*/
	@RequestMapping("/sendMsg")
	@ResponseBody
	public Object sendMsg(){
		Map<String, Object> requestParam = getRequestParam();
		String userName = String.valueOf(requestParam.get("userName"));
		if(!ValidateUtils.isMobile(userName)){
			return renderError("100401", "手机号错误");
		}
		String type = String.valueOf(requestParam.get("type"));
		//发送 key
		String sendKey = type+"_"+userName;
		
		//防止重复点击
		String brushTime = sendKey+"_brushTime";
		if(StringUtils.isNotBlank(jedisClientUtil.get(brushTime))){
			return renderError("100402", "频率过高");
		}
		String code ="123456";
		if(ConstantsUtils.isProd) {
			code = VerifyCodeUtils.generateVerifyCode(6, "0123456789");
			
		}
		boolean sendType=true ;
		String msgContent="";
		switch (type) {
		case "10":
//			注册
			msgContent = String.format(ConstantsUtils.Msg.REG_MEG,code);
			break;
		case "11":
//			忘记密码
			msgContent = String.format(ConstantsUtils.Msg.FORGETPWD_MEG,code);
			break;

		default:
			return renderError("10000", "参数错误");
		}
		log.info("手机号："+userName+"  短信内容： "+msgContent);
		if(ConstantsUtils.isProd) {
		   sendType = NewSendMessageUtil.sendSmsProduct(userName, msgContent);
		}
		if(sendType) {
			//过期时间
			//有效期 秒
			int exists = ConstantsUtils.Msg.MES_EXPIRE;
			jedisClientUtil.set(sendKey, code, exists);
			//刷新时间
			//30秒发一次
			int existsBrush = ConstantsUtils.Msg.MES_BRUSH;
			jedisClientUtil.set(brushTime, code, existsBrush);
			return renderSuccess(null);
		}else {
			return renderError("100403", "发送失败");
		}
	}
	@RequestMapping("/info")
	@ResponseBody
	@AccessRequired(login=true)
	public Object info() {
		Integer userId = getSession().getUserId();
		  Map<String, Object> userInfoById = loginUserService.getUserInfoById(userId);
		Map<String,String> retMap = new HashMap<String,String>();
		String userName = String.valueOf(userInfoById.get("username"));
		userName = userName.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
		Object idCard = userInfoById.get("card_code");
		if(null != userInfoById.get("real_name")) {
			retMap.put("realName", String.valueOf(userInfoById.get("real_name")));
		}
		if(null != idCard) {
			String idCardStr = idCard.toString().replaceAll("(\\d{6})\\d{8}(\\w{4})","$1********$2");
			retMap.put("idCard", idCardStr);
		}else {
			retMap.put("idCard", "");
		}
		retMap.put("partnerRecommend",String.valueOf(userInfoById.get("partner_recommend_code")));
		retMap.put("customerRecommend",String.valueOf(userInfoById.get("customer_recommend_code")));
		retMap.put("userName", userName);
		return renderSuccess(retMap);
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	@AccessRequired(login=true)
	public Object logout() {
		removeSession();
		return renderSuccess(null);
	}	
	@RequestMapping("/picCode")
	@ResponseBody
	public void picCode(Integer width,Integer height,HttpServletResponse response,Integer type){
		try {
			String imgCode = VerifyCodeUtils.generateVerifyCode(4, "0123456789");
			type = type==null?1:type;
			 if(type == 1){
				 //注册
				 getRequest().getSession().setAttribute("", imgCode);;
			 }else if(type == 2){
				 //忘记密码
				 getRequest().getSession().setAttribute("", imgCode);;
			 }else if(type == 3){
				 //登录
				 getRequest().getSession().setAttribute("", imgCode);;
			 }
			if (width == null)
				width = 94;
			if (height == null)
				height = 32;
			VerifyCodeUtils.outputImage(width, height,response.getOutputStream(), imgCode);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}
