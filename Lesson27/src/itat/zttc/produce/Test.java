package itat.zttc.produce;

public class Test {
	public static void main(String[] args) {
		//�����ֿ�
		Disk d = new Disk();
		//����������
		Cooker co = new Cooker("�����", d);
		//����������
		Custom cu = new Custom("��С��", d);
		
		Thread cot = new Thread(co);
		Thread cut = new Thread(cu);
		//cut������Ϊ��̨�̣߳�ֻҪ���е��߳̽���������߳̾��Զ�����
		cut.setDaemon(true);
		//��������
		cot.start();
		cut.start();
	}
}
