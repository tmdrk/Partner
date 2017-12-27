package com.hui.zhong.cf.job.model;

import java.io.Serializable;
import java.util.Date;

public class LogData implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer employeeId;//员工id

    private String employeeNo;//员工编号

    private String operationType;//操作类型：增加，修改，删除，导入，导出)

    private String tradeType;//操作名称(页面按钮操作)
    
    private String reqData;//传递参数(json类型数据)

    private String resultData;//响应报文(请求和回调接口的响应,如果接口请求失败则为null,如果是回调接口该字段不能为null)

    private Date createTime;//创建时间

	public LogData() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getReqData() {
		return reqData;
	}

	public void setReqData(String reqData) {
		this.reqData = reqData;
	}

	public String getResultData() {
		return resultData;
	}

	public void setResultData(String resultData) {
		this.resultData = resultData;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "LogData [id=" + id + ", employeeId=" + employeeId + ", employeeNo=" + employeeNo + ", operationType="
				+ operationType + ", tradeType=" + tradeType + ", reqData=" + reqData + ", resultData=" + resultData
				+ ", createTime=" + createTime + "]";
	}

}