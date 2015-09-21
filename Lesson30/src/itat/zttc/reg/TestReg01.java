package itat.zttc.reg;

public class TestReg01 {
	public static void main(String[] args) {
		//.表示任意字符
		System.out.println("a".matches("."));
		System.out.println("aa".matches(".a"));
		System.out.println("\\d");
		//\\d表示是否是数字
		System.out.println("123".matches("\\d\\d\\d"));
		System.out.println("1d32e".matches("\\d\\D\\d\\d\\D"));
		//\\s表示是否是空白字符
		System.out.println("1  2		d".matches("\\d\\s\\s\\d\\s\\sd"));
		//\\w表示常用输入字符:a-z,A-Z,0-9,_
		System.out.println("aa b1 22".matches("\\w\\w\\s\\w\\w\\s\\w\\w"));
		//[abcd]表示是否是abcd这是个字符中的某一个
		System.out.println("a".matches("[abcd]"));
		//[a-z]表示是否是a-z之间的字符
		System.out.println("D".matches("[a-zA-D]"));
		//[^a-z]表示不在a-z之间
		System.out.println("h".matches("[^a-z]"));
		//也支持&&和||
		System.out.println("a".matches("[a-z&&[def]]"));
		System.out.println("H".matches("[a-z]||[A-D]"));
	}
}
