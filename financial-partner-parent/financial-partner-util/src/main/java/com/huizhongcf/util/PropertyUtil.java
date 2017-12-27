package com.huizhongcf.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * 
 * Description:读取本地配置文件
 * 
 * @author yubin
 * @version 1.0
 * 
 *          <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2012-2-13    Administrator       1.0        1.0 Version
 * </pre>
 */
public class PropertyUtil {
	private static String path = "/property/config.properties";// UC、CAS、WEBSERVICE配置
	private static String pathMessageCode = "/property/message_code.properties";// 提示信息代码
	private static Properties p = new Properties();// 一般参数使用
	private static Properties mail = new Properties();// 一般参数使用
	private static Properties messageCode = new Properties();// 一般参数使用
	InputStream inLog = null;
	InputStream in = null;
	static {
		InputStream in = PropertyUtil.class.getResourceAsStream(path);
		InputStream inMessageCode = PropertyUtil.class.getResourceAsStream(pathMessageCode);
		try {
			p.load(in);
			messageCode.load(inMessageCode);
		} catch (IOException e) {
			throw new ExceptionInInitializerError("不能正确读取资源文件");
		} finally{
			if (in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inMessageCode != null){
				try {
					inMessageCode.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	/**
	 * 从properties文件中根据key取出value值
	 * 
	 * @param key 
	 * @return String
	 */
	public static String getInfo(String key) {
		return p.getProperty(key);
	}
	
	/**
	 * 从properties文件中根据key取出value值
	 * 
	 * @param key 
	 * @return String
	 */
	public static String getMailInfo(String key) {
		return mail.getProperty(key);
	}
	
	/**
	 * 从properties文件中根据key取出value值
	 * 
	 * @param key 
	 * @return String
	 */
	public static String getMessageCodeInfo(String key){
		return messageCode.getProperty(key);
	}
	
	public static void main(String args[]){
		System.out.println(PropertyUtil.getInfo("Sync_account"));
	}
}
