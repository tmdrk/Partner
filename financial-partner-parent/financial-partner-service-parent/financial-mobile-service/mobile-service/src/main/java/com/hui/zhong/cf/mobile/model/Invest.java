package com.hui.zhong.cf.mobile.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Invest implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer investId;

    private Integer userId;

    private String userRole;

    private Integer infoId;

    private String productType;

    private String productName;

    private Integer productTerm;

    private BigDecimal annualRate;

    private String contractNo;

    private BigDecimal investAmount;

    private BigDecimal investIncome;

    private BigDecimal investRepayAmount;

    private Date buyDate;

    private Date payDate;

    private Date dueDate;

    private Date redemptionDate;

    private String investStatus;

    private String redemptionType;

    private String payBackType;

    private Integer discountMolecular;

    private Integer discountDenominator;

    private BigDecimal discountAmount;

    private Integer superiorRecommendCodeUserId;

    private Integer onSuperiorRecommendCodeUserId;

    private Integer teamRecommendCodeUserId;

    private String liquidationMonth;

    private String sealStatus;

    private String systemCode;

    private Integer creator;

    private Date createTime;

    private Integer operator;

    private Date operateTime;

    public Invest() {
        super();
    }

    public Integer getInvestId() {
        return investId;
    }

    public void setInvestId(Integer investId) {
        this.investId = investId;
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductTerm() {
        return productTerm;
    }

    public void setProductTerm(Integer productTerm) {
        this.productTerm = productTerm;
    }

    public BigDecimal getAnnualRate() {
        return annualRate;
    }

    public void setAnnualRate(BigDecimal annualRate) {
        this.annualRate = annualRate;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public BigDecimal getInvestAmount() {
        return investAmount;
    }

    public void setInvestAmount(BigDecimal investAmount) {
        this.investAmount = investAmount;
    }

    public BigDecimal getInvestIncome() {
        return investIncome;
    }

    public void setInvestIncome(BigDecimal investIncome) {
        this.investIncome = investIncome;
    }

    public BigDecimal getInvestRepayAmount() {
        return investRepayAmount;
    }

    public void setInvestRepayAmount(BigDecimal investRepayAmount) {
        this.investRepayAmount = investRepayAmount;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getRedemptionDate() {
        return redemptionDate;
    }

    public void setRedemptionDate(Date redemptionDate) {
        this.redemptionDate = redemptionDate;
    }

    public String getInvestStatus() {
        return investStatus;
    }

    public void setInvestStatus(String investStatus) {
        this.investStatus = investStatus;
    }

    public String getRedemptionType() {
        return redemptionType;
    }

    public void setRedemptionType(String redemptionType) {
        this.redemptionType = redemptionType;
    }

    public String getPayBackType() {
        return payBackType;
    }

    public void setPayBackType(String payBackType) {
        this.payBackType = payBackType;
    }

    public Integer getDiscountMolecular() {
        return discountMolecular;
    }

    public void setDiscountMolecular(Integer discountMolecular) {
        this.discountMolecular = discountMolecular;
    }

    public Integer getDiscountDenominator() {
        return discountDenominator;
    }

    public void setDiscountDenominator(Integer discountDenominator) {
        this.discountDenominator = discountDenominator;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
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

    public String getLiquidationMonth() {
        return liquidationMonth;
    }

    public void setLiquidationMonth(String liquidationMonth) {
        this.liquidationMonth = liquidationMonth;
    }

    public String getSealStatus() {
        return sealStatus;
    }

    public void setSealStatus(String sealStatus) {
        this.sealStatus = sealStatus;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
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
        return "Invest{" +
                "investId=" + investId +
                ", userId=" + userId +
                ", userRole='" + userRole + '\'' +
                ", infoId=" + infoId +
                ", productType='" + productType + '\'' +
                ", productName='" + productName + '\'' +
                ", productTerm=" + productTerm +
                ", annualRate=" + annualRate +
                ", contractNo='" + contractNo + '\'' +
                ", investAmount=" + investAmount +
                ", investIncome=" + investIncome +
                ", investRepayAmount=" + investRepayAmount +
                ", buyDate=" + buyDate +
                ", payDate=" + payDate +
                ", dueDate=" + dueDate +
                ", redemptionDate=" + redemptionDate +
                ", investStatus='" + investStatus + '\'' +
                ", redemptionType='" + redemptionType + '\'' +
                ", payBackType='" + payBackType + '\'' +
                ", discountMolecular=" + discountMolecular +
                ", discountDenominator=" + discountDenominator +
                ", discountAmount=" + discountAmount +
                ", superiorRecommendCodeUserId=" + superiorRecommendCodeUserId +
                ", onSuperiorRecommendCodeUserId=" + onSuperiorRecommendCodeUserId +
                ", teamRecommendCodeUserId=" + teamRecommendCodeUserId +
                ", liquidationMonth='" + liquidationMonth + '\'' +
                ", sealStatus='" + sealStatus + '\'' +
                ", systemCode='" + systemCode + '\'' +
                ", creator=" + creator +
                ", createTime=" + createTime +
                ", operator=" + operator +
                ", operateTime=" + operateTime +
                '}';
    }
}