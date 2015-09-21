
public class TestBank {
	
	public static void main(String[] args) {
		new TestBank().run();
	}

	public void run() {
		Family f = new Family();
		new Thread(f,"�ɷ�").start();
		new Thread(f,"����").start();
		while(true){
			if(f.times>=2) {
				f.show();
				break;
			}
		}
	}
	class Family implements Runnable {
		private int saveMoney;
		private int getMoney;
		private int curMoney;
		private int times = 0;
		
		//����ֱ�Ӵ���һ����������Ϊͬ������Կ��
		Object key = new Object();
		
		
		public Family() {
			saveMoney = 5000;
			getMoney = 2000;
			curMoney = 0;
		}
		@Override
		public void run() {
			getMoney();
		}
		
		
		//ͬ��������Ĭ��ʹ��this��ΪԿ��
		public synchronized void getMoney() {
//			//Ҳ����ֱ��ʹ��this����ΪԿ�ף��κ�һ�����󶼿�����Կ��
//			synchronized(this) {
				System.out.println(Thread.currentThread().getName()+"ȡ��:"+getMoney+"Ԫ");
				curMoney+=getMoney;
				int temp = saveMoney - getMoney;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				saveMoney = temp;
				times++;
//			}
		}
		
		/*
		 * ʹ��ͬ��������ʵ�͵���
		 * public void getMoney() {
			synchronized(this) {
				System.out.println(Thread.currentThread().getName()+"ȡ��:"+getMoney+"Ԫ");
				curMoney+=getMoney;
				int temp = saveMoney - getMoney;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				saveMoney = temp;
				times++;
			}
		}
		 */
		
		public void show() {
			System.out.println("���л���:"+saveMoney+",��������:"+curMoney);
		}
	}
}
