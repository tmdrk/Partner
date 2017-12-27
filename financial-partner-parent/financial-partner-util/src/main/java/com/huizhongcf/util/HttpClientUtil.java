package com.huizhongcf.util;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;

/**
 * @author xiexincheng
 * http post 请求
 * 
 ***/
public class HttpClientUtil {

	private static HttpClient client=null;
	static{
		
		 client = new HttpClient();
	}
	
	/**
	 *  
	 *   设置HttpClient 超时时间
	 * 
	 * **/
	private static HttpClient getHttpClient(){
		
	
		 client.getHttpConnectionManager().getParams()
		    .setConnectionTimeout(3000);
	     client.getHttpConnectionManager().getParams().setSoTimeout(5000);
	     client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
			
		return client;
		
	}
	
	/**
	 *  根据URL 传回对应的参数
	 * **/
	public static String doPost(String Url){
		String resultcontent="";
		if(StringUtils.isNotEmpty(Url)){
			
			 PostMethod post = new PostMethod(Url);
			 try {
				 int result=client.executeMethod(post);
				 if(result==200){						//-- 返回调用的数据
					 
					 resultcontent= post.getResponseBodyAsString();
					
				 }
				
			} catch (HttpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		return resultcontent;
	}
	
	public static void main(String args[]){
		
		System.out.println(HttpClientUtil.doPost("http://210.73.90.18/merchant/order/order_ack_list.jsp?v_mid=6927&v_ymd=20121011&v_pstatus=1"));
	}

}
