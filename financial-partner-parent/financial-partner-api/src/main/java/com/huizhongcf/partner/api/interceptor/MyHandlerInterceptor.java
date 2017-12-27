package com.huizhongcf.partner.api.interceptor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huizhongcf.util.CommonUtil;
import com.huizhongcf.util.DateUtil;
import com.huizhongcf.util.MD5Util;
import com.huizhongcf.util.PropertyUtil;
import com.huizhongcf.util.ReturnMsgData;
import com.huizhongcf.util.UUIDUtil;

public class MyHandlerInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(MyHandlerInterceptor.class);
	
	public static final int invalidTime = 10;//请求链接失效时间为10秒
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		MDC.put("threadId",UUIDUtil.getUUID());
		ServletOutputStream out = null;
		try {
		    out = response.getOutputStream();
			ReturnMsgData data = null;
			String timestamp = request.getHeader("timestamp");
			String sign = request.getHeader("sign");
			String requestUrl = request.getRequestURI();
			/**signIsOn：表示失效时间
			 * "true"：表示验证失效时间
			 * "false"：表示不验证失效时间
			 **/
//			String signIsOn = "true";
			String signIsOn= PropertyUtil.getInfo("signIsOn");//获取signIsOn
				String body = CommonUtil.getBody(request);
				if("true".equals(signIsOn)){
					if(checkTimeStamp(timestamp)){
						String mySign = getSign(body, timestamp);
						if(!mySign.equals(sign)){
							data = new ReturnMsgData("10000", "验签失败!");
						}
					}else{
						data = new ReturnMsgData("10000", "请求链接已过期!");
					}
				}
				CommonUtil.setBody(body);
			if(data != null){
				String responseStr = JSON.toJSONString(data);
				out.write(responseStr.getBytes("UTF-8"));
				out.flush();
				out.close();
				return false;
			}
		} catch (Exception e) {
			logger.error("", e);
			if(out != null){
				out.close();
			}
		}
		return true;
	}
	
	/**
	 * 
	 * 
	 * Description: 讲请求参数进行MD5加密
	 *
	 * @param 
	 * @return String
	 * @throws UnsupportedEncodingException 
	 * @throws 
	 * @Author bao
	 * Create Date: 2017年8月18日 下午3:21:46
	 */
	public String getSign(String body,String timestamp) throws UnsupportedEncodingException{
		
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		JSONArray array = JSON.parseArray(body);
		for(int i = 0;i < array.size();i++){
			JSONObject json = array.getJSONObject(i);
			Map<String,String> param = new HashMap<String,String>();
			Set<Entry<String, Object>> entrySet = json.entrySet();
			for(Entry<String, Object> entry : entrySet) {
				param.put(entry.getKey(), (String) entry.getValue());
		     }
			list.add(param);
		}
		String signKey="368a72dd1e9443728d186bfcc046b402";
		//拼接加密字符串
		String str = MD5Util.createLinkString(list)+"&key="+signKey+"&timestamp="+timestamp;
		String mysign = DigestUtils.md5Hex(str.getBytes("UTF-8"));
		//字母全部大写
	    return mysign.toUpperCase();	
	}
	
	
	/**
	 * 
	 * Description: 判断请求是否失效
	 *
	 * @param 
	 * @return boolean
	 * @throws 
	 * @Author bao
	 * Create Date: 2017年8月18日 下午2:40:57
	 */
	public boolean checkTimeStamp(String timestamp){
		Date time_stamp = DateUtil.parseDate(timestamp,"yyyyMMddHHmmss");
		//连接超出时间(毫秒)
		long  outTime = new Date().getTime() - time_stamp.getTime();
		if(outTime/1000 > invalidTime){//连接超时
			return false;
		}
		return true;
	}
	
}
