package gz.itcast.b_xpath;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * xpath案例： 模拟用户登录效果
 * @author APPle
 *
 */
public class Demo3 {

	public static void main(String[] args)throws Exception{
		//1.获取用户输入的用户名和密码
		BufferedReader br = 
				new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("请输入用户名：");
		String name = br.readLine();
		
		System.out.println("请输入密码：");
		String password = br.readLine();
		
		//2.到“数据库”中查询是否有对应的用户
		//对应的用户：  在user.xml文件中找到一个
		   //name属性值为‘用户输入’，且password属性值为‘用户输入’的user标签
		Document doc = new SAXReader().read(new File("./src/user.xml"));
		Element userElem = (Element)doc.selectSingleNode("//user[@name='" +name +"' and @password='"+password+"']");
		
		if(userElem!=null){
			//登录成功
			System.out.println("登录成功");
		}else{
			//登录失败
			System.out.println("登录失败");
		}
	}

}
