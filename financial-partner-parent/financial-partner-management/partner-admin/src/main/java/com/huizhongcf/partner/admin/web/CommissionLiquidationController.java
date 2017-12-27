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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huizhongcf.partner.admin.baseweb.BaseController;
import com.huizhongcf.partner.admin.baseweb.DataMsg;
import com.huizhongcf.partner.service.CommissionLiquidationService;
import com.huizhongcf.util.DateTimeUtil;
import com.huizhongcf.util.ExportExcel;
import com.huizhongcf.util.PageModel;
import com.huizhongcf.util.StringUtil;

	

/**
*<dl>
*<dt>类名：CommissionLiquidationController.java</dt>
*<dd>描述: 结算单管理</dd>
*<dd>创建时间： 2017年12月8日 下午1:53:48</dd>
*<dd>创建人：lixiaoshuai</dd>
*<dt>版本历史: </dt>
* <pre>
* Date Author Version Description
* ------------------------------------------------------------------
* 2017年12月8日 下午1:53:48 lixiaoshuai 1.0 1.0 Version
* </pre>
*</dl>
*/
@Controller
@RequestMapping(value="/commissionLiquidation")
public class CommissionLiquidationController extends BaseController{ 
	
	@Autowired
	private CommissionLiquidationService commissionLiquidationService;
	
