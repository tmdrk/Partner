package com.huizhongcf.util;

import org.apache.commons.lang.StringUtils;

/**
 * 对某些敏感信息脱敏。
 * 
 * @author zhangfei
 * @date 2017年12月11日
 */
public class MaskUtils {
	private MaskUtils() {}
	
	/**
	 * 对姓名脱敏，隐藏名。
	 * 
	 * @param name
	 * @return
	 */
	public static String maskName(String name) {
		if (StringUtils.isEmpty(name) || name.length() == 1) {
			return name;
		}
		
		StringBuilder builder = new StringBuilder(name.charAt(0));
		for (int i = 0; i < name.length() - 1; i++) {
			builder.append('*');
		}
		return builder.toString();
	}
	
	/**
	 * 对手机号脱敏，隐藏中间四位。
	 * 
	 * @return
	 */
	public static String maskMobile(String mobile) {
		if (StringUtils.isEmpty(mobile) || mobile.length() != 11) {
			return mobile;
		}
		
		StringBuilder builder = new StringBuilder();
		builder.append(mobile.substring(0, 3)).append("****").append(mobile.substring(7));
		return builder.toString();
	}
}
