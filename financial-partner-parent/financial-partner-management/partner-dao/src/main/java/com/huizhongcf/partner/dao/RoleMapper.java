package com.huizhongcf.partner.dao;

import java.util.List;
import java.util.Map;

import com.huizhongcf.partner.model.Role;

public interface RoleMapper extends BaseMapper<Role>{
	
	/**
	 * 
	 * Description: 查询当前用户拥有的角色
	 *
	 * @param 
	 * @return List<Role>
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-9 下午03:29:24
	 */
    List<Role> findRoleByUserId(Integer id);
    
    /**
     * 
     * Description: 查询所有角色，并且标识出当前员工拥有的角色
     *
     * @param 
     * @return List<Map<String,Object>>
     * @throws 
     * @Author ydw
     * Create Date: 2014-12-10 上午10:34:44
     */
    public List<Map<String, Object>> authorizationEmpList(Integer employeeId);
    
    /**
     * 
     * Description: 查询所有角色，并且标识出菜单拥有的角色
     *
     * @param 
     * @return List<Map<String,Object>>
     * @throws 
     * @Author ydw
     * Create Date: 2014-12-10 上午10:34:44
     */
    public List<Map<String, Object>> authorizationMenuList(Integer menuId);
    
    /**
     * 
     * Description: 查询所有角色，并且标识出控件按钮拥有的角色
     *
     * @param 
     * @return List<Map<String,Object>>
     * @throws 
     * @Author ydw
     * Create Date: 2014-12-9 下午03:30:19
     */
    public List<Map<String, Object>> authorizationControlList(Integer controlId);
    
}