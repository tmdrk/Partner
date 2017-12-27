package com.huizhongcf.partner.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huizhongcf.partner.dao.PartnerOrganizationMapper;
import com.huizhongcf.partner.model.PartnerOrganization;
import com.huizhongcf.partner.service.PartnerOrganizationService;
import com.huizhongcf.util.PageModel;

/** 
*
* Description: 组织架构管理
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
@Service("partnerOrganizationService")
public class PartnerOrganizationServiceImpl implements PartnerOrganizationService{

	@Autowired
	private PartnerOrganizationMapper partnerOrganizationMapper;
	
	@Override
	public PartnerOrganization selectByPrimaryKey(Integer organizationId) {
		return partnerOrganizationMapper.selectByPrimaryKey(organizationId);
	}

	@Override
	public Integer insertSelective(PartnerOrganization partnerOrganization) {
		return partnerOrganizationMapper.insertSelective(partnerOrganization);
	}

	@Override
	public Integer updateByPrimaryKeySelective(PartnerOrganization partnerOrganization) {
		return partnerOrganizationMapper.updateByPrimaryKeySelective(partnerOrganization);
	}
	@Override
	public List<Map<String, Object>> findOrganizationList() {
		return partnerOrganizationMapper.findOrganizationList();
	}
	
	@Override
	public Map<String, Object> findUpdateOrganizationInfoByorganizationId(Integer organizationId) {
		return partnerOrganizationMapper.findUpdateOrganizationInfoByorganizationId(organizationId);
	}

	@Override
	public PageModel findOrganizationChildInfo(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paramsCondition.get("pageNo"));
		pageModel.setPageSize((Integer) paramsCondition.get("pageSize"));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> data = partnerOrganizationMapper.findOrganChildInfoByParentId(paramsCondition);
		Long totalRecords = partnerOrganizationMapper.findOrganChildInfoByParentIdCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}
	
	@Override
	public List<Map<String, Object>> findSecondPartnerOrganizationInfo() {
		return partnerOrganizationMapper.findSecondPartnerOrganizationInfo();
	}

	@Override
	public List<Map<String, Object>> findCascadingPartnerOrganizationInfo(Integer organizationId) {
		return partnerOrganizationMapper.findCascadingPartnerOrganizationInfo(organizationId);
	}

}
