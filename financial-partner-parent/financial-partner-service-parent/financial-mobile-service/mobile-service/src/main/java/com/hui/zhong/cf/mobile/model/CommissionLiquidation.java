package com.hui.zhong.cf.mobile.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CommissionLiquidation implements Serializable {
    private Integer commissionLiquidationId;

    private Integer userId;

    private String userType;

    private Integer infoId;

    private String liquidationNum;

    private String liquidationMonth;

    private BigDecimal investTotalAmount;

    private BigDecimal discountTotalAmount;

    private BigDecimal lendBonus;

    private BigDecimal diffBonus;

    private BigDecimal serviceBonus;

    private BigDecimal totalReward;

    private String liquidationStatus;

    private Date liquidationTime;

    private Integer creator;

    private Date createTime;

    private Integer operator;

    private Date operateTime;

    public CommissionLiquidation() {
        super();
    }

    public Integer getCommissionLiquidationId() {
        return commissionLiquidationId;
    }

    public void setCommissionLiquidationId(Integer commissionLiquidationId) {
        this.commissionLiquidationId = commissionLiquidationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public String getLiquidationNum() {
        return liquidationNum;
    }

    public void setLiquidationNum(String liquidationNum) {
        this.liquidationNum = liquidationNum;
    }

    public String getLiquidationMonth() {
        return liquidationMonth;
    }

    public void setLiquidationMonth(String liquidationMonth) {
        this.liquidationMonth = liquidationMonth;
    }

    public BigDecimal getInvestTotalAmount() {
        return investTotalAmount;
    }

    public void setInvestTotalAmount(BigDecimal investTotalAmount) {
        this.investTotalAmount = investTotalAmount;
    }

    public BigDecimal getDiscountTotalAmount() {
        return discountTotalAmount;
    }

    public void setDiscountTotalAmount(BigDecimal discountTotalAmount) {
        this.discountTotalAmount = discountTotalAmount;
    }

    public BigDecimal getLendBonus() {
        return lendBonus;
    }

    public void setLendBonus(BigDecimal lendBonus) {
        this.lendBonus = lendBonus;
    }

    public BigDecimal getDiffBonus() {
        return diffBonus;
    }

    public void setDiffBonus(BigDecimal diffBonus) {
        this.diffBonus = diffBonus;
    }

    public BigDecimal getServiceBonus() {
        return serviceBonus;
    }

    public void setServiceBonus(BigDecimal serviceBonus) {
        this.serviceBonus = serviceBonus;
    }

    public BigDecimal getTotalReward() {
        return totalReward;
    }

    public void setTotalReward(BigDecimal totalReward) {
        this.totalReward = totalReward;
    }

    public String getLiquidationStatus() {
        return liquidationStatus;
    }

    public void setLiquidationStatus(String liquidationStatus) {
        this.liquidationStatus = liquidationStatus;
    }

    public Date getLiquidationTime() {
        return liquidationTime;
    }

    public void setLiquidationTime(Date liquidationTime) {
        this.liquidationTime = liquidationTime;
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
        return "CommissionLiquidation{" +
                "commissionLiquidationId=" + commissionLiquidationId +
                ", userId=" + userId +
                ", userType='" + userType + '\'' +
                ", infoId=" + infoId +
                ", liquidationNum='" + liquidationNum + '\'' +
                ", liquidationMonth='" + liquidationMonth + '\'' +
                ", investTotalAmount=" + investTotalAmount +
                ", discountTotalAmount=" + discountTotalAmount +
                ", lendBonus=" + lendBonus +
                ", diffBonus=" + diffBonus +
                ", serviceBonus=" + serviceBonus +
                ", totalReward=" + totalReward +
                ", liquidationStatus='" + liquidationStatus + '\'' +
                ", liquidationTime=" + liquidationTime +
                ", creator=" + creator +
                ", createTime=" + createTime +
                ", operator=" + operator +
                ", operateTime=" + operateTime +
                '}';
    }
}