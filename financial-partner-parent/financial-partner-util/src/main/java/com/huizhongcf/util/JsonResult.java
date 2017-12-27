package com.huizhongcf.util;

 

 
/**
 *<dl>
 *<dt>类名：JsonResult.java</dt>
 *<dd>描述:  用户后台向前台返回的JSON对象</dd> 
 *<dd>创建时间： 2017年8月9日 下午3:41:49</dd>
 *<dd>创建人： Gaofeng</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年8月9日 下午3:41:49    Gaofeng       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
public class JsonResult implements java.io.Serializable {

	private static final long serialVersionUID = 2349487124465234825L;

	private boolean success = false;

	private String msg = "";

	private Object obj = null;

	public JsonResult(boolean success, String msg, Object obj) {
		this.success = success;
		this.msg = msg;
		this.obj = obj;
	}

	public JsonResult(boolean success) {
		this.success = success;
	}

	public JsonResult() {

	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}
