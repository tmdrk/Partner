package com.huizhongcf.util;


/**
 *<dl>
 *<dt>类名：SystemMessage.java</dt>
 *<dd>描述: 设置返回结果的工具类，系统消息详情</dd> 
 *<dd>创建时间： 2017年8月10日 下午2:18:36</dd>
 *<dd>创建人： peigaoxiang</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年8月10日 下午2:18:36    peigaoxiang       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
public enum SystemMessage {
	/**0000-0999  操作成功
	 * 1000-1999  操作失败
     * 2000-2999 参数错误
     * 3000-3999 参数不符合规范
	 * 4000-4999 ...
	 */
	/**操作成功*/	
	OPERATE_SUCCESS("0000","操作成功"),
	
	/**操作失败*/
	OPERATE_ERROR("1000","操作失败"),
	OPERATE_ERROR_EXCEPTION("1001","操作失败，发生异常"),
	OPERATE_ERROR_TIMEOUT("1002","操作失败，请求超时"),
	
	/**参数错误*/
	PARAMETER_LOST("2000","参数丢失,数据不完整"),
	PARAMETER_NULL("2001","参数不能为空"),
	PARAMETER_DATATYPE_NOT_MATCH("2002","参数类型不匹配,数据格式不正确"),
	
	
	/**参数不符合规范*/
	DATA_ERROR("3000","操作失败，"),
	
	FAIL_TIME_OUT("4000","请求链接已过期!"),

	FAIL_SIGNATURE("5000","验签失败!")
	
	;
	private String code;
	private String message;
	private SystemMessage(String code, String message) {
		this.code = code;
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}