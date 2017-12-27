package com.hui.zhong.cf.job.model;

import java.io.Serializable;
import java.util.Date;

public class ProductConfig implements Serializable {
    private Integer productId;

    private Integer productTerm;

    private String productType;

    private Integer discountMolecular;

    private Integer discountDenominator;

    private Integer creator;

    private Date createTime;

    private Integer operator;

    private Date operateTime;

    public ProductConfig() {
        super();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductTerm() {
        return productTerm;
    }

    public void setProductTerm(Integer productTerm) {
        this.productTerm = productTerm;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
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
        return "ProductConfig{" +
                "productId=" + productId +
                ", productTerm=" + productTerm +
                ", productType='" + productType + '\'' +
                ", discountMolecular=" + discountMolecular +
                ", discountDenominator=" + discountDenominator +
                ", creator=" + creator +
                ", createTime=" + createTime +
                ", operator=" + operator +
                ", operateTime=" + operateTime +
                '}';
    }
}