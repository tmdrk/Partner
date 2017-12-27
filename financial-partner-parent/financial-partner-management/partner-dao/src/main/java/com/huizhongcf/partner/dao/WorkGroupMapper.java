package com.huizhongcf.partner.dao;

import java.util.List;
import java.util.Map;

import com.huizhongcf.partner.model.WorkGroup;

public interface WorkGroupMapper extends BaseMapper<WorkGroup>{
    
	/**
     * 
     * Description: 根据员工id查找拥有的工作组信息
     *
     * @param 
     * @return List<Map<String,Object>>
     * @throws 
     * @Author ydw
     * Create Date: 2014-2-19 下午01:16:13
     */
    public List<Map<String, Object>> findWorkGroupInfoByEmpId(Integer employeeId);
    
    /**
     * 
     * Description: 查找当前登录人拥有的工作部门id根据员工id
     *
     * @param 
     * @return List<Integer>
     * @throws 
     * @Author ywd
     * Create Date: 2014-3-5 下午08:34:10
     */
    public List<Integer> findWorkDeptIdByEmpId(Integer employeeId);
    
    /**
     * 
     * Description: 根据员工id删除拥有的工作组信息
     *
     * @param 
     * @return void
     * @throws 
     * @Author ydw
     * Create Date: 2014-2-19 下午01:42:40
     */
    public void deleteWorkGroupInfoByEmpId(Integer employeeId);
}