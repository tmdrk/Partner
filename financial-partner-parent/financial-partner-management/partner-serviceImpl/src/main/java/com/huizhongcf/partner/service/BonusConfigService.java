/* 
 * Copyright (C) 2006-2017 普惠融通科技（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: BonusConfigService.java 
 *
 * Created: [2017年12月8日 下午5:14:19] by cuimanman 
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

package com.huizhongcf.partner.service;

import java.util.List;
import java.util.Map;

import com.huizhongcf.partner.model.BonusConfig;

/**
 *<dl>
 *<dt>类名：BonusConfigService.java</dt>
 *<dd>描述: ~节点业务逻辑实现</dd> 
 *<dd>创建时间： 2017年12月8日 下午5:14:19</dd>
 *<dd>创建人： cuimanman</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年12月8日 下午5:14:19    cuimanman       1.0        1.0 Version 
 * </pre>
 *</dl>
 */

public interface BonusConfigService {


	List<Map<String, Object>> selectBonusConfig();

	
	/**
	 * 
	 * Description: 獎金係數配置
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 * @Author cuimanman
	 * Create Date: 2017年12月8日 下午6:09:37
	 */


}
