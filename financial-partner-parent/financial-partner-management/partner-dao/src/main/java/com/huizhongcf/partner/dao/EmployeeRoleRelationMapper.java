package com.huizhongcf.partner.dao;

import java.util.List;

import com.huizhongcf.partner.model.EmployeeRoleRelation;

public interface EmployeeRoleRelationMapper extends BaseMapper<EmployeeRoleRelation>{
    
    /**
     * 
     * Description: 查找当前登录人拥有的角色
     *
     * @param 
     * @return List<String>
     * @throws 
     * @Author yaodawei
     * Create Date: 2014-5-4 下午02:43:13
     */
    public List<String> findEmpRoleByEmpId(Integer employeeId);
    
    /**
     * 
     * Description: 通过员工id删除授权角色
     *
     * @param 
     * @return void
     * @throws 
     * @Author ydw
     * Create Date: 2014-12-10 下午03:28:28
     */
    public void deleteRoleByEmployeeId(Integer employeeId);
}