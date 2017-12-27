/* 
 * Copyright (C) 2006-2017 普惠融通科技（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: ProductConfigController.java 
 *
 * Created: [2017年12月7日 上午11:09:38] by cuimanman 
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


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huizhongcf.partner.admin.baseweb.BaseController;
import com.huizhongcf.partner.admin.baseweb.DataMsg;
import com.huizhongcf.partner.model.ProductConfig;
import com.huizhongcf.partner.service.BonusConfigService;
import com.huizhongcf.partner.service.ProductConfigService;
import com.huizhongcf.util.PageModel;
import com.huizhongcf.util.StringUtil;

/**
 *<dl>
 *<dt>类名：ProductConfigController.java</dt>
 *<dd>描述: 奖金配置管理</dd> 
 *<dd>创建时间： 2017年12月7日 上午11:09:38</dd>
 *<dd>创建人： cuimanman</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年12月7日 上午11:09:38    cuimanman       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Controller
@RequestMapping("/productRateConfig")
public class ProductConfigController extends BaseController{
	
	@Autowired
	private ProductConfigService productConfigService;
	
	/**
	 * 
	 * Description: 跳转到奖金配置管理页面
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author cuimanman
	 * Create Date: 2017年12月7日 下午3:08:53
	 */
	@RequestMapping("toProductRateConfigList")
	public String toProductRateConfigList(String refreshTag,String messageCode,Model model) {
		showMessageAlert(refreshTag,messageCode,model);
		return "app/biz/productconfig/product_rate_config_list";
	}
	
	/**
	 * 
	 * Description: 奖金配置管理列表展示
	 *
	 * @param 
	 * @return DataMsg
	 * @throws 
	 * @Author cuimanman
	 * Create Date: 2017年12月7日 下午5:03:49
	 */
	@ResponseBody
	@RequestMapping(value="/productRateConfigList")
	public DataMsg productRateConfigList(HttpServletRequest request,DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("page")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			String productTerm = StringUtil.trim(request.getParameter("productTerm"));
			if(StringUtil.isNotBlank(productTerm)) {
				paramsCondition.put("productTerm", productTerm);
			}
			PageModel pageModel = productConfigService.findAllByPage(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}
	
	/**
	 * 
	 * Description: 跳转到新增奖金配置页面
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author cuimanman
	 * Create Date: 2017年12月7日 上午11:21:27
	 */
	@RequestMapping(value="/toProductRateConfig")
	public String toProductRateConfig() {
		return "app/biz/productconfig/product_rate_config_add";
	}
	
	/**
	 * 
	 * Description: 新增奖金配置
	 *
	 * @param 
	 * @return DataMsg
	 * @throws 
	 * @Author cuimanman
	 * Create Date: 2017年12月7日 下午4:59:24
	 */
	@ResponseBody
	@RequestMapping(value="/addProductRateConfig")
	public DataMsg addProductRateConfig(@ModelAttribute ProductConfig productConfig,DataMsg dataMsg,HttpSession session){
		try {
			productConfig.setOperateTime(new Date());
			productConfig.setOperator(getSystemCurrentUser(session).getEmployeeId());
			productConfigService.insertSelective(productConfig);
			dataMsg.setMessageCode("0001");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			dataMsg.setMessageCode("0002");
		}
		return dataMsg;
	}
	
	
}
