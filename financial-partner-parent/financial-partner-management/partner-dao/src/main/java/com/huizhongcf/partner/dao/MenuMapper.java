package com.huizhongcf.partner.dao;

import java.util.List;
import java.util.Map;

import com.huizhongcf.partner.model.Menu;

public interface MenuMapper extends BaseMapper<Menu>{
	
	/**
	 * 
	 * Description: 查询该用户拥有的菜单权限 
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-10 下午05:22:11
	 */
	public List<Map<String, Object>> findMenuByUserId(Integer userId);
	
	/**
	 * 
	 * Description: 根据父Id查找根菜单
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-10 下午05:35:56
	 */
	public List<Map<String, Object>> findRootMenuByPid(List<Integer> menuPid);
    
    /**
	 * 
	 * Description: 根据角色Id查找菜单
	 *
	 * @param roleId
	 * @return List<Menu> 
	 * @Author lijie
	 * @Create Date: 2013-11-14 下午3:17:56
	 */
	public List<Menu> findMenuByRoleId(Integer roleId);
	
	/**
	 * 
	 * Description: 查询菜单
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-4 上午10:58:44
	 */
	public List<Map<String, Object>> selectMenu();
	
	/**
	 * 
	 * Description: 查询所用root节点
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-4 下午01:12:55
	 */
	public List<Map<String, Object>> selectMenuRoot();
	
	/**
	 * 
	 * Description: 检查菜单中文名称、英文名称是否重复
	 *
	 * @param 
	 * @return int
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-4 下午01:53:41
	 */
	public int checkMenuIsRepeat(Map<String, Object> paramsCondition);
	
	/**
	 * 
	 * Description: 更新菜单排序
	 *
	 * @param 
	 * @return int
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-4 下午04:42:12
	 */
	public int updateNodesSort(Map<String, Object> paramsCondition);
}