/* 
 * Copyright (C) 2006-2017 普惠融通科技（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: BonusConfigServiceImpl.java 
 *
 * Created: [2017年12月8日 下午5:14:38] by cuimanman 
 *
 * $Id$
 * 
 * $Revision$
 *
 * $Author$
 *
 * $Date$
 *
 * ============================================================ 
 * 
 * ProjectName: partner-serviceImpl 
 * 
 * Description: 
 * 
 * ==========================================================*/

package com.huizhongcf.partner.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huizhongcf.partner.dao.BonusConfigMapper;
import com.huizhongcf.partner.model.BonusConfig;
import com.huizhongcf.partner.service.BonusConfigService;

/**
 *<dl>
 *<dt>类名：BonusConfigServiceImpl.java</dt>
 *<dd>描述: ~节点业务逻辑实现</dd> 
 *<dd>创建时间： 2017年12月8日 下午5:14:38</dd>
 *<dd>创建人： cuimanman</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年12月8日 下午5:14:38    cuimanman       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Service("bonusConfigService")
public class BonusConfigServiceImpl implements BonusConfigService {
	@Autowired
	private BonusConfigMapper bonusConfigMapper;

	@Override
	public List<Map<String, Object>> selectBonusConfig() {
		
		return bonusConfigMapper.selectBonusConfig();
	}
}
