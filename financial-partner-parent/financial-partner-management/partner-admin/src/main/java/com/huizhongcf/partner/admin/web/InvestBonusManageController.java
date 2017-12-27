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
import com.huizhongcf.partner.service.InvestBonusManageService;
import com.huizhongcf.util.DateTimeUtil;
import com.huizhongcf.util.ExportExcel;
import com.huizhongcf.util.PageModel;
import com.huizhongcf.util.StringUtil;

	

/**
*<dl>
*<dt>类名：InvestBonusManageController.java</dt>
*<dd>描述: 奖励单管理</dd>
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
@RequestMapping(value="/investBonusManage")
public class InvestBonusManageController extends BaseController{ 
	
	@Autowired
	private InvestBonusManageService investBonusManageService;
	
	/**
	 * 跳转到分页查询页面
	 * @param refreshTag
	 * @param messageCode
	 * @param model
	 * @return
	 */
	@RequestMapping("/toInvestBonusManageList")
	public String toEmpList(String refreshTag,String messageCode,Model model) {
		showMessageAlert(refreshTag,messageCode,model);
		return "app/biz/bonus_manage/bonus_order_manage";
	}
	/**
	 * 分页查询奖励单记录列表
	 * @param request
	 * @param dataMsg
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getInvestBonusManageList")
	public DataMsg getInvestBonusManageList(DataMsg dataMsg, HttpServletRequest request,HttpSession session){
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageNo", Integer.valueOf(request.getParameter("page")));
			map.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			String reward_num = StringUtil.trim(request.getParameter("reward_num"));
			if(StringUtil.isNotBlank(reward_num)) {
				map.put("reward_num", reward_num);
			}
			String contract_no = StringUtil.trim(request.getParameter("contract_no"));
			if(StringUtil.isNotBlank(contract_no)) {
				map.put("contract_no", contract_no);
			}
			String product_name = StringUtil.trim(request.getParameter("product_name"));
			if(StringUtil.isNotBlank(product_name)) {
				map.put("product_name", product_name);
			}
			String real_name = StringUtil.trim(request.getParameter("real_name"));
			if(StringUtil.isNotBlank(real_name)) {
				map.put("real_name", real_name);
			}
			String username = StringUtil.trim(request.getParameter("username"));
			if(StringUtil.isNotBlank(username)) {
				map.put("username", username);
			}
			String user_role = StringUtil.trim(request.getParameter("user_role"));
			if(StringUtil.isNotBlank(user_role)) {
				map.put("user_role", user_role);
			}
			String superior_real_name = StringUtil.trim(request.getParameter("superior_real_name"));
			if(StringUtil.isNotBlank(superior_real_name)) {
				map.put("superior_real_name", superior_real_name);
			}
			String superior_username = StringUtil.trim(request.getParameter("superior_username"));
			if(StringUtil.isNotBlank(superior_username)) {
				map.put("superior_username", superior_username);
			}
			String superior_user_type = StringUtil.trim(request.getParameter("superior_user_type"));
			if(StringUtil.isNotBlank(superior_user_type)) {
				map.put("superior_user_type", superior_user_type);
			}
			String on_superior_real_name = StringUtil.trim(request.getParameter("on_superior_real_name"));
			if(StringUtil.isNotBlank(on_superior_real_name)) {
				map.put("on_superior_real_name", on_superior_real_name);
			}
			String on_superior_username = StringUtil.trim(request.getParameter("on_superior_username"));
			if(StringUtil.isNotBlank(on_superior_username)) {
				map.put("on_superior_username", on_superior_username);
			}
			String on_superior_user_type = StringUtil.trim(request.getParameter("on_superior_user_type"));
			if(StringUtil.isNotBlank(on_superior_user_type)) {
				map.put("on_superior_user_type", on_superior_user_type);
			}
			String team_real_name = StringUtil.trim(request.getParameter("team_real_name"));
			if(StringUtil.isNotBlank(team_real_name)) {
				map.put("team_real_name", team_real_name);
			}
			String team_username = StringUtil.trim(request.getParameter("team_username"));
			if(StringUtil.isNotBlank(team_username)) {
				map.put("team_username", team_username);
			}
			String invest_status = StringUtil.trim(request.getParameter("invest_status"));
			if(StringUtil.isNotBlank(invest_status)) {
				map.put("invest_status", invest_status);
			}
			String min_create_time = request.getParameter("min_create_time");
			if(StringUtil.isNotBlank(min_create_time)) {
				map.put("min_create_time", min_create_time);
			}
			String max_create_time = request.getParameter("max_create_time");
			if(StringUtil.isNotBlank(max_create_time)) {
				map.put("max_create_time", max_create_time);
			}
			Integer employeeId = getSystemCurrentUser(session).getEmployeeId();
			map.put("employeeId", employeeId);
			PageModel pageModel = investBonusManageService.getInvestBonusList(map);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}
	/**
	 * 
	 * Description: 跳转订单详情页面
	 *
	 * @param
	 * @return String
	 * @throws 
	 * @Author lixiaoshuai 
	 * Create Date: 2017年12月9日 18:32:38
	 */
	@RequestMapping(value = "/toInvestDetail/{contractNo}")
	public String toSupplierEdit(@PathVariable String contractNo, String messageCode, Model model) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			paramsCondition.put("contractNo", contractNo);
			Map<String,Object> investDetail = investBonusManageService.getInvestDetail(paramsCondition);
			model.addAttribute("investDetail", investDetail);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return "common/exception";
		}
		return "app/biz/bonus_manage/invest_detail";
	}
	
	/**
	 * 
	 * Description: 用户数据导出功能
	 *
	 * @param 
	 * @param request
	 * @param response
	 * @param session
	 * @Author lixiaoshuai
	 * Create Date: 2017-12-8 上午10:32:54
	 */
	@RequestMapping(value="/exportInvestBonusList")
	public void exportInvestBonusList(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String reward_num = StringUtil.trim(request.getParameter("reward_num"));
			if(StringUtil.isNotBlank(reward_num)) {
				map.put("reward_num", reward_num);
			}
			String contract_no = StringUtil.trim(request.getParameter("contract_no"));
			if(StringUtil.isNotBlank(contract_no)) {
				map.put("contract_no", contract_no);
			}
			String product_name = StringUtil.trim(request.getParameter("product_name"));
			if(StringUtil.isNotBlank(product_name)) {
				map.put("product_name", product_name);
			}
			String real_name = StringUtil.trim(request.getParameter("real_name"));
			if(StringUtil.isNotBlank(real_name)) {
				map.put("real_name", real_name);
			}
			String username = StringUtil.trim(request.getParameter("username"));
			if(StringUtil.isNotBlank(username)) {
				map.put("username", username);
			}
			String user_role = StringUtil.trim(request.getParameter("user_role"));
			if(StringUtil.isNotBlank(user_role)) {
				map.put("user_role", user_role);
			}
			String superior_real_name = StringUtil.trim(request.getParameter("superior_real_name"));
			if(StringUtil.isNotBlank(superior_real_name)) {
				map.put("superior_real_name", superior_real_name);
			}
			String superior_username = StringUtil.trim(request.getParameter("superior_username"));
			if(StringUtil.isNotBlank(superior_username)) {
				map.put("superior_username", superior_username);
			}
			String superior_user_type = StringUtil.trim(request.getParameter("superior_user_type"));
			if(StringUtil.isNotBlank(superior_user_type)) {
				map.put("superior_user_type", superior_user_type);
			}
			String on_superior_real_name = StringUtil.trim(request.getParameter("on_superior_real_name"));
			if(StringUtil.isNotBlank(on_superior_real_name)) {
				map.put("on_superior_real_name", on_superior_real_name);
			}
			String on_superior_username = StringUtil.trim(request.getParameter("on_superior_username"));
			if(StringUtil.isNotBlank(on_superior_username)) {
				map.put("on_superior_username", on_superior_username);
			}
			String on_superior_user_type = StringUtil.trim(request.getParameter("on_superior_user_type"));
			if(StringUtil.isNotBlank(on_superior_user_type)) {
				map.put("on_superior_user_type", on_superior_user_type);
			}
			String team_real_name = StringUtil.trim(request.getParameter("team_real_name"));
			if(StringUtil.isNotBlank(team_real_name)) {
				map.put("team_real_name", team_real_name);
			}
			String team_username = StringUtil.trim(request.getParameter("team_username"));
			if(StringUtil.isNotBlank(team_username)) {
				map.put("team_username", team_username);
			}
			String invest_status = StringUtil.trim(request.getParameter("invest_status"));
			if(StringUtil.isNotBlank(invest_status)) {
				map.put("invest_status", invest_status);
			}
			String min_create_time = request.getParameter("min_create_time");
			if(StringUtil.isNotBlank(min_create_time)) {
				map.put("min_create_time", min_create_time);
			}
			String max_create_time = request.getParameter("max_create_time");
			if(StringUtil.isNotBlank(max_create_time)) {
				map.put("max_create_time", max_create_time);
			}
			List<Map<String, Object>> result = investBonusManageService.exportInvestBonusList(map);
			String title = "奖励单数据信息"+DateTimeUtil.getDateNormalString(new Date());
	        String[] rowsName = new String[]{"序号","奖励单号","合同编号","订单状态","产品名称","用户姓名","用户手机号","用户角色","用户身份","交易金额(元)","折标系数","单笔折标额(元)","出借奖金(元)","差额奖金(元)","奖励总额(元)","邀请人姓名","邀请人手机号","邀请人身份","出借奖金(元)","差额奖金(元)","奖励总额(元)","上级邀请人姓名","上级邀请人手机号","上级邀请人身份","出借奖金(元)","奖励总额(元)","团队管理者姓名","团队管理者手机号","服务奖金(元)","奖励总额(元)","生成时间"};
	        List<Object[]>  dataList = new ArrayList<Object[]>();
	        Object[] objs = null;
	        Map<String, Object> promotionMap = null;
	        for (int i = 0; i < result.size(); i++) {
	        	promotionMap = result.get(i);
	            objs = new Object[rowsName.length];
	            objs[0] = i+1;
	            objs[1] = promotionMap.get("reward_num");
	            objs[2] = promotionMap.get("contract_no");
	            objs[3] = promotionMap.get("invest_status");
	            objs[4] = promotionMap.get("product_name");
	            objs[5] = promotionMap.get("real_name");
	            objs[6] = promotionMap.get("username");
	            objs[7] = promotionMap.get("user_role");
	            objs[8] = promotionMap.get("user_type");
	            objs[9] = promotionMap.get("invest_amount");
	            objs[10] = promotionMap.get("discount_rate");
	            objs[11] = promotionMap.get("discount_amount");
	            objs[12] = promotionMap.get("user_lend_bonus");
	            objs[13] = promotionMap.get("user_diff_bonus");
	            objs[14] = promotionMap.get("user_total_bonus");
	            objs[15] = promotionMap.get("superior_real_name");
	            objs[16] = promotionMap.get("superior_username");
	            objs[17] = promotionMap.get("superior_user_type");
	            objs[18] = promotionMap.get("superior_lend_bonus");
	            objs[19] = promotionMap.get("superior_diff_bonus");
	            objs[20] = promotionMap.get("superior_total_bonus");
	            objs[21] = promotionMap.get("on_superior_real_name");
	            objs[22] = promotionMap.get("on_superior_username");
	            objs[23] = promotionMap.get("on_superior_user_type");
	            objs[24] = promotionMap.get("on_superior_lend_bonus");
	            objs[25] = promotionMap.get("on_superior_total_bonus");
	            objs[26] = promotionMap.get("team_real_name");
	            objs[27] = promotionMap.get("team_username");
	            objs[28] = promotionMap.get("service_bonus");
	            objs[29] = promotionMap.get("team_total_bonus");
	            objs[30] = promotionMap.get("create_time");
	            dataList.add(objs);
	        }
	        ExportExcel ex = new ExportExcel(title, rowsName, dataList);
	        ex.export(response);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
	}
	/**
	 * 分页查询奖励结算详情列表
	 * @param request
	 * @param dataMsg
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCommissionLiquidationDetailList")
	public DataMsg getCommissionLiquidationDetailList(DataMsg dataMsg, HttpServletRequest request,HttpSession session){
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageNo", Integer.valueOf(request.getParameter("page")));
			map.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			String userId = StringUtil.trim(request.getParameter("userId"));
			if(StringUtil.isNotBlank(userId)) {
				map.put("userId", userId);
			}
			String liquidationMonth = StringUtil.trim(request.getParameter("liquidationMonth"));
			if(StringUtil.isNotBlank(liquidationMonth)) {
				map.put("liquidationMonth", liquidationMonth);
			}
			PageModel pageModel = investBonusManageService.getCommissionLiquidationDetailList(map);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}
	/**
	 * 
	 * Description: 用户数据导出功能
	 *
	 * @param 
	 * @param request
	 * @param response
	 * @param session
	 * @Author lixiaoshuai
	 * Create Date: 2017-12-8 上午10:32:54
	 */
	@RequestMapping(value="/exportCommissionLiquidationDetailList")
	public void exportCommissionLiquidationDetailList(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String user_id = StringUtil.trim(request.getParameter("user_id"));
			if(StringUtil.isNotBlank(user_id)) {
				map.put("userId", user_id);
			}
			List<Map<String, Object>> result = investBonusManageService.exportCommissionLiquidationDetailList(map);
			String title = "奖励结算详情数据信息"+DateTimeUtil.getDateNormalString(new Date());
	        String[] rowsName = new String[]{"序号","合同编号","订单状态","产品名称","用户姓名","用户手机号","用户角色","用户身份","交易金额(元)","折标系数","单笔折标额(元)","邀请人姓名","邀请人手机号","邀请人身份","上级邀请人姓名","上级邀请人手机号","上级邀请人身份","团队管理者姓名","团队管理者手机号","生成时间"};
	        List<Object[]>  dataList = new ArrayList<Object[]>();
	        Object[] objs = null;
	        Map<String, Object> promotionMap = null;
	        for (int i = 0; i < result.size(); i++) {
	        	promotionMap = result.get(i);
	            objs = new Object[rowsName.length];
	            objs[0] = i+1;
	            objs[1] = promotionMap.get("contract_no");
	            objs[2] = promotionMap.get("invest_status");
	            objs[3] = promotionMap.get("product_name");
	            objs[4] = promotionMap.get("real_name");
	            objs[5] = promotionMap.get("username");
	            objs[6] = promotionMap.get("user_role");
	            objs[7] = promotionMap.get("user_type");
	            objs[8] = promotionMap.get("invest_amount");
	            objs[9] = promotionMap.get("discount_rate");
	            objs[10] = promotionMap.get("discount_amount");
	            objs[11] = promotionMap.get("superior_real_name");
	            objs[12] = promotionMap.get("superior_username");
	            objs[13] = promotionMap.get("superior_user_type");
	            objs[14] = promotionMap.get("on_superior_real_name");
	            objs[15] = promotionMap.get("on_superior_username");
	            objs[16] = promotionMap.get("on_superior_user_type");
	            objs[17] = promotionMap.get("team_real_name");
	            objs[18] = promotionMap.get("team_username");
	            objs[19] = promotionMap.get("create_time");
	            dataList.add(objs);
	        }
	        ExportExcel ex = new ExportExcel(title, rowsName, dataList);
	        ex.export(response);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
	}
}

