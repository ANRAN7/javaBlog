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

/**
 * 
* @ClassName: SendWithImg
* @Description: 带图片的资源
* @author peace w_peace@163.com 
* @date 21 Nov 2015 8:48:13 pm
*
 */
public class SendWithImg {
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
			//4.1构建一个多功能邮件快
			MimeMultipart related = new MimeMultipart("related");
			// 4.2 构建多功能邮件块内容 = 左侧文本 + 右侧图片资源
			MimeBodyPart content = new MimeBodyPart();
			MimeBodyPart resource = new MimeBodyPart();
			//设置具体内容
			//图片路径
			String filePath=SendWithImg.class.getResource("1.png").getPath();
			DataSource ds= new FileDataSource(new File(filePath));
			DataHandler handler=new DataHandler(ds);
			resource.setDataHandler(handler);
			resource.setContentID("mark");//设置资源名称，给外键引用
			//设置具体文本
			content.setContent("<img src='cid:mark'/>  我的公司Ptool", "text/html;charset=UTF-8");
			//将内容加入
			related.addBodyPart(content);
			related.addBodyPart(resource);
			//把复杂快添加到邮件中
			message.setContent(related);
			//发送
			Transport.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
