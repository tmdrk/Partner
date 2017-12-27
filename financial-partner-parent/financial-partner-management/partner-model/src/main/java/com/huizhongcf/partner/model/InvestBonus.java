package com.huizhongcf.partner.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class InvestBonus extends Entity{
    private Integer investBonusId;

    private Integer userId;

    private String userRole;

    private Integer infoId;

    private Integer investId;

    private String rewardNum;

    private BigDecimal userLendBonus;

    private BigDecimal userDiffBonus;

    private BigDecimal superiorLendBonus;

    private BigDecimal superiorDiffBonus;

    private Integer superiorRecommendCodeUserId;

    private BigDecimal onSuperiorLendBonus;

    private Integer onSuperiorRecommendCodeUserId;

    private BigDecimal serviceBonus;

    private Integer teamRecommendCodeUserId;

    private Date createTime;

    private Integer operator;

    private Date operateTime;

    public InvestBonus() {
        super();
    }

    public Integer getInvestBonusId() {
        return investBonusId;
    }

    public void setInvestBonusId(Integer investBonusId) {
        this.investBonusId = investBonusId;
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

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public Integer getInvestId() {
        return investId;
    }

    public void setInvestId(Integer investId) {
        this.investId = investId;
    }

    public String getRewardNum() {
        return rewardNum;
    }

    public void setRewardNum(String rewardNum) {
        this.rewardNum = rewardNum;
    }

    public BigDecimal getUserLendBonus() {
        return userLendBonus;
    }

    public void setUserLendBonus(BigDecimal userLendBonus) {
        this.userLendBonus = userLendBonus;
    }

    public BigDecimal getUserDiffBonus() {
        return userDiffBonus;
    }

    public void setUserDiffBonus(BigDecimal userDiffBonus) {
        this.userDiffBonus = userDiffBonus;
    }

    public BigDecimal getSuperiorLendBonus() {
        return superiorLendBonus;
    }

    public void setSuperiorLendBonus(BigDecimal superiorLendBonus) {
        this.superiorLendBonus = superiorLendBonus;
    }

    public BigDecimal getSuperiorDiffBonus() {
        return superiorDiffBonus;
    }

    public void setSuperiorDiffBonus(BigDecimal superiorDiffBonus) {
        this.superiorDiffBonus = superiorDiffBonus;
    }

    public Integer getSuperiorRecommendCodeUserId() {
        return superiorRecommendCodeUserId;
    }

    public void setSuperiorRecommendCodeUserId(Integer superiorRecommendCodeUserId) {
        this.superiorRecommendCodeUserId = superiorRecommendCodeUserId;
    }

    public BigDecimal getOnSuperiorLendBonus() {
        return onSuperiorLendBonus;
    }

    public void setOnSuperiorLendBonus(BigDecimal onSuperiorLendBonus) {
        this.onSuperiorLendBonus = onSuperiorLendBonus;
    }

    public Integer getOnSuperiorRecommendCodeUserId() {
        return onSuperiorRecommendCodeUserId;
    }

    public void setOnSuperiorRecommendCodeUserId(Integer onSuperiorRecommendCodeUserId) {
        this.onSuperiorRecommendCodeUserId = onSuperiorRecommendCodeUserId;
    }

    public BigDecimal getServiceBonus() {
        return serviceBonus;
    }

    public void setServiceBonus(BigDecimal serviceBonus) {
        this.serviceBonus = serviceBonus;
    }

    public Integer getTeamRecommendCodeUserId() {
        return teamRecommendCodeUserId;
    }

    public void setTeamRecommendCodeUserId(Integer teamRecommendCodeUserId) {
        this.teamRecommendCodeUserId = teamRecommendCodeUserId;
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
        return "InvestBonus{" +
                "investBonusId=" + investBonusId +
                ", userId=" + userId +
                ", userRole='" + userRole + '\'' +
                ", infoId=" + infoId +
                ", investId=" + investId +
                ", rewardNum='" + rewardNum + '\'' +
                ", userLendBonus=" + userLendBonus +
                ", userDiffBonus=" + userDiffBonus +
                ", superiorLendBonus=" + superiorLendBonus +
                ", superiorDiffBonus=" + superiorDiffBonus +
                ", superiorRecommendCodeUserId=" + superiorRecommendCodeUserId +
                ", onSuperiorLendBonus=" + onSuperiorLendBonus +
                ", onSuperiorRecommendCodeUserId=" + onSuperiorRecommendCodeUserId +
                ", serviceBonus=" + serviceBonus +
                ", teamRecommendCodeUserId=" + teamRecommendCodeUserId +
                ", createTime=" + createTime +
                ", operator=" + operator +
                ", operateTime=" + operateTime +
                '}';
    }
}