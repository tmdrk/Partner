package com.hui.zhong.cf.mobile.dao;


import java.util.List;
import java.util.Map;

import com.hui.zhong.cf.mobile.model.LoginUser;

public interface LoginUserMapper extends BaseMapper<LoginUser> {
	
	/** 
	* @Description: 查询用户信息 
	* @param @param user
	* @param @return     
	* @return List<LoginUser>    返回类型 
	* @author  shiyang
	* @date 2017年12月12日 下午5:14:58 
	*/
	public List<LoginUser> selectByLoginUser(LoginUser user);
	 
	/** 
	* @Description: 修改密码 
	* @param @param user
	* @param @return     
	* @return int    返回类型 
	* @author  shiyang
	* @date  2017年12月12日 下午5:14:40 
	*/
	public int updataPassWord(LoginUser user);
	
	/** 
	* @Description: 查询用户详细 实名
	* @param userId
	* @return     
	* @return Map<String,String>   
	* @throws 
	* @author shiyang 
	* @date 2017年12月20日 上午9:58:44  
	*/
	public Map<String, Object> getUserInfoById(Integer userId);
	
	/**
	 * 查询合伙人自己发展的客户，查询结果中包含投资统计信息。<br>
	 * 该查询是分页查询。
	 * 
	 * @param queryMap
	 * @return
	 */
	List<Map<String, Object>> getMyCustomerWithInvestInfo(Map<String, Object> queryMap);
	
	/**
	 * 根据查询条件查询合伙人自己发展的客户数量。
	 * 
	 * @param queryMap
	 * @return
	 */
	Long getMyCustomerWithInvestInfoCount(Map<String, Object> queryMap);
	
	/**
	 * 查询合伙人直接发展的下级合伙人，查询结果中包含投资统计信息。
	 * 
	 * @param queryMap
	 * @return
	 */
	List<Map<String, Object>> getDirectSubordinatePartnerWithInvestInfo(Map<String, Object> queryMap);
	
	/**
	 * 根据查询条件查询下级合伙人数量。
	 * 
	 * @param queryMap
	 * @return
	 */
	Long getDirectSubordinatePartnerWithInvestInfoCount(Map<String, Object> queryMap);

	/**
	 * 查询其他下级合伙人。<br>
	 * 其他下级合伙人：该团队下，非该合伙人直接发展的下级合伙人。
	 * 
	 * @param queryMap
	 * @return
	 */
	List<Map<String, Object>> getOtherSubordinatePartnerWithInvestInfo(Map<String, Object> queryMap);
	
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
	public List<Integer> getOtherSubordinatePartnerUserIds(Integer partnerUserId);

	/**
	 * 查询本月/累计出借总额
	 * @param paraMap
	 * @return
	 * @author zhoujie
	 * @date 2017年12月12日 下午5:14:14
	 */
	Map<String, Object> queryloanAmount(Map<String, Object> paraMap);

	/**
	 * 查询本月/累计邀请合伙人(人)
	 * @param paraMap
	 * @return
	 * @author zhoujie
	 * @date 2017年12月12日 下午5:55:06
	 */
	Map<String, Object> queryInviter(Map<String, Object> paraMap);

	/**
	 * 查询累计出借单数
	 * @param paraMap
	 * @return
	 * @author zhoujie
	 * @date 2017年12月12日 下午7:18:54
	 */
	Map<String, Object> queryTotalLoanOrder(Map<String, Object> paraMap);
	
	/**
	 * 查询已出单合伙人/客户
	 * @param paraMap
	 * @return
	 * @author zhoujie
	 * @date 2017年12月12日 下午7:30:45
	 */
	Map<String, Object> queryLoanOrderCount(Map<String, Object> paraMap);

	/**
	 * 查询直属下级合伙人数
	 * @param paraMap
	 * @return
	 * @author zhoujie
	 * @date 2017年12月13日 下午4:09:42
	 */
	int countByCondition(Map<String, Object> paraMap);

	/**
	 * 根据合伙人user_id列表查询该合伙人的直接客户的user_id列表。
	 * 
	 * @param partnerUserIdList
	 * @return
	 */
	List<Integer> getDirectCustomerUserIds(List<Integer> partnerUserIdList);
	
	/**
	 * 根据合伙人user_id列表查询该合伙人的直接客户的总数量。
	 * 
	 * @param partnerUserIdList
	 * @return
	 */
	Long getDirectCustomerCount(List<Integer> partnerUserIdList);

	/**
	 * 
	 * 
	 * @param queryMap
	 * @return
	 */
	Long getOtherSubordinatePartnerWithInvestInfoCount(Map<String, Object> queryMap);
}