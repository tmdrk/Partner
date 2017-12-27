package com.hui.zhong.cf.service.partner.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * Description: 部门实体
 *
 * @author ydw
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014-12-11    ydw       1.0        1.0 Version 
 * </pre>
 */
public class Department implements Serializable {

    private Integer deptId;//主键

    private String deptName;//部门名称

    private String deptNo;//部门编号
    
    private String remark;//部门描述

    private Integer parentId;//上一级ID

    private Integer orderId;//排序号
    
    private Date createTime;//创建时间

    private Integer creator;//创建人

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo == null ? null : deptNo.trim();
    }

    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}
}