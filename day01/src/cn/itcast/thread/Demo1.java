package cn.itcast.thread;
/*
线程：

多线程的好处： 多线程解决了在一个进程中同时可以执行多个任务代码的问题。


自定义线程的创建方式：

	
	方式一：继承Thread.
		1. 自定义一个类继承Thread类。
		2. 重写Thread的run方法，把自定义线程的任务代码定义在run方法上。
		3. 创建Thread子类的对象，并且调用start方法启动一个线程。

	
	方式二： 实现Runnable接口。
		1. 自定义一个类实现Runnable接口。
		2. 实现Runnable接口中的run方法，把自定义线程的任务代码定义在run方法上。
		3. 创建Runable实现类 的对象。
		4. 创建Thread对象，并且把Runnable实现类的对象作为参数传递。
		5. 调用Thread对象的start方法开启线程。
		
线程安全 问题的解决方案：

线程安全问题出现 的根本原因：
	1. 必须要存在两个或者两个以上的线程共享着一个资源。
	2. 操作共享资源的代码必须有两句或者两句以上。
	


	1. 同步代码块
		
		synchronized(锁){
			需要被同步的代码
		}
		
		
		
	2. 同步函数。
		
		修饰符 synchronized 返回值类型   函数名(形参列表..){
		
		}
		
		
	注意：
		1. 同步代码块的锁可以是任意的对象。 同步函数的锁是固定 的，非静态函数的锁对象是this对象。 静态函数的锁对象是class对象。
		2. 锁对象必须是多线程共享的对象，否则锁不住。
		3. 在同步代码块或者是同步函数中调用sleep方法是不会释放锁对象的，如果是调用了wait方法是会释放锁对象的。

 */

public class Demo1 extends Thread {
	
	
	public Demo1(String name){
		super(name);
	}
	
	@Override
	public void run() {
		for(int i = 0 ; i< 100 ; i++){
			System.out.println(Thread.currentThread().getName()+":"+ i);
		}
	}
	
	public static void main(String[] args) {

		Demo1 d = new Demo1("狗娃");
		d.start(); //开启线程。 线程一旦开启就会指定run方法中 的代码。
		
		
		for(int i = 0 ; i< 100 ; i++){
			System.out.println(Thread.currentThread().getName()+":"+ i);
		}
	}
	

}
