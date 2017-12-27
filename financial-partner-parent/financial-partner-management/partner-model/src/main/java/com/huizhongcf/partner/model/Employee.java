package com.huizhongcf.partner.model;

import java.util.Date;

/**
 * 
 *
 * Description: 员工实体
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
public class Employee extends Entity{
	
    private Integer employeeId;//用户ID
    
    private String name;//姓名

    private String sex;//性别

    private String email;//邮箱

    private String password;//登录密码

    private String mobile;//移动电话

    private String telephone;//固定电话

    private Integer deptId;//当前部门
    
    private String employeeNo;//员工编号
    private String activatedState;//账号使用状态
    

    private String remark;//备注

    private Date createTime;//创建时间

    private Integer creator;//创建人

    private Date operateTime;//操作时间

    private Integer operator;//操作人

    public Employee() {
    }

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getActivatedState() {
		return activatedState;
	}

	public void setActivatedState(String activatedState) {
		this.activatedState = activatedState;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public Integer getOperator() {
		return operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", name=" + name + ", sex=" + sex + ", email=" + email
				+ ", password=" + password + ", mobile=" + mobile + ", telephone=" + telephone + ", deptId=" + deptId
				+ ", employeeNo=" + employeeNo + ", activatedState=" + activatedState + ", remark=" + remark
				+ ", createTime=" + createTime + ", creator=" + creator + ", operateTime=" + operateTime + ", operator="
				+ operator + "]";
	}

}