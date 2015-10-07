package com.rlovep.dom4j;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Studentxml {

	public static void main(String[] args) {
		
		List<Student>stus=new ArrayList<Student>();
        //读取xml文档
		SAXReader reader=new SAXReader();
        try {
			Document doc = reader.read("./src/student.xml");
			//获得所有学生标签
			List<Element> elements = doc.getRootElement().elements("student");
			for(Element e:elements){
				//进行对student设置
				Student s=new Student();
				s.setId(e.attributeValue("id"));
				s.setName(e.element("name").getText());
				s.setSex(e.element("sex").getText());
				stus.add(s);
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        for(Student s:stus){
        	System.out.println(s);
        }
        
	}

}
