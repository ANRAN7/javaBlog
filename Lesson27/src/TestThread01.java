
public class TestThread01 {
	public static void main(String[] args) {
		new TestThread01().run();
	}
	
	public void run() {
		//每个成员变量都有不同的存储空间
		MyThread mt1 = new MyThread("mt1");
		mt1.start();
		MyThread mt2 = new MyThread("mt2");
		mt2.start();
//		for(int i=0;i<10;i++) {
//			System.out.println("main:"+i);
//		}
	}
	
	class MyThread extends Thread {
		public MyThread(String name) {
			super(name);
		}
		private int index = 0;
		@Override
		public void run() {
			for(;index<100;index++) {
				System.out.println(Thread.currentThread().getName()+":"+index);
			}
		}
	}
}
