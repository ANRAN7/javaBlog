package com.rlovep.sax;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.rlovep.dom4j.Student;
//当相应的事件发生时会自动调用这些方法
/**
 * 
* @ClassName: MyHandler
* @Description: 读取xml并且封装成对象
* @author peace w_peace@163.com 
* @date 1 Oct 2015 4:06:06 pm
*
 */
public class MyHandler3 extends DefaultHandler {
	//存储student对象
	private List<Student> stulist=new ArrayList<Student>();
	//返回student对象list
	public List<Student> getStulist(){
		return stulist;
	}
	//保存一个学生的信息 引用
	private Student student=null;
	//保存当前的标签名
	private String curTag;
    /**
     * 读取标签开始时调用
     * @author peace
     * @param qName  :开始的标签名 
     * @param attributes：标签内包含的属性列表
     * 
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    	curTag=qName;
    	//如果开始标签为student 就创建对象
    	if("student".equals(qName))
    		{
    		student=new Student();
    		//同事设置属性
    		student.setId(attributes.getValue("id"));
    		student.setQq(attributes.getValue("qq"));
    		}
    }
    /**
     * 读取结束标签时调用
     * @author peace
     * @param qName  :标签名 
     * 
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    	//为了避免空格换行设置到对象的属性中
    	curTag=null;
       //如果结束标签为student 就将对象加入列表
       if("student".equals(qName))
		{
    	   stulist.add(student);
		}
    }
    /**
     * 读到文本时调用
     * @author peace
     * @param ch:表示当前读完的所有文本内容
     * @param start：当前文本内容的开始位置
     * @param length：当前文本内容的长度
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
    	String text=new String(ch, start, length);
    	//如果当前表签名为name就设置name变量
    	if("name".equals(curTag)){
    		student.setName(text);
    	}
    	//如果当前表签名为sex就设置sex变量
    	
    	if("sex".equals(curTag)){
    		student.setSex(text);
    	}
    	//如果当前表签名为stnum就设置atnum变量
    	if("stnum".equals(curTag)){
    		student.setStnum(text);
    	}
    }
}
