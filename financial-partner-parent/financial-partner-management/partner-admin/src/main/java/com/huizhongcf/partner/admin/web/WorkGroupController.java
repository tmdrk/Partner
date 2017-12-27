/* 
 * Copyright (C) 2006-2014 亿谱汇投资管理（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: WorkGroupController.java 
 *
 * Created: [2014-12-2 下午05:02:28] by ydw 
 *
 * $Id$
 * 
 * $Revision$
 *
 * $Author$
 *
 * $Date$
 *
 * ============================================================ 
 * 
 * ProjectName: sping-mvc 
 * 
 * Description: 
 * 
 * ==========================================================*/

package com.huizhongcf.partner.admin.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huizhongcf.partner.admin.baseweb.BaseController;
import com.huizhongcf.partner.admin.baseweb.DataMsg;
import com.huizhongcf.partner.model.WorkGroup;
import com.huizhongcf.partner.service.WorkGroupService;
import com.huizhongcf.util.StringUtil;


/** 
 *
 * Description: 员工工作组Controller
 *
 * @author ydw
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014-12-2    ydw       1.0        1.0 Version 
 * </pre>
 */
@Controller
@RequestMapping("/workGroup")
public class WorkGroupController extends BaseController{

	@Autowired
	private WorkGroupService workGroupService;
	
	/**
	 * 
	 * Description: 添加工作组
	 *
	 * @param 
	 * @return DataMsg
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-2 下午05:13:24
	 */
	@ResponseBody
	@RequestMapping("/addWorkGroup")
	public DataMsg addWorkGroup(@RequestParam Integer employeeId, @RequestParam String deptIds, @RequestParam String checkedStatus, @RequestParam String halfStatus, DataMsg dataMsg) {
		try {
			String[] deptIdsArray = {};
			String[] checkedStatusArray = {};
			String[] halfStatusArray = {};
			if(StringUtil.isNotBlank(deptIds) && StringUtil.isNotBlank(checkedStatus) && StringUtil.isNotBlank(halfStatus)){
				deptIdsArray = deptIds.split(",");
				checkedStatusArray = checkedStatus.split(",");
				halfStatusArray = halfStatus.split(",");
			}
			List<WorkGroup> workGroups = new ArrayList<WorkGroup>();
			WorkGroup workGroup = null;
			for (int i = 0; i < deptIdsArray.length; i++) {
				workGroup = new WorkGroup();
				workGroup.setEmployeeId(employeeId);
				workGroup.setDeptId(Integer.parseInt(deptIdsArray[i]));
				workGroup.setCheckedStatus(checkedStatusArray[i]);
				workGroup.setHalfStatus(halfStatusArray[i]);
				workGroups.add(workGroup);
			}
			workGroupService.insertWorkGroups(workGroups,employeeId);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			dataMsg.setMessageCode("0013");
		}
		return dataMsg;
	}
}
