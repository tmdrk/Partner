package com.hui.zhong.cf.job.model;

import java.io.Serializable;
import java.util.Date;

public class ReservedInviteCode implements Serializable {
    private Integer reservedId;

    private String inviteCodeType;

    private String inviteCode;

    private String useStatus;

    private Integer creator;

    private Date createTime;

    private Integer operator;

    private Date operateTime;


    public ReservedInviteCode() {
		super();
	}

	public Integer getReservedId() {
        return reservedId;
    }

    public void setReservedId(Integer reservedId) {
        this.reservedId = reservedId;
    }

    public String getInviteCodeType() {
        return inviteCodeType;
    }

    public void setInviteCodeType(String inviteCodeType) {
        this.inviteCodeType = inviteCodeType;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

	@Override
	public String toString() {
		return "ReservedInviteCode [reservedId=" + reservedId + ", inviteCodeType=" + inviteCodeType + ", inviteCode="
				+ inviteCode + ", useStatus=" + useStatus + ", creator=" + creator + ", createTime=" + createTime
				+ ", operator=" + operator + ", operateTime=" + operateTime + "]";
	}

}