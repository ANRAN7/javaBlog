package com.rlovep.xpath;

import java.util.List;



import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;
import org.dom4j.io.SAXReader;

/**
 * 
* @ClassName: TestXpath
* @Description: xpath语法入门；
* @author peace w_peace@163.com 
* @date 1 Oct 2015 1:58:30 pm
*
 */
public class TestXpath {
	private static int count=0;
	public static void main(String[] args) throws Exception {
		Document doc = new SAXReader().read("./src/student.xml");
		String xpath="";
		/*
		 * 1.学习 /   绝对路径，一个 /表示一层 与文件系统类似
		 */
		//从doc中选出根标签
		xpath="/studentList";
		System.out.println("根标签>>"+doc.selectSingleNode(xpath).getName());
		//从doc中选出所有的student标签
		xpath="/studentList/student";
		List<Node> nodes = doc.selectNodes(xpath);
		count=1;
		for(Node i:nodes){
			
			System.out.println("student>>>"+(count++)+":"+i.getName());
		}
		/*
		 * 2。学习 //   表示不分层次结构选择元素，只要符合要求都选出
		 */
		//从doc中选出所有的name标签
		xpath="//name";
		nodes = doc.selectNodes(xpath);
		count=1;
		for(Node i:nodes){
			
			System.out.println("name>>>"+(count++)+":"+i.getName());
			}
		
		/*
		 * 3.学习 * 通配符  表示匹配所有元素
		 */
		//将根节点下的所有孩子节点选出来 不包括孙标签
		xpath="/studentList/*";
		nodes = doc.selectNodes(xpath);
		count=1;
		for(Node i:nodes){
			
			System.out.println("student>>>"+(count++)+":"+i.getName());
			}
		//将根标签下的所有标签选出：//* 表示选出所有不分层次结构
		xpath="/studentList//*";
		nodes = doc.selectNodes(xpath);
		count=1;
		for(Node i:nodes){
			
			System.out.println("Allnode>>>"+(count++)+":"+i.getName());
			}
		/*
		 * 4.学习 @  表示属性  表示选择属性节点
		 */
		//找出所有的id属性节点
		xpath="//@id";
		List<Attribute> idNode = (List<Attribute>)doc.selectNodes(xpath);
		count=1;
	    for(Attribute a:idNode){
	    	System.out.println(a.getName()+"="+a.getValue());
	    }
		/*
		 * 5.学习 [] 条件 表示在一定条件下的元素
		 */
		//找出带有id属性的student标签   [@id] 
		xpath="//student[@id]";
		nodes = doc.selectNodes(xpath);
		count=1;
		for(Node i:nodes){
			System.out.println("student and id>>>"+(count++)+":"+i.getName());
			}
		//找出第2个student标签 [2]
		xpath="//student[2]";
		System.out.println("第二个Student>>"+doc.selectSingleNode(xpath).getName());
		//找出最后一个student标签 [last()]
		xpath="//student[last()]";
		System.out.println("最后一个Student>>"+doc.selectSingleNode(xpath).getName());
		//一下不做演示，自己演示：
		xpath = "//student[not(@id)]";//选择不包含id属性的student标签节点
		xpath = "//student[@id='007']";//选择id属性值为007的student标签
		xpath = "//student[@id='008' and @qq='374126165']";//选择id属性值为008，且qq属性为374126165的student标签
		/*
		 * 6.学习 text() 表示文本内容
		 */
		//选择name标签下的文本内容，返回Text对象
		xpath="//name/text()";
		List<Text> text = (List<Text>)doc.selectNodes(xpath);
		count=1;
		for(Text t:text){
			System.out.println("name Text>>>"+(count++)+":"+t.getText());
		}
		//选择名字为蘅嵘的name标签
		xpath="//name[text()='蘅嵘']";
		System.out.println("name=="+doc.selectSingleNode(xpath).getText());
	}

}
