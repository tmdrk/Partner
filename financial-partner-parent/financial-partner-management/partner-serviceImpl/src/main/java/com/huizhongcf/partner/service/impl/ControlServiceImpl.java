/* 
 * Copyright (C) 2006-2014 亿谱汇投资管理（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: ControlServiceImpl.java 
 *
 * Created: [2014-11-17 下午04:19:24] by ydw 
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

package com.huizhongcf.partner.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huizhongcf.partner.dao.ControlMapper;
import com.huizhongcf.partner.dao.ControlRoleRelationMapper;
import com.huizhongcf.partner.model.Control;
import com.huizhongcf.partner.service.ControlService;

/** 
 *
 * Description: 
 *
 * @author ydw
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014-11-17    ydw       1.0        1.0 Version 
 * </pre>
 */
@Service("controlService")
public class ControlServiceImpl implements ControlService{
	
	@Autowired
	private ControlMapper controlMapper;
	
	@Autowired
	private ControlRoleRelationMapper controlRoleRelationMapper;

	@Override
	public Map<String, String> findRoleByCtrl() {
		Map<String, String> ctrlRoleMap = new HashMap<String, String>();
		List<Map<String, Object>> ctrlList = controlMapper.findCtrlAllBySecurity();
		for(Map<String, Object> ctrlMap : ctrlList){
			List<String> roleList = controlRoleRelationMapper.findRoleCodeByCtrlId(Integer.valueOf(ctrlMap.get("control_id").toString()));
			StringBuffer sb = new StringBuffer();
			for(String roleCode : roleList){
				sb.append(roleCode);
				sb.append(",");
			}
			if(sb.length() >= 1){
				sb.delete(sb.length()-1, sb.length());
			}
			if(ctrlMap != null && sb != null){
				ctrlRoleMap.put(ctrlMap.get("control_mark").toString(), sb.toString());
			}
		}
		return ctrlRoleMap;
	}

	@Override
	public List<Map<String, Object>> findAllNoPage(Map<String, Object> paramsCondition) {
		List<Map<String, Object>> data = controlMapper.findAllRetMapNoPage(paramsCondition);
		return data;
	}

	@Override
	public Integer addControl(Control control) {
		return controlMapper.insertSelective(control);
	}
	
	@Override
	public Control getControlByControlId(Integer controlId) {
		return controlMapper.selectByPrimaryKey(controlId);
	}

	@Override
	public Integer editControl(Control control) {
		return controlMapper.updateByPrimaryKeySelective(control);
	}

	@Override
	public Map<String, Object> findPageControlInfoByContolId(Integer controlId) {
		return controlMapper.selectPageControlInfoByContolId(controlId);
	}
}
