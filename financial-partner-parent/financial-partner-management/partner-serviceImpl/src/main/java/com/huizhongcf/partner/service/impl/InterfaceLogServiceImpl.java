package com.huizhongcf.partner.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huizhongcf.partner.dao.InterfaceLogMapper;
import com.huizhongcf.partner.service.InterfaceLogService;
import com.huizhongcf.util.PageModel;
/**
 * 
 * Description: 接口日志 service实现类
 *
 * @author haochunhe
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年2月23日    	haochunhe      	  1.0       1.0 Version 
 * </pre>
 */
@Service
public class InterfaceLogServiceImpl implements InterfaceLogService{

	@Autowired
	private InterfaceLogMapper interfaceLogMapper;
	
	@Override
	public PageModel getInterfaceLog(Map<String, Object> map) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) map.get("pageNo"));
		pageModel.setPageSize((Integer) map.get("pageSize"));
		map.put("startIndex", pageModel.getStartIndex());
		map.put("endIndex", pageModel.getEndIndex());
		List<Map<String,Object>> list = interfaceLogMapper.findAllRetMapByPage(map);
		Long totalRecords = interfaceLogMapper.findAllByPageCount(map);
		pageModel.setList(list);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}

}
