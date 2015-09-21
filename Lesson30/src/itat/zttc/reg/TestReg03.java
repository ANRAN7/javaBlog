package itat.zttc.reg;

public class TestReg03 {
	public static void main(String[] args) {
		System.out.println("helloworld".matches("^h\\w+"));
		System.out.println("h".matches("^h\\w+"));
		//^不在[]中就表示以xx为开头，特别注意:[^abc]
		System.out.println("1you".matches("^\\d\\w+"));
		//$表示以xx为结尾
		System.out.println("1you1".matches("\\w*\\d$"));
		System.out.println("1you".matches("\\w*\\d$"));
	}
}
