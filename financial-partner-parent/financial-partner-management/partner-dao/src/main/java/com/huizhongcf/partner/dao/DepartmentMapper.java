package com.huizhongcf.partner.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huizhongcf.partner.model.Department;

public interface DepartmentMapper extends BaseMapper<Department>{
	
	/**
	 * 
	 * Description: 查询部门列表，用于部门管理页面
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-11-28 下午06:09:33
	 */
	public List<Map<String, Object>> findDepartmentList();
	
	/**
	 * 
	 * Description: 查找员工列表    一级部门 财富管理部
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-1 下午01:51:08
	 */
	public List<Map<String, Object>> findFirstDeptInfo();
	
	/**
	 * 
	 * Description: 查找所在团队 三级级联的第二级(营业部)或第三级(团队)
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-1 下午02:03:38
	 */
	public List<Map<String, Object>> findSubWorkDeptInfoByDeptId(Department department);
	
	/**
	 * 
	 * Description: 获得层级部门信息名称
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-2 上午11:59:56
	 */
	public String getLevelDeptInfo(Integer deptId);
	
	/**
	 * 
	 * Description: 查询部门信息用于给员工设置工作组 
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-2 下午04:41:51
	 */
	public List<Map<String, Object>> findDepartmentGroupList(Integer employeeId);

	/**
	 * Description: 查询业绩管理的三三级部门名称
	 * @param 
	 * @return String
	 * @throws 
	 * @Author Gaofeng
	 * Create Date:2017年8月8日
	*/
	public List<Map<String, Object>> findDepartmentTwoAndThree();
	
	/**
	 * Description:根据名称和父id查询当前部门是否存在
	 * @param map
	 * @return Department
	 * @Author haochunhe
	 * Create Date:2017年8月9日
	 */
	public Department findTeamDetail(Map<String, Object> map);
	
	/**
	 * 根据父ID查询部门编号
	 * @param parentId
	 * @return Map
	 * @Author haochunhe
	 * Create Date:2017年8月9日
	 */
	public Map<String,Object> selectCountDeptnoByParentId(Department department);
 

	public Integer getDeptId(@Param("deptName")String deptName);

	/**
	 * 根据deptId查询所有parentId
	* @Description: 
	* @param @param customMap
	* @param @return    
	* @return List<Department>
	* @throws
	 */
	public List<Department> getParentIdByChildrenId(Map<String, Object> departMap);
	
	
}