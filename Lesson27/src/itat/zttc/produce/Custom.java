package itat.zttc.produce;

public class Custom implements Runnable{
	private String name;//消费者名字
	private Disk d;//仓库
	/*public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Disk getD() {
		return d;
	}
	public void setD(Disk d) {
		this.d = d;
	}*/
	//构造函数
	public Custom(String name, Disk d) {
		this.name = name;
		this.d = d;
	}
	//消费者消费商品
	public void eat() {
		//进行同步，与生产则会使用能够同一把钥匙，两者产生互斥
		synchronized (d) {
			//如果仓库不为空进行消费
			if(!d.isEmpty()) {
				//获得仓库的商品
				String food = d.getFood();
				System.out.println(name+"正在享受"+food);
				//设置仓库为空
				d.setEmpty(true);
				//唤起一次任务调度，此去唤醒生产者者
				d.notify();
				try {
					//睡眠2s
					Thread.sleep(2000);
					//将消费者挂起
					d.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				//如果仓库为空进行等待；
				try {
					d.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void run() {
		while(true) {
			eat();
		}
	}
}
