package com.huizhongcf.partner.service;

import java.util.Map;

import com.huizhongcf.util.PageModel;

/**
 * 接口日志
 * @author hch
 * 2017-2-23 13:00
 */
public interface InterfaceLogService {
	
	/**
	 * 接口日志分页列表
	 * @param map
	 * @return
	 */
	public PageModel getInterfaceLog(Map<String,Object> map);
}
