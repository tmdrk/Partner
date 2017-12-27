/* 
 * Copyright (C) 2006-2017 普惠融通科技（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: AttachmentController.java 
 *
 * Created: [2017年6月27日 下午2:49:14] by yaodawei
 *
 * $Id$
 * 
 * $Revision$
 *
 * $Author$
 *
 * $Date$
 *
 * ============================================================ 
 * 
 * ProjectName: partner-admin 
 * 
 * Description: 
 * 
 * ==========================================================*/

package com.huizhongcf.partner.admin.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.huizhongcf.partner.admin.baseweb.BaseController;
import com.huizhongcf.partner.admin.util.FastDFSClient;
import com.huizhongcf.partner.model.Attachment;
import com.huizhongcf.partner.service.AttachmentService;
import com.huizhongcf.util.FileRenameUtil;
import com.huizhongcf.util.StringUtil;

/** 
 *
 * Description: 附件上传
 *
 * @author yaodawei
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年6月27日    yaodawei       1.0        1.0 Version 
 * </pre>
 */
@Controller
@RequestMapping("/fileUpload")
public class FileUploadAction extends BaseController{
	
	@Autowired
	private AttachmentService attachmentService;
	
	/**
	 * 
	 * Description: 上传文件到fastdfs文件服务器
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author yaodawei
	 * Create Date: 2017年6月28日 上午11:12:08
	 */
	@RequestMapping(value="/upload")
	public String upload(HttpServletRequest request, HttpServletResponse response,MultipartFile filedata) throws IOException{
		try {
			String fileType = request.getParameter("fileType");
			String fileName = filedata.getOriginalFilename();
			String filePrefix = fileName.substring(fileName.lastIndexOf(".")+1);
			String filePath=FastDFSClient.uploadFile(filedata.getBytes(), filePrefix);
			Attachment attachment = new Attachment();
			attachment.setFileName(fileName);
			attachment.setFilePath(filePath);
			attachment.setFileType(fileType);
			attachment.setCreator(Integer.valueOf(request.getParameter("creator")));
			attachment.setCreateTime(new Date());
			Integer attachmentId = attachmentService.insertSelective(attachment);
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("success:" + "," + filePath + "," + attachmentId + ","+ fileName);
		} catch (IOException e) {
			e.printStackTrace();
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("error:上传文件失败!");
		}
		return null;
	}
	
