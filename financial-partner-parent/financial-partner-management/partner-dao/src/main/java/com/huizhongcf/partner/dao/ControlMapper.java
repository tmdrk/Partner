package com.huizhongcf.partner.dao;

import java.util.List;
import java.util.Map;

import com.huizhongcf.partner.model.Control;

public interface ControlMapper extends BaseMapper<Control>{
	
	/**
	 * 
	 * Description: 查询页面信息和控件信息通过控件id
	 *
	 * @param 
	 * @return Map<String,Object>
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-9 下午02:57:31
	 */
	public Map<String, Object> selectPageControlInfoByContolId(Integer controlId);
	
	/**
	 * 
	 * Description: 查找所有按钮供控制按钮权限使用
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-11 上午10:35:13
	 */
	public List<Map<String, Object>> findCtrlAllBySecurity();
}