package com.rlovep.sax;

import java.io.File;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class TestSax {

	public static void main(String[] args) throws Exception{
		//创建SAXParser对象
		SAXParser parser=SAXParserFactory.newInstance().newSAXParser();
        //调用parse方法
		/**
		 * 参数一为：xml文档
		 * 参数二为：DefaultHandler的子类
		 */
		parser.parse(new File("./src/student.xml"), new MyHandler());
	}
}
