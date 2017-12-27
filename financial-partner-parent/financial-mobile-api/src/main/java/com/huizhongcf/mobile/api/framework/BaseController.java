package com.huizhongcf.mobile.api.framework;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.hui.zhong.cf.mobile.model.LoginUser;
import com.huizhongcf.mobile.api.utils.ConstantsUtils;
import com.huizhongcf.mobile.api.utils.JedisClientUtil;
import com.huizhongcf.util.MD5Util;

public class BaseController {
	public static Log log = LogFactory.getLog(BaseController.class);
	public static final String SESSION_MOBILE_API_KEY = ConstantsUtils.Session.SESSION_MOBILE_API_KEY;
	public static final int SESSION_TOKEN_EXPIRE = ConstantsUtils.Session.SESSION_TOKEN_EXPIRE;
	
	@Autowired
	public JedisClientUtil jedisClientUtil;
	
	/** 
	* @Description: 获取当前登录用户 
	* @param @return     
	* @return LoginUser    返回类型 
	* @author  shiyang
	* @date 2017年12月12日 下午7:51:25 
	*/
	public LoginUser getSession(){
		String jsonUser = jedisClientUtil.get(SESSION_MOBILE_API_KEY+getToken());
		if(StringUtils.isNotBlank(jsonUser)){
			return JSON.parseObject(jsonUser, LoginUser.class);
		}
		return null;
	}
	/** 
	* @Description: 初始化session
	* @param @param user
	* @param @param token     
	* @return void    返回类型 
	* @author  shiyang
	* @date 2017年12月12日 下午7:51:41 
	*/
	public String setSession(LoginUser user){
		 String token = MD5Util.md5(user.getUserId().toString()+System.currentTimeMillis());
		jedisClientUtil.set(SESSION_MOBILE_API_KEY+token, JSON.toJSONString(user), 60*30);
		return token;
	}
	
	/** 
	* @Description: 退出登录 删除session 
	* @param      
	* @return void    返回类型 
	* @author  shiyang
	* @date 2017年12月12日 下午7:52:04 
	*/
	public void removeSession(){
		jedisClientUtil.del(SESSION_MOBILE_API_KEY+getToken());
	}
	public  HttpServletRequest getRequest(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;  
	}
	public String getToken(){
		return getRequest().getParameter("token");
	}
	protected Map<String, Object> cookies2Map(HttpServletRequest request) {
		Map<String, Object> cookieMap = new HashMap<>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie c : cookies) {
				cookieMap.put(c.getName(), c.getValue());
			}
		}

		return cookieMap;
	}
	protected Map<String, Object> getParams() {
		HttpServletRequest request = getRequest();
		Map<String, Object> param = new HashMap<String, Object>();
		Enumeration e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			if (null != request.getParameter(name) && !"".equals(request.getParameter(name).trim())) {
				try {
					param.put(StringUtils.trim(name),request.getParameter(name).trim());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		return param;
	}

	 
	 
	/**
	 * 错误
	 * @param errorCode
	 * @param errorMsg
	 * @return
	 */
	public Object renderError(String errorCode,String errorMsg){
		ResultBean bean = new ResultBean();
		bean.setErrorMsg(errorMsg);
		bean.setErrorCode(errorCode);
		bean.setBody(new HashMap<>());
		return JSON.toJSON(bean);
	 }

	/**
	 * @description   成功响应
	 * @param body
	 * @return
	 * @author shiyang
	 * 2016年10月20日 下午2:45:21
	 */
	public Object renderSuccess(Object body){
		ResultBean bean = new ResultBean();
		bean.setErrorCode("200");
		bean.setErrorMsg("SUCCESS");
		if(body == null)
			body = new HashMap<String,String>();
		bean.setBody(body);
		Object json = JSON.toJSON(bean);
		log.info(json);
		return json;
	}
	
	 
 
	public Map<String, Object> getRequestParam() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Map<String, Object> result = new HashMap<String, Object>();
		Iterator<?> iter = request.getParameterMap().entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = entry.getKey().toString();
			String value = request.getParameter(key);
			try {
				value = new String(value.getBytes("utf-8"),request.getCharacterEncoding());
			} catch (UnsupportedEncodingException e) {
			}
			result.put(key, value);
		}
		return result;
	}
    /**
     * @description  获取客户端ip
     * @param request
     * @return
     * @author shiyang
     * 2016年10月26日 下午2:11:17
     */
    public static String getIpAddr(HttpServletRequest request) {
        if (request == null) {
            return "unknown host";
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    public boolean isH5(){
    	if("H5".equals(getHeader().get("os"))) {
    		return true;
    	}else {
    		return false;
    	}
    }
    public boolean isIOS(){
    	if("IOS".equals(getHeader().get("os"))) {
    		return true;
    	}else {
    		return false;
    	}
    }
    public String getVersion() {
    	return getHeader().get("version");
    }
    public boolean isAndroid(){
    	if("Android".equals(getHeader().get("os"))) {
    		return true;
    	}else {
    		return false;
    	}
    	
    }
    public Map<String,String> getHeader(){
    	Map<String,String> retMap = new HashMap<String,String>();
    	Enumeration headerNames = getRequest().getHeaderNames();
    	 while (headerNames.hasMoreElements()) {
             String key = (String) headerNames.nextElement();
             String value = getRequest().getHeader(key);
             retMap.put(key, value);
         }
    	return retMap;
    }
}
