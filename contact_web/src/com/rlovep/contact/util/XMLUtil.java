package com.rlovep.contact.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.io.OutputFormat;

public class XMLUtil {
	/**
	 * 
	* @Title: getDocument 
	* @Description: 获得xml文档对象
	* @return 
	* @return:Document   
	* @throws 
	* @author peace w_peace@163.com
	 */
	public static Document getDocument(){
		Document doc=null;
		try {
			//通过sax解析获得
			doc = new SAXReader().read(new File("/home/peace/workspace/contact.xml"));
			return doc;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	/**
	 * 
	* @Title: writexml 
	* @Description: 将xml文档写到文件
	* @param doc 
	* @return:void   
	* @throws 
	* @author peace w_peace@163.com
	 */
	public static void writexml(Document doc)
	{
		FileOutputStream out=null;
		try {
			//获得文件输出流
			out=new FileOutputStream("/home/peace/workspace/contact.xml");
			//获得文件的输出格式
			OutputFormat format=OutputFormat.createPrettyPrint();
			//设置字符格式
			format.setEncoding("utf-8");
			//创建xml写出流
			XMLWriter writer=new XMLWriter( out ,format);
			//写出
			writer.write(doc);
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			
		}
		
	}
}
