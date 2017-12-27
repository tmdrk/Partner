package com.huizhongcf.partner.admin.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class PropertyUtil {
	
	public static Map<String,String> dataMap=new HashMap<String,String>();

	public static void init(String confFilePath){
		Properties prop = new Properties();  
		InputStream in = null;
		try{
			//读取属性文件a.properties
			in = new BufferedInputStream (new FileInputStream(confFilePath));
			prop.load(in);
			Iterator<String> it=prop.stringPropertyNames().iterator();
			while(it.hasNext()){
				String key=it.next();
				dataMap.put(key, prop.getProperty(key));
			}
			in.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
