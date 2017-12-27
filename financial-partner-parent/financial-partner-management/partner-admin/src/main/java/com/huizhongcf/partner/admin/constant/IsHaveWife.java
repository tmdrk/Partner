package com.huizhongcf.partner.admin.constant;

/**
 * 
 * Description: 婚姻状况
 *
 * @author baohongjian
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年6月29日    bao       1.0        1.0 Version 
 * </pre>
 */
public enum IsHaveWife {
	
	Single("01","单身"),Married("02","已婚"),NotMarried("03","未婚"),Divorce("离异","04");
	
	private String code;
	private String msg;
	
	IsHaveWife(String code,String msg){
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
