package com.hui.zhong.cf.mobile.utils;

import com.huizhongcf.util.MD5Util;

public class EncryptionUtil {
	/** 
	* @Description: 密码加密
	* @param pwd
	* @return     
	* @return String   
	* @throws 
	* @author shiyang 
	* @date 2017年12月14日 下午2:57:06  
	*/
	public static String passWord(String pwd) {
		return MD5Util.md5(MD5Util.md5(pwd)+PropUtils.get("passwod_key"));
	}
	 public static void main(String[] args) {
		System.out.println(passWord("test1234"));
	}
}
