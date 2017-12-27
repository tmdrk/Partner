package com.huizhongcf.partner.dao;

import java.util.List;
import java.util.Map;

import com.huizhongcf.partner.model.PartnerOrganization;

/**
*
* Description: 组织架构管理
*
* @author gdj
* @version 1.0
* 
*          <pre>
* Modification History: 
* Date         Author      Version     Description 
* ------------------------------------------------------------------ 
* 20171205   gdj      1.0        1.0 Version
*          </pre>
*/
public interface PartnerOrganizationMapper extends BaseMapper<PartnerOrganization>{
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
	 * Description: 查询当前选中组织架构下一级的组织信息，用于分页
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 * @Author gdj
	 * Create Date: 20171205
	 */
	public List<Map<String, Object>> findOrganChildInfoByParentId(Map<String, Object> paramsCondition);

	/**
	 * 
	 * Description: 查询当前选中组织架构下一级的组织数量，用于分页
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 * @Author gdj
	 * Create Date: 20171206
	 */
	public Long findOrganChildInfoByParentIdCount(Map<String, Object> paramsCondition);
	
	/**
	 * Description: 修改时查询组织架构信息通过主键ID,并关联出上级组织名称
	 * @param organizationId
	 * @return
	 * @Author gdj
	 * Create Date: 20171206
	 */
	public Map<String, Object> findUpdateOrganizationInfoByorganizationId(Integer organizationId);
	
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