package com.huizhongcf.partner.service;

import java.util.List;
import java.util.Map;

import com.huizhongcf.partner.model.LoginUser;
import com.huizhongcf.util.PageModel;

public interface LoginUserService {

	/**
	 * 登录用户分页列表
	 * @param map
	 * @return
	 * haochunhe
	 */
	public PageModel getLoginUser(Map<String,Object> map);
	
	/**
	 * 
	 * Description: 修改
	 *
	 * @param 
	 * @return int
	 * @throws 
	 * haochunhe
	 */
	public int updateByPrimaryKeySelective(LoginUser loginUser);
	
	/**
	 * 
	 * Description: 添加
	 *
	 * @param 
	 * @return int
	 * @throws 
	 * haochunhe
	 */
	public int insertSelective(LoginUser loginUser);
	
	/**
	 * 
	 * Description: 通过主键查询
	 *
	 * @param 
	 * @return userId
	 * @throws 
	 * haochunhe
	 */
    public LoginUser selectByPrimaryKey(Integer userId);
    
    /**
     * 根据userId查询需要的用户信息
     * @param userId
     * @return
     * haochunhe
     */
    public Map<String,Object> selectByUserId(Integer userId);
    
    /**
	 * 异常用户分页列表
	 * @param map
	 * @return
	 * haochunhe
	 */
	public PageModel getExceptionLoginUser(Map<String,Object> map);
	
	/**
	 * 查看邀请用户分页列表
	 * @param map
	 * @return
	 * haochunhe
	 */
	public PageModel getLoginUserList(Map<String,Object> map);
	
	/**
	 * 组织架构分配
	 * @param organizationChannelId
	 * @param organizationAreaId
	 * @param userIds
	 * haochunhe
	 */
	public void addOrganization(String organizationChannelId,String organizationAreaId,String userIds);
	
	/**
	 * 用户数据列表导出
	 * @param param
	 * @return
	 * haochunhe
	 */
	public List<Map<String,Object>> exportLoginUsers(Map<String,Object> param);
	
	/**
	 * 邀请用户数据列表导出
	 * @param param
	 * @return
	 * haochunhe
	 */
	public List<Map<String,Object>> exportInviteLoginUser(Map<String,Object> param);
	
	/**
	 * 查看一般合伙人下级用户数据列表
	 * @param param
	 * @return
	 * haochunhe
	 */
	public PageModel getLoginPartnerUserList(Map<String,Object> param);
	
	/**
	 * 团队管理者
	 * @param param
	 * @return
	 */
	public PageModel getTeamLoginPartnerUserList(Map<String,Object> param);
	
	/**
	  * 导出查看下级用户数据列表
	  * @param param
	  * @return
	  * haochunhe
	  */
	public List<Map<String,Object>> exportLoginPartnerUserList(Map<String,Object> param);
	
	/**
	  * 导出查看下级团队管理者用户数据列表
	  * @param param
	  * @return
	  * haochunhe
	  */
	 public List<Map<String,Object>> exportTeamLoginPartnerUserList(Map<String,Object> param);
	 
	 /**
	   * 
	   * Description: 根据邀请人推荐码查询用户信息
	   * @param partnerRecommendCode
	   * @return
	   * @Author haochunhe
	   * Create Date: 2017-12-9 下午3:32:54
	   */
	 public Map<String,Object> getUserByPartnerRecommendCodeOrUsername(String partnerRecommendCode);
	 
	 /**
	  * Description: 根据邀请人user_id变更用户关系
	  * @param user_id
	  * @param userIds
	  * @return
	  * @Author haochunhe
	  * Create Date: 2017-12-9 下午5:38:54
	  */
	 public void updataLoginUserRelation(String user_id,String userIds);
	 
	 /**
	  * Description: 数据导入功能
	  * @param file
	  * @param request
	  * @return int
	  * @Author haochunhe
	  * Create Date: 2017-12-18 下午7:38:54
	  */
	// public Map<String,Object> uploadLoginUser(MultipartFile file,HttpServletRequest request)throws Exception;
	
}
