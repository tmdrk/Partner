/* 
 * Copyright (C) 2006-2017 普惠融通科技（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: ProductConfigServiceImpl.java 
 *
 * Created: [2017年12月7日 上午11:15:57] by cuimanman 
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
 * ProjectName: partner-serviceImpl 
 * 
 * Description: 
 * 
 * ==========================================================*/

package com.huizhongcf.partner.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huizhongcf.partner.dao.ProductConfigMapper;
import com.huizhongcf.partner.model.ProductConfig;
import com.huizhongcf.partner.service.ProductConfigService;
import com.huizhongcf.util.PageModel;

/**
 *<dl>
 *<dt>类名：ProductConfigServiceImpl.java</dt>
 *<dd>描述: 奖金配置管理</dd> 
 *<dd>创建时间： 2017年12月7日 上午11:15:57</dd>
 *<dd>创建人： cuimanman</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年12月7日 上午11:15:57    cuimanman       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Service("productConfigService")
public class ProductConfigServiceImpl implements ProductConfigService{
	
	@Autowired
	private ProductConfigMapper productConfigMapper;
	
	@Override
	public PageModel findAllByPage(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paramsCondition.get("pageNo"));
		pageModel.setPageSize((Integer) paramsCondition.get("pageSize"));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> data = productConfigMapper.findAllRetMapByPage(paramsCondition);
		Long totalRecords = productConfigMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}

	@Override
	public Integer insertSelective(ProductConfig productConfig) {
		return productConfigMapper.insertSelective(productConfig);
	}


	
}
