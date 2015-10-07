package gz.itcast.b_xpath;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 * 学习xPath表达式语法
 * @author APPle
 *
 */
public class Demo2 {

	public static void main(String[] args) throws Exception {
		Document doc = new SAXReader().read(new File("./src/contact.xml"));
		
		String xpath = "";
		
		/**
		 * 1.  	/      绝对路径      表示从xml的根位置开始或子元素（一个层次结构）
		 */
		xpath = "/contactList";
		xpath = "/contactList/contact";
		
		/**
		 * 2. //     相对路径       表示不分任何层次结构的选择元素。
		 */
		xpath = "//contact/name";
		xpath = "//name";
		
		/**
		 * 3. *      通配符         表示匹配所有元素
		 */
		xpath = "/contactList/*"; //根标签contactList下的所有子标签
		xpath = "/contactList//*";//根标签contactList下的所有标签（不分层次结构）
		
		/**
		 * 4. []      条件           表示选择什么条件下的元素
		 */
		//带有id属性的contact标签
		xpath = "//contact[@id]";
		//第二个的contact标签
		xpath = "//contact[2]";
		//选择最后一个contact标签
		xpath = "//contact[last()]";
		
		/**
		 * 5. @     属性            表示选择属性节点
		 */
		xpath = "//@id"; //选择id属性节点对象，返回的是Attribute对象
		xpath = "//contact[not(@id)]";//选择不包含id属性的contact标签节点
		xpath = "//contact[@id='002']";//选择id属性值为002的contact标签
		xpath = "//contact[@id='001' and @name='eric']";//选择id属性值为001，且name属性为eric的contact标签
		
		/**
		 *6.  text()   表示选择文本内容
		 */
		//选择name标签下的文本内容，返回Text对象
		xpath = "//name/text()";
		xpath = "//contact/name[text()='张三']";//选择姓名为张三的name标签
		
		
		List<Node> list = doc.selectNodes(xpath);
		for (Node node : list) {
			System.out.println(node);
		}
	}

}
