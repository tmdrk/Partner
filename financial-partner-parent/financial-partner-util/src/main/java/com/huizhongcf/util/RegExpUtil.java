package com.huizhongcf.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 *
 * Description:正则表达式工具类
 *
 * @author yubin
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2012-9-12    Administrator       1.0        1.0 Version 
 * </pre>
 */
public class RegExpUtil {
	
	/** Email表达式 */
	//private static final String EXP_EMAIL = "[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+";
	private static final String EXP_EMAIL = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
	
	/** 正整数表达式 */
	private static final String EXP_POSITIVE_INTEGER = "^\\d+$";
	
	private static final String EXP_POSITIVE_INTEGER_NO = "^\\d{12}+$";
	private static final String EXP_POSITIVE_INTEGER_CODE = "^\\d{11}+$";
	
	/** 整数 */
	private static final String EXP_INTEGER = "^-?\\d+$";
	
	/** 非负浮点数*/
	private static final String EXP_NOT_NEGATIVE_FLOAT = "^\\d+(\\.\\d+)?$";
	
	/** 正浮点数 */
	private static final String EXP_POSITIVE_FLOAT = "^((\\[0-9\\]+\\.\\[0-9\\]*\\[1-9\\]\\[0-9\\]*)\\|(\\[0-9\\]*\\[1-9\\]\\[0-9\\]*\\.\\[0-9\\]+)\\|(\\[0-9\\]*\\[1-9\\]\\[0-9\\]*))$";
	
	/** 由26个英文字母组成的字符串 */
	private static final String EXP_ENGLIS_CHAR_STRING = "^\\[A-Za-z\\]+$";
	
	/** 由26个英文字母的大写组成的字符串 */
	private static final String EXP_ENGLIS_UPPER_CHAR_STRING = "^\\[A-Z\\]+$";
	
	/**由26个英文字母的小写组成的字符串*/
	private static final String EXP_ENGLIS_LOWER_CHAR_STRING = "^\\[a-z\\]+$";
	
	/**由数字和26个英文字母组成的字符串*/
	private static final String EXP_NUMBER_AND_EN_STRING = "^\\[A-Za-z0-9\\]+$";
	
	/**由数字、26个英文字母或者下划线组成的字符串*/
	private static final String EXP_COMMON_EN_STRING = "^\\w+$";
	
	/**手机号*/
	private static final String EXP_MOBILE = "^0?1[3|4|5|7|8][0-9]\\d{8}$";

    /**
     * 密码：6到16位数字字母组合
     */
    private static final String EXP_PASSWORD = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";

	private static boolean isRight(String input, String regExp) {
		Pattern pattern = Pattern.compile(regExp);
		Matcher matcher = pattern.matcher(input);
		return matcher.find();
	}

    /**
     * 校验 密码
     * @param input 需要检验的密码
     * @return
     */
    public static boolean isPassword(String input){
        return isRight(input, EXP_PASSWORD);
    }
	
	/**
	 * 邮箱校验
	 * @param input
	 * @return
	 */
	public static boolean isEmail(String input) {
		return isRight(input, EXP_EMAIL);
	}
	
	/**
	 * 正整数校验
	 * @param input
	 * @return
	 */
	public static boolean isPositiveInteger(String input) {
		return isRight(input, EXP_POSITIVE_INTEGER);
	}
	/**
	 * 正整数校验No
	 * @param input
	 * @return
	 */
	public static boolean isPositiveIntegerNo(String input) {
		return isRight(input, EXP_POSITIVE_INTEGER_NO);
	}
	/**
	 * 正整数校验Code
	 * @param input
	 * @return
	 */
	public static boolean isPositiveIntegerCode(String input) {
		return isRight(input, EXP_POSITIVE_INTEGER_CODE);
	}
	
	/**
	 * 整数校验
	 * @param input
	 * @return
	 */
	public static boolean isInteger(String input) {
		return isRight(input, EXP_INTEGER);
	}
	
	/**
	 * 非负浮点数校验
	 * @param input
	 * @return
	 */
	public static boolean isNotNegativeFloat(String input) {
		return isRight(input, EXP_NOT_NEGATIVE_FLOAT);
	}
	
	/**
	 * 正浮点数校验
	 * @param input
	 * @return
	 */
	public static boolean isPositiveFloat(String input) {
		return isRight(input, EXP_POSITIVE_FLOAT);
	} 
	
	/**
	 * 校验 由26个英文字母组成的字符串
	 * @param input
	 * @return
	 */
	public static boolean isEnglisCharString(String input) {
		return isRight(input, EXP_ENGLIS_CHAR_STRING);
	}
	
	/**
	 * 校验 由26个大写英文字母组成的字符串
	 * @param input
	 * @return
	 */
	public static boolean isEnglisUpperCharString(String input) {
		return isRight(input, EXP_ENGLIS_UPPER_CHAR_STRING);
	}
	
	/**
	 * 校验 由26个小写英文字母组成的字符串
	 * @param input
	 * @return
	 */
	public static boolean isEnglisLowerCharString(String input) {
		return isRight(input, EXP_ENGLIS_LOWER_CHAR_STRING);
	}
	
	/**
	 * 校验 由数字和26个英文字母组成的字符串
	 * @param input
	 * @return
	 */
	public static boolean isNumberAndEnString(String input) {
		return isRight(input, EXP_NUMBER_AND_EN_STRING);
	}
	
	/**
	 * 校验 由数字、26个英文字母或者下划线组成的字符串
	 * @param input
	 * @return
	 */
	public static boolean isCommonEnString(String input) {
		return isRight(input, EXP_COMMON_EN_STRING);
	}
	
	/**
	 * 校验 手机号码
	 * @param input
	 * @return
	 */
	public static boolean isMobile(String input){
		return isRight(input, EXP_MOBILE);
	}
	
	public static void main(String[] args) {
//		System.out.println(isEmail("12@a.com"));
		System.out.println(isPositiveIntegerNo("123456789111"));
	}
}
