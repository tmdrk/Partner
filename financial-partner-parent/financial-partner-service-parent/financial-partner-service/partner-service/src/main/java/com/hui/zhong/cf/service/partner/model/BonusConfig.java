package com.hui.zhong.cf.service.partner.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BonusConfig implements Serializable {
    private Integer bonusId;

    private String bonusType;

    private BigDecimal bonusOneCoefficient;

    private Integer creator;

    private Date createTime;

    private Integer operator;

    private Date operateTime;

    public BonusConfig() {
        super();
    }

    public Integer getBonusId() {
        return bonusId;
    }

    public void setBonusId(Integer bonusId) {
        this.bonusId = bonusId;
    }

    public String getBonusType() {
        return bonusType;
    }

    public void setBonusType(String bonusType) {
        this.bonusType = bonusType;
    }

    public BigDecimal getBonusOneCoefficient() {
        return bonusOneCoefficient;
    }

    public void setBonusOneCoefficient(BigDecimal bonusOneCoefficient) {
        this.bonusOneCoefficient = bonusOneCoefficient;
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
        return "BonusConfig{" +
                "bonusId=" + bonusId +
                ", bonusType='" + bonusType + '\'' +
                ", bonusOneCoefficient=" + bonusOneCoefficient +
                ", creator=" + creator +
                ", createTime=" + createTime +
                ", operator=" + operator +
                ", operateTime=" + operateTime +
                '}';
    }
}