/* 
 * Copyright (C) 2006-2014 亿谱汇投资管理（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: PageServiceImpl.java 
 *
 * Created: [2014-11-17 下午04:28:29] by ydw 
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

package com.huizhongcf.partner.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huizhongcf.partner.dao.PageMapper;
import com.huizhongcf.partner.model.Page;
import com.huizhongcf.partner.service.PageService;
import com.huizhongcf.util.PageModel;

/** 
 *
 * Description: 
 *
 * @author ydw
 * @version 1.0x
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014-11-17    ydw       1.0        1.0 Version 
 * </pre>
 */
@Service("pageService")
public class PageServiceImpl implements PageService{
	
	@Autowired
	private PageMapper pageMapper;

	@Override
	public PageModel findAllByPage(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paramsCondition.get("pageNo"));
		pageModel.setPageSize((Integer) paramsCondition.get("pageSize"));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> data = pageMapper.findAllRetMapByPage(paramsCondition);
		Long totalRecords = pageMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}

	@Override
	public Integer addPage(Page page) {
		return pageMapper.insertSelective(page);
	}

	@Override
	public Page getPageByPageId(Integer pageId) {
		return pageMapper.selectByPrimaryKey(pageId);
	}

	@Override
	public Integer updatePage(Page page) {
		return pageMapper.updateByPrimaryKeySelective(page);
	}

}
