package com.rlovep.mail;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.sun.mail.imap.Rights.Right;

public class SendAttach {
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
		//3.设置参数：标题，发件人，收件人，时间，内容
		try {
			//3.1标题
			message.setSubject("带图片邮件","utf-8");
			//3.2发送时间
			message.setSentDate(new Date());
			//3.3发件人
			message.setFrom(new InternetAddress("374126165@qq.com"));
			//3.4接收人
			message.setRecipient(Message.RecipientType.TO, new InternetAddress("491629346@qq.com"));
			/***************设置邮件内容: 多功能用户邮件 (related)*******************/
			MimeMultipart mixed=new MimeMultipart("mixed");
			//建立文本和附加
			MimeBodyPart text=new MimeBodyPart();
			MimeBodyPart attach=new MimeBodyPart();
			/**附件添加**/
			String  attr_path=SendAttach.class.getResource("1.txt").getPath();
			DataSource ds=new FileDataSource(new File(attr_path));
			DataHandler handler=new DataHandler(ds);
			attach.setDataHandler(handler);
			attach.setFileName("1.txt");
			//设置邮件内容
			text.setContent("我是peace", "text/html;charset=UTF-8");
			//将内容加入邮件
			mixed.addBodyPart(text);
			mixed.addBodyPart(attach);
			message.setContent(mixed);
			//发送
			Transport.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}
