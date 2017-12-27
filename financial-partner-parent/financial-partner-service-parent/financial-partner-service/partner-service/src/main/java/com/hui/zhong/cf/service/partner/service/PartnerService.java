package com.hui.zhong.cf.service.partner.service;

import java.util.List;

import com.alibaba.dubbo.common.json.ParseException;
import com.hui.zhong.cf.service.partner.model.Partner;
import com.huizhongcf.util.ReturnMsgData;

public interface PartnerService {
	
	public List<Partner> find(Partner partner);
	
	/**
	 * 
	 * Description: 汇中网向合伙人同步注册用户
	 *
	 * @param 
	 * @return ReturnMsgData
	 * @throws 
	 * @Author bao
	 * Create Date: 2017年12月11日 下午6:34:19
	 */
	public ReturnMsgData SaveRegister(String body) throws ParseException;
	
	
	 

}
