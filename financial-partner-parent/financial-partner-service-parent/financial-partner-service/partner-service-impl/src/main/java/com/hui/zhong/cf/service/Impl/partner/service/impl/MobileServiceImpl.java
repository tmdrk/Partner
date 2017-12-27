package com.hui.zhong.cf.service.Impl.partner.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.json.ParseException;
import com.hui.zhong.cf.service.Impl.partner.dao.InterfaceLogMapper;
import com.hui.zhong.cf.service.Impl.partner.dao.LoginUserMapper;
import com.hui.zhong.cf.service.partner.model.LoginUser;
import com.hui.zhong.cf.service.partner.model.Partner;
import com.hui.zhong.cf.service.partner.service.MobileService;
import com.hui.zhong.cf.service.partner.service.PartnerService;
import com.huizhongcf.util.ReturnMsgData;

/**
 * 
 *
 * Description: H5调用
 *
 * @author baohongjian
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ * 2017年12月14日    bao       1.0        1.0 Version
 * </pre>
 */
@Service
public class MobileServiceImpl implements MobileService{
	private static final Logger LOG = LoggerFactory.getLogger(MobileServiceImpl.class);
	@Autowired
	private InterfaceLogMapper interfaceLogMapper;
	@Autowired
	private PartnerService partnerService;
	@Autowired
	private LoginUserMapper loginUserMapper;
 
	/**
	 * 
	 * Description: 调用汇中网接口，判断手机号是否存在
	 *
	 * @param 
	 * @return boolean
	 * @throws 
	 * @Author bao
	 * Create Date: 2017年12月14日 上午10:39:52
	 */
	public boolean checkMobileIsExist(String mobile){
		//汇中网提供接口以后再添加调用逻辑
		
		
		return true;
	}
	
	/**
	 * 
	 * Description: 用户注册
	 *
	 * @param 
	 * @return ReturnMsgData
	 * @throws 
	 * @Author bao
	 * Create Date: 2017年12月21日 下午3:09:40
	 */
	public ReturnMsgData doRegister(Partner partner) throws ParseException{
		// TODO 注册汇中网
		
		String recommendCode = partner.getRecommendCode();
		String loginPwd = partner.getLoginPwd();
		String username = partner.getUsername();
		LoginUser user = new LoginUser();
		
		user.setUsername(partner.getUsername());
		user.setUserStatus("1");
		user.setInputInvitationCode(partner.getRecommendCode());
		user.setPartnerTime(new Date());
		if(recommendCode.startsWith("301")) {
			//合伙人
			
		}else if(recommendCode.startsWith("601")) {
			//普通客户
			
		}else {
			//手机号码
		}
		return  new ReturnMsgData("0001", "");
	}

}
