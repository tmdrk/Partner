package com.huizhongcf.partner.dao;

import java.util.List;
import java.util.Map;

import com.huizhongcf.partner.model.Employee;

public interface EmployeeMapper extends BaseMapper<Employee>{
    
    /**
	 * 
	 * Description: 根据用户名查找用户
	 *
	 * @param username 
	 * @return String 
	 * @Author lijie
	 * @Create Date: 2013-11-4 下午5:10:22
	 */
	public Employee findByUsername(String username);
	
	 /**
	  * 
	  * Description: 验证用户
	  *
	  * @param 
	  * @return Employee
	  * @throws 
	  * @Author ydw
	  * Create Date: 2014-12-1 下午05:48:53
	  */
    public Employee checkUsers(Employee employee);
    
    /**
     * 根据员工编号查询员工
     * @param employee
     * @return map
     */
    public Map<String,Object> findEmployeeByEmployeeNo(Employee employee);
    
    /**
	 * 批量插入人员
	 * @param list
	 * @return
	 */
	public int insertEmployeeList(List list);
	/**
     * 根据推荐码查询员工
     * @param employee
     * @return map
     */
	public Map<String, Object> getEmpByinviteCode(Map<String, Object> request);

	/**
	 * 导出查询的员工信息数据
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>> exportEmployes(Map<String,Object> params);
	/**
	 * 
	 * Description: 客户赠予查询列表数据,并且实现分页
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 * @Author Lee
	 * Create Date: 2014-11-24 下午07:56:35
	 */
	public List<Map<String, Object>> findAllRetMapByPageToGive(Map<String, Object> paramsCondition);
    
	/**
	 * 
	 * Description: 客户赠予查询列表分页总记录数
	 *
	 * @param 
	 * @return Long
	 * @throws 
	 * @Author Lee
	 * Create Date: 2014-11-24 下午07:56:49
	 */
    public Long findAllByPageCountToGive(Map<String,Object> paramsCondition);
    
}