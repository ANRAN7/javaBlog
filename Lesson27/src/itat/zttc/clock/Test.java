package itat.zttc.clock;

public class Test {
	public static void main(String[] args) {
		Clock c = new Clock();
		Ming m = new Ming(c);
		
		Thread ct = new Thread(c);
		Thread mt = new Thread(m);
		mt.setDaemon(true);
		ct.start();
		mt.start();
	}
}
