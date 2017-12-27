/* 
 * Copyright (C) 2006-2017 普惠融通科技（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: BonusConfigController.java 
 *
 * Created: [2017年12月8日 下午5:20:05] by cuimanman 
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
 * ProjectName: partner-admin 
 * 
 * Description: 
 * 
 * ==========================================================*/

package com.huizhongcf.partner.admin.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huizhongcf.partner.admin.baseweb.BaseController;
import com.huizhongcf.partner.model.BonusConfig;
import com.huizhongcf.partner.service.BonusConfigService;

/**
 *<dl>
 *<dt>类名：BonusConfigController.java</dt>
 *<dd>描述: ~节点业务逻辑实现</dd> 
 *<dd>创建时间： 2017年12月8日 下午5:20:05</dd>
 *<dd>创建人： cuimanman</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年12月8日 下午5:20:05    cuimanman       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Controller
@RequestMapping("/bonusConfig")
public class BonusConfigController extends BaseController{
	@Autowired
	private BonusConfigService bonusConfigService;
	
	/**
	 * 
	 * Description: 奖金系数配置
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author cuimanman
	 * Create Date: 2017年12月8日 下午6:10:25
	 */
	@RequestMapping("/selectBonusConfig")
	public String selectBonusConfig(String refreshTag,String messageCode,Model model) {
		try {
			showMessageAlert(refreshTag,messageCode,model);
			List<Map<String, Object>> bonusConfigs = bonusConfigService.selectBonusConfig();
			model.addAttribute("bonusConfigs",bonusConfigs);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return "common/exception";
		}
		return "app/biz/productconfig/product_rate_config_edit";
	}
	
}
