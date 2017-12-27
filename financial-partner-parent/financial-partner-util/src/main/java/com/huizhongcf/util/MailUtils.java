/* 
 * Copyright (C) 2006-2013 达飞财富科技发展（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: MailUtils.java 
 *
 * Created: [2013-9-24 下午02:21:03] by jie 
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
 * ProjectName: dfd 
 * 
 * Description: 邮件工具类
 * 
 * ==========================================================*/

package com.huizhongcf.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/** 
 *
 * Description:邮件工具类
 *
 * @author lijie
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2013-9-24      lijie       1.0         1.0 Version 
 * </pre>
 */

public class MailUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(MailUtils.class);
	
	// 发件人
	private static String frommail = PropertyUtil.getMailInfo("mail.from");
	
	static JavaMailSender sender = null;
	static TaskExecutor taskExecutor = null;  
	static {
		Environment e = Environment.getInstance();
		sender = (JavaMailSender) e.getBean("mailSender");
	}
	
	/**
	 * 
	 * Description: 发送HTML邮件
	 *
	 * @param subJect 主题
	 * @param text 内容
	 * @param to 收件人
	 * @Author lijie
	 * @Create Date: 2013-9-26 下午03:52:40
	 */
	public static void sendHtmlMailBySynchro(String subJect, String text, String to) {
		try {
			if("".equals(to)){
				logger.info("【邮箱为空】");
			}else{
				// 建立邮件消息
				MimeMessage mailMessage = sender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, "UTF-8");
				try {
					// 寄件人
					messageHelper.setFrom(frommail);
					// 收件人
					messageHelper.setTo(to);
					messageHelper.setSubject(subJect);
					messageHelper.setText(text, true);
					logger.info("【正在给收件人"+to+"发送邮件。。。】");
				} catch (MessagingException e) {
					e.printStackTrace();
				}
				// 发送邮件
				sender.send(mailMessage);
				logger.info("【邮件发送成功】");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【邮件发送失败】");
		}
	}
	
	
	/**
	 * Description: test mail
	 * @param args
	 * @Author lijie
	 * @Create Date: 2013-9-24 下午02:54:10
	 */
	public static void main(String[] args) {
		try {
			MailUtils.sendHtmlMailBySynchro("你好,这是主题", "狗产党  社会","852855513@qq.com");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
