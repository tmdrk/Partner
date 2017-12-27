package com.huizhongcf.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import com.huizhongcf.util.PropertyUtil;
/**
 * 
 * 
 * Description:
 * 
 * @author ouyangjin
 * @version 1.0
 * 
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2013-7-1      ouyangjin       1.0         1.0 Version
 * </pre>
 */
public class MailUtil {
	private static final Logger loger = LoggerFactory.getLogger(MailUtil.class);
	private static String username = PropertyUtil.getMailInfo("mail.username");// 用户名(即发件人的@前缀)
	private static String password = PropertyUtil.getMailInfo("mail.password");// 密码
	private static String frommail = PropertyUtil.getMailInfo("mail.from");// 发件人(必须开通与服务器地址对应的协议)
	private static String host = PropertyUtil.getMailInfo("mail.smtp");// 服务器地址
	private static int port = 25;// 服务器端口
	private static String defaultencoding = "UTF-8";// 默认编码
	private String subject = "欢迎注册达飞贷";// 标题
	private String text;// 主题内容
	private String tomail;// 收件人
	private static String regmail = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";// 验证邮箱

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFrommail() {
		return frommail;
	}

	public void setFrommail(String frommail) {
		this.frommail = frommail;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getDefaultencoding() {
		return defaultencoding;
	}

	public void setDefaultencoding(String defaultencoding) {
		this.defaultencoding = defaultencoding;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTomail() {
		return tomail;
	}

	public void setTomail(String tomail) {
		this.tomail = tomail;
	}

	/**
	 * 构造邮件信息
	 * 
	 * @param text
	 *            邮件内容
	 * @param tomail
	 *            收件人
	 */
	public MailUtil(String text, String tomail) {
		this.text = text;
		this.tomail = tomail;
	}

	/**
	 * 发送邮件
	 * 
	 * @return
	 */
	public boolean sendFileMail() {
		try {
			JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
			// 设定服务器地址、端口
			senderImpl.setHost(host);
			senderImpl.setPort(port);
			// 邮件默认编码
			senderImpl.setDefaultEncoding(defaultencoding);
			// 登录用户、密码
			senderImpl.setUsername(username);
			senderImpl.setPassword(password);
			// 建立HTML邮件消息
			MimeMessage mailMessage = senderImpl.createMimeMessage();
			// true表示开始附件模式
			MimeMessageHelper messageHelper = new MimeMessageHelper(
					mailMessage, false, defaultencoding);
			// 寄件人
			messageHelper.setFrom(frommail);
			// 设置收件人
			messageHelper.setTo(tomail);
			// 标题
			messageHelper.setSubject(subject);
			// 内容,true 表示启动HTML格式的邮件
			messageHelper.setText(text, true);
			senderImpl.send(mailMessage);
			loger.info("【正在给收件人 "+tomail+" 发送邮件。。。】");
			loger.info("【邮件发送成功】");
		} catch (MessagingException me) {
			loger.info("【邮件发送失败】");
			loger.error("", me);
			return false;
		}
		return true;
	}

	public static boolean checkEmail(String email) {
		if (null == email) {
			return false;
		}
		return email.matches(regmail);
	}

}
