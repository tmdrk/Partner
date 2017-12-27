package com.huizhongcf.partner.admin.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huizhongcf.partner.dao.SystemDictMapper;
import com.huizhongcf.partner.model.SystemDict;
import com.huizhongcf.util.Environment;

/**
 * 
 * 
 * 系统字典，项目启动时加载此servlet初始化字典 原来字典的方式不能新增类别，现在改为可以新增字典类别
 * 
 * #Date: 2010-12-1
 * 
 * #Title: SystemDataUtil.java
 * 
 * #Company: CreditEase
 * 
 * #Time: 上午10:22:44
 * 
 * #Author: yub
 * 
 * #Email:yb989@126.com
 */
public class SystemDataUtil implements Servlet {
	private static final Logger logger = LoggerFactory.getLogger(SystemDataUtil.class);
	
	private static SystemDataUtil instance = null;
	private static LinkedHashMap<String, LinkedHashMap<String, String>> codeMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();// 最外层的Map容器，其中包含各个类别的map
	private static LinkedHashMap<String, String> tmpMap = new LinkedHashMap<String, String>();
	private static List<SystemDict> list = null;// SystemDict表中的所有数据
	private ServletConfig config;
	private ServletContext context;

	/**
	 * 初始化数据字典
	 */
	private static void initSystemDataMap() {
		logger.info("【系统提示】字典初始化开始");
		String tmpSystemType = "";
		for (SystemDict system : list) {
			if (!system.getDictType().equals(tmpSystemType)) {
				tmpSystemType = system.getDictType();
				tmpMap = new LinkedHashMap<String, String>();
				codeMap.put(tmpSystemType, tmpMap);
			}
			tmpMap.put(system.getDictCode(), system.getDictName());
		}
		logger.info("【系统提示】字典初始化结束");
	}
	

	/**
	 * 获得Application范围的codeMap
	 * 
	 * @return
	 */
	public static LinkedHashMap<String, LinkedHashMap<String, String>> getApplicationCodeMap() {
		return codeMap;
	}
	
	public static Logger getLogger() {
		return logger;
	}

	public static SystemDataUtil getInstance() {
		if (instance == null) {
			instance = new SystemDataUtil();
		}
		return instance;
	}

	/**
	 * 刷新字典codeMap
	 * 
	 * @param obj
	 *            字典对象
	 * @param operate
	 *            操作 add:追加/更新, remove:移除
	 */
	@SuppressWarnings({ "unused" })
	public void refresh(SystemDict obj, String operate) {
		if (obj == null)
			return;
		if (codeMap.isEmpty())
			return;
		LinkedHashMap<String, String> typeMap = null;
		if (codeMap.containsKey(obj.getDictType())) {
			typeMap = codeMap.get(obj.getDictType());
			if ("add".equals(operate)) {
				Map<String, Object> conditions = new HashMap<String, Object>();
				conditions.put("systemType", obj.getDictType());
				List<SystemDict> list = null;
				try {
					if (list != null && list.size() > 0) {
						typeMap.clear();
						for (SystemDict t : list) {
							typeMap.put(t.getDictCode(), t.getDictName());
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if ("remove".equals(operate)) {
				typeMap.remove(obj.getDictCode());
				logger.info(obj.getDictType() + "  移除字典    " + "(key:" + obj.getDictCode() + ",value:" + obj.getDictName() + ")");
			}
		} else {
			typeMap = new LinkedHashMap<String, String>();
			typeMap.put(obj.getDictCode(), obj.getDictName());
			logger.info("原字典中没有此类别,新增字典类别");
		}
		codeMap.put(obj.getDictType(), typeMap);
		context.setAttribute("CodeMap", codeMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Servlet#destroy()
	 */
	public void destroy() {
		logger.debug("-------------关闭容器-------------");
		clearMap();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Servlet#getServletConfig()
	 */
	public ServletConfig getServletConfig() {
		return this.config;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Servlet#getServletInfo()
	 */
	public String getServletInfo() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Servlet#init(javax.servlet.ServletConfig)
	 */
	public void init(ServletConfig arg0) throws ServletException {
		try {
			Environment e = Environment.getInstance();
			SqlSessionFactory factory = (SqlSessionFactory) e.getBean("sqlSessionFactory");
			factory.openSession().flushStatements();
			this.config = arg0;
			context = config.getServletContext();
			context.removeAttribute("CodeMap");
			clearMap();
			SystemDictMapper systemDictMapper = (SystemDictMapper)e.getBean("systemDictMapper");
			Map<String,Object> params = new HashMap<String,Object>();
			list = systemDictMapper.findAllRetEntityNoPage(params);
			initSystemDataMap();
			context.setAttribute("CodeMap", codeMap);
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Servlet#service(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse)
	 */
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		init(getServletConfig());
		response.getWriter().write("<script>alert('刷新成功!');</script>");
	}

	/**
	 * 清空map
	 */
	private void clearMap() {
		codeMap.clear();
		tmpMap.clear();
	}

}
