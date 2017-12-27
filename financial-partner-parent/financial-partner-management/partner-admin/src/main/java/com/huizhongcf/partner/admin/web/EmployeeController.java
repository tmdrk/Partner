package com.huizhongcf.partner.admin.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huizhongcf.partner.admin.baseweb.BaseController;
import com.huizhongcf.partner.admin.baseweb.DataMsg;
import com.huizhongcf.partner.admin.constant.Constants;
import com.huizhongcf.partner.model.Employee;
import com.huizhongcf.partner.service.DepartmentService;
import com.huizhongcf.partner.service.EmployeeRoleRelationService;
import com.huizhongcf.partner.service.EmployeeService;
import com.huizhongcf.partner.service.WorkGroupService;
import com.huizhongcf.util.MD5Util;
import com.huizhongcf.util.PageModel;
import com.huizhongcf.util.PropertyUtil;
import com.huizhongcf.util.StringUtil;


/** 
 *<dl> 
 *<dt>类名：EmployeeController</dt> 
 *<dd>描述: 员工管理</dd>  
 *<dd>创建时间： 2017-8-9 13:19:43</dd> 
 *<dd>创建人： haochunhe</dd> 
 *<dt>版本历史: </dt> 
 * <pre> 
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2017-8-9 13:19:43    haochunhe       2.0        2.0 Version  
 * </pre> 
 *</dl> 
 */ 
@Controller
@RequestMapping("/employee")
public class EmployeeController extends BaseController{

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private EmployeeRoleRelationService employeeRoleRelationService;
	
	@Autowired
	private WorkGroupService workGroupService;
	
	/**
	 * 
	 * Description: 跳转到员工列表页面
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author yaodawei
	 * Create Date: 2014-1-23 下午03:19:43
	 */
	@RequestMapping("toEmpList")
	public String toEmpList(String refreshTag,String messageCode,Model model) {
		showMessageAlert(refreshTag,messageCode,model);
		return "app/employee/employee_list";
	}
	
	/**
	 * 
	 * Description: 员工分页查询列表
	 * 
	 * @param
	 * @return String
	 * @throws
	 * @Author yaodawei Create Date: 2013-7-2 下午07:01:23
	 */
	@ResponseBody
	@RequestMapping(value="/employeees")
	public DataMsg pageList(HttpServletRequest request,DataMsg dataMsg,HttpSession session) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("page")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			String name = StringUtil.trim(request.getParameter("name"));// 姓名
			if (StringUtil.isNotBlank(name)) {
				paramsCondition.put("name", name);
			}
			String position = StringUtil.trim(request.getParameter("position"));// 职位
			if (StringUtil.isNotBlank(position)) {
				paramsCondition.put("position", position);
			}
			String employeeNo = StringUtil.trim(request.getParameter("employeeNo"));// 员工编号
			if (StringUtil.isNotBlank(employeeNo)) {
				paramsCondition.put("employeeNo", employeeNo);
			}
			String email = StringUtil.trim(request.getParameter("email"));// 邮箱
			if (StringUtil.isNotBlank(email)) {
				paramsCondition.put("email", email);
			}
			String firstDept = request.getParameter("firstDept");//一级部门
			if (StringUtil.isNumeric(firstDept)) {
				paramsCondition.put("deptId", Integer.parseInt(firstDept));
			}
			String areaId = request.getParameter("areaId");//员工所属地区
			if (StringUtil.isNumeric(areaId)) {
				paramsCondition.put("deptId", Integer.parseInt(areaId));
			}
			String salesDeptId = request.getParameter("salesDeptId");//员工所属营业部
			if (StringUtil.isNumeric(salesDeptId)) {
				paramsCondition.put("deptId", Integer.parseInt(salesDeptId));
			}
			String teamId = request.getParameter("teamId");//员工所属团队
			if (StringUtil.isNumeric(teamId)) {
				paramsCondition.put("deptId", Integer.parseInt(teamId));
			}
			String groupId = request.getParameter("groupId");//员工所属小组
			if (StringUtil.isNumeric(groupId)) {
				paramsCondition.put("deptId", Integer.parseInt(groupId));
			}
			String employeeInviteCode = StringUtil.trim(request.getParameter("employeeInviteCode"));// 推荐码
			if (StringUtil.isNotBlank(employeeInviteCode)) {
				paramsCondition.put("employeeInviteCode", employeeInviteCode);
			}
			String activatedState = StringUtil.trim(request.getParameter("activatedState"));//账号使用状态
			if (StringUtil.isNotBlank(activatedState)) {
				paramsCondition.put("activatedState", activatedState);
			}
			Integer employeeId = getSystemCurrentUser(session).getEmployeeId();
			paramsCondition.put("employeeId", employeeId);
			PageModel pageModel = employeeService.findAllByPage(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}
	
