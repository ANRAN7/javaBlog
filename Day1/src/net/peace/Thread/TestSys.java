package net.peace.Thread;

public class TestSys {
	private  int times = 0;//取钱的次数
	public static void main(String[] args) {
		new TestSys().run();
	}

	public void run() {
		//创建Family对象
		Family f = new Family();
		//创建第一个线程
		Thread f1=new Thread(f,"丈夫");
		//创建第二个线程 使用相同的资源
		Thread f2=new Thread(f,"妻子");
		f1.start();
		f2.start();
		while(true){
			if(times==1){
				f1.stop();
			}
			if(times>=2){
				f.show();
				f2.stop();
				break;
			}
		}
		
	}
	/**
	 * 
	* @ClassName: Family
	* @Description: 该类实现了线程的同步，同步就是线程必须等待一个线程执行完同步的代码，才能运行；
	* @author peace w_peace@163.com 
	* @date Sep 19, 2015 3:15:43 PM
	*
	 */
	 class Family implements Runnable {
		private int saveMoney;//银行存储的钱
		private int getMoney;//到银行取钱
		private int curMoney;//家里面剩余的钱
		
		private boolean flag=true;
		//可以直接创建一个对象来作为同步锁的钥匙
		Object key=new Object();
		
		
		public Family() {
			//初始化
			saveMoney = 5000;
			getMoney = 2000;
			curMoney = 0;
		}
		@Override
		//实现接口方法
		public void run() {
			
			getMoney();
			System.out.println("times"+times);
			/*while(true) 
			{
				if(!flag)break;
			}*/
		}
		
		
		//同步方法，默认使用this作为钥匙
		public void getMoney() {
//			//也可以直接使用this来作为钥匙，任何一个对象都可以做钥匙
			synchronized(key){
				System.out.println(Thread.currentThread().getName()+"取了:"+getMoney+"元"+times+"次");
				//家里面的钱算法
				curMoney+=getMoney;
				//银行剩余的钱
				int temp = saveMoney - getMoney;
				times++; 
				saveMoney = temp;
				
				/*try {
					//休息1s
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}*/
				/*if(times==1) {
					//展示最后的钱数
					show();
					
				}*/
							
		}
		}
		
		/*
		 * 使用同步方法
		 * public synchronized void getMoney() {
			
				System.out.println(Thread.currentThread().getName()+"取了:"+getMoney+"元");
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
		 */
		
		public void show() {
			//打印出钱数
			System.out.println("银行还有:"+saveMoney+",家里中有:"+curMoney);
			flag=false;
		}
	}
}