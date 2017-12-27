package com.huizhongcf.util;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;

public class NewSendMessageUtil {		
	 
	public static void main(String[] args) {
		
		Map<String, String> map = new HashMap<>();
		//String systemSourceId = "FLAGSHIP";
		String telephone = "18001279094";
		String message = "你好，我是合伙人,请勿告诉他人。";
		//String key = "278e60fc68fd967b106e7fd9f081ba8f";
		//String sendmessage_path = "http://hzdispatch.p2phz.com:8080/app/dispatch/sendBill.do";
		//map.put("systemSourceId", systemSourceId);
		map.put("telephone", telephone);
		map.put("message", message);
		//map.put("key", key);
		//map.put("sendmessage_path", sendmessage_path);
		boolean sendSmsProduct = new NewSendMessageUtil().sendSmsProduct(telephone,message);
		System.out.println(sendSmsProduct);
	}
	
	public static boolean sendSmsProduct(String telephone,String message){
		 String bizId=new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date())+new Random().nextInt(10); 
				String systemSourceId = "FLAGSHIP";
				String key = "278e60fc68fd967b106e7fd9f081ba8f";
				String sendmessage_path = "http://hzdispatch.p2phz.com:8080/app/dispatch/sendBill.do";
				try {
					//短信接口传递参数
					Map<String, Object> dataMap = new HashMap<String, Object>(); 
					dataMap.put("bizId", bizId);
					dataMap.put("sendType", "1");
					dataMap.put("systemSourceId", systemSourceId);
					
					List<Map<String, Object>> sendBillList = new ArrayList<Map<String, Object>>();
					Map<String, Object> dm = new HashMap<String, Object>();
					Map<String, Object> cont = new HashMap<String, Object>();
					cont.put("content", message);
					String jsonString = JSON.toJSONString(cont);
					//System.out.println(jsonString);
					dm.put("bizId", new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date())+new Random().nextInt(10));
					dm.put("addr", telephone);
					dm.put("content", jsonString);
					sendBillList.add(dm);
					
					dataMap.put("sendBillList", sendBillList);
					// 生成签名信息
					String signature = Md5Util2.getMD5String(StringUtils.join(new String[] { systemSourceId, bizId }, ","), key);
					dataMap.put("signature", signature);
					// 这里将map用json工具转换为json串
					String str = JSON.toJSONString(dataMap);
					//System.out.println("加密前,str=" + str);
					// 这一步对json串进行加密，便于传输
					
					//System.err.println(str);
					str = AESUtil.enCrypt(str, key);
				 
					String result = HttpRequestUtil.sendPost(sendmessage_path, "billParms="+str); 
					//System.out.println("接口返回信息:" + result);
					//System.out.println("接口调用完毕");
				}catch (Exception e) {
					e.printStackTrace(); 
					return false;
				}
				return true;
	}
}
