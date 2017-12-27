package com.hui.zhong.cf.service.Impl.partner.dao;


import com.hui.zhong.cf.service.partner.model.LoginUser;

public interface LoginUserMapper extends BaseMapper<LoginUser>{
	
	/**
	 * 
	 * Description: 查询用户信息
	 *
	 * @param 
	 * @return LoginUser
	 * @throws 
	 * @Author bao
	 * Create Date: 2017年12月12日 下午3:11:42
	 */
	public LoginUser getUserByInvitCode(LoginUser user);
	/**
	 * Description: 根据手机号查询用户信息
	 * @param
	 * @return LoginUser
	 * @throws
	 * @Author peigaoxiang
	 * Create Date: 2017年12月20日 下午4:12:42
	 */
	public LoginUser selectLoginUserByUsername(String username);
	/**
	 * Description: 验证邀请码是否重复
	 * @param
	 * @return LoginUser
	 * @throws
	 * @Author peigaoxiang
	 * Create Date: 2017年12月20日 下午4:12:42
	 */
	public Long selectCountByRecommendCode(String partnerRecommendCode);
}