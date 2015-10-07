package gz.itcast.c_sax;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * 第一个SAX读取xml文件程序
 * @author APPle
 *
 */
public class Demo1 {

	
	public static void main(String[] args) throws Exception{
		//1.创建SAXParser对象
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		
		//2.调用parse方法
		/**
		 * 参数一： xml文档
		 * 参数二： DefaultHandler的子类
		 */
		parser.parse(new File("./src/contact.xml"), new MyDefaultHandler());
	}

}
