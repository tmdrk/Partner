package com.hui.zhong.cf.mobile.utils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties; 

public class PropUtils {
	private static Properties properties =  init();
	private static Properties init() {
		Properties prop = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream in = PropUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        // 使用properties对象加载输入流
        try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
        return prop;
	}
	public static String get(String key) {
		return properties.getProperty(key, "");
	}
}