package com.huizhongcf.mobile.api.utils;

/**
 * 接口常亮类
 * @author hzcf
 *
 */
public class ConstantsUtils {
	/**
	 * 是否生产
	 */
	public static boolean isProd = false;
	
	public static class Session{
		/**
		 * session 存放路径
		 */
		public static final String SESSION_MOBILE_API_KEY =  "seesion_mobile_api_login_";
		/**
		 * session 失效时间
		 */
		public static final int SESSION_TOKEN_EXPIRE =  60*30;
		
	}
	public static class Msg {
		/**
		 * 短信有效期限（秒）
		 */
		public static final int MES_EXPIRE =  60*3;
		
		/**
		 * 短信防刷机制（秒）同渠道同手机号
		 */
		public static final int MES_BRUSH = 30;
		/**
		 * 注册短信
		 */
		public static String REG_MEG = "汇友钱平台找回登录密码验证码：%s，3分钟内有效，请妥善保存";
		/**
		 * 忘记密码
		 */
		public static String FORGETPWD_MEG = "汇友钱平台注册验证码：%s，3分钟内有效，请妥善保存！";
	}
	 
}