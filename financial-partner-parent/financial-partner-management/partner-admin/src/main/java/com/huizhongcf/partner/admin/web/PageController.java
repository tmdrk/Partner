/* 
 * Copyright (C) 2006-2014 亿谱汇投资管理（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: PageController.java 
 *
 * Created: [2014-12-8 上午10:47:56] by ydw 
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

package com.huizhongcf.partner.admin.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huizhongcf.partner.admin.baseweb.BaseController;
import com.huizhongcf.partner.admin.baseweb.DataMsg;
import com.huizhongcf.partner.model.Page;
import com.huizhongcf.partner.service.PageService;
import com.huizhongcf.util.PageModel;
import com.huizhongcf.util.StringUtil;

/** 
 *
 * Description: 页面管理Controller
 *
 * @author ydw
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014-12-8    ydw       1.0        1.0 Version 
 * </pre>
 */
@Controller
@RequestMapping("/page")
public class PageController extends BaseController {
	
	@Autowired
	private PageService pageService;
	
	/**
	 * 
	 * Description: 跳转到页面管理列表页
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-8 上午10:51:25
	 */
	@RequestMapping(value="/toPageList")
	public String toPageList(String refreshTag,String messageCode,Model model){
		showMessageAlert(refreshTag,messageCode,model);
		return "app/page/page_list";
	}
	
	/**
	 * 
	 * Description: 页面分页
	 *
	 * @param 
	 * @return DataMsg
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-8 上午10:52:57
	 */
	@ResponseBody
	@RequestMapping(value="/pages")
	public DataMsg pageList(HttpServletRequest request,DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("page")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			String pageName = request.getParameter("pageName");//页面中文名
			if(StringUtil.isNotBlank(pageName)){
				paramsCondition.put("pageName", pageName.trim());
			}
			String pageMark = request.getParameter("pageMark");//页面唯一标识
			if(StringUtil.isNotBlank(pageMark)){
				paramsCondition.put("pageMark", pageMark.trim());
			}
			PageModel pageModel = pageService.findAllByPage(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}
	
	/**
	 * 
	 * Description: 跳转到添加页面 
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-8 下午01:51:55
	 */
	@RequestMapping(value="/toAddPage")
	public String toAddPage() {
		return "app/page/page_add";
	}
	
	/**
	 * 
	 * Description: 保存添加页面
	 *
	 * @param 
	 * @return DataMsg
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-8 下午02:09:54
	 */
	@ResponseBody
	@RequestMapping(value="/doAddPage")
	public DataMsg doAddPage(@ModelAttribute Page page,DataMsg dataMsg,HttpSession session){
		try {
			page.setCreator(getSystemCurrentUser(session).getEmployeeId());
			page.setCreateTime(new Date());
			pageService.addPage(page);
			dataMsg.setMessageCode("0001");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			dataMsg.setMessageCode("0002");
		}
		return dataMsg;
	}
	
	/**
	 * 
	 * Description: 跳转到修改页面
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-8 下午03:01:40
	 */
	@RequestMapping(value="/toUpdatePage/{pageId}")
	public String toUpdatePage(@PathVariable Integer pageId,Model model){
		try {
			Page page = pageService.getPageByPageId(pageId);
			model.addAttribute("page", page);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return "common/exception";
		}
		return "app/page/page_edit";
	}

	/**
	 * 
	 * Description: 修改
	 *
	 * @param 
	 * @return DataMsg
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-12-8 下午03:23:14
	 */
	@ResponseBody
	@RequestMapping(value="/doUpdatePage")
	public DataMsg doUpdatePage(@ModelAttribute Page page,DataMsg dataMsg){
		try {
			pageService.updatePage(page);
			dataMsg.setMessageCode("0003");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			dataMsg.setMessageCode("0004");
		}
		return dataMsg;
	}
}
