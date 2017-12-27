package com.hui.zhong.cf.job.model;

import java.io.Serializable;
import java.util.Date;

public class LoginUser implements Serializable {
    private Integer userId;

    private String userRole;

    private String userType;

    private String username;

    private String loginPwd;

    private String partnerRecommendCode;

    private String customerRecommendCode;

    private Integer organizationChannelId;

    private Integer organizationAreaId;

    private String userStatus;

    private Date partnerTime;

    private String inputInvitationCode;

    private Integer parentUserId;

    private String isPlatformInvite;

    private String inviteShareType;

    private Integer superiorRecommendCodeUserId;

    private Integer onSuperiorRecommendCodeUserId;

    private Integer teamRecommendCodeUserId;

    private Date registTime;

    private String registSource;

    private Integer creator;

    private Date createTime;

    private Integer operator;

    private Date operateTime;

    public LoginUser() {
        super();
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getPartnerRecommendCode() {
        return partnerRecommendCode;
    }

    public void setPartnerRecommendCode(String partnerRecommendCode) {
        this.partnerRecommendCode = partnerRecommendCode;
    }

    public String getCustomerRecommendCode() {
        return customerRecommendCode;
    }

    public void setCustomerRecommendCode(String customerRecommendCode) {
        this.customerRecommendCode = customerRecommendCode;
    }

    public Integer getOrganizationChannelId() {
        return organizationChannelId;
    }

    public void setOrganizationChannelId(Integer organizationChannelId) {
        this.organizationChannelId = organizationChannelId;
    }

    public Integer getOrganizationAreaId() {
        return organizationAreaId;
    }

    public void setOrganizationAreaId(Integer organizationAreaId) {
        this.organizationAreaId = organizationAreaId;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public Date getPartnerTime() {
        return partnerTime;
    }

    public void setPartnerTime(Date partnerTime) {
        this.partnerTime = partnerTime;
    }

    public String getInputInvitationCode() {
        return inputInvitationCode;
    }

    public void setInputInvitationCode(String inputInvitationCode) {
        this.inputInvitationCode = inputInvitationCode;
    }

    public Integer getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(Integer parentUserId) {
        this.parentUserId = parentUserId;
    }

    public String getIsPlatformInvite() {
        return isPlatformInvite;
    }

    public void setIsPlatformInvite(String isPlatformInvite) {
        this.isPlatformInvite = isPlatformInvite;
    }

    public String getInviteShareType() {
        return inviteShareType;
    }

    public void setInviteShareType(String inviteShareType) {
        this.inviteShareType = inviteShareType;
    }

    public Integer getSuperiorRecommendCodeUserId() {
        return superiorRecommendCodeUserId;
    }

    public void setSuperiorRecommendCodeUserId(Integer superiorRecommendCodeUserId) {
        this.superiorRecommendCodeUserId = superiorRecommendCodeUserId;
    }

    public Integer getOnSuperiorRecommendCodeUserId() {
        return onSuperiorRecommendCodeUserId;
    }

    public void setOnSuperiorRecommendCodeUserId(Integer onSuperiorRecommendCodeUserId) {
        this.onSuperiorRecommendCodeUserId = onSuperiorRecommendCodeUserId;
    }

    public Integer getTeamRecommendCodeUserId() {
        return teamRecommendCodeUserId;
    }

    public void setTeamRecommendCodeUserId(Integer teamRecommendCodeUserId) {
        this.teamRecommendCodeUserId = teamRecommendCodeUserId;
    }

    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    public String getRegistSource() {
        return registSource;
    }

    public void setRegistSource(String registSource) {
        this.registSource = registSource;
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
        return "LoginUser{" +
                "userId=" + userId +
                ", userRole='" + userRole + '\'' +
                ", userType='" + userType + '\'' +
                ", username='" + username + '\'' +
                ", loginPwd='" + loginPwd + '\'' +
                ", partnerRecommendCode='" + partnerRecommendCode + '\'' +
                ", customerRecommendCode='" + customerRecommendCode + '\'' +
                ", organizationChannelId=" + organizationChannelId +
                ", organizationAreaId=" + organizationAreaId +
                ", userStatus='" + userStatus + '\'' +
                ", partnerTime=" + partnerTime +
                ", inputInvitationCode='" + inputInvitationCode + '\'' +
                ", parentUserId=" + parentUserId +
                ", isPlatformInvite='" + isPlatformInvite + '\'' +
                ", inviteShareType='" + inviteShareType + '\'' +
                ", superiorRecommendCodeUserId=" + superiorRecommendCodeUserId +
                ", onSuperiorRecommendCodeUserId=" + onSuperiorRecommendCodeUserId +
                ", teamRecommendCodeUserId=" + teamRecommendCodeUserId +
                ", registTime=" + registTime +
                ", registSource='" + registSource + '\'' +
                ", creator=" + creator +
                ", createTime=" + createTime +
                ", operator=" + operator +
                ", operateTime=" + operateTime +
                '}';
    }
}