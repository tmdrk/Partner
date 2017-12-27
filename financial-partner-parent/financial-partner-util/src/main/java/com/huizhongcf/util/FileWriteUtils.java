package com.huizhongcf.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

/**
 * @author xxc
 * 【
 *   将字符串写入文件中
 *  】
 * 
 * */
public class FileWriteUtils {
	
	//换行标识，每个操作系统不一样
	private static final String LINE_SEPARATOR = System.getProperty("line.separator"); 
	
	/**
	 *将批量的字符串写入文件中
	 *@ userpath 指定的文件目录
	 *@ filename 写入的文件名 
	 *@ type lend ,repay
	 *@return String 文件返回的路径
	 * */
	public static String writeFile(String info,String userpath,String type){
		String returncontent = null;
		if(StringUtils.isEmpty(info)){
			throw new RuntimeException("文件内容不能为空");
		}
		if(StringUtils.isEmpty(userpath)){
			throw new RuntimeException("文件路径不能为空");
		}
		
		String datenow = DateTimeUtil.getNowDateShortString();
		File file = new File( userpath + "/" + datenow);
		if(file.exists() == false){										// --验证文件目录是否存在
			file.mkdir();
		}
		
		/****************将文件写入到指定的目录下begin********************/
		BufferedWriter bw = null;
		FileOutputStream writerStream = null;
		String filepath = "";						                  //-- 文件路径
		String filename = null;						                  //-- 文件路径
		try {
			if(type.equals("lend")){
				filename = "lend_" + Math.abs(UUID.randomUUID().hashCode()) + ".txt";
			}else if(type.equals("repay")){
				filename = "repay_" + Math.abs(UUID.randomUUID().hashCode()) + ".txt";
			}
		    filepath = userpath + "/" + datenow + "/" + filename;
			writerStream = new FileOutputStream(userpath + "/"+datenow + "/" + filename); 
			bw = new BufferedWriter(new OutputStreamWriter(writerStream,"UTF-8"));
			byte[] bytestr = Des3.encryptMode(Des3.keycode, info.getBytes());
			bw.write(new String(bytestr));
			bw.flush();
		} catch (IOException e) {
			returncontent="";
			e.printStackTrace();
		}finally{
				try {
					if(writerStream!=null){
						writerStream.close();
					} 
					if(bw!=null){
						bw.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		/****************将文件写入到指定的目录下end*********************/
		//filepath = filemd5(filepath);
		if(StringUtils.isNotEmpty(filepath)){
			returncontent="{\'filepath\':\'" + filepath + "\',\'filename\':\'"+filename+"\'}";
		}
		return returncontent;		
	}
	
	/**
	 * 
	 * Description: 将字符串写入文件
	 *
	 * @param info 文件内容
	 * @param type 类型
	 * @return void
	 * @throws 
	 * @Author yaodawei
	 * Create Date: 2014-6-23 上午10:41:24
	 */
	public static void writeFile(List<String> info,String type){
		String returncontent = null;
		String filePath = PropertyUtil.getInfo("attachmentPath");//文件路径
		File file = new File(filePath);
		if(file.exists() == false){										// --验证文件目录是否存在
			file.mkdir();
		}
		
		/****************将文件写入到指定的目录下begin********************/
		BufferedWriter bw = null;
		FileOutputStream writerStream = null;
		String filepath = "";						                  //-- 文件路径
		String filename = null;						                  //-- 文件名称
		try {
			filename = type + ".txt";
			writerStream = new FileOutputStream(filePath + "/" + filename); 
			bw = new BufferedWriter(new OutputStreamWriter(writerStream,"UTF-8"));
			for (int i = 0; i < info.size(); i++) {
				String str = (String)info.get(i);
				if(i == 0){
					bw.write(new String(str.getBytes()) + "：");
				}else if(i == info.size()-1){
					bw.write(new String(str.getBytes()));
				}else {
					bw.write(new String(str.getBytes()) + "，");
				}
			}
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
				try {
					if(writerStream!=null){
						writerStream.close();
					} 
					if(bw!=null){
						bw.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		/****************将文件写入到指定的目录下end*********************/
	}
	
	public static void main(String args[]){
		String info ="[{\'payment_day\':\'2中国中国\',\'money_num\':\'HKHBXZH-192\'}],[{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}],[{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}],[{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}],[{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}],[{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}],[{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}],[{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}],[{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}],[{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}],[{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}],[{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}],[{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}],[{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}],[{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}],[{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}],[{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}]，[{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}][{\'payment_day\':\'2013-09-12\',\'money_num\':\'HKHBXZH-192\'}]";
		System.out.println(FileWriteUtils.writeFile(info, "D://upload","repay"));
	}
}
