package itat.zttc.reg;

public class TestReg03 {
	public static void main(String[] args) {
		System.out.println("helloworld".matches("^h\\w+"));
		System.out.println("h".matches("^h\\w+"));
		//^����[]�оͱ�ʾ��xxΪ��ͷ���ر�ע��:[^abc]
		System.out.println("1you".matches("^\\d\\w+"));
		//$��ʾ��xxΪ��β
		System.out.println("1you1".matches("\\w*\\d$"));
		System.out.println("1you".matches("\\w*\\d$"));
	}
}
