package itat.zttc.reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestReg04 {
	public static void main(String[] args) {
		//�����Ƚ�һ��������ʽ�����Ϊһ��Pattern���󣬿������Ч��
		Pattern p = Pattern.compile("\\d{4}");
		//ͨ��Pattern���Ի�ȡһ��Matcher����ͨ��Matcher������Ի�ȡ������������Ϣ
		Matcher m = p.matcher("23338888-3233-1111");
		//�ж��Ƿ�ƥ��
		System.out.println(m.matches());
		//�����ҵ�ָ������
		m.reset();
		//���»򱨴�������find֮�����ִ��group
		//System.out.println(m.group());
		//findָ����˳��ƥ����Ӧ���ַ���
//		System.out.println(m.find());
		//ÿ����һ��find���Ϳ��Խ��ַ���ͨ��group��ȡ��һ��Ҫִ����find֮�����ִ��group
//		System.out.println(m.group());
//		System.out.println(m.find());
//		System.out.println(m.group());
//		System.out.println(m.find());
//		System.out.println(m.group());
		while(m.find()) {
			//m.start��m.end���Ի�ȡƥ���ַ����Ŀ�ʼλ�úͽ���λ��
			System.out.println(m.group()+"["+m.start()+","+m.end()+"]");
		}
	}
}
