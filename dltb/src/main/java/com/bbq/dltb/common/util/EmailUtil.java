package com.bbq.dltb.common.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sun.mail.util.MailSSLSocketFactory;

public class EmailUtil {
	private static final Logger log = LoggerFactory.getLogger(EmailUtil.class);

	// // @Value("#{configProperties['email.sender_username']}")
	// private static String sender_user = "plus_cly@163.com";
	// private static String sender_username = "plus_cly";
	// // @Value("#{configProperties['email.sender_password']}")
	// private static String sender_password = "123abc";
	// // @Value("#{configProperties['email.sender_mail_server']}")
	// private static String sender_mail_server = "smtp.163.com";

	// @Value("#{configProperties['email.sender_username']}")
	private static String sender_user = "no_reply@iflyplus.com";
	private static String sender_username = "no_reply";
	// @Value("#{configProperties['email.sender_password']}")
	private static String sender_password = "Flyfly9";
	// @Value("#{configProperties['email.sender_mail_server']}")
	private static String sender_mail_server = "smtp.exmail.qq.com";// "smtp.ifyplus.com";

	private final static Properties props = new Properties();// 配置发送邮件的环境属性
	static {
		// 表示SMTP发送邮件，需要进行身份验证
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", sender_mail_server);
		// 发件人的账号
		props.put("mail.user", sender_user);
		// 访问SMTP服务时需要提供的密码
		props.put("mail.password", sender_password);
	}

	/**
	 * 发送邮件
	 * 
	 * @param toEmail
	 *            收件人
	 * @param subject
	 *            标题
	 * @param content
	 *            内容
	 */
	public static void sendMsg(String toEmail, String subject, String content) {
		log.info("发送邮件，toEmail={},subject={},msg={},sender_username={},sender_password={},sender_mail_server={}",
				toEmail, subject, content, sender_username, sender_password, sender_mail_server);

		try {
			// 构建授权信息，用于进行SMTP进行身份验证
			Authenticator authenticator = new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					// 用户名、密码
					String userName = props.getProperty("mail.user");
					String password = props.getProperty("mail.password");
					return new PasswordAuthentication(userName, password);
				}
			};
			MailSSLSocketFactory sf = null;
			try {
				sf = new MailSSLSocketFactory();
			} catch (Exception e) {
				log.info("error", e);
			}
			sf.setTrustAllHosts(true);
			props.put("mail.smtp.ssl.enable", "true");
			props.put("mail.smtp.ssl.socketFactory", sf);
			// 使用环境属性和授权信息，创建邮件会话
			Session mailSession = Session.getInstance(props, authenticator);
			// 创建邮件消息
			MimeMessage message = new MimeMessage(mailSession);
			// 设置发件人
			InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
			message.setFrom(form);

			// 设置收件人
			InternetAddress to = new InternetAddress(toEmail);
			message.setRecipient(RecipientType.TO, to);

			// // 设置抄送
			// InternetAddress cc = new InternetAddress("luo_aaaaa@yeah.net");
			// message.setRecipient(RecipientType.CC, cc);

			// 设置密送，其他的收件人不能看到密送的邮件地址
			// InternetAddress bcc = new InternetAddress("aaaaa@163.com");
			// message.setRecipient(RecipientType.CC, bcc);

			// 设置邮件标题
			message.setSubject(subject);

			// 设置邮件的内容体
			message.setContent(content, "text/html;charset=UTF-8");

			// 发送邮件
			Transport.send(message);
		} catch (AddressException e) {
			log.error("收件人地址错误", e);
		} catch (MessagingException e) {
			log.error("发送失败", e);
		}
	}

	public static void main(String[] args) throws Exception {
		sendMsg("150491137@qq.com", "验证码", "123456");
	}
}
