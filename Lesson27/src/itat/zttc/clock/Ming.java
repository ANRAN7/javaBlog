package itat.zttc.clock;

public class Ming implements Runnable{
	private Clock c;

	public Clock getC() {
		return c;
	}

	public void setC(Clock c) {
		this.c = c;
	}

	public Ming(Clock c) {
		super();
		this.c = c;
	}
	
	@Override
	public void run() {
		while(true) {
			operator();
		}
	}
	
	public void operator() {
		synchronized(c) {
			try {
				if(c.isWakeup()) {
					System.out.println("小明说:知道了！知道了！");
					c.setWakeup(false);
					Thread.sleep(3000);
					c.notify();
					c.wait();
				} else {
					c.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
