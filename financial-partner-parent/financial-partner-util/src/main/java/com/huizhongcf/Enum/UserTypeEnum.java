package com.huizhongcf.Enum;

/**
 * 
 * Description: 301:代表合伙人,601:代表客户
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
public enum UserTypeEnum {
	//301:代表合伙人,601:代表客户
	customer("601","客户"),partner("301","合伙人");
	
	private String type;
	private String des;
	
	UserTypeEnum(String type,String des){
		this.type = type;
		this.des = des;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

}
