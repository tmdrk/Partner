package com.huizhongcf.partner.model;

/**
 * 
 *
 * Description: 工作组实体
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
public class WorkGroup {
	
    private Integer id;//主键id

    private Integer employeeId;//员工id

    private Integer deptId;//部门id
    
    private String checkedStatus; //选中状态：选中：'1'，不选中：'0'
    
    private String halfStatus; //半选状态： 半选:'1',不半选:'0'

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
    
    public String getCheckedStatus() {
		return checkedStatus;
	}

	public void setCheckedStatus(String checkedStatus) {
		this.checkedStatus = checkedStatus;
	}

	public String getHalfStatus() {
		return halfStatus;
	}

	public void setHalfStatus(String halfStatus) {
		this.halfStatus = halfStatus;
	}
}