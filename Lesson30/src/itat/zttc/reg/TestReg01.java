package itat.zttc.reg;

public class TestReg01 {
	public static void main(String[] args) {
		//.��ʾ�����ַ�
		System.out.println("a".matches("."));
		System.out.println("aa".matches(".a"));
		System.out.println("\\d");
		//\\d��ʾ�Ƿ�������
		System.out.println("123".matches("\\d\\d\\d"));
		System.out.println("1d32e".matches("\\d\\D\\d\\d\\D"));
		//\\s��ʾ�Ƿ��ǿհ��ַ�
		System.out.println("1  2		d".matches("\\d\\s\\s\\d\\s\\sd"));
		//\\w��ʾ���������ַ�:a-z,A-Z,0-9,_
		System.out.println("aa b1 22".matches("\\w\\w\\s\\w\\w\\s\\w\\w"));
		//[abcd]��ʾ�Ƿ���abcd���Ǹ��ַ��е�ĳһ��
		System.out.println("a".matches("[abcd]"));
		//[a-z]��ʾ�Ƿ���a-z֮����ַ�
		System.out.println("D".matches("[a-zA-D]"));
		//[^a-z]��ʾ����a-z֮��
		System.out.println("h".matches("[^a-z]"));
		//Ҳ֧��&&��||
		System.out.println("a".matches("[a-z&&[def]]"));
		System.out.println("H".matches("[a-z]||[A-D]"));
	}
}
