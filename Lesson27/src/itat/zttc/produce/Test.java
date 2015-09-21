package itat.zttc.produce;

public class Test {
	public static void main(String[] args) {
		//建立仓库
		Disk d = new Disk();
		//建立生产者
		Cooker co = new Cooker("刘大厨", d);
		//建立消费者
		Custom cu = new Custom("李小胖", d);
		
		Thread cot = new Thread(co);
		Thread cut = new Thread(cu);
		//cut消费者为后台线程，只要所有的线程结束，这个线程就自动结束
		cut.setDaemon(true);
		//启动任务；
		cot.start();
		cut.start();
	}
}
