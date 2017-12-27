package com.hui.zhong.cf.service.partner.service;

import com.alibaba.dubbo.common.json.ParseException;
import com.hui.zhong.cf.service.partner.model.Partner;
import com.huizhongcf.util.ReturnMsgData;

public interface MobileService {
	
	public boolean checkMobileIsExist(String mobile);
	
	public ReturnMsgData doRegister(Partner partner) throws ParseException;
}
