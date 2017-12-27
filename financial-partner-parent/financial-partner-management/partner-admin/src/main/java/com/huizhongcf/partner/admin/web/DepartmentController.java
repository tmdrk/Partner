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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huizhongcf.partner.admin.baseweb.BaseController;
import com.huizhongcf.partner.admin.baseweb.DataMsg;
import com.huizhongcf.partner.model.Department;
import com.huizhongcf.partner.service.DepartmentService;

/** 
 *
 * Description: 部门管理
 *
 * @author ydw
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014-11-28    ydw       1.0        1.0 Version 
 * </pre>
 */
@Controller
@RequestMapping("/department")
public class DepartmentController extends BaseController{
	
	@Autowired
	private DepartmentService departmentService;

	/**
	 * 
	 * Description: 跳转到部门管理页面
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-11-28 下午02:34:25
	 */
	@RequestMapping(value="/toListTreeDept")
	public String toListTreeDept(String refreshTag,String messageCode,Model model) {
		showMessageAlert(refreshTag,messageCode,model);
		return "app/department/dept_tree";
	}
	
	/**
	 * 
	 * Description: 查找所有部门
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-11-28 下午06:24:03
	 */
	@ResponseBody
	@RequestMapping(value="/departments")
	public List<Map<String, Object>> listTreeDept(){
		List<Map<String, Object>> deptLists = departmentService.findDepartmentList();
		return deptLists;
	}
	
	/**
	 * Description: 查询业绩目标管理的二三级部门
	 * @param 
	 * @return String
	 * @throws 
	 * @Author Gaofeng
	 * Create Date:2017年8月8日
	*/
	@ResponseBody
	@RequestMapping(value="/departmentTwoAndThree")
	public List<Map<String, Object>> listTreeDeptcopy(){
		List<Map<String, Object>> deptLists = departmentService.findDepartmentTwoAndThree();
		return deptLists;
		
	}
	/**
	 * 
	 * Description: 跳转到部门录入或编辑页面
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-1 上午10:15:02
	 */
	@RequestMapping(value="/toInputDept")
	public String toInputDept(HttpServletRequest request){
		try {
			String parentId =  request.getParameter("parentId");//父节点，供添加使用
			if(null != parentId){
				request.setAttribute("parentId",Integer.parseInt(parentId));
			}
			String deptId = request.getParameter("deptId");//当前节点，供修改使用
			if(null != deptId){
				request.setAttribute("department", departmentService.selectByPrimaryKey(Integer.parseInt(deptId)));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return "common/exception";
		}
		return "app/department/dept_input";
	}
	
	/**
	 * 
	 * Description: 添加修改部门
	 *
	 * @param 
	 * @return DataMsg
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-1 上午11:05:00
	 */
	@ResponseBody
	@RequestMapping(value="/doAddEditDept")
	public DataMsg doAddEditDept(@ModelAttribute Department department,DataMsg dataMsg,HttpSession session){
		try {
			if(null != department.getDeptId()){
				departmentService.updateByPrimaryKeySelective(department);
				dataMsg.setMessageCode("0003");
			}else{
				department.setCreateTime(new Date());
				department.setCreator(getSystemCurrentUser(session).getEmployeeId());
				departmentService.insertSelective(department);
				dataMsg.setMessageCode("0001");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			dataMsg.setMessageCode("0007");
		}
		return dataMsg;
	}
	
	/**
	 * 
	 * Description: 删除部门
	 *
	 * @param 
	 * @return DataMsg
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-1 上午11:21:57
	 */
	@ResponseBody
	@RequestMapping(value="/doDelDept/{deptId}")
	public DataMsg doDelDept(@PathVariable Integer deptId,DataMsg dataMsg) {
		try {
			departmentService.deleteByPrimaryKey(deptId);
			dataMsg.setMessageCode("0005");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			dataMsg.setMessageCode("0006");
		}
		return dataMsg;
	}
	
	/**
	 * 
	 * Description: 查找员工列表    一级部门 财富管理部  
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-1 下午01:46:14
	 */
	@ResponseBody
	@RequestMapping(value="/findFirstDeptInfo")
	public List<Map<String, Object>> findFirstDeptInfo() throws Exception {
		List<Map<String, Object>> list = null;
		try {
			list = departmentService.findFirstDeptInfo();
			Map<String, Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put("dept_id", "");
			tmpMap.put("dept_name", "请选择");
			list.add(0,tmpMap);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return list;
	}
	
	/**
	 * 
	 * Description: 查找所在团队 三级级联的第二级(营业部)或第三级(团队)
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author yaodawei
	 * Create Date: 2014-2-20 上午10:47:18
	 */
	@ResponseBody
	@RequestMapping(value="/findSubWorkDeptInfoByDeptId")
	public List<Map<String, Object>> findSubWorkDeptInfoByDeptId(@RequestParam Integer deptId) throws Exception {
		List<Map<String, Object>> list = null;
		try {
			list = new ArrayList<Map<String,Object>>();
			Department department = new Department();
			department.setParentId(deptId);
			list = departmentService.findSubWorkDeptInfoByDeptId(department);
			Map<String, Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put("dept_id", "");
			tmpMap.put("dept_name", "请选择");
			list.add(0,tmpMap);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return list;
	}
	
	/**
	 * 
	 * Description: 跳转到选择部门的页面
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-2 上午11:21:21
	 */
	@RequestMapping(value="/toSelectDept")
	public String toSelectDept(@RequestParam Integer deptId,Model model) {
		model.addAttribute("deptId", deptId);
		return "app/department/dept_select";
	}
	
	/**
	 * 
	 * Description: 获得层级部门信息名称
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author yaodawei
	 * Create Date: 2014-2-11 下午02:05:20
	 */
	@RequestMapping("/getLevelDeptInfo/{deptId}")
	public String getLevelDeptInfo(@PathVariable Integer deptId,HttpServletResponse response) throws Exception {
		try {
			response.getWriter().write(departmentService.getLevelDeptInfo(deptId));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	/**
	 * 
	 * Description: 选择工作组
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-2-18 下午02:07:00
	 */
	@RequestMapping(value="/selectDeptGroup/{employeeId}")
	public String selectDeptGroup(@PathVariable Integer employeeId) {
		return "app/department/dept_group_select";
	}
	
	/**
	 * 
	 * Description: 查找所有部门，供工作组使用
	 *
	 * @param 
	 * @return DataMsg
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-2 下午04:39:26
	 */
	@ResponseBody
	@RequestMapping(value="/listTreeDeptGroup/{employeeId}")
	public List<Map<String, Object>> listTreeDeptGroup(@PathVariable Integer employeeId){
		List<Map<String, Object>> deptLists = null;
		try {
			deptLists = departmentService.findDepartmentGroupList(employeeId);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return deptLists;
	}
}
