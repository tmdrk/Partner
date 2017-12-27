package com.huizhongcf.partner.service;

import java.util.List;
import java.util.Map;

import com.huizhongcf.partner.model.Department;
import com.huizhongcf.partner.model.PartnerOrganization;
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
public interface PartnerOrganizationService {
	
	/**
	 * 
	 * Description: 通过主键查询
	 *
	 * @param 
	 * @return PartnerOrganization
	 * @throws 
	 * @Author gdj
	 * Create Date: 20171206
	 */
    public PartnerOrganization selectByPrimaryKey(Integer organizationId);
    
	/**
	 * 
	 * Description: 新增部门
	 *
	 * @param 
	 * @return Integer
	 * @throws 
	 * @Author gdj
	 * Create Date: 20171206
	 */
	public Integer insertSelective(PartnerOrganization partnerOrganization);
    
    /**
     * Description: 根据主键查询，组织架构信息并包含它上级名称
     * @param organizationId
     * @return
     * @Author gdj
	 * Create Date: 20171206
     */
    public Map<String, Object> findUpdateOrganizationInfoByorganizationId(Integer organizationId);
	
    /**
     * Description: 根据主键，修改组织架构信息
     * @param partnerOrganization
     * @return
     * @Author gdj
	 * Create Date: 20171206
     */
	public Integer updateByPrimaryKeySelective(PartnerOrganization partnerOrganization);
	
	/**
	 * 
	 * Description: 查询组织架构列表，用于组织架构管理页面
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 * @Author gdj
	 * Create Date: 20171205
	 */
	public List<Map<String, Object>> findOrganizationList();
	
	/**
	 * 
	 * Description: 查询当前选中组织架构下一级的组织信息
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 * @Author gdj
	 * Create Date: 20171205
	 */
	public PageModel findOrganizationChildInfo(Map<String, Object> paramsCondition);
	
	/**
	 * Description: 查询组织名称(级联二级)用于下拉展示
	 * @param organizationId
	 * @return
	 * @Author gdj
	 * Create Date: 20171206
	 */
	public List<Map<String, Object>> findSecondPartnerOrganizationInfo();
	
	/**
	 * Description: 查询组织名称(级联二级以后)用于下拉展示
	 * @param organizationId
	 * @return
	 * @Author gdj
	 * Create Date: 20171206
	 */
	public List<Map<String, Object>> findCascadingPartnerOrganizationInfo(Integer organizationId);
}
