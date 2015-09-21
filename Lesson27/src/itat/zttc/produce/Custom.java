package itat.zttc.produce;

public class Custom implements Runnable{
	private String name;//����������
	private Disk d;//�ֿ�
	/*public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Disk getD() {
		return d;
	}
	public void setD(Disk d) {
		this.d = d;
	}*/
	//���캯��
	public Custom(String name, Disk d) {
		this.name = name;
		this.d = d;
	}
	//������������Ʒ
	public void eat() {
		//����ͬ�������������ʹ���ܹ�ͬһ��Կ�ף����߲�������
		synchronized (d) {
			//����ֿⲻΪ�ս�������
			if(!d.isEmpty()) {
				//��òֿ����Ʒ
				String food = d.getFood();
				System.out.println(name+"��������"+food);
				//���òֿ�Ϊ��
				d.setEmpty(true);
				//����һ��������ȣ���ȥ������������
				d.notify();
				try {
					//˯��2s
					Thread.sleep(2000);
					//�������߹���
					d.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				//����ֿ�Ϊ�ս��еȴ���
				try {
					d.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void run() {
		while(true) {
			eat();
		}
	}
}
