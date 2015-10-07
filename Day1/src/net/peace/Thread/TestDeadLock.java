package net.peace.Thread;
/**
 * 
* @ClassName: TestDeadLock
* @Description: 测试死锁
* @author peace w_peace@163.com 
* @date Sep 19, 2015 4:24:06 PM
*
 */
public class TestDeadLock {

	public static void main(String[] args) {
		new TestDeadLock().run();
	}
	public void run() {
		//建立两个任务，每个任务都只能在另外一个任务执行完才能继续执行；
		MyThread mt = new MyThread();
		new Thread(mt,"张三").start();
		new Thread(mt,"李四").start();
	}
	//产生死锁的类
	class MyThread implements Runnable {
		//第一个钥匙
		private Object k1 = new Object();
		//第二个钥匙
		private Object k2 = new Object();
		//标示位
		private boolean flag = true;
		@Override
		public void run() {
			if(flag) {
				flag = false;
				//死锁产生原因，有嵌套。当第一个线程进来的时候，将k1钥匙拿在手里，睡眠。并等待k2钥匙
				synchronized (k1) {
					System.out.println(Thread.currentThread().getName()+":k1");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					//此去等待由于k2被线程2拿着，多以此处一直等待
					synchronized (k2) {
						System.out.println(Thread.currentThread().getName()+":k2");
					}
				}
			} else {
				//当第二个线程进来的时候，将k2钥匙拿在手里，睡眠。并等待k1钥匙
				flag = true;
				synchronized (k2) {
					System.out.println(Thread.currentThread().getName()+":k2");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					//此去等待由于k1被线程1拿着，多以此处一直等待 产生死锁
					synchronized (k1) {
						System.out.println(Thread.currentThread().getName()+":k1");
					}
				}
			}
		}
	}

}
