package com.hui.zhong.cf.mobile.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.json.ParseException;
import com.alibaba.fastjson.JSON;
import com.google.inject.internal.Lists;
import com.hui.zhong.cf.mobile.dao.LoginUserMapper;
import com.hui.zhong.cf.mobile.model.LoginUser;
import com.hui.zhong.cf.mobile.model.PageModel;
import com.hui.zhong.cf.mobile.service.LoginUserService;
import com.hui.zhong.cf.mobile.utils.EncryptionUtil;
import com.hui.zhong.cf.service.partner.model.Partner;
import com.hui.zhong.cf.service.partner.service.MobileService;
import com.huizhongcf.util.ReturnMsgData;

@Service
public class LoginUserServiceImpl implements LoginUserService {
	public static Log log = LogFactory.getLog(LoginUserServiceImpl.class);
	@Autowired
	private LoginUserMapper loginUserMapper;
	@Autowired
	private MobileService mobileService;
	
	@Override
	public LoginUser doLogin(String userName, String passWord) {
		log.info("用后登录开始");
		LoginUser user = new LoginUser();
		user.setUsername(userName);
		user.setLoginPwd(EncryptionUtil.passWord(passWord));
		List<LoginUser> selectByLoginUser = loginUserMapper.selectByLoginUser(user);
		if (CollectionUtils.isNotEmpty(selectByLoginUser)) {
			return selectByLoginUser.get(0);
		}
		return null;
	}

	@Override
	public boolean checkUserNameExist(String userName) {
		LoginUser user = new LoginUser();
		user.setUsername(userName);
		List<LoginUser> selectByLoginUser = loginUserMapper.selectByLoginUser(user);
		if(selectByLoginUser !=null && selectByLoginUser.size()>0){
			return true;
		}
		// 远程查询用户是否存在
		log.info("本地不存在      远程调用 checkUserNameExist");
		if(!mobileService.checkMobileIsExist(userName)) {
			return true;
		};
		return false;
	}

	@Override
	public LoginUser doRegister(String userName, String passWord, String recommendCode) {
		LoginUser user = new LoginUser();
		user.setUsername(userName);
		// 远程调用接口
		Partner partner = new Partner();
		partner.setUsername(userName);
		partner.setRecommendCode(recommendCode);
		partner.setLoginPwd(EncryptionUtil.passWord(passWord));
		partner.setChannelCode(recommendCode.substring(0,3));
		try {
			log.info("远程注册调用");
			ReturnMsgData doRegister = mobileService.doRegister(partner);
			if("0000".equals(doRegister.getReturnCode())) {
				log.info("注册成功");
				List<LoginUser> selectByLoginUser = loginUserMapper.selectByLoginUser(user);
				return selectByLoginUser.get(0);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		log.error("注册异常");
		throw new RuntimeException("注册失败");
	}

	@Override
	public boolean forgetPassWord(String userName, String newPassWord) {
		LoginUser user = new LoginUser();
		user.setUsername(userName);
		user.setLoginPwd(EncryptionUtil.passWord(newPassWord));
		int updataPassWord = loginUserMapper.updataPassWord(user);
		if(updataPassWord >0 ){
			return true;
		}
		return false;
	}
	
	@Override
	public PageModel getMyCustomerWithInvestInfo(Map<String, Object> queryMap) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo(MapUtils.getIntValue(queryMap, "pageNo"));
		pageModel.setPageSize(MapUtils.getIntValue(queryMap, "pageSize"));
		queryMap.put("startIndex", pageModel.getStartIndex());
		queryMap.put("pageSize", pageModel.getPageSize());
		
		List<Map<String, Object>> customerList = loginUserMapper.getMyCustomerWithInvestInfo(queryMap);
		Long customerCount = loginUserMapper.getMyCustomerWithInvestInfoCount(queryMap);
		
		pageModel.setList(customerList);
		pageModel.setTotalRecords(customerCount);
		return pageModel;
	}

	@Override
	public PageModel getDirectSubordinatePartnerWithInvestInfo(Map<String, Object> queryMap) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo(MapUtils.getIntValue(queryMap, "pageNo"));
		pageModel.setPageSize(MapUtils.getIntValue(queryMap, "pageSize"));
		queryMap.put("startIndex", pageModel.getStartIndex());
		queryMap.put("pageSize", pageModel.getPageSize());
		
		List<Map<String, Object>> partnerList = loginUserMapper.getDirectSubordinatePartnerWithInvestInfo(queryMap);
		Long partnerCount = loginUserMapper.getDirectSubordinatePartnerWithInvestInfoCount(queryMap);
		
		pageModel.setList(partnerList);
		pageModel.setTotalRecords(partnerCount);
		return pageModel;
	}

	@Override
	public PageModel getOtherSubordinatePartnerWithInvestInfo(Map<String, Object> queryMap) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo(MapUtils.getIntValue(queryMap, "pageNo"));
		pageModel.setPageSize(MapUtils.getIntValue(queryMap, "pageSize"));
		queryMap.put("startIndex", pageModel.getStartIndex());
		queryMap.put("pageSize", pageModel.getPageSize());
		
		List<Map<String, Object>> partnerList = loginUserMapper.getOtherSubordinatePartnerWithInvestInfo(queryMap);
		Long partnerCount = loginUserMapper.getOtherSubordinatePartnerWithInvestInfoCount(queryMap);
		
		pageModel.setList(partnerList);
		pageModel.setTotalRecords(partnerCount);
		return pageModel;
	}

	@Override
	public List<Integer> getDirectSubordinatePartnerUserIds(Integer partnerUserId) {
		return loginUserMapper.getDirectSubordinatePartnerUserIds(partnerUserId);
	}

	@Override
	public List<Integer> getOtherSubordinatePartnerUserIds(Integer partnerUserId) {
		return loginUserMapper.getOtherSubordinatePartnerUserIds(partnerUserId);
	}

	@Override
	public List<Integer> getDirectCustomerUserIds(Integer... partnerUserIds) {
		List<Integer> partnerUserIdList = Lists.newArrayList(partnerUserIds);
		return loginUserMapper.getDirectCustomerUserIds(partnerUserIdList);
	}

	@Override
	public Long getDirectCustomerCount(Integer... partnerUserIds) {
		List<Integer> partnerUserIdList = Lists.newArrayList(partnerUserIds);
		return loginUserMapper.getDirectCustomerCount(partnerUserIdList);
	}

	@Override
	public Map<String, Object> getUserInfoById(Integer userId) {
		Map<String, Object> userInfoById = loginUserMapper.getUserInfoById(userId);
		if("10".equals(userInfoById.get("user_role"))) {
			
		}
		return userInfoById;
	}
	public static void main(String[] args) {
		String  a = "shiyang";
		System.out.println(a.substring(0,3));
	}
}