	/**
	 * 
	 * Description: 修改密码时,验证原密码是否正确
	 *
	 * @param 
	 * @return void
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-1 下午05:27:55
	 */
	@ResponseBody
	@RequestMapping(value="/validateBasePwd")
	public DataMsg validateBasePwd(HttpServletRequest request,HttpSession session,DataMsg dataMsg) {
		try {
			Employee sessionEmployee = getSystemCurrentUser(session);
			
			Employee employee =new Employee();
			employee.setEmployeeId(sessionEmployee.getEmployeeId());
			employee.setPassword(MD5Util.md5(request.getParameter("password")+PropertyUtil.getInfo("md5Key")));
			//用户验证
			Employee ckEmp = employeeService.selectUsers(employee);
			if(ckEmp == null){
				dataMsg.setRequestState("false");
			}else{
				dataMsg.setRequestState("true");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			dataMsg.setRequestState("error");
		}
		return dataMsg;
	}
	
	/**
	 * 
	 * Description: 密码修改
	 *
	 * @param 
	 * @return DataMsg
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-1 下午06:59:53
	 */
	@ResponseBody
	@RequestMapping(value="/doUpdatePwd")
	public DataMsg doUpdatePwd(HttpServletRequest request,HttpSession session,DataMsg dataMsg){
		try {
			Employee sessionEmployee = getSystemCurrentUser(session);
			
			Employee employee = new Employee();
			employee.setEmployeeId(sessionEmployee.getEmployeeId());
			employee.setPassword(MD5Util.md5(request.getParameter("empPwd")+PropertyUtil.getInfo("md5Key")));
			//修改密码
			int result = employeeService.updateByPrimaryKeySelective(employee);
			sessionEmployee.setPassword(MD5Util.md5(request.getParameter("empPwd")+PropertyUtil.getInfo("md5Key")));
			//修改成功重新保存用户session
			session.setAttribute(Constants.SYSTEM_USER, sessionEmployee);
			dataMsg.setRequestState("true");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			dataMsg.setRequestState("error");
		}
		return dataMsg;
	}
	
	/**
	 * 
	 * Description: 跳转到添加员工页面
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-2 上午10:36:19
	 */
	@RequestMapping(value="/toAddEmp")
	public String toAddEmp(){
		return "app/employee/employee_add";
	}
	
	/**
	 * 
	 * Description: 添加员工
	 *
	 * @param 
	 * @return DataMsg
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-2 上午11:47:02
	 */
	@ResponseBody
	@RequestMapping(value="/doAddEmp")
	public DataMsg doAddEmp(@ModelAttribute Employee employee,HttpSession session,DataMsg dataMsg) {
		try {
			//employee.setPassword(MD5Util.md5("123@abc"+PropertyUtil.getInfo("md5Key")));//初始密码
			employee.setPassword(MD5Util.md5(PropertyUtil.getInfo("pwd")+PropertyUtil.getInfo("md5Key")));//初始密码
			employee.setCreateTime(new Date());
			employee.setCreator(getSystemCurrentUser(session).getEmployeeId());
			employeeService.insertSelective(employee);
			dataMsg.setMessageCode("0001");
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			dataMsg.setMessageCode("0002");
		}
		return dataMsg;
	}
	
	/**
	 * 
	 * Description: 跳转到修改员工页面
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author yaodawei
	 * Create Date: 2014-2-11 下午04:33:32
	 */
	@RequestMapping(value="/toEditEmp/{employeeId}")
	public String toEditEmp(@PathVariable Integer employeeId,Model model) {
		try {
			Employee employee = employeeService.selectByPrimaryKey(employeeId);
			model.addAttribute("employee", employee);
			model.addAttribute("deptInfo",departmentService.getLevelDeptInfo(employee.getDeptId()));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return "common/exception";
		}
		return "app/employee/employee_edit";
	}
	
	/**
	 * 
	 * Description: 修改员工信息
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author yaodawei
	 * Create Date: 2014-2-11 下午05:21:52
	 */
	@ResponseBody
	@RequestMapping("/doEditEmp")
	public DataMsg doEditEmp(@ModelAttribute Employee employee,HttpSession session,DataMsg dataMsg){
		try {
			employee.setOperator(getSystemCurrentUser(session).getEmployeeId());
			employee.setOperateTime(new Date());
			employeeService.updateByPrimaryKeySelective(employee);
			dataMsg.setMessageCode("0003");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			dataMsg.setMessageCode("0004");
		}
		return dataMsg;
	}
	
	/**
	 * 
	 * Description: 停用、启用
	 *
	 * @param 
	 * @return DataMsg
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-2 下午02:32:54
	 */
	@ResponseBody
	@RequestMapping(value="/disableOrEnabled")
	public DataMsg disableOrEnabled(@ModelAttribute Employee employee,HttpSession session,DataMsg dataMsg) {
		try {
			employee.setOperator(getSystemCurrentUser(session).getEmployeeId());
			employee.setOperateTime(new Date());
			// 执行
			employeeService.updateByPrimaryKeySelective(employee);
			if(employee.getActivatedState().equals(Constants.ACTIVATED_STATE_ENABLED)){
				dataMsg.setMessageCode("0008");
			}else if(employee.getActivatedState().equals(Constants.ACTIVATED_STATE_DISABLE)){
				dataMsg.setMessageCode("0010");
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			if(employee.getActivatedState().equals(Constants.ACTIVATED_STATE_ENABLED)){
				dataMsg.setMessageCode("0009");
			}else if(employee.getActivatedState().equals(Constants.ACTIVATED_STATE_DISABLE)){
				dataMsg.setMessageCode("0011");
			}
		}
		return dataMsg;
	}
	
	/**
	 * 
	 * Description: 密码重置
	 *
	 * @param 
	 * @return DataMsg
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-2 下午02:47:23
	 */
	@ResponseBody
	@RequestMapping(value="/resetPassword")
	public DataMsg resetPassword(@ModelAttribute Employee employee,HttpSession session,DataMsg dataMsg) {
		try {
			//employee.setPassword(MD5Util.md5("123@abc"+PropertyUtil.getInfo("md5Key")));//初始密码
			employee.setPassword(MD5Util.md5(PropertyUtil.getInfo("pwd")+PropertyUtil.getInfo("md5Key")));//初始密码
			employee.setOperator(getSystemCurrentUser(session).getEmployeeId());
			employee.setOperateTime(new Date());
			// 执行
			employeeService.updateByPrimaryKeySelective(employee);
			dataMsg.setRequestState("true");
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			dataMsg.setRequestState("false");
		}
		return dataMsg;
	}
	
	/**
	 * 
	 * Description: 跳转到设置员工工作组页面
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author yaodawei
	 * Create Date: 2014-2-14 上午10:29:43
	 */
	@RequestMapping(value="/toSetWorkGroup")
	public String toSetWorkGroup(Employee employee,Model model) {
		try {
			employee.setName(URLDecoder.decode(employee.getName(), "UTF-8"));
			model.addAttribute("levelDeptInfo", departmentService.getLevelDeptInfo(employee.getDeptId()));
			List<Map<String, Object>> workGroupInfo = workGroupService.findWorkGroupInfoByEmpId(employee.getEmployeeId());
			String deptIds = "";
			String checkedStatus = "";
			String halfStatus = "";
			for (Map<String, Object> map : workGroupInfo) {
				deptIds += map.get("dept_id") + ",";
				checkedStatus += map.get("checked_status") + ",";
				halfStatus += map.get("half_status") + ",";
			}
			if (workGroupInfo.size() > 1) {
				model.addAttribute("deptIds", deptIds.substring(0, deptIds.length()-1));
				model.addAttribute("checkedStatus", checkedStatus.substring(0, checkedStatus.length()-1));
				model.addAttribute("halfStatus", halfStatus.substring(0, halfStatus.length()-1));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return "common/exception";
		}
		return "app/employee/employee_workgroup";
	}
	
	/**
	 * 
	 * Description: 跳转到员工授权页面
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-10 下午01:15:34
	 */
	@RequestMapping(value="/toAuthorization/{empIds}")
	public String toAuthorization(@PathVariable String empIds) {
		return "app/employee/employee_authorization";
	}
	
	/**
	 * 
	 * Description: 添加员工角色关系
	 *
	 * @param 
	 * @return DataMsg
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-10 下午02:25:02
	 */
	@ResponseBody
	@RequestMapping(value="/doAddEmpAuthorization/{empIds}/{roleIds}")
	public DataMsg doAddEmpAuthorization(@PathVariable String empIds,@PathVariable String roleIds,DataMsg dataMsg,HttpSession session){
		try {
			employeeRoleRelationService.addEmpRoleRelation(empIds, roleIds, getSystemCurrentUser(session).getEmployeeId());
			dataMsg.setMessageCode("0014");
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			dataMsg.setMessageCode("0015");
		}
		return dataMsg;
	}
	
	/**
	 * 
	 * Description: 跳转到员工修改授权页面
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-10 下午02:52:51
	 */
	@RequestMapping(value="/toEditAuthorization")
	public String toEditAuthorization(Employee employee,Model model){
		try {
			employee.setName(URLDecoder.decode(employee.getName(), "UTF-8"));
			model.addAttribute("levelDeptInfo", departmentService.getLevelDeptInfo(employee.getDeptId()));
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(),e);
			return "common/exception";
		}
		return "app/employee/employee_authorization_edit";
	}
	
	/**
	 * 
	 * Description: 修改员工角色关系
	 *
	 * @param 
	 * @return DataMsg
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-10 下午02:25:02
	 */
	@ResponseBody
	@RequestMapping(value="/doEditEmpAuthorization")
	public DataMsg doEditEmpAuthorization(@RequestParam Integer employeeId,@RequestParam String roleIds,DataMsg dataMsg,HttpSession session){
		try {
			employeeRoleRelationService.editEmpRoleRelation(employeeId,roleIds,getSystemCurrentUser(session).getEmployeeId());
			dataMsg.setMessageCode("0014");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			dataMsg.setMessageCode("0015");
		}
		return dataMsg;
	}
}