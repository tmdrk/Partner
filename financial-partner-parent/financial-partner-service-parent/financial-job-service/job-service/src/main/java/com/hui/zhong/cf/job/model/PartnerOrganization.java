package com.hui.zhong.cf.job.model;

import java.io.Serializable;
import java.util.Date;

public class PartnerOrganization implements Serializable {
    private Integer organizationId;

    private String organizationNo;

    private String organizationName;

    private String shortName;

    private Integer parentId;

    private String principal;

    private Integer creator;

    private Date createTime;

    private Integer operator;

    private Date operateTime;

    public PartnerOrganization() {
        super();
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationNo() {
        return organizationNo;
    }

    public void setOrganizationNo(String organizationNo) {
        this.organizationNo = organizationNo;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
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
        return "PartnerOrganization{" +
                "organizationId=" + organizationId +
                ", organizationNo='" + organizationNo + '\'' +
                ", organizationName='" + organizationName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", parentId=" + parentId +
                ", principal='" + principal + '\'' +
                ", creator=" + creator +
                ", createTime=" + createTime +
                ", operator=" + operator +
                ", operateTime=" + operateTime +
                '}';
    }
}