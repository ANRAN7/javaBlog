package com.rlovep.sax;

import java.io.File;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Test;

import com.rlovep.dom4j.Student;

public class ReadXml {
	@Test
	public void addStudent() throws Exception {
		// 创建SAXParser
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		// 读取xml文件
		MyHandler3 handler=new MyHandler3();
		parser.parse(new File("./src/student.xml"), handler);
		//获得学生list
		List<Student> stulist = handler.getStulist();
		//打印
		for(Student s:stulist){
			System.out.println(s.toString());
		}
	}
	public static void main(String[] args) throws Exception{
		//创建SAXParser
		SAXParser parser=SAXParserFactory.newInstance().newSAXParser();
		//读取xml文件
		MyHandler2 handler=new MyHandler2();
		parser.parse(new File("./src/student.xml"), handler);
		//打印xml
		System.out.println(handler.getxml());
	}
	

}
