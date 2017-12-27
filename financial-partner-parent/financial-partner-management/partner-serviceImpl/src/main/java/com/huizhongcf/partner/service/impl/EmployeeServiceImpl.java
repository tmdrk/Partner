package com.huizhongcf.partner.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huizhongcf.partner.dao.EmployeeMapper;
import com.huizhongcf.partner.model.Employee;
import com.huizhongcf.partner.service.EmployeeService;
import com.huizhongcf.util.PageModel;

/** 
 *
 * Description: 
 *	员工管理实现类EmployeeServiceImpl
 * @author haochunhe
 * @version 2.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017-8-9    hch       2.0        2.0 Version 
 * </pre>
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService{
	private Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Override
	public Employee selectUsers(Employee employee) {
		return employeeMapper.checkUsers(employee);
	}

	@Override
	public PageModel findAllByPage(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paramsCondition.get("pageNo"));
		pageModel.setPageSize((Integer) paramsCondition.get("pageSize"));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> data = employeeMapper.findAllRetMapByPage(paramsCondition);
		Long totalRecords = employeeMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}

	@Override
	public int updateByPrimaryKeySelective(Employee employee) {
		return employeeMapper.updateByPrimaryKeySelective(employee);
	}

	@Override
	public int insertSelective(Employee employee) {
		return employeeMapper.insertSelective(employee);
	}

	@Override
	public Employee selectByPrimaryKey(Integer employeeId) {
		return employeeMapper.selectByPrimaryKey(employeeId);
	}

}
