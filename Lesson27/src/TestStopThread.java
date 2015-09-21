
public class TestStopThread {
	public static void main(String[] args) {
		new TestStopThread().run();
	}

	public void run() {
		MyThread mt = new MyThread();
		Thread tmt = new Thread(mt);
		tmt.start();
		for(int i=0;i<100;i++) {
			System.out.println("main:"+i);
			if(i==86) {
				mt.stopThread();
			}
		}
	}
	
	class MyThread implements Runnable {
		int index = 0;
		private boolean flag = true;
		@Override
		public void run() {
			for(index=0;index<2000;index++) {
				if(!flag) break;
				System.out.println("index:"+index);
			}
		}
		
		public void stopThread() {
			//在这个位置释放资源
			flag = false;
		}
	}
}
