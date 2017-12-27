package com.huizhongcf.util;

import java.io.Serializable;
import java.util.List;

public class ReturnMsgData implements Serializable{

	private static final long serialVersionUID = 5014607822539057104L;
	private String returnCode;// 返回状态码
	private String returnMsg;// 返回状态描述信息
	private List<String> successIds;//操作成功的集合
	private List<String> failIds;//操作失败的集合
	private Object data;// 返回参数表
	public ReturnMsgData() {
		super();
	}
	public ReturnMsgData(String returnCode, String returnMsg, Object data) {
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
		this.data = data;
	}

	public ReturnMsgData(String returnCode, String returnMsg) {
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
	}

	public ReturnMsgData(String returnCode, String returnMsg, List<String> successIds, List<String> failIds, Object data) {
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
		this.successIds = successIds;
		this.failIds = failIds;
		this.data = data;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<String> getSuccessIds() {
		return successIds;
	}

	public void setSuccessIds(List<String> successIds) {
		this.successIds = successIds;
	}

	public List<String> getFailIds() {
		return failIds;
	}

	public void setFailIds(List<String> failIds) {
		this.failIds = failIds;
	}

	@Override
	public String toString() {
		return "ReturnMsgData{" +
				"returnCode='" + returnCode + '\'' +
				", returnMsg='" + returnMsg + '\'' +
				", successIds=" + successIds +
				", failIds=" + failIds +
				", data=" + data +
				'}';
	}
}
