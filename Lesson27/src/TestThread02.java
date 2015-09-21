
public class TestThread02 {
	public static void main(String[] args) {
		new TestThread02().run();
	}
	
	public void run() {
		//共享mt中的成员变量
		MyThread mt = new MyThread();
		new Thread(mt, "mt1").start();
		new Thread(mt, "mt2").start();
//		for(int i=0;i<10;i++) {
//			System.out.println("main:"+i);
//		}
	}
	
	private class MyThread implements Runnable {
		private int index;
		@Override
		public void run() {
			for(;index<100;index++) {
				System.out.println(Thread.currentThread().getName()+":"+index);
			}
		}
	}
}
