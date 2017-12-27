package com.huizhongcf.mobile.api.utils;

import java.util.regex.Pattern;

 
public class ValidateUtils {
	/**
     * 正则表达式：验证非负整数
     */
    public static final String REGEX_NUMBER = "^\\d+$";
	/**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^(1[3,4,5,7,8])\\d{9}$";
 
    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    
    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";
    
    /**
     * 正则表达式：密码6到16位数字字母组合
     */
    public static final String REGEX_PASSWORD = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
    /**
     * 校验非负整数
     * @param mobile
     * @return
     * @author zhoujie
     * @date 2017年5月12日 下午2:54:36
     */
    public static boolean isNumber(String number) {
        return Pattern.matches(REGEX_NUMBER, number);
    }
    
    /**
     * 校验手机号
     * @param mobile
     * @return
     * @author zhoujie
     * @date 2017年5月12日 下午1:55:50
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }
 
    /**
     * 校验邮箱
     * @param email
     * @return 校验通过返回true，否则返回false
     * @author zhoujie
     * @date 2017年5月12日 下午1:55:19
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }
    public static boolean checkPassWord(String passWord) {
        return Pattern.matches(REGEX_PASSWORD, passWord);
    }
    
    public static void main(String[] args) {
    	String str = "38745630@qq.cn";
    	System.out.println(isEmail(str));
    	System.out.println(isMobile("18090070786"));
    	System.out.println(checkPassWord("tessss1"));
	}
}
