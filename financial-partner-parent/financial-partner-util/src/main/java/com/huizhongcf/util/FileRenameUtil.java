package com.huizhongcf.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 
 * 
 * 文件重命名工具类
 * 
 * #Date: 2011-8-17
 * 
 * #Title: FileRenameUtil.java
 * 
 * #Company: CreditEase
 * 
 * #Time: 上午10:29:53
 * 
 * #Author: yub
 * 
 * #Email:yb989@126.com
 */
public class FileRenameUtil {
	
	/**
	 * 
	 * Description: 
	 *
	 * @param fileName 文件名
	 * @return 新文件名
	 * @throws Exception 
	 * @Author yubin
	 * Create Date: 2012-9-24 下午06:20:23
	 */
	public static String rename(String fileName) throws Exception{
		String body = "";
		String ext = "";
		Date date = new Date();

		int pot = fileName.lastIndexOf(".");
		if (pot != -1) {
			body = date.getTime() + "";
			ext = fileName.substring(pot);
		} else {
			body = (new Date()).getTime() + "";
			ext = "";
		}
		String newName = body + ext;
		// file=new File(file.getParent(),newName);
		return newName;
	}
	
	/**
	 * 生成新的文件名，格式：yyyyMMddHHmmssSSS
	 * @param fileName 原文件名
	 * @return String
	 */
	public static String getNewFileName(String fileName) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String newName = sdf.format(Calendar.getInstance().getTime());
		String extendName = "";
		if (fileName.lastIndexOf(".") != -1){
			extendName = fileName.substring(fileName.lastIndexOf("."));
		}
		return newName + extendName;
	}
}