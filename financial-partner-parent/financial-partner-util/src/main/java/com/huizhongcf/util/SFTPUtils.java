package com.huizhongcf.util;

/*  
 *  This is a part of the Mobile Agent Server3(MAS3)
 *  Copyright (C) 2010-2012 uskytec.com Corporation 
 *  All rights reserved.
 *
 *  Licensed under the uskytec.com private License.
 *  
 *
 */


import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * SFTP 文件上传下载工具
 * 
 * @author syf
 * 
 */
public class SFTPUtils {
	private static Logger logger = Logger.getLogger(SFTPUtils.class);

	private  ChannelSftp sftp = null;

	private String localPath = "";

	public SFTPUtils(String localPath) {
		this.localPath = localPath;
	}

	/**
	 * connect server via sftp
	 */
	public  ChannelSftp getChannel(String userName, String password, String ftpHost, String port,
			Integer timeout) throws JSchException {

		int ftpPort = 22;
		if (port != null && !port.equals("")) {
			ftpPort = Integer.valueOf(port);
		}

		JSch jsch = new JSch(); // 创建JSch对象
		Session session = jsch.getSession(userName, ftpHost, ftpPort); // 根据用户名，主机ip，端口获取一个Session对象
		if (password != null) {
			session.setPassword(password); // 设置密码
		}
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config); // 为Session对象设置properties
		if (timeout != null) {
			session.setTimeout(timeout); // 设置timeout时间
		}
		session.connect(); // 通过Session建立链接
		logger.debug("Session connected.");

		logger.debug("Opening Channel.");
		Channel channel = session.openChannel("sftp"); // 打开SFTP通道
		channel.connect(); // 建立SFTP通道的连接
		logger.debug("Connected successfully to ftpHost = " + ftpHost + ",as ftpUserName = " + userName
				+ ", returning: " + channel);
		sftp = (ChannelSftp) channel;
		return sftp;
	}

	/**
	 * Disconnect with server
	 */
	public void disconnect() {
		if (this.sftp != null) {
			if (this.sftp.isConnected()) {
				this.sftp.disconnect();
			} else if (this.sftp.isClosed()) {
				System.out.println("sftp is closed already");
			}
		}

	}

	/**
	 * 下载文件
	 * 
	 * @param directory
	 * @param downloadFile
	 * @param saveFile
	 */
	public void download(String directory, String downloadFile, String saveFile) {
		try {
			sftp.cd(directory);
			File file = new File(saveFile);
			sftp.get(downloadFile, new FileOutputStream(file));
		} catch (Exception e) {
			logger.error("SFTP下载文件失败，异常信息为" + e.getMessage());
			e.printStackTrace();
		}
	}

	public String put(String localPath, String src, String dst, int mode) {
		String result="success";
		String datenow=DateTimeUtil.getNowDateShortString();
		try {
			sftp.cd(localPath);
			try{
				
				sftp.ls(datenow) ;										// --判断
			}catch (SftpException e1){
				
				sftp.mkdir(datenow);									// --创建FTP 目录
			}
			sftp.cd(datenow);
			sftp.put(src, dst, mode);
		} catch (Exception e) {
			result="false";
			logger.error("SFTP上传文件失败，异常信息为" + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	public void put(){
		try {
			sftp.put("/opt/sftp/dafei/t1.txt", "D://sftp001.txt",ChannelSftp.RESUME);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	/**
	 * @return the localPath
	 */
	public String getLocalPath() {
		return localPath;
	}

	/**
	 * @param localPath
	 *            the localPath to set
	 */
	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}
	
	/**
	 * Ftp 文件上传
	 * 
	 ***/
	public static String sftpFileup(Map<String,Object> jsonMap){
		
		String returnstr="true";
		 SFTPUtils sftpUtils= new SFTPUtils(PropertyUtil.getInfo("ftpdir"));
		  try {
			  sftpUtils.getChannel(PropertyUtil.getInfo("ftpuser"), PropertyUtil.getInfo("ftppassword"), PropertyUtil.getInfo("ftphost"), PropertyUtil.getInfo("ftpport"),3*60*1000);
			  String ss=sftpUtils.put("dafei",jsonMap.get("filepath").toString(),jsonMap.get("filename").toString(),ChannelSftp.RESUME);
		  } catch (JSchException e) {
			  returnstr="false";
			e.printStackTrace();
		 }finally{
			 
			 sftpUtils.disconnect();
		 }
		  
		  return returnstr;
	}
	
	
	
//	public static void main(String[] args){
//		SFTPUtils sftpUtils= new SFTPUtils("/opt/sftp/");
//		try {
//		sftpUtils.getChannel("dafei", "+\\Ygxyuv", "119.2.4.77", "60022",3*60*1000);
//			String ss=sftpUtils.put("dafei","D://upload/20130917/lend_2133012771.txt","abc.txt",ChannelSftp.RESUME);
//			System.out.println(ss);
//		} catch (JSchException e) {
//			e.printStackTrace();
//		} 
//	}
	
	
	public static void main(String[] args){
		SFTPUtils sftpUtils= new SFTPUtils("/opt/sftp/");
		try {
		sftpUtils.getChannel("dafei", "zZY&%mi2", "210.73.90.230", "60022",3*60*1000);
			String ss=sftpUtils.put("dafei","D://upload/20130917/lend_2133012771.txt","abc.txt",ChannelSftp.RESUME);
			System.out.println(ss);
		} catch (JSchException e) {
			e.printStackTrace();
		} 
	}
//	
//	public static void main(String[] args){
//		SFTPUtils sftpUtils= new SFTPUtils("/opt/sftp/");
//		try {
//			sftpUtils.getChannel("dafei", "zZY&%mi2", "210.73.90.230", "60022",3*60*1000);
//			String ss=sftpUtils.put("dafei","D://upload/20130828/repay_925826474.txt","111.txt",ChannelSftp.RESUME);
//			System.out.println(ss);
//		} catch (JSchException e) {
//			e.printStackTrace();
//		} 
//	}
	
	
}

