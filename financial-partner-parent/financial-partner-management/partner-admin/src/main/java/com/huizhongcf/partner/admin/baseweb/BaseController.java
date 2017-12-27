/* 
 * Copyright (C) 2006-2014 亿谱汇投资管理（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: BaseController.java 
 *
 * Created: [2014-11-21 下午01:54:57] by ydw 
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
 * ProjectName: sping-mvc 
 * 
 * Description: 
 * 
 * ==========================================================*/

package com.huizhongcf.partner.admin.baseweb;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.huizhongcf.partner.admin.constant.Constants;
import com.huizhongcf.partner.model.Employee;
import com.huizhongcf.util.PropertyUtil;
import com.huizhongcf.util.StringUtil;

/** 
 *
 * Description: Controller的父类，用于封装一些公共的逻辑
 *
 * @author ydw
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014-11-21    ydw       1.0        1.0 Version 
 * </pre>
 */

public class BaseController {
	
	protected final Log logger = LogFactory.getLog(getClass());

	/**
	 * 
	 * Description: 
	 * 	注册一个自定义的处理类
	 * 	使用DateEditor来处理Date类型的日期转换
	 * 	即将Spring默认处理日期的类型换成DateEditor
	 * @param binder
	 * @return void
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-11-21 下午01:56:06
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) throws Exception {
		// 对于需要转换为Date类型的属性，使用DateEditor进行处理
		binder.registerCustomEditor(Date.class, new DateEditor());
	}

	/**
	 * 
	 * Description: 提交表单、操作处理后显示提示信息
	 *
	 * @Author yubin
	 * @Create Date: 2013-5-7 上午10:22:06
	 */
	protected void showMessageAlert(String refreshTag,String messageCode,Model model){
		//refreshTag代表是不是右键刷新标志,当是右键刷新时，提示信息不能显示
		if(StringUtil.isBlank(refreshTag)){
			if (!StringUtil.isBlank(messageCode)) {
				// 读取提示信息
				model.addAttribute("messageCode", PropertyUtil.getMessageCodeInfo(messageCode));
			}
		}else {
			messageCode = "";//当是右键刷新时，提交标志置空
		}
	}
	
	/**
	 * 
	 * Description: 返回后台管理系统登录用户ENTITY
	 *
	 * @param 
	 * @return Employee
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-11-25 下午02:56:08
	 */
	protected Employee getSystemCurrentUser(HttpSession session){
		Employee employee = (Employee) session.getAttribute(Constants.SYSTEM_USER);
		return employee;
	}
	
	/**
	 * 
	 * Description: 从容器中获得附件的上传路径
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-11-25 下午03:04:58
	 */
	public String getAttachmentPath (HttpSession session) {
		return (String)session.getServletContext().getAttribute("attachmentPath");
	}
}
