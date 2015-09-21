package itat.zttc.reg;

public class TestReg05 {
	public static void main(String[] args) {
		String str = "234445sdsff3444sdss";
		//第一个参数是正则表达式，第二参数是要替换的值
		System.out.println(str.replaceAll("\\d", "*"));
		System.out.println(str.replaceAll("\\d+", "*"));
		System.out.println("13222331111".replaceAll("\\d{4}$", "****"));
	}
}
