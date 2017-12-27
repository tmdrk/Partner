package com.hui.zhong.cf.service.Impl.partner.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.json.ParseException;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.hui.zhong.cf.service.Impl.partner.dao.InterfaceLogMapper;
import com.hui.zhong.cf.service.Impl.partner.dao.LoginUserMapper;
import com.hui.zhong.cf.service.Impl.partner.dao.PartnerMapper;
import com.hui.zhong.cf.service.Impl.partner.dao.ReservedInviteCodeMapper;
import com.hui.zhong.cf.service.partner.model.InterfaceLog;
import com.hui.zhong.cf.service.partner.model.LoginUser;
import com.hui.zhong.cf.service.partner.model.Partner;
import com.hui.zhong.cf.service.partner.model.ReservedInviteCode;
import com.hui.zhong.cf.service.partner.service.PartnerService;
import com.huizhongcf.Enum.UserTypeEnum;
import com.huizhongcf.util.Constants;
import com.huizhongcf.util.NewSendMessageUtil;
import com.huizhongcf.util.ReturnMsgData;
import com.huizhongcf.util.TradeWaterNumUtil;

/**
 * 
 *
 * Description: 汇中网对外接口
 *
 * @author baohongjian
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年12月12日    bao       1.0        1.0 Version 
 * </pre>
 */
@Service
public class PartnerServiceImpl implements PartnerService{

	private static final Logger LOG = LoggerFactory.getLogger(PartnerServiceImpl.class);

	@Autowired
	private PartnerMapper partnermapper;
	@Autowired
	private LoginUserMapper loginUserMapper;
	@Autowired
	private ReservedInviteCodeMapper reservedInviteCodeMapper;
	
	@Autowired
	private InterfaceLogMapper interfaceLogMapper;//日志service

	public List<Partner> find(Partner partner){
		return partnermapper.find(partner);
	}
	
	/**
	 * 
	 * Description: 汇中网向合伙人同步注册用户
	 *
	 * @param 
	 * @return 
	 * @throws 
	 * @Author bao
	 * Create Date: 2017年12月11日 下午6:34:45
	 */
	public ReturnMsgData SaveRegister(String body) throws ParseException{
		LOG.info("汇中网用户注册,入参:body="+body);
		//保存日志
		try{
		 InterfaceLog interfaceLog=new InterfaceLog();
	     interfaceLog.setTradeType("‘汇中网’向‘合伙人’推送注册信息");//接口类型
	     interfaceLog.setReqData(body);// 请求报文
	     interfaceLog.setSystemDocking(Constants.SYSTEM_CODE_01);//系统标识，01：汇中网，20：合伙人
	     interfaceLog.setCreateTime(new Date());//创建时间
	     interfaceLogMapper.insertSelective(interfaceLog);//保存日志
		}catch(Exception e){
			LOG.error("", e);
		}
		ReturnMsgData data = new ReturnMsgData("0000","成功");
		Partner partner = JSONArray.parseArray(body, Partner.class).get(0);
		String channelCode = partner.getChannelCode();
		String recommendCode = partner.getRecommendCode();
		if(StringUtils.isEmpty(channelCode)){
			LOG.warn("渠道码为空");
			data.setReturnCode("1002");
			data.setReturnMsg("渠道码不能为空");
			return data;
		}
		if(StringUtils.isEmpty(recommendCode)){
			LOG.warn("邀请码码为空");
			data.setReturnCode("1002");
			data.setReturnMsg("邀请码码不能为空");
			return data;
		}
		LoginUser checkUser = new LoginUser();
		checkUser.setUsername(partner.getUsername());
		LoginUser getCheckUser = loginUserMapper.getUserByInvitCode(checkUser);
		if(getCheckUser != null){
			LOG.warn("用户已经存在");
			data.setReturnCode("1003");
			data.setReturnMsg("用户已经存在");
			return data;
		}
		
		LoginUser user = new LoginUser();
		user.setUsername(partner.getUsername());
		user.setUserStatus("1");
		user.setPartnerTime(new Date());
		user.setInputInvitationCode(recommendCode);
		user.setIsPlatformInvite("0");//添加是否是平台邀请  "1" 是 , "0" 否

		if(UserTypeEnum.customer.getType().equals(channelCode)){
			LOG.info("客户注册流程");
			//补全客户信息
			updateCustomerInfo(user);
		}else if(UserTypeEnum.partner.getType().equals(channelCode)){
			LOG.info("合伙人注册流程");
			//补全合伙人信息
			updatePartner(user);
		}else{
			LOG.warn("【异常数据】不能判断住注册人员是客户还是合伙人");
			data.setReturnCode("1002");
			data.setReturnMsg("渠道码异常");
			LOG.error("异常数据:"+body);
			return data;
		}
		user.setRegistTime(partner.getRegistTime());
		user.setCreateTime(new Date());
		user.setOperateTime(new Date());
//		user.setLoginPwd(partner.getLoginPwd());

		String userRole = user.getUserRole();
		String message = "";
		if("10".equals(userRole)){//合伙人
			message = "您已成功注册合伙人平台,您的下级合伙人推荐码为"+user.getPartnerRecommendCode()+",客户推荐码为"+user.getCustomerRecommendCode();
		}else{
			message = "您已成功注册合伙人平台，您的客户推荐码为"+user.getCustomerRecommendCode();
			user.setCustomerRecommendCode(null);
		}

		loginUserMapper.insertSelective(user);

		//user.setUserType("20");
		if("20".equals(user.getUserType())){//如果是团队管理者,更新用户表达团队管理者字段
			LoginUser updateUser = new LoginUser();
			updateUser.setUserId(user.getUserId());
			updateUser.setTeamRecommendCodeUserId(user.getUserId());
			loginUserMapper.updateByPrimaryKeySelective(updateUser);
		}

		LOG.info("发送短信:"+user.getUsername());
		NewSendMessageUtil.sendSmsProduct(user.getUsername(), message);

		LOG.info("用户注册,出参:"+JSON.toJSONString(user));
		return data;
	}

