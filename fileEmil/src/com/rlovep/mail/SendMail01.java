package com.rlovep.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail01 {
    public static void main(String[] args) {
		//邮件参数
    	Properties prop=new Properties();
    	prop.put("mail.transport.protocol", "smtp");	// 指定协议
		prop.put("mail.smtp.host", "smtp.qq.com");		// 主机   stmp.qq.com
		prop.put("mail.smtp.port", 25);					// 端口
		prop.put("mail.smtp.auth", "true");				// 用户密码认证
		prop.put("mail.debug", "true");					// 调试模式
		//设置密码和账户，和发送人必须一致；
		Authenticator authenticator=new EmailAuthenticator("374126165@qq.com","wang126165");
    	//1.创建一个邮件会话
		Session session=Session.getDefaultInstance(prop,authenticator);
		//2.创建邮件体对象
		MimeMessage message=new MimeMessage(session);
		try {
			//3.设置邮件体参数：
			//3.1标题
			message.setSubject("我的第一封邮件","utf-8");
			//3.2发送时间
			message.setSentDate(new Date());
			//3.3发件人
			message.setFrom(new InternetAddress("374126165@qq.com"));
			//3.4接收人
			message.setRecipient(Message.RecipientType.TO, new InternetAddress("491629346@qq.com"));
			//3.5内容
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart multipart=new MimeMultipart();
			//创建一个包含HTML内容的MimeBodyPart
			BodyPart html=new MimeBodyPart();
			//设置html内容message.setContent("<a href='#'>百度</a>", "text/html;charset=UTF-8");
			html.setContent("<a href='http://rlovep.com'>peace的网站</a>", "text/html;charset=UTF-8");
			multipart.addBodyPart(html);
			message.setContent(multipart);
			//4.发送
			Transport.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
