package itat.zttc.reg;

public class TestReg02 {
	public static void main(String[] args) {
		//*表示任意多个字符(0个或多个)
		System.out.println("aaaa".matches("a*"));
		//为false因为*表示的多个
		System.out.println("abcd".matches("a*"));
		System.out.println("abcdlskdjff".matches("a[a-z]*"));
		//为true
		System.out.println("".matches("a*"));
		//+表示1个或者多个
		System.out.println("aa".matches("a+"));
		System.out.println("a".matches("a+"));
		//false，+表示一个或者多个
		System.out.println("".matches("a+"));
		//?表示0个或者1个
		System.out.println("a".matches("a?"));
		System.out.println("aa".matches("a?"));
		System.out.println("".matches("a?"));
		//{n,m}表示至少出现n次最多出现m次
		System.out.println("2kk3-12-22".matches("\\d{4}-\\d{1,2}-\\d{1,2}"));
		//第一个:检测一个字符串是否是数字
		System.out.println("2334.99".matches("\\d+\\.?\\d+"));
		System.out.println("38".matches("\\d{1}||[12]{1}\\d{1}|3{1}[0-5]{1}"));
		//第二个:检测一个字符串是否是一个电话号码0870-2233445-01
		System.out.println("0870-2233445-01".matches("\\d{3,4}-\\d{7}-\\d{2,5}"));
		System.out.println("0870-2233445".matches("\\d{3,4}-\\d{7}-*\\d*"));
		//第三个:匹配一个IP地址 111.22.33.22
		System.out.println("192.168.0.33".matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}"));
		System.out.println("92".matches("[1-2]?\\d{0,2}"));
		System.out.println("192.234.22.33".matches("[1-2]?\\d{0,2}\\.[1-2]?\\d{0,2}\\.[1-2]?\\d{0,2}\\.[1-2]?\\d{0,2}"));
		//第四：匹配一个身份证号
		System.out.println("53210119761209005X".matches("\\d{15}||\\d{18}||\\d{17}[X]"));
		//匹配一个电子邮件
		System.out.println("ynkonghao@gmail-pun.com.ddfdfdf".matches("[\\w-\\.]*\\w+@[\\w\\.-]*\\w+\\.\\w{2,6}"));
	}
}
