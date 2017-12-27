package com.huizhongcf.partner.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huizhongcf.partner.dao.LoginUserMapper;
import com.huizhongcf.partner.model.LoginUser;
import com.huizhongcf.partner.service.LoginUserService;
import com.huizhongcf.util.Constants;
import com.huizhongcf.util.PageModel;
import com.huizhongcf.util.StringUtil;


@Service("LoginUserService")
public class LoginUserServiceImpl implements LoginUserService{
	
	private Logger logger = LoggerFactory.getLogger(LoginUserServiceImpl.class);
	@Autowired
	private LoginUserMapper loginUserMapper;
//	@Autowired
//	private UserInfoMapper userInfoMapper;
//	@Autowired
//	private PartnerService partnerService;//用户注册dubbo服务
//	@Autowired
//	private PartnerOrderService partnerOrderService;//用户实名dubbo服务
	
	@Override
	public PageModel getLoginUser(Map<String, Object> map) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) map.get("pageNo"));
		pageModel.setPageSize((Integer) map.get("pageSize"));
		map.put("startIndex", pageModel.getStartIndex());
		map.put("endIndex", pageModel.getEndIndex());
		List<Map<String,Object>> list = loginUserMapper.findAllRetMapByPage(map);
		Long totalRecords = loginUserMapper.findAllByPageCount(map);
		pageModel.setList(list);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}
	
	@Override
	public int updateByPrimaryKeySelective(LoginUser loginUser) {
		return loginUserMapper.updateByPrimaryKeySelective(loginUser);
	}
	
	@Override
	public int insertSelective(LoginUser loginUser) {
		return loginUserMapper.insertSelective(loginUser);
	}
	@Override
	public LoginUser selectByPrimaryKey(Integer userId) {
		return loginUserMapper.selectByPrimaryKey(userId);
	}
	
	@Override
	public Map<String,Object> selectByUserId(Integer userId) {
		return loginUserMapper.selectByUserId(userId);
	}

	@Override
	public PageModel getExceptionLoginUser(Map<String, Object> map) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) map.get("pageNo"));
		pageModel.setPageSize((Integer) map.get("pageSize"));
		map.put("startIndex", pageModel.getStartIndex());
		map.put("endIndex", pageModel.getEndIndex());
		List<Map<String,Object>> list = loginUserMapper.findAllExceptionLoginUser(map);
		Long totalRecords = loginUserMapper.findAllExceptionLoginUserCount(map);
		pageModel.setList(list);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}

	@Override
	public PageModel getLoginUserList(Map<String, Object> map) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) map.get("pageNo"));
		pageModel.setPageSize((Integer) map.get("pageSize"));
		map.put("startIndex", pageModel.getStartIndex());
		map.put("endIndex", pageModel.getEndIndex());
		List<Map<String,Object>> list = loginUserMapper.findAllLoginUserList(map);
		Long totalRecords = loginUserMapper.findAllLoginUserListCount(map);
		pageModel.setList(list);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}

	@Override
	public void addOrganization(String organizationChannelId, String organizationAreaId, String userIds) {
		String[] userIdArray = userIds.split(",");
		LoginUser loginUser = null;
		Date date = new Date();
		for(int i = 0; i < userIdArray.length; i++){
			loginUser = new LoginUser();
			loginUser.setUserId(Integer.valueOf(userIdArray[i]));
			loginUser.setOrganizationChannelId(Integer.valueOf(organizationChannelId));
			loginUser.setOrganizationAreaId(Integer.valueOf(organizationAreaId));
			loginUser.setOperateTime(date);
			loginUserMapper.updateByPrimaryKeySelective(loginUser);
		}
	}

	@Override
	public List<Map<String, Object>> exportLoginUsers(Map<String, Object> param) {
		return loginUserMapper.exportLoginUsers(param);
	}

	@Override
	public List<Map<String, Object>> exportInviteLoginUser(Map<String, Object> param) {
		return loginUserMapper.exportInviteLoginUser(param);
	}

	@Override
	public PageModel getLoginPartnerUserList(Map<String, Object> map) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) map.get("pageNo"));
		pageModel.setPageSize((Integer) map.get("pageSize"));
		map.put("startIndex", pageModel.getStartIndex());
		map.put("endIndex", pageModel.getEndIndex());
		List<Map<String,Object>> list = loginUserMapper.getLoginPartnerUserList(map);
		Long totalRecords = loginUserMapper.getLoginPartnerUserCount(map);
		pageModel.setList(list);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}
	@Override
	public PageModel getTeamLoginPartnerUserList(Map<String, Object> map) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) map.get("pageNo"));
		pageModel.setPageSize((Integer) map.get("pageSize"));
		map.put("startIndex", pageModel.getStartIndex());
		map.put("endIndex", pageModel.getEndIndex());
		List<Map<String,Object>> list = loginUserMapper.getTeamLoginPartnerUserList(map);
		Long totalRecords = loginUserMapper.getTeamLoginPartnerUserCount(map);
		pageModel.setList(list);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}

	@Override
	public List<Map<String, Object>> exportLoginPartnerUserList(Map<String, Object> param) {
		return loginUserMapper.exportLoginPartnerUserList(param);
	}

	@Override
	public List<Map<String, Object>> exportTeamLoginPartnerUserList(Map<String, Object> param) {
		return loginUserMapper.exportTeamLoginPartnerUserList(param);
	}

	@Override
	public Map<String, Object> getUserByPartnerRecommendCodeOrUsername(String partnerRecommendCode) {
		logger.info("=============根据用户推荐码或手机号查询,用户输入的推荐码是=====>" +partnerRecommendCode);
		Map<String,Object> param = new HashMap<>();
		Map<String,Object> result = new HashMap<>();
		String isMobile = partnerRecommendCode.substring(0,1);
		if (StringUtil.isNotBlank(partnerRecommendCode)) {
			if("1".equals(isMobile)){//判断推荐码是否是手机号
				param.put("username", partnerRecommendCode);
			}else if("3".equals(isMobile)){
				param.put("partnerRecommendCode", partnerRecommendCode);
			}else{
				param.put("customerRecommendCode", partnerRecommendCode);
			}
		}
		result = loginUserMapper.getUserByPartnerRecommendCodeOrUsername(param);
		if(!"1".equals(isMobile) && result==null){
			//1101表示推荐码查询用户数据不存在 
			Map<String,Object> result1 = new HashMap<>();
			result1.put("code", "1101");
			return result1;
		}
		if(result !=null){
			String partner_recommend_code = (String) result.get("partner_recommend_code");
			if (StringUtil.isNotBlank(partner_recommend_code) && "1".equals(isMobile)) {
				result.put("code", "1111");//1111表示推荐码是手机号查询用户数据成功
			}
			if(StringUtil.isBlank(partner_recommend_code) && "1".equals(isMobile)){
				result.put("code", "1001");//1001表示推荐码是手机号查询用户数据不存在
			}
			if(!"1".equals(isMobile)&&(StringUtil.isNotBlank(partner_recommend_code))){
				result.put("code", "0000");//推荐码查询成功
			}
		}else{
			Map<String,Object> result1 = new HashMap<>();
			result1.put("code", "1101");
			return result1;
		}
		logger.info("=======根据邀请人推荐码查询用户信息结果是========" +result.toString());
		return result;
	}

	@Override
	public void updataLoginUserRelation(String user_id, String userIds) {
		logger.info("======>变更的用户user_id有=====" +userIds+"=======>变更为的用户是=====" + user_id);
		Integer userId = Integer.parseInt(user_id);
		String[] userIdArray = userIds.split(",");
		Date date = new Date();
		Map<String,Object> resultParam = loginUserMapper.selectByUserId(userId);
		Integer teamRecommendCodeUserId = null;
		Integer onSuperiorRecommendCodeUserId = null;
		if(resultParam != null){
			teamRecommendCodeUserId = (Integer) resultParam.get("team_recommend_code_user_id");
			onSuperiorRecommendCodeUserId = (Integer) resultParam.get("superior_recommend_code_user_id");
			logger.info("==============查询出变更为的用户上级业绩归属和服务费归属是===" +teamRecommendCodeUserId +"==>和===>"+onSuperiorRecommendCodeUserId);
		}
		for(int i = 0; i < userIdArray.length; i++){
			LoginUser loginUser = new LoginUser();
			Map<String,Object> resMap = loginUserMapper.selectByUserId(Integer.valueOf(userIdArray[i]));
			String userRole = null;
			if(resMap != null){
				userRole = (String) resMap.get("user_role");
			}
			loginUser.setUserId(Integer.valueOf(userIdArray[i]));
			loginUser.setParentUserId(userId);
			loginUser.setSuperiorRecommendCodeUserId(userId);
			loginUser.setTeamRecommendCodeUserId(teamRecommendCodeUserId);
			loginUser.setOperateTime(date);
			loginUser.setOperator(1);
			if(Constants.USER_ROLE_20.equals(userRole)){
				logger.info("==========变更的是客户============");
				loginUser.setOnSuperiorRecommendCodeUserId(onSuperiorRecommendCodeUserId);
				loginUserMapper.updateByPrimaryKeySelective(loginUser);
				List<LoginUser> userIdLists = loginUserMapper.getChilUserIdByUserId(loginUser);
				for (LoginUser loginUserId : userIdLists) {
					loginUser.setParentUserId(null);
					loginUser.setUserId(loginUserId.getUserId());
					if(Integer.valueOf(userIdArray[i])==loginUserId.getUserId()){
						continue;
					}
					logger.info("==========变更的是客户,客户数据是============" + loginUser.toString());
					loginUserMapper.updateByPrimaryKeySelective(loginUser);
				}
			}else{
				logger.info("============变更合伙人关系的数据是========>" +loginUser.toString());
				loginUserMapper.updateByPrimaryKeySelective(loginUser);//变更合伙人关系
				List<LoginUser> customerUsers = loginUserMapper.getUserIdBySuperiorRecommendCodeUserId(loginUser);
				for (LoginUser customer : customerUsers) {//变更合伙人邀请的客户
					customer.setOperator(1);
					customer.setOperateTime(date);
					customer.setOnSuperiorRecommendCodeUserId(userId);
					customer.setTeamRecommendCodeUserId(teamRecommendCodeUserId);
					logger.info("==========变更的是合伙人下级客户为 =======>" + customer.toString());
					loginUserMapper.updateByPrimaryKeySelective(customer);
				}
			}
		}
	}

	/*@Override
	public Map<String, Object> uploadLoginUser(MultipartFile file, HttpServletRequest request) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		if(!file.isEmpty()){
			//文件不为空保存到webapp下
			String realPath=request.getSession().getServletContext().getRealPath("\\common");
			//修改文件名称
			int index = (file).getOriginalFilename().indexOf('.');
			String prefix = (file).getOriginalFilename().substring(index);
			//给上传的文件一个新的名称
			String fileName = DateTimeUtil.getNowTimeShortString()+ prefix ;
			// 设置存放文件的路径
			String path=realPath+"\\"+fileName;
			// 转存文件到指定的路径
			file.transferTo(new File(path));
			File file2 = new File(path);
			map = saveLoginUser(file2);// 插入用户数据
			String returnCode = (String) map.get("returnCode");
			if("0000".equals(returnCode)){
				doUploadUserInfo(file2);// 用户实名认证
			}
		}
		return map;
	}
	
	*//** 
	 * 导入用户
	 * @param file
	 * @return result
	 * @throws IOException
	 * @author haochunhe
	 * 2017-12-18 下午7:25:36
	 *//*
	public Map<String,Object> saveLoginUser(File file){
		Map<String,Object> resultParam = new HashMap<>();//返回结果
		List<Map<String,Object>> listParam = new ArrayList<Map<String,Object>>();
		//"1001" 团队管理者和一般合伙人失败 "1002"是顶级合伙人注册失败 "1003"是实名认证失败
		resultParam.put("returnCode", "0000");//成功
		try {
			List<List<Object>> list = ImportUtil.readExcel(file);
			for (int i = 1; i <list.size(); i++) {
				Map<String,Object> param = new HashMap<String, Object>();
				String body = null;//请求参数
				String username = StringUtil.trim((String)list.get(i).get(0));//注册手机号
				String inputInvitationCode = StringUtil.trim((String)list.get(i).get(1));//上级用户手机号(推荐码)
				String ChannelCode = StringUtil.trim((String)list.get(i).get(2)); //渠道码
				String realName = StringUtil.trim((String)list.get(i).get(3));//真实姓名
				String cardNo = StringUtil.trim((String)list.get(i).get(4));//身份证号
				String isTeam = StringUtil.trim((String)list.get(i).get(5));//团队管理者
				String isMPartner = StringUtil.trim((String)list.get(i).get(6));//是否是等级合伙人
				if (StringUtil.isNotBlank(isTeam) && StringUtil.isNotBlank(isMPartner)){
					param.put("username", username);//注册手机号
					param.put("recommendCode", inputInvitationCode);//汇中网输入推荐码
					param.put("ChannelCode", ChannelCode);//渠道号
					param.put("realName", realName);//真是姓名
					param.put("cardCode", cardNo);//身份证号
					param.put("registTime", DateUtil.formatDateTime2());//注册时间
					listParam.add(param);
					body = JSONUtils.toJSONString(param);
					logger.info("=====>用户注册请求参数是====" + body);
					Map<String,Object> oldUser = loginUserMapper.getUserByPartnerRecommendCodeOrUsername(param);
					if(oldUser ==null && isTeam.equals(isMPartner)){
						//处理是团队管理者或者一般合伙人的情况
						ReturnMsgData resParam = partnerService.SaveRegister(body);
						if(!"0000".equals(resParam.getReturnCode())){
							resultParam.put("returnCode", "1001");
							return resultParam;
						}
					}
					if(oldUser ==null && "否".equals(isTeam) && "是".equals(isMPartner)){
						//处理是顶级合伙人的情况
						int Mpartner = this.insertLoginUserPartner(param);
						if(Mpartner < 0){
							resultParam.put("returnCode", "1002");
							return resultParam;
						}
					}
					//TimeUnit.SECONDS.sleep(1);//秒
					TimeUnit.MILLISECONDS.sleep(100);//毫秒
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			resultParam.put("returnCode", "1001");
			return resultParam;
		}
		return resultParam;
	}
	*//**
	 * 处理顶级合伙人情况
	 * @param param
	 * @return
	 *//*
	public int insertLoginUserPartner(Map<String,Object> param){
		LoginUser loginUser = new LoginUser();
		Date date = new Date();
		String username = null;
		String ChannelCode = null;
		String recommendCode = null;
		if(param != null){
			username = (String) param.get("username");
			ChannelCode = (String) param.get("ChannelCode");
			ChannelCode = (String) param.get("ChannelCode");
			if (StringUtil.isNotBlank(username) && StringUtil.isNotBlank(ChannelCode) && "301".equals(ChannelCode)){
				loginUser.setUsername(username);
				loginUser.setRegistTime(date);
				loginUser.setPartnerTime(date);
				loginUser.setCreateTime(date);
				loginUser.setCreator(1);
				loginUser.setRegistSource("01");//注册来源汇中网
				loginUser.setIsPlatformInvite("1");//是平台邀请
				loginUser.setUserRole("10");//用户角色是合伙人
				loginUser.setUserType("10");//身份为一般和合伙人
				loginUser.setUserStatus("1");//用户状态为启用
				loginUser.setInputInvitationCode(recommendCode);//会中网输入邀请码
				loginUser.setPartnerRecommendCode(TradeWaterNumUtil.genTradeWaterNum(UserTypeEnum.partner));
				loginUser.setCustomerRecommendCode(TradeWaterNumUtil.genTradeWaterNum(UserTypeEnum.customer));
			}
		}
		logger.info("=====>顶级合伙人用户注册请求参数是====" + loginUser.toString());
		int result = loginUserMapper.insertSelective(loginUser);
		if(result >0){
			String message = "您已成功注册合伙人平台,您的下级合伙人推荐码为"+loginUser.getPartnerRecommendCode()+",客户推荐码为"+loginUser.getCustomerRecommendCode();
			NewSendMessageUtil.sendSmsProduct(username,message);
		}
		return result;
	}
	
	public void doUploadUserInfo(File file) throws Exception{
		List<Map<String,Object>> listParam = new ArrayList<>();
		List<List<Object>> list = ImportUtil.readExcel(file);
		for (int i = 1; i <list.size(); i++) {
			Map<String,Object> param = new HashMap<String, Object>();
			String username = StringUtil.trim((String)list.get(i).get(0));//注册手机号
			String realName = StringUtil.trim((String)list.get(i).get(3));//真实姓名
			String cardNo = StringUtil.trim((String)list.get(i).get(4));//身份证号
			param.put("username", username);//注册手机号
			param.put("realName", realName);//真是姓名
			param.put("cardCode", cardNo);//身份证号
			Map<String,Object> oldUserInfo = userInfoMapper.getUserInfo(param);
			if(oldUserInfo ==null){
				listParam.add(param);
			}
		}
		JSONArray body = JSONArray.fromObject(listParam);
		logger.info("=====>用户实名认证请求参数是====" + body.toString());
		if(!body.isEmpty()){
			ReturnMsgData resRealName = partnerOrderService.updateUserRealInfo(body.toString());
			if(!"0000".equals(resRealName.getReturnCode())){
				new Exception();
			}
		}
	}
*/
}
