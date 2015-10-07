package com.rlovep.dom4j;

import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;
import org.dom4j.io.SAXReader;
import org.junit.Test;

/**
 * 
* @ClassName: ReadStu
* @Description: 读取xml的完整内容
* @author peace w_peace@163.com 
* @date 30 Sep 2015 7:22:29 pm
*
 */
public class ReadStu {
	/**
	 * 
	* @Title: readall 
	* @Description: 测试读取全部的xml内容，原样输出  
	* @return:void   
	* @throws 
	* @author peace w_peace@163.com
	 */
	@Test
	public void readall(){
		try {
			//创建buffer存储读出来的数据
			StringBuffer sb=new StringBuffer();
			//读取xml文档
			SAXReader reader=new SAXReader();
			Document doc = reader.read("./src/student.xml");
			//读取传入标签的所有内容
			getChildNodes(doc.getRootElement(), sb);
			System.out.println(sb);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	* @Title: getChildNodes 
	* @Description: 获得传入标签的所有内容并保存到sb 
	* @param elem :传入标签
	* @param sb ：传入的stringbuffer
	* @return:void   
	* @throws 
	* @author peace w_peace@163.com
	 */
	private void getChildNodes(Element elem,StringBuffer sb){
		//开始标签
		sb.append("<"+elem.getName());
	    //获得标签所有属性
		List<Attribute> attributes = elem.attributes();
	    for(Attribute a:attributes){
	    	if(a!=null){
	    		sb.append(" "+a.getName()+"="+"\""+a.getValue()+"\"");
	    	}
	    }
	    sb.append(">");
	    //获得所有节点
	    Iterator<Node> nodeIterator = elem.nodeIterator();
	    while(nodeIterator.hasNext()){
	    	Node next = nodeIterator.next();
	        //处理标签节点    
	    	if(next instanceof Element){
	    		getChildNodes((Element)next, sb);
	    	}
	    	//处理内容节点
	    	if(next instanceof Text){
	    		Text text=(Text)next;
	    		sb.append(text.getText());
	    	}
	    }
	    sb.append("</"+elem.getName()+">");
	}

}
