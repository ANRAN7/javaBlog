package com.rlovep.clinet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
/**
 * 
* @ClassName: TalkClinet
* @Description: 聊天客户端：
* @author peace w_peace@163.com 
* @date 15 Oct 2015 7:21:22 pm
*
 */
public class TalkClinet {
	// 获得控制台输入
	private BufferedReader sysin = null;
	// socket
	private Socket s = null;
	private BufferedReader in = null;
	private PrintWriter out = null;
	// 线程停止flag
	private boolean flag = true;
    //单聊
	private final static String single="single";
	
	public static void main(String[] args) {
		// 启动：
		new TalkClinet().start();
	}
	/**
	 * 
	* @Title: start 
	* @Description: 主要作用：启动发送和接收任务  
	* @return:void   
	* @throws 
	* @author peace w_peace@163.com
	 */
  public void  start(){
	 try {
			// 获得系统输入：
			sysin = new BufferedReader(new InputStreamReader(System.in, "utf-8"));
			// 设置名字：
			System.out.println("请输入用户名：");
			String name = sysin.readLine();
			// 建立客户端socket
			s = new Socket("127.0.0.1", 8888);
			// 获得socket输出out
			out = new PrintWriter(s.getOutputStream(), true);
			out.println(name);
			// 获得socket输入 in
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			// 建立接收线程；原因获取系统输入会阻塞主线程
			new Thread(new ClinetThread()).start();
			// 发送消息：
			String str = null;
			while (flag && ((str = sysin.readLine()) != null)) {
				if(single.equalsIgnoreCase(str)){
					System.out.println("请输入想跟谁聊 ：");
					if(flag && ((str = sysin.readLine()) != null))
						{
						     out.println(single) ;
						}
				}
				out.println(str);
			}
		
	 } catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	 finally{
		 //关闭资源
		 if(sysin!=null){
			 try {
				sysin.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 if(s!=null){
			 try {
				s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	 }
  }
 private class ClinetThread implements Runnable{
	/**
	 * 
	* @Title: recive 
	* @Description: 接收消息，当消息为disconnect时退出，此去需要在按下一次回车用来终止系统输入； 
	* @return:void   
	* @throws 
	* @author peace w_peace@163.com
	 */
	 private void recive(){
		try {
			String str=in.readLine();
			if(str!=null){
			if("disconnect".equals(str)){
				stop();
				System.out.println("回车退出:");
			}
			else 
			{
			  System.out.println(str);
			  
			}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	 }
	 //线程停止方法
	 public void  stop()
	 {
		 flag=false;
	 }
	 //线程主要任务
	@Override
	public void run() {
		while(flag){
			recive();
		}
	} 
	 
 }
}
