package com.huizhongcf.partner.service;

import java.util.List;
import java.util.Map;

import com.huizhongcf.util.PageModel;

/** 
*
* Description:  推荐码管理service
*
* @author gdj
* @version 1.0
* <pre>
* Modification History: 
* Date         Author      Version     Description 
* ------------------------------------------------------------------ 
* 20171207    gdj       1.0        1.0 Version 
* </pre>
*/
public interface RecommendManageService {

	 /**
	  *  合伙人推荐码管理分页条件查询
	  * @param paramsCondition
	  * @return
	  */
	 public PageModel findAllRecommendCodePage(Map<String, Object> paramsCondition);
	 
	 /**
	  * 推荐码管理页面的导出全部记录
	  * @param paramsCondition
	  * @return
	  */
	 public List<Map<String,Object>> exportAllRecommendCodeInfo(Map<String, Object> paramsCondition);
	 
	 /**
	  * 推荐码管理页面的导出选中记录
	  * @param paramsCondition
	  * @return
	  */
	 public List<Map<String,Object>> exportSelectRecommendCodeInfo(Map<String, Object> paramsCondition);
	 
}
