package com.huizhongcf.partner.admin.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huizhongcf.partner.admin.baseweb.BaseController;
import com.huizhongcf.partner.admin.baseweb.DataMsg;
import com.huizhongcf.partner.service.RecommendManageService;
import com.huizhongcf.util.DateTimeUtil;
import com.huizhongcf.util.ExportExcel;
import com.huizhongcf.util.PageModel;
import com.huizhongcf.util.StringUtil;

/**
 * 推荐码管理Controller
 *
 * @author gdj
 * @version 1.0
 * 
 *          <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 20171207    gdj       1.0        1.0 Version
 *          </pre>
 */
@Controller
@RequestMapping("/recommendManage")
public class RecommendManageController extends BaseController {

	@Autowired
	private RecommendManageService recommendManageService;

	/**
	 * 
	 * Description: 跳转到推荐码管理页面
	 *
	 * @param
	 * @return String
	 * @throws 
	 * @Author gdj 
	 * Create Date: 20171205
	 */
	@RequestMapping(value = "/toRecommendManage")
	public String toRecommendManage(String refreshTag, String messageCode, Model model) {
		showMessageAlert(refreshTag, messageCode, model);
		return "app/biz/loginuser/recommend_manage";
	}

	/**
	 * 
	 * Description: 查看推荐码历史记录
	 *
	 * @param
	 * @return String
	 * @throws 
	 * @Author gdj 
	 * Create Date: 20171205
	 */
	@RequestMapping(value = "/getRommendHistoryList")
	@ResponseBody
	public DataMsg getRommendHistoryList(DataMsg dataMsg, HttpServletRequest request) {
		Map<String, Object> paramsCondition = new HashMap<String, Object>();
		paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("page")));
		paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("rows")));
		// 获取筛选条件内容
		String realName = StringUtil.trim(request.getParameter("realName"));
		if (StringUtil.isNotBlank(realName)) {
			paramsCondition.put("realName", realName);//真实姓名
		}
		String mobile = StringUtil.trim(request.getParameter("mobile"));//手机号
		if (StringUtil.isNotBlank(mobile)) {
			paramsCondition.put("mobile", mobile);
		}
		String partnerRecommendCode = StringUtil.trim(request.getParameter("partnerRecommendCode"));//合伙人推荐码
		if (StringUtil.isNotBlank(partnerRecommendCode)) {
			paramsCondition.put("partnerRecommendCode", partnerRecommendCode);
		}
		String customerRecommendCode = StringUtil.trim(request.getParameter("customerRecommendCode"));//客户推荐码
		if (StringUtil.isNotBlank(customerRecommendCode)) {
			paramsCondition.put("customerRecommendCode", customerRecommendCode);
		}
		String minCreateRecommendTime = StringUtil.trim(request.getParameter("minCreateRecommendTime"));
		if (StringUtil.isNotBlank(minCreateRecommendTime)) {
			paramsCondition.put("minCreateRecommendTime", minCreateRecommendTime);
		}
		String maxCreateRecommendTime = StringUtil.trim(request.getParameter("maxCreateRecommendTime"));
		if (StringUtil.isNotBlank(maxCreateRecommendTime)) {
			paramsCondition.put("maxCreateRecommendTime", maxCreateRecommendTime);
		}
		PageModel pageModel = recommendManageService.findAllRecommendCodePage(paramsCondition);
		dataMsg.setTotal(pageModel.getTotalRecords());
		dataMsg.setRows(pageModel.getList());
		return dataMsg;
	}

	/**
	 * 推荐码导出全部功能
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @Author gdj 
	 * Create Date: 20171205
	 */
	@RequestMapping(value = "/exportAllRommend")
	public void exportEmployes(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			// 获取筛选条件内容
			String realName = StringUtil.trim(request.getParameter("realName"));
			if (StringUtil.isNotBlank(realName)) {
				paramsCondition.put("realName", realName);
			}
			String mobile = StringUtil.trim(request.getParameter("mobile"));
			if (StringUtil.isNotBlank(mobile)) {
				paramsCondition.put("mobile", mobile);
			}
			String partnerRecommendCode = StringUtil.trim(request.getParameter("partnerRecommendCode"));
			if (StringUtil.isNotBlank(partnerRecommendCode)) {
				paramsCondition.put("partnerRecommendCode", partnerRecommendCode);
			}
			String customerRecommendCode = StringUtil.trim(request.getParameter("customerRecommendCode"));
			if (StringUtil.isNotBlank(customerRecommendCode)) {
				paramsCondition.put("customerRecommendCode", customerRecommendCode);
			}
			String minCreateRecommendTime = StringUtil.trim(request.getParameter("minCreateRecommendTime"));
			if (StringUtil.isNotBlank(minCreateRecommendTime)) {
				paramsCondition.put("minCreateRecommendTime", minCreateRecommendTime);
			}
			String maxCreateRecommendTime = StringUtil.trim(request.getParameter("maxCreateRecommendTime"));
			if (StringUtil.isNotBlank(maxCreateRecommendTime)) {
				paramsCondition.put("maxCreateRecommendTime", maxCreateRecommendTime);
			}
			List<Map<String, Object>> result = recommendManageService.exportAllRecommendCodeInfo(paramsCondition);
			ExportExcel ex = this.exportRecommendExcel(result);
			ex.export(response);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 推荐码导出选中功能
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @Author gdj 
	 * Create Date: 20171205
	 */
	@RequestMapping(value = "/exportSelectRommend")
	public void exportSelectRommend(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			// 获取筛选条件内容
			String selectIds = StringUtil.trim(request.getParameter("selectIds"));
			if (StringUtil.isNotBlank(selectIds)) {
				String[] ids = selectIds.split("-");
				paramsCondition.put("selectIds", ids);
			}
			List<Map<String, Object>> result = recommendManageService.exportSelectRecommendCodeInfo(paramsCondition);
			ExportExcel ex = this.exportRecommendExcel(result);
			ex.export(response);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	//全部导出和选中公共的部分
	private static ExportExcel exportRecommendExcel(List<Map<String, Object>> result){
		String title = "推荐码信息" + DateTimeUtil.getNowTimeShortString();
		String[] rowsName = new String[] { "序号","用户ID","用户姓名", "用户角色", "用户手机号", "合伙人推荐码", "客户推荐码", "推荐码生成时间" };
		List<Object[]> dataList = new ArrayList<Object[]>();
		Object[] objs = null;
		Map<String, Object> promotionMap = null;
		for (int i = 0; i < result.size(); i++) {
			promotionMap = result.get(i);
			objs = new Object[rowsName.length];
			objs[0] = i + 1;
			objs[1] = promotionMap.get("user_id");
			objs[2] = promotionMap.get("real_name");
			objs[3] = promotionMap.get("user_role_name");
			objs[4] = promotionMap.get("username");
			objs[5] = promotionMap.get("partner_recommend_code");
			objs[6] = promotionMap.get("customer_recommend_code");
			objs[7] = promotionMap.get("create_time");
			dataList.add(objs);
		}
		ExportExcel ex = new ExportExcel(title, rowsName, dataList);
		return ex;
	}
}
