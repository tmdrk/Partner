package com.huizhongcf.partner.admin.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huizhongcf.partner.admin.baseweb.BaseController;
import com.huizhongcf.partner.admin.baseweb.DataMsg;
import com.huizhongcf.partner.service.InterfaceLogService;
import com.huizhongcf.util.PageModel;
import com.huizhongcf.util.StringUtil;

/**
 * 
 * Description: 接口日志 Controller
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
@Controller
@RequestMapping("/interfacelog")
public class InterfaceLogController extends BaseController{

	@Autowired
	private InterfaceLogService interfaceLogService;
	
	/**
	 * 跳转到接口日志列表
	 * @param refreshTag
	 * @param messageCode
	 * @param model
	 * @return
	 */
	@RequestMapping("/toInterfaceLogListPage")
	public String toInterfaceLogListPage(String refreshTag,String messageCode,Model model) {
		showMessageAlert(refreshTag,messageCode,model);
		return "app/biz/interfacelog/interface_log";
	}
	
	/**
	 * 接口分页列表
	 * @param request
	 * @param dataMsg
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/interfaceLog")
	public DataMsg InterfaceLogList(HttpServletRequest request,DataMsg dataMsg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", Integer.valueOf(request.getParameter("page")));
		map.put("pageSize", Integer.valueOf(request.getParameter("rows")));
		String tradeType = StringUtil.trim(request.getParameter("tradeType"));
		if (StringUtil.isNotBlank(tradeType)) {
			map.put("tradeType", tradeType);
		}
		String reqData = StringUtil.trim(request.getParameter("reqData"));
		if (StringUtil.isNotBlank(reqData)) {
			map.put("reqData", reqData);
		}
		String respResult = StringUtil.trim(request.getParameter("respResult"));
		if (StringUtil.isNotBlank(respResult)) {
			map.put("respResult", respResult);
		}
		String systemDocking = StringUtil.trim(request.getParameter("systemDocking"));
		if (StringUtil.isNotBlank(systemDocking)) {
			map.put("systemDocking", systemDocking);
		}
		String minInterfaceLogTime = StringUtil.trim(request.getParameter("min_interface_log_time"));//查询接口日志的起始时间
		if (StringUtil.isNotBlank(minInterfaceLogTime)) {
			map.put("minInterfaceLogTime", minInterfaceLogTime);
		}
		String maxInterfaceLogTime = StringUtil.trim(request.getParameter("max_interface_log_time"));//查询接口日志的终止时间
		if (StringUtil.isNotBlank(maxInterfaceLogTime)) {
			map.put("maxInterfaceLogTime", maxInterfaceLogTime);
		}
		PageModel pageModel = interfaceLogService.getInterfaceLog(map);
		dataMsg.setTotal(pageModel.getTotalRecords());
		dataMsg.setRows(pageModel.getList());
		
		return dataMsg;
	}
}
