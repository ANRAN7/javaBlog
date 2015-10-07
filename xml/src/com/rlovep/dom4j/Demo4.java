package com.rlovep.dom4j;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

/**
 *  课堂练习： 
 * 1.使用dom4j的api来生成以下的xml文件
<Students>
<Student id="1">
	<name>张三</name>
	<gender>男</gender>
	<grade>计算机1班</grade>
	<address>广州天河</address>
</Student>
<Student id="2">
	<name>李四</name>
	<gender>女</gender>
	<grade>计算机2班</grade>
	<address>广州越秀</address>
</Student>
</Students>

2.修改id为2的学生的姓名，改为“王丽”

3.删除id为2的学生
 * @author APPle
 *
 */
public class Demo4 {

	/**
	 * 1.生成指定xml文档
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception{
		//1.内存创建xml文档
		Document doc = DocumentHelper.createDocument();
		
		//2.写入内容
		Element rootElem = doc.addElement("Students");
		
		//2.1 增加标签
		Element studentElem1 = rootElem.addElement("Student");
		//2.2 增加属性
		studentElem1.addAttribute("id", "1");
		//2.3 增加标签，同时设置文本
		studentElem1.addElement("name").setText("张三");
		studentElem1.addElement("gender").setText("男");
		studentElem1.addElement("grade").setText("计算机1班");
		studentElem1.addElement("address").setText("广州天河");
		
		//2.1 增加标签
		Element studentElem2 = rootElem.addElement("Student");
		//2.2 增加属性
		studentElem2.addAttribute("id", "2");
		//2.3 增加标签，同时设置文本
		studentElem2.addElement("name").setText("李四");
		studentElem2.addElement("gender").setText("女");
		studentElem2.addElement("grade").setText("计算机2班");
		studentElem2.addElement("address").setText("广州越秀");
		
		
		//3.内容写出到xml文件
		//3.1 输出位置
		FileOutputStream out = new FileOutputStream("e:/student.xml");
		//3.2 指定格式
		OutputFormat format = OutputFormat.createPrettyPrint();
		// 设置编码
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(out,format);
		//3.3 写出内容
		writer.write(doc);
		//3.4关闭资源
		writer.close();
		
	}
	
	/**
	 * 2.修改id为2的学生姓名
	 * @throws Exception
	 */
	@Test
	public void test2() throws Exception{
		//1.查询到id为2的学生
		Document doc = new SAXReader().read(new File("e:/student.xml"));
		//1.1 找到所有的Student标签
		Iterator<Element> it = doc.getRootElement().elementIterator("Student");
		while(it.hasNext()){
			Element stuElem = it.next();
			//1.2 查询id为id的学生标签
			if(stuElem.attributeValue("id").equals("2")){
				stuElem.element("name").setText("王丽");
				break;
			}
		}
	
		
		//3.1 输出位置
		FileOutputStream out = new FileOutputStream("e:/student.xml");
		//3.2 指定格式
		OutputFormat format = OutputFormat.createPrettyPrint();
		// 设置编码
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(out,format);
		//3.3 写出内容
		writer.write(doc);
		//3.4关闭资源
		writer.close();
	}
	
	/**
	 * 3.删除id为2的学生
	 * @throws Exception
	 */
	@Test
	public void test3() throws Exception{
		//1.查询到id为2的学生
		Document doc = new SAXReader().read(new File("e:/student.xml"));
		//1.1 找到所有的Student标签
		Iterator<Element> it = doc.getRootElement().elementIterator("Student");
		while(it.hasNext()){
			Element stuElem = it.next();
			//1.2 查询id为id的学生标签
			if(stuElem.attributeValue("id").equals("2")){
				//1.3 删除该学生标签
				stuElem.detach();
				break;
			}
		}
	
		
		//3.1 输出位置
		FileOutputStream out = new FileOutputStream("e:/student.xml");
		//3.2 指定格式
		OutputFormat format = OutputFormat.createPrettyPrint();
		// 设置编码
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(out,format);
		//3.3 写出内容
		writer.write(doc);
		//3.4关闭资源
		writer.close();
	}
}