	/**
	 * 
	 * Description: 补全客户信息
	 *
	 * @param 
	 * @return void
	 * @throws 
	 * @Author bao
	 * Create Date: 2017年12月12日 下午2:09:42
	 */
	public void updateCustomerInfo(LoginUser user){
		user.setUserRole("20");
		user.setInviteShareType("3");
		String invitationCode = user.getInputInvitationCode();

		LOG.info("查询合伙人,客户邀请码:"+invitationCode);
		LoginUser getUser = null;
		if("1".equals(invitationCode.substring(0,1))){
			LOG.info("邀请码为手机号");
			LoginUser getUserByUsername = new LoginUser();
			getUserByUsername.setUsername(invitationCode);
			getUserByUsername.setUserStatus("1");
			getUser = loginUserMapper.getUserByInvitCode(getUserByUsername);
			if(getUser == null){
				LOG.warn("【数据异常】根据手机号查询登录用户,username = " + invitationCode);
				return;
			}
		}else{
			LOG.info("邀请码不是手机号");
			LoginUser newUser = new LoginUser();
			newUser.setCustomerRecommendCode(invitationCode);
			newUser.setUserRole("10");
			newUser.setUserStatus("1");
			getUser = loginUserMapper.getUserByInvitCode(newUser);
			if(getUser == null){
				LOG.warn("【数据异常】查询合伙人失败");
				return;
			}
		}
		LOG.info("设置业绩归属上级合伙人");
		user.setSuperiorRecommendCodeUserId(getUser.getUserId());
		LOG.info("设置业绩归属上上级合伙人");
		Integer parentUserId = getUser.getParentUserId();
		if(parentUserId != null){
			user.setOnSuperiorRecommendCodeUserId(parentUserId);
		}
		LOG.info("设置业绩归属团队管理者");
		Integer teamRecommendCodeUserId = null;
		if("20".equals(getUser.getUserType())){
			teamRecommendCodeUserId = getUser.getUserId();
		}else{
			if(getUser.getTeamRecommendCodeUserId() != null){
				teamRecommendCodeUserId = getUser.getTeamRecommendCodeUserId();
			}
		}
		user.setTeamRecommendCodeUserId(teamRecommendCodeUserId);
		user.setCustomerRecommendCode(getUser.getCustomerRecommendCode());
		user.setParentUserId(getUser.getUserId());
	}

