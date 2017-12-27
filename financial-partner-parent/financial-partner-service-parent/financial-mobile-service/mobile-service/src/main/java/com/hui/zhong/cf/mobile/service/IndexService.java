package com.hui.zhong.cf.mobile.service;

import java.util.Map;

/**
 * 首页服务
 * @ClassName: indexService 
 * @author zhoujie
 * @date 2017年12月12日 下午4:20:45
 */
public interface IndexService {
	/**
	 * 查询首页统计数据
	 * @param queryMap
	 * @return
	 * @author zhoujie
	 * @date 2017年12月12日 下午7:54:28
	 */
	Map<String,Object> queryData(Map<String, Object> queryMap);
}