	/**
	 * 跳转到分页查询页面
	 * @param refreshTag
	 * @param messageCode
	 * @param model
	 * @return
	 */
	@RequestMapping("/toCommissionLiquidationList")
	public String toEmpList(String refreshTag,String messageCode,Model model) {
		showMessageAlert(refreshTag,messageCode,model);
		return "app/biz/commission_liquidation/commission_liquidation_list";
	}
	/**
	 * 分页查询结算单记录列表
	 * @param request
	 * @param dataMsg
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCommissionLiquidationList")
	public DataMsg getCommissionPayList(DataMsg dataMsg, HttpServletRequest request,HttpSession session){
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageNo", Integer.valueOf(request.getParameter("page")));
			map.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			String liquidation_num = StringUtil.trim(request.getParameter("liquidation_num"));
			if(StringUtil.isNotBlank(liquidation_num)) {
				map.put("liquidation_num", liquidation_num);
			}

			String liquidation_month = StringUtil.trim(request.getParameter("liquidation_month"));
			if(StringUtil.isNotBlank(liquidation_month)) {
				map.put("liquidation_month", liquidation_month);
			}
			String liquidation_status = StringUtil.trim(request.getParameter("liquidation_status"));
			if(StringUtil.isNotBlank(liquidation_status)) {
				map.put("liquidation_status", liquidation_status);
			}
			String real_name = StringUtil.trim(request.getParameter("real_name"));
			if(StringUtil.isNotBlank(real_name)) {
				map.put("real_name", real_name);
			}
			String username = StringUtil.trim(request.getParameter("username"));
			if(StringUtil.isNotBlank(username)) {
				map.put("username", username);
			}
			String user_type = request.getParameter("user_type");
			if(StringUtil.isNotBlank(user_type)) {
				map.put("user_type", user_type);
			}
			String min_liquidation_time = request.getParameter("min_liquidation_time");
			if(StringUtil.isNotBlank(min_liquidation_time)) {
				map.put("min_liquidation_time", min_liquidation_time);
			}
			String max_liquidation_time = request.getParameter("max_liquidation_time");
			if(StringUtil.isNotBlank(max_liquidation_time)) {
				map.put("max_liquidation_time", max_liquidation_time);
			}
			Integer employeeId = getSystemCurrentUser(session).getEmployeeId();
			map.put("employeeId", employeeId);
			PageModel pageModel = commissionLiquidationService.getCommissionLiquidationList(map);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}
	/**
	 * 
	 * Description: 结算单结算
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author lixiaoshuai
	 * Create Date: 2017年12月9日 15:44:56
	 */
	@ResponseBody
	@RequestMapping("/commissionFeePay")
	public DataMsg commissionFeePay(HttpServletRequest request , DataMsg dataMsg ,HttpSession session) {
		try{
			Map<String, Object> requestMap = new HashMap<String,Object>();
			String commissionFeeId = request.getParameter("commissionFeeId");
			requestMap.put("commissionFeeId", commissionFeeId);
			int i = commissionLiquidationService.updateCommissionFeePay(requestMap);
			if (i > 0) {
				dataMsg.setMessageCode("0001");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			dataMsg.setMessageCode("0002");
		}
		return dataMsg;
	}
	
	/**
	 * 
	 * Description: 结算单批量结算
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author lixiaoshuai
	 * Create Date: 2017年12月9日 15:51:00
	 */
	@ResponseBody
	@RequestMapping("/commissionFeePayMany")
	public String commissionFeePayMany(String body , HttpSession session){
		String msg = commissionLiquidationService.commissionFeePayMany(body);
		return msg;
	}
	/**
	 * 导出结算单记录列表
	 * @param request
	 * @param dataMsg
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/exportCommissionLiquidationList")
	public void exportCommissionLiquidationList(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String liquidation_num = StringUtil.trim(request.getParameter("liquidation_num"));
			if(StringUtil.isNotBlank(liquidation_num)) {
				map.put("liquidation_num", liquidation_num);
			}

			String liquidation_month = StringUtil.trim(request.getParameter("liquidation_month"));
			if(StringUtil.isNotBlank(liquidation_month)) {
				map.put("liquidation_month", liquidation_month);
			}
			String liquidation_status = StringUtil.trim(request.getParameter("liquidation_status"));
			if(StringUtil.isNotBlank(liquidation_status)) {
				map.put("liquidation_status", liquidation_status);
			}
			String real_name = StringUtil.trim(request.getParameter("real_name"));
			if(StringUtil.isNotBlank(real_name)) {
				map.put("real_name", real_name);
			}
			String username = StringUtil.trim(request.getParameter("username"));
			if(StringUtil.isNotBlank(username)) {
				map.put("username", username);
			}
			String user_type = request.getParameter("user_type");
			if(StringUtil.isNotBlank(user_type)) {
				map.put("user_type", user_type);
			}
			String min_liquidation_time = request.getParameter("min_liquidation_time");
			if(StringUtil.isNotBlank(min_liquidation_time)) {
				map.put("min_liquidation_time", min_liquidation_time);
			}
			String max_liquidation_time = request.getParameter("max_liquidation_time");
			if(StringUtil.isNotBlank(max_liquidation_time)) {
				map.put("max_liquidation_time", max_liquidation_time);
			}
			List<Map<String, Object>> result = commissionLiquidationService.exportCommissionLiquidationList(map);
			String title = "奖励单数据信息"+DateTimeUtil.getDateNormalString(new Date());
	        String[] rowsName = new String[]{"序号","结算单号","结算年月","用户姓名","用户手机号","用户身份","交易总额(万元)","折标总额(万元)","出借奖金(元)","差额奖金(元)","服务奖金(元)","奖励总额(元)","结算状态","结算时间"};
	        List<Object[]>  dataList = new ArrayList<Object[]>();
	        Object[] objs = null;
	        Map<String, Object> promotionMap = null;
	        for (int i = 0; i < result.size(); i++) {
	        	promotionMap = result.get(i);
	            objs = new Object[rowsName.length];
	            objs[0] = i+1;
	            objs[1] = promotionMap.get("liquidation_num");
	            objs[2] = promotionMap.get("liquidation_month");
	            objs[3] = promotionMap.get("real_name");
	            objs[4] = promotionMap.get("username");
	            objs[5] = promotionMap.get("user_type");
	            objs[6] = promotionMap.get("invest_total_amount");
	            objs[7] = promotionMap.get("discount_total_amount");
	            objs[8] = promotionMap.get("lend_bonus");
	            objs[9] = promotionMap.get("diff_bonus");
	            objs[10] = promotionMap.get("service_bonus");
	            objs[11] = promotionMap.get("total_reward");
	            objs[12] = promotionMap.get("liquidation_status");
	            objs[13] = promotionMap.get("liquidation_time");
	            dataList.add(objs);
	        }
	        ExportExcel ex = new ExportExcel(title, rowsName, dataList);
	        ex.export(response);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	/**
	 * 
	 * Description: 跳转奖励结算详情页面
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author lixiaoshuai
	 * Create Date: 2017-12-7 下午2:33:32
	 */
	@RequestMapping(value="/toCommissionLiquidationDetail/{commissionLiquidationId}/{liquidationMonth}")
	public String toCommissionLiquidationDetail(@PathVariable Integer commissionLiquidationId,@PathVariable String liquidationMonth,Model model) {
		try {
			
			Map<String,Object> commissionLiquidationDetail = commissionLiquidationService.getCommissionLiquidationById(commissionLiquidationId);
			model.addAttribute("commissionLiquidationDetail", commissionLiquidationDetail);
			model.addAttribute("liquidationMonth", liquidationMonth);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return "common/exception";
		}
		return "app/biz/commission_liquidation/commission_liquidation_detail";
	}
}

