/* 
 * Copyright (C) 2006-2014 亿谱汇投资管理（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: BaseMapper.java 
 *
 * Created: [2014-11-24 下午07:13:50] by ydw 
 *
 * $Id$
 * 
 * $Revision$
 *
 * $Author$
 *
 * $Date$
 *
 * ============================================================ 
 * 
 * ProjectName: sping-mvc 
 * 
 * Description: 
 * 
 * ==========================================================*/

package com.huizhongcf.partner.dao;

import java.util.List;
import java.util.Map;




/** 
 *
 * Description: BaseMapper基本实现方法
 *
 * @author ydw
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014-11-24    ydw       1.0        1.0 Version 
 * </pre>
 */

public interface BaseMapper<T> {
	
	/**
	 * 
	 * Description: 插入
	 *
	 * @param 
	 * @return int
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-11-24 下午07:49:55
	 */
	public int insert(T record);

	/**
	 * 
	 * Description: 插入
	 *
	 * @param 
	 * @return int
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-11-24 下午07:50:04
	 */
	public int insertSelective(T record);
	
	/**
	 * 
	 * Description: 更新
	 *
	 * @param 
	 * @return int
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-11-24 下午07:50:18
	 */
	public int updateByPrimaryKeySelective(T record);

	/**
	 * 
	 * Description: 更新
	 *
	 * @param 
	 * @return int
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-11-24 下午07:50:26
	 */
	public int updateByPrimaryKey(T record);
	
	/**
	 * 
	 * Description: 根据主键删除
	 *
	 * @param 
	 * @return int
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-11-24 下午07:48:42
	 */
	public int deleteByPrimaryKey(Integer primaryKey);
	
	/**
	 * 
	 * Description: 根据主键查询实体
	 *
	 * @param 
	 * @return T
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-11-24 下午07:48:56
	 */
	public T selectByPrimaryKey(Integer primaryKey);
	
	/**
	 * 
	 * Description: 查询列表数据,并且实现分页
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-11-24 下午07:56:35
	 */
	public List<Map<String, Object>> findAllRetMapByPage(Map<String, Object> paramsCondition);
    
	/**
	 * 
	 * Description: 查询列表分页总记录数
	 *
	 * @param 
	 * @return Long
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-11-24 下午07:56:49
	 */
    public Long findAllByPageCount(Map<String,Object> paramsCondition);
    
    /**
     * 
     * Description: 查询列表数据,返回Map，不需要分页
     *
     * @param 
     * @return List<Map<String,Object>>
     * @throws 
     * @Author ydw
     * Create Date: 2014-11-24 下午08:04:28
     */
    public List<Map<String, Object>> findAllRetMapNoPage(Map<String, Object> paramsCondition);
    
    /**
     * 
     * Description: 查询列表数据,返回Entity，不需要分页
     *
     * @param 
     * @return List<T>
     * @throws 
     * @Author ydw
     * Create Date: 2014-11-25 下午05:33:51
     */
    public List<T> findAllRetEntityNoPage(Map<String, Object> paramsCondition);
    
    /**
     * 
     * Description: 根据某整型id查询,返回Map
     *
     * @param 
     * @return Map<String,Object>
     * @throws 
     * @Author ydw
     * Create Date: 2014-11-24 下午07:57:57
     */
    public Map<String,Object> selectRetMapByPrimaryKey(Integer objId);
    
    /**
     * 
     * Description: 根据多条件查询,返回Map
     *
     * @param 
     * @return Map<String,Object>
     * @throws 
     * @Author ydw
     * Create Date: 2014-11-24 下午08:06:54
     */
    public Map<String,Object> selectRetMapByParamsCondition(Map<String, Object> paramsCondition);
}
