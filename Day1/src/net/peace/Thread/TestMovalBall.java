package net.peace.Thread;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * 
* @ClassName: TestMovalBall
* @Description: 多线程初步介绍，使用swing进行演示多线程；
* @author peace w_peace@163.com 
* @date Sep 19, 2015 10:34:46 AM
*
 */
public class TestMovalBall extends JFrame {
	//创建画笔，画笔实现了runnable接口
	private Mypanel mp;
	//启动移动按键
	private  JButton jb;
	//停止移动按键
	private JButton jb1;
	//启动线程的对象
	private Thread th;
	/**
	 * 
	* <p>Title:构造器 </p>
	* <p>Description:创建窗口，和运行的球，以及注册按键监听事件，和初始化 </p>
	 */
	public TestMovalBall() {
		//设置窗口大小
		this.setSize(600,500);
		//窗口位置
		this.setLocation(200,100);
		//关闭方式
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//开始按键的初始化
		jb=new JButton("移动小球");
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-genrivaerated method stub
				//使线程开始运行
				th.start();
				
			}
		});
		//将开始按键放在顶部
		this.add(jb,BorderLayout.NORTH);
		//停止按键的初始化
		jb1=new JButton("停止小球");
		//停止按键注册监听事件
		jb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//停止移动，关闭线程，释放资源
				mp.stopMove();
			}
		});
		//放在底部
		this.add(jb1,BorderLayout.SOUTH);
	    //画出球体
		mp=new Mypanel(40, 50);
		//加入窗口
		this.add(mp);
		//使窗口可见
		this.setVisible(true);
	    //初始化线程；
		th=new Thread(mp);
	}
	/**
	 * 
	* @ClassName: Mypanel
	* @Description: 画出球体，以及线程的运行函数和停止函数；
	* @author peace w_peace@163.com 
	* @date Sep 19, 2015 3:08:07 PM
	*
	 */
	class Mypanel extends JPanel implements Runnable{
		
		private int x,y;
		//停止标志
		private boolean flag=true;
		public Mypanel(int x,int y){
			this.x=x;
			this.y=y;
			
		}
		//画笔，画出红色的球体
		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			g.setColor(Color.red);
		    g.fillOval(x, y, 50, 50);
		
		}
		//实现接口函数
		@Override
		public void run() {
			for(int i=0;i<50;i++){
				if(!flag){//falg==false时停止执行线程
					flag=true;
					break;
				}
				//使球体移动
				mp.x+=3;
				mp.repaint();
				try {
					//让线程休眠50ms
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		//停止线程
		public void stopMove(){
         //TODO 此去可以加释放资源的代码
			flag=false;
		}
	}
	
	public static void main(String[] args) {
		//启动界面；
		new TestMovalBall();
		//直接使用Thread类
		//建立线程类对象 并给予线程名
		//此去的两个线程是独立的，不共享资源
		Reflash f=new Reflash("peace1");
		Reflash f1=new Reflash("peace2");
		//使线程类开始运行，使用start方法，千万记住不是run
		f.start();
		f1.start();
		//使用runnable建立线程
		//建立Thread类对象，传入Runnable对象；并给予线程名
		Runal run=new Runal();
		//此去的两个线程是不独立的，他们互相使用同一资源，因为他们的任务对象是同一个；
		Thread thread=new Thread(run,"peace3");
		thread.start();
		  thread=new Thread(run,"peace4");
		  thread.start();
		
		for(int i=1;i<10;i++){
			//获得的main方法的线程名字为main，当是未给予名字的线程时，获得的名字为Thread-01依次类推
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
	}

}
/*
 *创建一个简单的线程类； 
 */
class Reflash extends Thread{
	private int index;
	//命名构造器
	public Reflash(String name) {
		// TODO Auto-generated constructor stub
		super(name);
	}
	@Override
	//建立run方法
	public void run() {
		for(;index<10;index++){
			System.out.println(this.getName()+":"+index);
		}
		super.run();
	}
}
//一般多线程百分之九十九都用这种方法；
class Runal implements Runnable{
	private int index;
   // 实现Runnable的run方法
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(;index<10;index++){
			//获取当前线程的名字
			System.out.println(Thread.currentThread().getName()+":"+index);
		}
	}
	
}