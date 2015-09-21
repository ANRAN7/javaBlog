package itat.zttc.clock;

public class Clock implements Runnable{
	private boolean wakeup;
	
	public Clock() {
		wakeup = false;
	}

	public boolean isWakeup() {
		return wakeup;
	}

	public void setWakeup(boolean wakeup) {
		this.wakeup = wakeup;
	}
	
	@Override
	public void run() {
		for(int i=0;i<10;i++) {
			wake();
		}
	}
	
	public synchronized void wake() {
		try {
			if(!wakeup) {
				for(int i=0;i<3;i++) {
					System.out.println("Æð´²ÁË£¡");
				}
				wakeup = true;
				this.notify();
				this.wait();
			} else {
				this.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
