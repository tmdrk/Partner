/* 
 * Copyright (C) 2006-2014 亿谱汇投资管理（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: PageService.java 
 *
 * Created: [2014-11-17 下午04:28:12] by ydw 
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

package com.huizhongcf.partner.service;

import java.util.Map;

import com.huizhongcf.partner.model.Page;
import com.huizhongcf.util.PageModel;

/** 
 *
 * Description: 
 *
 * @author ydw
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014-11-17    ydw       1.0        1.0 Version 
 * </pre>
 */

public interface PageService {

	/**
	 * 
	 * Description: 分页查询页面信息
	 *
	 * @param 
	 * @return PageModel
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-8 上午10:55:13
	 */
	public PageModel findAllByPage(Map<String, Object> paramsCondition);
	
	/**
	 * 
	 * Description: 保存添加页面
	 *
	 * @param 
	 * @return Integer
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-8 下午02:11:39
	 */
	public Integer addPage(Page page);
	
	/**
	 * 
	 * Description:  根据pageId查找页面实体
	 *
	 * @param 
	 * @return Page
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-8 下午03:03:25
	 */
	public Page getPageByPageId(Integer pageId);
	
	/**
	 * 
	 * Description: 修改
	 *
	 * @param 
	 * @return Integer
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-8 下午03:21:01
	 */
	public Integer updatePage(Page page);
}
