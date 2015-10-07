package com.rlovep.dom4j;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;


/**
 * 
* @ClassName: WriteXml
* @Description: 修改xml的内容：增删减；
* @author peace w_peace@163.com 
* @date 1 Oct 2015 10:52:49 am
*
 */
public class WriteXml {
   /**
    * 
   * @Title: addXml 
   * @Description: 增加xml文档，增加标签，增加属性； 
   * @throws Exception 
   * @return:void   
   * @throws 
   * @author peace w_peace@163.com
    */
	@Test
	public void addXml() throws Exception{
    	//创建文档
    	Document doc=DocumentHelper.createDocument();
    	//增加根标签
    	Element rootelem=doc.addElement("Studentlist");
    	//增加子标签
    	Element stu1=rootelem.addElement("study");
    	Element name1=stu1.addElement("name");
    	Element stu2=rootelem.addElement("study");
    	Element name2 = stu2.addElement("name");
    	//怎加属性
    	stu1.addAttribute("id", "001");
    	stu2.addAttribute("id", "002");
    	//创建输出流
    	FileOutputStream out=new FileOutputStream("./src/writeStu.xml");
    	//输出xml的格式
    	OutputFormat format=OutputFormat.createPrettyPrint();
    	format.setEncoding("utf-8");
    	//创建写出对象
    	XMLWriter writer=new XMLWriter(out,format);
    	//写入doc
    	writer.write(doc);
    	writer.close();
    }
	/**
	 * 
	* @Title: changexml 
	* @Description: 修改xml的文本和属性 
	* @throws Exception 
	* @return:void   
	* @author peace w_peace@163.com
	 */
	@Test
   public void changexml() throws Exception{
	   Document doc = new SAXReader().read("./src/student.xml");
	   /**
	    * 修改属性
	    * 方法1:1.得到标签对象 2.得到属性对象  3修改属性值
	    */
		//获得属性
	   Attribute id1=doc.getRootElement().element("student").attribute("id");
	   //修改
	   id1.setValue("007");
	   //方法二：1.得到标签对象  2通过增加同名属性修改
	   Element stu2=(Element) doc.getRootElement().elements("student").get(1);
	   //增加同名属性
	   stu2.addAttribute("id", "008");
	   
	   /**
	    * 修改文本
	    * 1.得到标签对象 2，设置文本
	    */
	   Element name=stu2.element("name");
	   name.setText("蘅嵘");
	 //创建输出流
   	FileOutputStream out=new FileOutputStream("./src/student.xml");
   	//输出xml的格式
   	OutputFormat format=OutputFormat.createPrettyPrint();
   	format.setEncoding("utf-8");
   	//创建写出对象
   	XMLWriter writer=new XMLWriter(out,format);
   	//写入doc
   	writer.write(doc);
   	writer.close();
   }
	/**
	 * 
	* @Title: deletXml 
	* @Description: 删除标签和属性测试
	* @return:void   
	* @throws 
	* @author peace w_peace@163.com
	 */
	@Test
	public void deletXml() throws Exception{
		//创建文档
    	Document doc=DocumentHelper.createDocument();
    	//增加根标签
    	Element rootelem=doc.addElement("Studentlist");
    	//增加子标签
    	Element stu1=rootelem.addElement("study");
    	Element name1=stu1.addElement("name");
    	Element stu2=rootelem.addElement("study");
    	Element name2 = stu2.addElement("name");
    	//怎加属性
    	stu1.addAttribute("id", "001");
    	stu2.addAttribute("id", "002");
    	/**
    	 * 删除标签  1得到标签对象  2删除标签对象
    	 */
    	Element name=doc.getRootElement().element("study").element("name");
    	//方法1  自杀
    	//name.detach();
    	//方法二  他杀
    	name.getParent().remove(name);
    	/**
    	 * 删除属性  1.得到属性对象  3删除属性
    	 */
    	Attribute id1=doc.getRootElement().element("study").attribute("id");
    	//方法1  自杀
    	id1.detach();
    	//方法二  他杀
    	//id1.getParent().remove(id1);
    	
    	//创建输出流
    	FileOutputStream out=new FileOutputStream("./src/deletStu.xml");
    	//输出xml的格式
    	OutputFormat format=OutputFormat.createPrettyPrint();
    	format.setEncoding("utf-8");
    	//创建写出对象
    	XMLWriter writer=new XMLWriter(out,format);
    	//写入doc
    	writer.write(doc);
    	writer.close();
	}
}