	/**
	 * 
	 * Description: fastdfs文件服务器下载
	 *
	 * @param 
	 * @return void
	 * @throws 
	 * @Author yaodawei
	 * Create Date: 2017年6月28日 上午11:11:23
	 */
	@RequestMapping("/download")
	public void download(@RequestParam String filePath,@RequestParam String fileName,HttpServletResponse response) throws IOException {
		//得到想客服端输出的输出流
		OutputStream outputStream = null;
		InputStream inputStream = null;
		try {
			outputStream = response.getOutputStream();
			//客户端使用保存文件的对话框
			response.setHeader("Content-disposition", "attachment;filename=" + fileName);
			response.setCharacterEncoding("utf-8");
			//输出文件用的字节数组，每次向输出流发送1024个字节
			byte b[] = new byte[1024];
			URL url = new URL(filePath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setConnectTimeout(5 * 1000);
	        inputStream = conn.getInputStream();// 通过输入流获取图片数据
			int n = 0;
			while((n=inputStream.read(b))!=-1){
			     outputStream.write(b,0,n);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			inputStream.close();
			outputStream.close();
		}
	}
	
	/**
	 * 
	 * Description: 附件删除
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author yaodawei
	 * Create Date: 2017年6月27日 下午5:00:52
	 */
	@RequestMapping(value="/del/{id}")
	public String delAttachment(@PathVariable Integer id,HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			attachmentService.deleteByPrimaryKey(id);
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("删除失败");
		}
		return null;
	}
	
	/**
	 * 
	 * Description: 上传文件到本地tomcat
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author yaodawei
	 * Create Date: 2017年6月27日 下午5:00:32
	 */
	@RequestMapping(value="/uploadLocal")
	public String uploadLocal(HttpServletRequest request, HttpServletResponse response,MultipartFile filedata) throws Exception {
		try {
			String fileType = new String(request.getParameter("fileType")); //附件类型
			File target = new File("D:/Program Files/apache-tomcat-7.0.47/webapps/" + fileType);
			if(!target.exists()){
				target.mkdirs();
			}
			String newName = FileRenameUtil.getNewFileName(filedata.getOriginalFilename()); //为防止图片名称一样产生的覆盖问题,对图片重新命名
			File newFile = new File(target,newName); //根据 target路径名字符串和 newName 路径名字符串创建一个新 File 实例。
			CommonsMultipartFile cf = (CommonsMultipartFile)filedata;   
		    DiskFileItem fi = (DiskFileItem) cf.getFileItem(); 
			FileUtils.copyFile(fi.getStoreLocation(), newFile);
			String newFilePath = "/" + fileType + "/" + newFile.getName();
			Attachment attachment = new Attachment();
			attachment.setFileName(filedata.getOriginalFilename());
			attachment.setFilePath(newFilePath);
			attachment.setFileType(fileType);
			attachment.setCreator(Integer.valueOf(request.getParameter("creator")));
			attachment.setCreateTime(new Date());
			Integer attachmentId = attachmentService.insertSelective(attachment);
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("success:" + "," + newFilePath + "," + attachmentId + ","+ filedata.getOriginalFilename());
		} catch (Exception e) {
			e.printStackTrace();
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("error:上传文件失败!");
		}
		return null;
	}
	
	/**
	 * 
	 * Description: tomcat本地文件服务器下载
	 *
	 * @param 
	 * @return void
	 * @throws 
	 * @Author yaodawei
	 * Create Date: 2017年6月28日 上午11:13:22
	 */
	@RequestMapping("/downloadLocal")
	public void downloadLocal(@RequestParam String filePath,@RequestParam String fileName,HttpServletResponse response) throws IOException {
		//得到想客服端输出的输出流
		OutputStream outputStream = null;
		//读取文件，并发送给客服端下载
		FileInputStream  inputStream = null;
		try {
			outputStream = response.getOutputStream();
			//输出文件用的字节数组，每次向输出流发送1024个字节
			byte b[] = new byte[1024];
			//要下载的文件
			File fileload = new File("D:/Program Files/apache-tomcat-7.0.47/webapps" + filePath);  
			//客服端使用保存文件的对话框
			response.setHeader("Content-disposition", "attachment;filename=" + fileName);
			//通知客服文件的长度
			long fileLength = fileload.length();
			String length = String.valueOf(fileLength);
			response.setHeader("Content_length", length);
			response.setCharacterEncoding("utf-8");
			inputStream = new FileInputStream(fileload);
			int n = 0;
			while((n=inputStream.read(b))!=-1){
				outputStream.write(b,0,n);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			inputStream.close();
			outputStream.close();
		}
	}
	
	/**
	 * 
	 * Description: 图片查看 
	 *
	 * @param 
	 * @return String 返回的图片信息（url）
	 * @throws IOException 
	 * @throws FileUploadException 
	 * @throws 
	 * @Author guoyanwei
	 * Create Date: 2017年6月26日 下午2:30:42
	 */
    @ResponseBody
	@RequestMapping(value="/imageurl")
	public List<Map<String, Object>> findimage(HttpServletRequest request, HttpServletResponse response){
    	List<Map<String, Object>> attachments = null;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String objectType = StringUtil.trim(request.getParameter("objectType"));//参照对象表名类型
			String objectId = StringUtil.trim(request.getParameter("objectId"));//参照对象ID
			params.put("objectType", objectType);
			params.put("objectId", objectId);
			attachments = attachmentService.findAttachments(params);
			System.out.println(JSONArray.toJSONString(attachments));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return attachments;
	}
}
