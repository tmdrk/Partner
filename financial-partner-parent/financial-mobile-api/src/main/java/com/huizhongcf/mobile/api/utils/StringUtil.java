package com.huizhongcf.mobile.api.utils;

import org.apache.commons.lang.StringUtils;

public class StringUtil {
	/**
	 * 字符串隐藏部分内容方法
	 * 
	 * @param str
	 *            待处理字符串
	 * @param start
	 *            前面保留长度
	 * @param end
	 *            末尾保留长度
	 * @return 处理过的字符串
	 */
	public static String formatStringByRange(String str, int start, int end) {
		if (StringUtils.isEmpty(str) || str.length() <= start + end) {
			return str;
		}
		int startNum = str.length() - start - end;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			if (i >= start && i < start + startNum) {
				sb.append("*");
				continue;
			}
			sb.append(str.charAt(i));
		}
		return sb.toString();
	}

	/**
	 * 判断对象是否为空
	 * 
	 * @param obj
	 * @return
	 * @author zhoujie
	 * @date 2017年3月27日 下午2:13:29
	 */
	public static Boolean isBlank(Object obj) {
		if (null == obj || StringUtils.isBlank(obj.toString())) {
			return true;
		}
		return false;
	}

	/**
	 * 判断对象是否不为空
	 * 
	 * @param obj
	 * @return
	 * @author zhoujie
	 * @date 2017年3月27日 下午2:13:33
	 */
	public static Boolean isNotBlank(Object obj) {
		if (isBlank(obj)) {
			return false;
		}
		return true;
	}
}
