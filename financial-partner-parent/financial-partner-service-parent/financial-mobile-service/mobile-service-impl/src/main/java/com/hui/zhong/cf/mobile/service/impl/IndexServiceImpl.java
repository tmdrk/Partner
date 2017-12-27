package com.hui.zhong.cf.mobile.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hui.zhong.cf.mobile.dao.LoginUserMapper;
import com.hui.zhong.cf.mobile.service.IndexService;
import com.huizhongcf.util.DateUtil;
import com.huizhongcf.util.MoneyUtil;

/**
 * 首页服务实现类
 * @ClassName: IndexServiceImpl 
 * @author zhoujie
 * @date 2017年12月12日 下午7:56:01
 */
@Service("indexService")
public class IndexServiceImpl implements IndexService{
	private Logger logger = LoggerFactory.getLogger(IndexServiceImpl.class);
	@Autowired
	private LoginUserMapper loginUserMapper;
	
	@Override
	public Map<String, Object> queryData(Map<String, Object> queryMap) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		Integer userId = Integer.parseInt(queryMap.get("userId").toString());
		String userType = queryMap.get("userType").toString();
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("userId", userId);
		paraMap.put("userType", userType);
		try {
			paraMap.put("loanDateStart", DateUtil.getCurrentMonthFirstDayDate());
		} catch (Exception e) {
			logger.error("IndexServiceImpl-->queryData 获取当月第一天日期失败！"+e.toString());
		}
		paraMap.put("loanDateEnd", new Date());
		//本月出借总额(万元)
		Map<String, Object> resultMap = loginUserMapper.queryloanAmount(paraMap);
		retMap.put("monthLoanAmount", MoneyUtil.kiloFormatDiv(resultMap.get("loanAmount"),10000));
		//本月邀请合伙人(人)
		paraMap.put("queryUserRole", "1");//1：查询合伙人 2：查询客户
		resultMap = loginUserMapper.queryInviter(paraMap);
		retMap.put("monthInvitePartner", resultMap.get("inviteNumber"));
		//本月邀请客户(人)
		paraMap.put("queryUserRole", "2");//1：查询合伙人 2：查询客户
		resultMap = loginUserMapper.queryInviter(paraMap);
		retMap.put("monthInviteCustomer", resultMap.get("inviteNumber"));
		//累计出借总额(万元)
		paraMap.remove("loanDateStart");
		paraMap.remove("loanDateEnd");
		resultMap = loginUserMapper.queryloanAmount(paraMap);
		retMap.put("totalLoanAmount",  MoneyUtil.kiloFormatDiv(resultMap.get("loanAmount"),10000));
		//累计出借单数(单)
		resultMap = loginUserMapper.queryTotalLoanOrder(paraMap);
		retMap.put("totalLoanOrder", resultMap.get("totalLoanOrder"));
		//累计邀请合伙人(人)
		paraMap.put("queryUserRole", "1");//1：查询合伙人 2：查询客户
		resultMap = loginUserMapper.queryInviter(paraMap);
		retMap.put("totalInvitePartner", resultMap.get("inviteNumber"));
		//已出单合伙人(人)
		resultMap = loginUserMapper.queryLoanOrderCount(paraMap);
		retMap.put("partnerLoanOrder", resultMap.get("loanOrderCount"));
		//累计邀请客户(人)
		paraMap.put("queryUserRole", "2");//1：查询合伙人 2：查询客户
		resultMap = loginUserMapper.queryInviter(paraMap);
		retMap.put("totalInviteCustomer", resultMap.get("inviteNumber"));
		//已出单客户(人)
		resultMap = loginUserMapper.queryLoanOrderCount(paraMap);
		retMap.put("customerLoanOrder", resultMap.get("loanOrderCount"));
		return retMap;
	}

}
