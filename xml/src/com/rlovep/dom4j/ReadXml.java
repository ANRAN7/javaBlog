package com.rlovep.dom4j;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;
import org.dom4j.io.SAXReader;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;

/**
 * 
* @ClassName: ReadXml
* @Description: TODO(这里用一句话描述这个类的作用)
* @author peace w_peace@163.com 
* @date 30 Sep 2015 10:46:08 am
*
 */
public class ReadXml {
	private  List<Element> list=new ArrayList<Element>();
	
	/**
	 * 
	* @Title: readnode 
	* @Description: 获取所有节点,只得到一层节点  
	* :void   
	* @throws 
	* @author peace w_peace@163.com
	 */
	@Test
	public void readFirstnode(){
		
		try {
			//1.读取xml文档，返回Document对象
			SAXReader reader =new SAXReader();
			Document doc=reader.read(new File("./src/student.xml"));
			//得到当前文档的所有子节点对象(不包含孙以下的节点)
			Iterator<Node> it=doc.nodeIterator();
			while(it.hasNext()){
				Node node=it.next();
				String name=node.getName();
				System.out.println(name+"----");
				//判断节点是否为标签，如果为标签就得到标签下的子节点；
				if(node instanceof Element){
					//获得标签下的子节点
					Iterator<Node> it2=((Element) node).nodeIterator();
					   while(it2.hasNext()){
						  Node el=it2.next();
						   System.out.println(el.getName());
					   }
							}
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	* @Title: readAllNide 
	* @Description: 获得所有标签  
	* @return:void   
	* @throws 
	* @author peace w_peace@163.com
	 */
	
	@Test
	public void readAllNide(){
		try {
			//读取xml文档
			SAXReader reader=new SAXReader();
			Document doc=reader.read("./src/student.xml");
			//获得根标签
			Element rootELem=doc.getRootElement();
			//获得根标签下的所有标签，并保存到list中
			getChildNodes(rootELem);
			System.out.println(list);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	* @Title: getChildNodes 
	* @Description: 获得传入标签的子标签
	* @param elem  
	* @throws 
	* @author peace w_peace@163.com
	 */
   private void getChildNodes(Element elem){
	   //保持标签
	   list.add(elem);
	   System.out.println(elem.getName());
	   Iterator<Node> it=elem.nodeIterator();
	   while(it.hasNext()){
		   Node node=it.next();
		   //如果节点为标签，进行递归
		   if(node instanceof Element){
			   getChildNodes((Element)node);
		   }
	   }
   }
   /**
    * 
   * @Title: getElement 
   * @Description: 获得所有标签；（将输入参数改为url）
   * @return:void   
   * @throws 
   * @author peace w_peace@163.com
    */
   @Test
   public void getElement(){
	   try {
		 //读取xml文档
			SAXReader reader=new SAXReader();
			Document doc=reader.read("./src/student.xml");
			//获得根标签
			Element rootElem=doc.getRootElement();
		//得到标签名称
		String rootName=rootElem.getName();
		System.out.println("root>>>>"+rootName);
		//得到当前标签下指定标签的第一个标签
		Element firstElembyname=rootElem.element("student");
		System.out.println("first>>>"+firstElembyname.getName());
		//得到当前标签下所有指定的标签
		Iterator<Element> elem=rootElem.elementIterator("student");
		while(elem.hasNext()){
			Element e=elem.next();
			System.out.println("elementIterator>>"+e.getName());
		}
		//5得到当前标签下的所有子标签
		List<Element> elements = rootElem.elements();
		for(Element e:elements){
			System.out.println("elements>>"+e.getName());
		}
		//或者重复式样获得想要的标签：
		Element nameElem=doc.getRootElement().element("student").element("name");
		System.out.println("name>>"+nameElem.getName());
		
	} catch (DocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   /**
    * 
   * @Title: getAttribut 
   * @Description: 获取属性  
   * @return:void   
   * @throws 
   * @author peace w_peace@163.com
    */
   @Test
   public void getAttribut(){
		try {
			//读取xml文档
			SAXReader reader=new SAXReader();
			Document doc=reader.read("./src/student.xml");
			//获得sudent标签
			Element student=doc.getRootElement().element("student");
			//得到指定名称的属性值
			String idvalue = student.attributeValue("id");
			System.out.println("idvalue1>>"+idvalue);
			//得到指定名称的属性
			Attribute attribute = student.attribute("id");
			System.out.println("idvalue2>>"+attribute.getValue());
			//得到标签的所有属性
			List<Attribute> attributes = student.attributes();
			for(Attribute a:attributes){
				System.out.println("attributes>>"+a.getName()+":"+a.getValue());
			}
			// 得到所有属性对象，返回迭代器
			Iterator<Attribute> it = student.attributeIterator();
			while(it.hasNext()){
				Attribute attr = it.next();
				System.out.println(attr.getName()+"="+attr.getValue());
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
   /**
    * 
   * @Title: getText 
   * @Description: 获得指定标签  
   * @return:void   
   * @throws 
   * @author peace w_peace@163.com
    */
   @Test
    public void getText(){
    	try {
			//读取xml文档
			SAXReader reader=new SAXReader();
			Document doc=reader.read("./src/student.xml");
			//获得sudent标签
			Element student=doc.getRootElement().element("student");
		    //获得student的文本
			String text = student.getText();
		    System.out.println("student>>"+text);
		    //获得指定标签的文本
		    Element name=student.element("name");
		    System.out.println("name1>>"+name.getText());
		    //获得指定子标签的文本
		    String elementText = student.elementText("name");
		    System.out.println("name2>>"+elementText);
		    
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        SAXReader reader=new SAXReader();
        try {
			Document doc=reader.read(new File("./src/student.xml"));
			System.out.println(doc);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
