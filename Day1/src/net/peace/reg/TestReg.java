package net.peace.reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestReg {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//边界处理
		//以什么开头：^;如以数字开头：^\\d;注意^在[]中表示取反的意思
		System.out.println("hell".matches("^h\\w+"));//以字母h开头；
		System.out.println("1hell".matches("^h\\w+"));//
		System.out.println("1hell".matches("^\\d\\w+"));//以数字开头
		//以什么结尾：$;如以数字结尾：\\d$
		System.out.println("1heeee".matches("^\\d\\w+e$"));//以数字开头，以字母e结尾
        //替换处理   使用了将最后4位替换；
		System.out.println("18816799124".replaceAll("\\d{4}$", "****"));
		//分组介绍  组0是整个表达式，组1是从左边开始的第一个最外层括号；组2一次类推
		final String str="430626199201027777,430626199309162222,430626198301021515";
		//组一是\\d{6}  组2是\\d{8}
		Pattern p=Pattern.compile("(\\d{6})(\\d{8})(\\d{4})");
		Matcher m=p.matcher(str);
		while(m.find()){
			System.out.println("出生地："+m.group(1)+"出生时间："+m.group(2));
		}
		//贪婪模式和非贪婪模式介绍：贪婪模式：按正常的就是  非贪婪模式：在表达式后面加上?或则+
		//贪婪模式如下：贪婪模式往网会一直匹配下去 ：输出为0:你好</td><td>我很好</td><td>你了
		String ss="<table><td>你好</td><td>我很好</td><td>你了</td></table>";
		p=Pattern.compile("<td>(.*)</td>");
		m=p.matcher(ss);
		int index=0;
		while(m.find()){
			System.out.println(index++ +":"+m.group(1));
		}
		//非贪婪模式如下：当匹配到一个时，非贪婪模式就匹配正常一次；此去匹配了三次；
				//String ss="<table><td>你好</td><td>我很好</td><td>你了</td></table>";
				p=Pattern.compile("<td>(.*?)</td>");
				m=p.matcher(ss);
				 index=0;
				while(m.find()){
					System.out.println(index++ +":"+m.group(1));
				}
		
	}

}
