package itat.zttc.reg;

public class TestReg02 {
	public static void main(String[] args) {
		//*��ʾ�������ַ�(0������)
		System.out.println("aaaa".matches("a*"));
		//Ϊfalse��Ϊ*��ʾ�Ķ��
		System.out.println("abcd".matches("a*"));
		System.out.println("abcdlskdjff".matches("a[a-z]*"));
		//Ϊtrue
		System.out.println("".matches("a*"));
		//+��ʾ1�����߶��
		System.out.println("aa".matches("a+"));
		System.out.println("a".matches("a+"));
		//false��+��ʾһ�����߶��
		System.out.println("".matches("a+"));
		//?��ʾ0������1��
		System.out.println("a".matches("a?"));
		System.out.println("aa".matches("a?"));
		System.out.println("".matches("a?"));
		//{n,m}��ʾ���ٳ���n��������m��
		System.out.println("2kk3-12-22".matches("\\d{4}-\\d{1,2}-\\d{1,2}"));
		//��һ��:���һ���ַ����Ƿ�������
		System.out.println("2334.99".matches("\\d+\\.?\\d+"));
		System.out.println("38".matches("\\d{1}||[12]{1}\\d{1}|3{1}[0-5]{1}"));
		//�ڶ���:���һ���ַ����Ƿ���һ���绰����0870-2233445-01
		System.out.println("0870-2233445-01".matches("\\d{3,4}-\\d{7}-\\d{2,5}"));
		System.out.println("0870-2233445".matches("\\d{3,4}-\\d{7}-*\\d*"));
		//������:ƥ��һ��IP��ַ 111.22.33.22
		System.out.println("192.168.0.33".matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}"));
		System.out.println("92".matches("[1-2]?\\d{0,2}"));
		System.out.println("192.234.22.33".matches("[1-2]?\\d{0,2}\\.[1-2]?\\d{0,2}\\.[1-2]?\\d{0,2}\\.[1-2]?\\d{0,2}"));
		//���ģ�ƥ��һ�����֤��
		System.out.println("53210119761209005X".matches("\\d{15}||\\d{18}||\\d{17}[X]"));
		//ƥ��һ�������ʼ�
		System.out.println("ynkonghao@gmail-pun.com.ddfdfdf".matches("[\\w-\\.]*\\w+@[\\w\\.-]*\\w+\\.\\w{2,6}"));
	}
}
