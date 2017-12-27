package com.hui.zhong.cf.job.model;

import java.io.Serializable;
import java.util.Date;

public class InterfaceLog implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String tradeType; //接口类型

    private String reqData; // 请求报文

    private String respResult; //响应报文

    private String systemDocking; //系统对接标识
    
    private Date createTime;//创建时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getRespResult() {
        return respResult;
    }

    public void setRespResult(String respResult) {
        this.respResult = respResult;
    }

    public String getSystemDocking() {
        return systemDocking;
    }

    public void setSystemDocking(String systemDocking) {
        this.systemDocking = systemDocking;
    }

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}