package com.hui.zhong.cf.mobile.service;

import java.util.List;
import java.util.Map;

import com.hui.zhong.cf.mobile.model.LoginUser;
import com.hui.zhong.cf.mobile.model.PageModel;

public interface LoginUserService {

	/** 
	* @Description: 登录
	* @param @param userName
	* @param @param passWrod
	* @param @return     
	* @return LoginUser    返回类型 
	* @author  shiyang
	* @date 2017年12月11日 下午5:00:39 
	*/
	public LoginUser doLogin(String userName, String passWord);

	/** 
	* @Description: 检查用户是否存在
	* @param  userName
	* @param      
	* @return boolean    返回类型 ：存在 返回true，不存在false
	* @author  shiyang
	* @date 2017年12月11日 下午5:00:49 
	*/
	public boolean checkUserNameExist(String userName);

	 
	/** 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param  userName
	* @param  passWrod
	* @param  recommendCode 推荐码
	* @param @return     
	* @return LoginUser    返回类型 
	* @author  shiyang
	* @date 2017年12月11日 下午5:02:59 
	*/
	public LoginUser doRegister(String userName, String passWord,String recommendCode);

	/** 
	* @Description: 修改密码 
	* @param  userName
	* @param  newPassWord
	* @param @return     
	* @return boolean    返回类型 
	* @author  shiyang
	* @date 2017年12月11日 下午5:01:29 
	*/
	public boolean forgetPassWord(String userName, String newPassWord);
	
	
	/** 
	* @Description: 查询用户详细 实名
	* @param userId
	* @return     
	* @return Map<String,String>   
	* @throws 
	* @author shiyang 
	* @date 2017年12月20日 上午9:58:44  
	*/
	public Map<String,Object> getUserInfoById(Integer userId);

	/**
	 * 查询合伙人自己发展的客户，查询结果中包含投资统计信息。<br>
	 * 本查询是分页查询。
	 * 
	 * @param queryMap
	 * <pre>
	 * 			username：t_login_user表的username字段
	 * 			realName：t_user_info表的real_name字段
	 * 			partnerUserId：合伙人的user_id
	 * 			partnerUserIdList：合伙人user_id列表
	 * 			pageNo：第几页
	 * 			pageSize：每页条数
	 * </pre>
	 * partnerUserId和partnerUserIdList，这两个参数只会使用其中一个，不能同时使用。
	 * 
	 * @return
	 */
	PageModel getMyCustomerWithInvestInfo(Map<String, Object> queryMap);
	
	/**
	 * 查询合伙人直接发展的下级合伙人，查询结果中包含投资统计信息。<br>
	 * 本查询是分页查询。
	 * 
	 * @param queryMap
	 * <pre>
	 * 			username：t_login_user表的username字段
	 * 			realName：t_user_info表的real_name字段
	 * 			partnerUserId：合伙人的user_id
	 * 			pageNo：第几页
	 * 			pageSize：每页条数
	 * </pre>
	 * @return
	 */
	PageModel getDirectSubordinatePartnerWithInvestInfo(Map<String, Object> queryMap);

	/**
	 * 查询其他下级合伙人。<br>
	 * 其他下级合伙人：该团队下，非该合伙人直接发展的下级合伙人。
	 * 
	 * @param queryMap
	 * @return
	 */
	PageModel getOtherSubordinatePartnerWithInvestInfo(Map<String, Object> queryMap);
	
	/**
	 * 根据合伙人user_id查询该合伙人的直接下级合伙人的user_id列表。
	 * 
	 * @param partnerUserId
	 * @return
	 */
	List<Integer> getDirectSubordinatePartnerUserIds(Integer partnerUserId);
	
	/**
	 * 根据合伙人user_id查询该合伙人的其他下级合伙人的user_id列表。
	 * 
	 * @param partnerUserId
	 * @return
	 */
	List<Integer> getOtherSubordinatePartnerUserIds(Integer partnerUserId);
	
	/**
	 * 根据合伙人user_id列表查询该合伙人的直接客户的user_id列表。<br>
	 * 该查询可查单个合伙人的客户列表，也可以查询多个合伙人的客户列表。
	 * 
	 * @param partnerUserIds
	 * @return
	 */
	List<Integer> getDirectCustomerUserIds(Integer... partnerUserIds);
	
	/**
	 * 根据合伙人user_id列表查询该合伙人的直接客户的总数量。
	 * 
	 * @param partnerUserIds
	 * @return
	 */
	Long getDirectCustomerCount(Integer... partnerUserIds);
}
