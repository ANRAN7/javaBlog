package com.rlovep.xpath;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/*
 *user.xml 
 <?xml version="1.0" encoding="utf-8"?>
<users>
	<user id="001" name="peace" password="123456"></user>
	<user id="002" name="rong" password="123456"></user>
	<user id="003" name="rose" password="123456"></user>
</users>
 */
/**
 * 
* @ClassName: Login
* @Description: 使用xpath向xml中查询实现用户登陆；
* @author peace w_peace@163.com 
* @date 1 Oct 2015 3:03:55 pm
*
 */
public class Login {

	public static void main(String[] args) throws Exception {
		//读取xml文档
		Document doc = new SAXReader().read("./src/user.xml");
		//xpath路径
		String xpath=" ";
		//读入控制台输入
		BufferedReader sb=new BufferedReader(new InputStreamReader(System.in));
		while(true){
		System.out.println("请输入用户名：");
		String name=sb.readLine();
		System.out.println("请输入密码：");
		String pass=sb.readLine();
		//从doc中挑选  user标签 名字属性为name 且密码属性为 pass
		xpath="//user[@name='"+name+"' and @password='"+pass+"'p]";
		Element user=(Element)doc.selectSingleNode(xpath);
		if(user!=null){
			System.out.println("success!!");
		}
		else{
			System.out.println("用户名或密码错误");
			
		}
		}
	}
}
