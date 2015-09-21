package itat.zttc.reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestReg04 {
	public static void main(String[] args) {
		//可以先将一个正则表达式编译成为一个Pattern对象，可以提高效率
		Pattern p = Pattern.compile("\\d{4}");
		//通过Pattern可以获取一个Matcher对象，通过Matcher对象可以获取大量的有用信息
		Matcher m = p.matcher("23338888-3233-1111");
		//判断是否匹配
		System.out.println(m.matches());
		//将查找的指针重置
		m.reset();
		//以下或报错，必须在find之后才能执行group
		//System.out.println(m.group());
		//find指的是顺序匹配相应的字符串
//		System.out.println(m.find());
		//每进行一次find，就可以将字符串通过group获取，一定要执行了find之后才能执行group
//		System.out.println(m.group());
//		System.out.println(m.find());
//		System.out.println(m.group());
//		System.out.println(m.find());
//		System.out.println(m.group());
		while(m.find()) {
			//m.start和m.end可以获取匹配字符串的开始位置和结束位置
			System.out.println(m.group()+"["+m.start()+","+m.end()+"]");
		}
	}
}
