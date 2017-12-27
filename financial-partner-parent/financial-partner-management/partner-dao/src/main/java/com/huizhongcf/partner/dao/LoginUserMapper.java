package com.huizhongcf.partner.dao;

import java.util.List;
import java.util.Map;

import com.huizhongcf.partner.model.LoginUser;

public interface LoginUserMapper extends BaseMapper<LoginUser>{

	/**
     * 根据userId查询需要的用户信息
     * @param userId
     * @return
     * haochunhe
     */
	 public Map<String,Object> selectByUserId(Integer userId);
	 
	 /**
	  * 异常用户数据分页
	  * @param paramsCondition
	  * @return
	  */
	 public List<Map<String,Object>> findAllExceptionLoginUser(Map<String, Object> paramsCondition);
	 
	 /**
	  * 异常用户数据分页总数
	  * @param paramsCondition
	  * @return
	  */
	 public Long findAllExceptionLoginUserCount(Map<String, Object> paramsCondition);
	 
	 /**
	  * 查看邀请用户数据分页
	  * @param paramsCondition
	  * @return
	  */
	 public List<Map<String,Object>> findAllLoginUserList(Map<String, Object> paramsCondition);
	 
	 /**
	  * 查看邀请用户分页总数
	  * @param paramsCondition
	  * @return
	  */
	 public Long findAllLoginUserListCount(Map<String, Object> paramsCondition);
	 
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
	public List<Map<String,Object>> getLoginPartnerUserList(Map<String,Object> param);
	
	 /**
	  * 查看一般合伙人下级用户数据总数
	  * @param paramsCondition
	  * @return
	  * haochunhe
	  */
	 public Long getLoginPartnerUserCount(Map<String, Object> paramsCondition);
	 
	 /**
	  * 查看团队管理者下级用户数据列表
	  * @param param
	  * @return
	  * haochunhe
	  */
	 public List<Map<String,Object>> getTeamLoginPartnerUserList(Map<String,Object> param);
	 
	 /**
	  * 查看团队管理者下级用户数据总数
	  * @param paramsCondition
	  * @return
	  * haochunhe
	  */
	 public Long getTeamLoginPartnerUserCount(Map<String, Object> paramsCondition);
	 
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
	 public Map<String,Object> getUserByPartnerRecommendCodeOrUsername(Map<String,Object> param);
	 
	 /**
	  * 
	  * Description: 根据用户业绩上级合伙人id查询用户信息
	  * @param param
	  * @return
	  * @Author haochunhe
	  * Create Date: 2017-12-9 下午5:32:54
	  */
	 public List<LoginUser> getUserIdBySuperiorRecommendCodeUserId(LoginUser loginUser);
	 
	 /**
	  * 
	  * Description: 根据角色是客户的user_id查询下级所有客户user_id 
	  * @param param
	  * @return
	  * @Author haochunhe
	  * Create Date: 2017-12-9 下午5:32:54
	  */
	 public List<LoginUser> getChilUserIdByUserId(LoginUser loginUser);
	 
	 /**
	  * 合伙人推荐码管理分页条件查询
	  * @param paramsCondition
	  * @return
	  * @Author GDJ
	  * Create Date: 2017-12-7
	  */
	 public List<Map<String,Object>> findAllRecommendCodeInfo(Map<String, Object> paramsCondition);
	 
	 /**
	  * 合伙人推荐码管理分页条件查询数量
	  * @param paramsCondition
	  * @return
	  * @Author GDJ
	  * Create Date: 2017-12-7
	  */
	 public Long findAllRecommendCodeCount(Map<String, Object> paramsCondition);
	 
	 /**
	  * 推荐码管理页面的导出全部记录
	  * @param paramsCondition
	  * @return
	  * @Author GDJ
	  * Create Date: 2017-12-8
	  */
	 public List<Map<String,Object>> exportAllRecommendCodeInfo(Map<String, Object> paramsCondition);
	 
	 /**
	  * 推荐码管理页面的导出选中记录
	  * @param paramsCondition
	  * @return
	  * @Author GDJ
	  * Create Date: 2017-12-8
	  */
	 public List<Map<String,Object>> exportSelectRecommendCodeInfo(Map<String, Object> paramsCondition);
	 
}