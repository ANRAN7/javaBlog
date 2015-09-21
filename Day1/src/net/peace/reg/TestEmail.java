package net.peace.reg;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//这个类的主要功能，读取指定网页的邮箱账号和链接；
public class TestEmail {
	public static void main(String[] args) {
		//System.out.println(new TestFile("./01.htm"));
		//打开指定网页
/*		TestFile tF=new TestFile("./01.htm");
		//读取邮箱账号并保存为mail.txt
	List<String> ss=getEmail(tF.toString());
	PrintWriter pw=null;
	try {
		pw=new PrintWriter(new BufferedWriter(new FileWriter("mail.txt")));
		for(String line:ss){
			pw.println(line);
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		if(pw!=null){
			pw.close();
		}
	}
	//读取链接，并保存为link.txt
	ss=getLink(tF.toString());
	try {
		pw=new PrintWriter(new BufferedWriter(new FileWriter("link.txt")));
		for(String line:ss){
			pw.println(line);
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		if(pw!=null){
			pw.close();
		}
	}*/
		String str="<a class=\"digu\" href=\"javascript:(function(){window.open('http://www.w3school.com.cn/')\">Visit W3School</a>"
				+ "<a class=\"digu\" href=\"http://www.w3school.com.cn/\">Visit W3School</a>";
		List<String> es = getLink(str);
		for(String e:es) {
			System.out.println(e);
		}
	
	}
	/*
	*********************************************************************************************************
	*                                        List<String> getEmail(String str)
	*
	* Description ： 这个实现了在字符串中获取邮箱账号
	*
	* Arguments   : 字符串
	*
	* Returns     : 匹配的邮箱账号列表：List<String>
	*
	* Note(s)     : 使用了正则表达式"[a-zA-Z][\\w\\.-]*\\w+@[\\w\\.-]*\\w+\\.\\w{2,5}"：以字母开头，@前面必须为词字符，点后面必须在2个词字符以上三个词字符一下
	* 
	* Author      ：peace 
	*********************************************************************************************************
	*/
	public static List<String> getEmail(String str) {
		List<String> es = new ArrayList<String>();
		Pattern p = Pattern.compile("[a-zA-Z][\\w\\.-]*\\w+@[\\w\\.-]*\\w+\\.\\w{2,5}");//[
		Matcher m = p.matcher(str);
		while(m.find()) {
			es.add(m.group());
		}
		return es;
	}
	/*
	*********************************************************************************************************
	*                                        List<String> getLink(String str)
	*
	* Description ： 这个实现了在字符串中获取网页链接
	*
	* Arguments   : 字符串
	*
	* Returns     : 匹配的链接列表：List<String>
	*
	* Note(s)     : 使用了正则表达式"<a.*?\\s+href=['\"]([^\"'>]*?)['\"].*?>(.*?)</a>"：以<a开头，以</a>结尾。使用分组([^\"'>]*?)获得链接网址；
	* 
	* Author      ：peace 
	*********************************************************************************************************
	*/
	public static List<String> getLink(String str) {
		List<String> es = new ArrayList<String>();
		Pattern p = Pattern.compile("<a.*?\\s+href=['\"]([^\"'>]*?)['\"].*?>(.*?)</a>");//"<a href="?'?http: // [a-zA-Z0-9]+/.[a-zA-Z0-9]+/.[a-zA-Z]+/?[/.?[/S|/s]]+[a>]$"; 
		Matcher m = p.matcher(str);
		while(m.find()) {
			es.add(m.group(1));
		}
		return es;
	}
}
