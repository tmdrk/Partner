package com.huizhongcf.partner.admin.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huizhongcf.partner.admin.baseweb.BaseController;
import com.huizhongcf.partner.admin.baseweb.DataMsg;
import com.huizhongcf.partner.admin.constant.Constants;
import com.huizhongcf.partner.model.PartnerOrganization;
import com.huizhongcf.partner.service.PartnerOrganizationService;
import com.huizhongcf.util.PageModel;
import com.huizhongcf.util.PropertyUtil;
import com.huizhongcf.util.RandomNumUtil;
import com.huizhongcf.util.StringUtil;

/** 
*
* Description: 合伙人组织架构管理
*
* @author gdj
* @version 1.0
* <pre>
* Modification History: 
* Date         Author      Version     Description 
* ------------------------------------------------------------------ 
* 20171205   gdj      1.0        1.0 Version 
* </pre>
*/
@Controller
@RequestMapping("/partnerOrganization")
public class PartnerOrganizationController extends BaseController{
	
	@Autowired
	private PartnerOrganizationService partnerOrganizationService;
	
	/**
	 * 
	 * Description: 跳转到合伙人组织架构管理页面
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author gdj
	 * Create Date: 20171205
	 */
	@RequestMapping(value="/toListTreeOrgan")
	public String toListTreeOrgan(String refreshTag,String messageCode,Model model) {
		showMessageAlert(refreshTag,messageCode,model);
		return "app/biz/loginuser/organization/organization_tree";
	}
	
	/**
	 * 
	 * Description: 查找所有合伙人组织架构信息
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 * @Author gdj
	 * Create Date: 20171205
	 */
	@ResponseBody
	@RequestMapping(value="/organizations")
	public List<Map<String, Object>> listTreeDept(){
		List<Map<String, Object>> deptLists = partnerOrganizationService.findOrganizationList();
		return deptLists;
	}
	
