
package com.huizhongcf.partner.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * Description: 公共Entity
 *
 * @author lijie 
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2015年4月7日      lijie       1.0         1.0 Version 
 * </pre>
 */

public class Entity implements Serializable{
	
	/*
	 * 主键 
	 */
	protected Integer id;
	
	/**
	 * 创建人
	 */
	protected Integer creator;
	
	/**
	 * 操作人
	 */
	protected Integer operator;
	
	/**
	 * 创建时间
	 */
	protected Date createTime;
	
	/**
	 * 操作时间
	 */
	protected Date operateTime;
	
	/**
	 * 备注
	 */
	protected String remark;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Integer getOperator() {
		return operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
