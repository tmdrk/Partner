package com.huizhongcf.util;

/** 
 *<dl> 
 *<dt>类名：Constants</dt> 
 *<dd>描述: 系统常量</dd>  
 *<dd>创建时间： 2017-8-9 15:19:43</dd> 
 *<dd>创建人： haochunhe</dd> 
 *<dt>版本历史: </dt> 
 * <pre> 
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2017-8-9 15:19:43    haochunhe       2.0        2.0 Version  
 * </pre> 
 *</dl> 
 */ 
public class Constants {
	
	public static final String ACTIVATED_STATE_DISABLE = "0";//员工用户使用状态：0:停用
	
	public static final String ACTIVATED_STATE_ENABLED = "1";//员工用户使用状态：1:启用
	
	public static final String EMPLOYEE_SEX_NAN = "0";//员工性别 0是男， 1是女
	public static final String EMPLOYEE_SEX_NV = "1";
	
	public static final String SYSTEM_CODE = "zhywlc"; /** 理财端的系统标识*/
	
	public static final String Type_Code_QYJL = "01"; //区域经理
	public static final String Type_Code_CSJL = "02"; //城市经理
	public static final String Type_Code_YYBJL = "03"; //营业部经理
	public static final String Type_Code_TDJL = "04"; //团队经理
	public static final String Type_Code_ZXZY = "05"; //咨询专员
	public static final String Type_Code_ZND = "06"; //职能端
	
	
	
	public static final String LIQUIDATION_STATUS_DJS = "1";//结算状态待结算
	public static final String LIQUIDATION_STATUS_YJS = "2";//结算状态已结算
	
	/**投资表中的订单状态*/
	public static final String INVEST_STATUS_1 = "1";//投资状态，1：预约中，2：计息中，3：已结清，4：作废
	public static final String INVEST_STATUS_2 = "2";//投资状态，1：预约中，2：计息中，3：已结清，4：作废
	public static final String INVEST_STATUS_3 = "3";//投资状态，1：预约中，2：计息中，3：已结清，4：作废
	public static final String INVEST_STATUS_4 = "4";//投资状态，1：预约中，2：计息中，3：已结清，4：作废

	/**奖金类型*/
	public static final String BONUS_TYPE_10 = "10";//奖金类型，10：出借奖金，20：差额奖金，30：服务奖金
	public static final String BONUS_TYPE_20 = "20";//奖金类型，10：出借奖金，20：差额奖金，30：服务奖金
	public static final String BONUS_TYPE_30 = "30";//奖金类型，10：出借奖金，20：差额奖金，30：服务奖金

	/**用户角色*/
	public static final String USER_ROLE_10 = "10";//用户角色，10：合伙人，20：客户
	public static final String USER_ROLE_20 = "20";//用户角色，10：合伙人，20：客户

	/**封存状态*/
	public static final String SEAL_STATUS_0 = "0";//封存状态，0：未封存，1：已封存
	public static final String SEAL_STATUS_1 = "1";//封存状态，0：未封存，1：已封存

	/**结算状态*/
	public static final String LIQUIDATION_STATUS_10 = "10";//结算状态，10：已结算，20：已支付
	public static final String LIQUIDATION_STATUS_20 = "20";//结算状态，10：已结算，20：已支付

	/**系统标识*/
	public static final String SYSTEM_CODE_01 = "01";//系统标识，01：汇中网，20：合伙人
	public static final String SYSTEM_CODE_02 = "02";//系统标识，02：汇中网，20：合伙人


    public static final int PAGE_SIZE = 10;



}