	/**
	 * 
	 * Description: 补全合伙人信息
	 *
	 * @param 
	 * @return void
	 * @throws 
	 * @Author bao
	 * Create Date: 2017年12月12日 下午2:10:45
	 */
	public void updatePartner(LoginUser user){
		user.setUserRole("10");
		user.setInviteShareType("1");
		String invitationCode = user.getInputInvitationCode();
		
		LOG.info("判断是否为平台邀请码");
		ReservedInviteCode code = new ReservedInviteCode();
		code. setInviteCode(invitationCode);
		code.setInviteCodeType("10");
		code.setUseStatus("1");
		List<ReservedInviteCode> getInviteCodes = reservedInviteCodeMapper.getReservedInviteCode(code);
		if(getInviteCodes != null && getInviteCodes.size() != 0){
			LOG.info("邀请码{}是平台邀请码",invitationCode);
			LOG.info("设置为团队管理者");
			user.setUserType("20");
			user.setIsPlatformInvite("1");//添加是否是平台邀请  "1" 是 , "0" 否
			ReservedInviteCode teemLead = new ReservedInviteCode();
			teemLead.setInviteCodeType("20");
			teemLead.setUseStatus("1");
			List<ReservedInviteCode> getTeemLeads = reservedInviteCodeMapper.getReservedInviteCode(teemLead);
			if(getTeemLeads == null || getTeemLeads.size() == 0){
				LOG.error("【数据异常】查询预留邀请码为空");
				return;
			}
			String inviteCode = getTeemLeads.get(0).getInviteCode();
			user.setPartnerRecommendCode(inviteCode);
			user.setCustomerRecommendCode("6"+inviteCode.substring(1));
		    LOG.info("设置预留邀请码为已使用状态");
		    ReservedInviteCode updateInviteCode = new ReservedInviteCode();
		    updateInviteCode.setReservedId(getTeemLeads.get(0).getReservedId());
		    updateInviteCode.setUseStatus("2");
		    reservedInviteCodeMapper.updateByPrimaryKeySelective(updateInviteCode);
		}else{
			LOG.info("邀请码{}不是平台邀请码",invitationCode);
			user.setUserType("10");

			//生成合伙人的“合伙人推荐码”“客户推荐码”
			String partnerRecommendCode="";
			for (int i = 0; ; i++) {
				partnerRecommendCode="301"+TradeWaterNumUtil.getRandomEX();
				Long countRecommendCode= loginUserMapper.selectCountByRecommendCode(partnerRecommendCode);
				long  countRecommendCodeValue=countRecommendCode.longValue();
				if(countRecommendCodeValue>0){
					continue;
				}else{
					break;
				}
			}
//			user.setPartnerRecommendCode(TradeWaterNumUtil.genTradeWaterNum(UserTypeEnum.partner));
//			user.setCustomerRecommendCode(TradeWaterNumUtil.genTradeWaterNum(UserTypeEnum.customer));
			LOG.info("生成的邀请码:{}",partnerRecommendCode);
			user.setPartnerRecommendCode(partnerRecommendCode);
			user.setCustomerRecommendCode("6"+partnerRecommendCode.substring(1));

			LOG.info("查询上级合伙人");
			LoginUser getUser = null;
			if("1".equals(invitationCode.substring(0,1))){
				LOG.info("邀请码是手机号:"+invitationCode);
				LoginUser getUserByUsername = new LoginUser();
				getUserByUsername.setUsername(invitationCode);
				getUserByUsername.setUserStatus("1");
				getUser = loginUserMapper.getUserByInvitCode(getUserByUsername);
			}else{
				LoginUser newUser = new LoginUser();
				newUser.setPartnerRecommendCode(invitationCode);
				newUser.setUserRole("10");
				getUser = loginUserMapper.getUserByInvitCode(newUser);
			}
			if(getUser == null){
				LOG.warn("【数据异常】查询上级合伙人");
				return;
			}
			String userType = getUser.getUserType();
			Integer teamRecommendCodeUserId;
			if("20".equals(userType)){//团队管理者
				teamRecommendCodeUserId = getUser.getUserId();
			}else{
				teamRecommendCodeUserId = getUser.getTeamRecommendCodeUserId();
			}
			user.setSuperiorRecommendCodeUserId(getUser.getUserId());
			user.setTeamRecommendCodeUserId(teamRecommendCodeUserId);
			user.setParentUserId(getUser.getUserId());
		}
	}


}
