/* 
 * Copyright (C) 2006-2017 普惠融通科技（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: ProductConfigService.java 
 *
 * Created: [2017年12月7日 下午12:01:14] by cuimanman 
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

import java.util.Map;

import com.huizhongcf.partner.model.ProductConfig;
import com.huizhongcf.util.PageModel;

/**
 *<dl>
 *<dt>类名：ProductConfigService.java</dt>
 *<dd>描述: 奖励配置管理</dd> 
 *<dd>创建时间： 2017年12月7日 下午12:01:14</dd>
 *<dd>创建人： cuimanman</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年12月7日 下午12:01:14    cuimanman       1.0        1.0 Version 
 * </pre>
 *</dl>
 */

public interface ProductConfigService {
	
	/**
	 * 
	 * Description: 奖金配置管理列表展示
	 *
	 * @param 
	 * @return PageModel
	 * @throws 
	 * @Author cuimanman
	 * Create Date: 2017年12月7日 下午5:03:28
	 */
	public PageModel findAllByPage(Map<String, Object> paramsCondition);
	
	/**
	 * 
	 * Description: 新增奖金配置
	 *
	 * @param 
	 * @return void
	 * @throws 
	 * @Author cuimanman
	 * Create Date: 2017年12月7日 下午5:03:13
	 */
	public Integer insertSelective(ProductConfig productConfig);


}