	/**
	 * 
	 * Description: 查询当前选中合伙人组织架构下一级的组织信息
	 *
	 * @param
	 * @return
	 * @throws 
	 * @Author gdj
	 * Create Date: 20171205
	 */
	@RequestMapping(value="/organizationChildInfo")
	@ResponseBody
	public DataMsg organizationChildInfo(Integer organizationId,DataMsg dataMsg,HttpServletRequest request) {
		Map<String, Object> paramsCondition = new HashMap<String, Object>();
		paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("page")));
		paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("rows")));
		paramsCondition.put("organizationId", organizationId);
		PageModel pageModel = partnerOrganizationService.findOrganizationChildInfo(paramsCondition);
		dataMsg.setTotal(pageModel.getTotalRecords());
		dataMsg.setRows(pageModel.getList());
		return dataMsg;
	}
	
	/**
	 * 
	 * Description: 跳转到合伙人组织架构录入或编辑页面
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author gdj
	 * Create Date: 20171205
	 */
	@RequestMapping(value="/toEditPartnerOrganization")
	public String toEditPartnerOrganization(HttpServletRequest request){
		try {
			String organizationId = request.getParameter("organizationId");//当前节点，供修改使用
			if(null != organizationId){
				request.setAttribute("partnerOrganizationInfo", partnerOrganizationService.findUpdateOrganizationInfoByorganizationId(Integer.parseInt(organizationId)));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return "common/exception";
		}
		return "app/biz/loginuser/organization/partnerOrganization_edit";
	}
	
	/**
	 * 
	 * Description: 跳转到合伙人组织架构添加页面
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author gdj
	 * Create Date: 20171205
	 */
	@RequestMapping(value="/toAddPartnerOrganization")
	public String toAddPartnerOrganization(HttpServletRequest request){
		try {
			String parentId =  request.getParameter("parentId");//父节点，供添加使用
			if(null != parentId){
				PartnerOrganization partnerOrganization = partnerOrganizationService.selectByPrimaryKey(Integer.parseInt(parentId));//父节点组织名称，供添加页面显示使用
				request.setAttribute("parentId",Integer.parseInt(parentId));
				if(partnerOrganization != null){
					request.setAttribute("previousOrganizationName",partnerOrganization.getOrganizationName());
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return "common/exception";
		}
		return "app/biz/loginuser/organization/partnerOrganization_add";
	}
	
	/**
	 * 
	 * Description: 添加和修改组织架构信息
	 *
	 * @param 
	 * @return DataMsg
	 * @throws 
	 * @Author gdj
	 * Create Date: 20171205
	 */
	@ResponseBody
	@RequestMapping(value="/doAddEditOrganization")
	public DataMsg doAddEditOrganization(@ModelAttribute PartnerOrganization partnerOrganization,DataMsg dataMsg,HttpSession session){
		try {
			if(null != partnerOrganization.getOrganizationId()){
				//修改要编辑的组织架构信息，增加修改的操作人和修改时间
				partnerOrganization.setOperator(getSystemCurrentUser(session).getEmployeeId());
				partnerOrganization.setOperateTime(new Date());
				partnerOrganizationService.updateByPrimaryKeySelective(partnerOrganization);
				dataMsg.setMessageCode("0003");
			}else{
				//添加组织架构信息，增加组织架构编号、创建人、创建时间
				partnerOrganization.setOrganizationNo("1"+RandomNumUtil.getRandomNum(Constants.ORGANIZATION_NO_SIZE));//组织编号的首位为1，拼接7为随机数
				partnerOrganization.setCreateTime(new Date());
				partnerOrganization.setCreator(getSystemCurrentUser(session).getEmployeeId());
				partnerOrganizationService.insertSelective(partnerOrganization);
				dataMsg.setMessageCode("0001");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			dataMsg.setMessageCode("0007");
		}
		return dataMsg;
	}
	
	/**
	 * Description: 查询组织名称(级联二级)用于下拉展示
	 * @param organizationId
	 * @return
	 * @Author gdj
	 * Create Date: 20171206
	 */
	@ResponseBody
	@RequestMapping(value="/findSecondOrgan")
	public List<Map<String, Object>> findSecondOrgan(HttpServletRequest request){
		List<Map<String, Object>> list = null;
		list = partnerOrganizationService.findSecondPartnerOrganizationInfo();
		Map<String, Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("organization_id", "");
		tmpMap.put("organization_name", "请选择");
		list.add(0,tmpMap);
		return list;
	}
	
	/**
	 * Description: 查询组织名称(级联二级以后)用于下拉展示
	 * @param organizationId
	 * @return
	 * @Author gdj
	 * Create Date: 20171206
	 */
	@ResponseBody
	@RequestMapping(value="/findCascadingOrgan")
	public List<Map<String, Object>> findCascadingOrgan(HttpServletRequest request){
		List<Map<String, Object>> list = null;
		String organizationId = request.getParameter("organizationId");//当前级联选中的节点，供下拉列表展示
		if(null != organizationId){
			list = partnerOrganizationService.findCascadingPartnerOrganizationInfo(Integer.parseInt(organizationId));
		}
		Map<String, Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("organization_id", "");
		tmpMap.put("organization_name", "请选择");
		list.add(0,tmpMap);
		return list;
	}
	
	/**
	 * Description: 提交表单、操作处理后显示提示信息
	 */
	protected void showMessageAlert(String refreshTag,String messageCode,Model model){
		//refreshTag代表是不是右键刷新标志,当是右键刷新时，提示信息不能显示
		if(StringUtil.isBlank(refreshTag)){
			if (!StringUtil.isBlank(messageCode)) {
				// 读取提示信息
				model.addAttribute("messageCode", PropertyUtil.getMessageCodeInfo(messageCode));
			}
		}else {
			messageCode = "";//当是右键刷新时，提交标志置空
		}
	}
}
