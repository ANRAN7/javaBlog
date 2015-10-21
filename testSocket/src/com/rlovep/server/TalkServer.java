package com.rlovep.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;


public class TalkServer {
	//保存聊天线程：
	private Map<String, ServerThread> map = new HashMap<String, ServerThread>();
	//单聊标志位：
		private boolean sin=false;
	public static void main(String[] args) {
		//启动服务器
		new TalkServer().start();
	}
	/**
	 * 
	* @Title: start 
	* @Description: 为每一个客户端创建一个独立线程 
	* @return:void   
	* @throws 
	* @author peace w_peace@163.com
	 */
	public void start(){
		//服务器servesocket
		ServerSocket serverSocket=null;
		//服务器socket
		Socket socket=null;
		try {		
			//服务器创建
			serverSocket=new ServerSocket(8888);
			while(true){
				//聊天线程创建
			socket=serverSocket.accept();
			ServerThread st=new ServerThread(socket);
			new Thread(st).start();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			//释放资源
			try {
				if(serverSocket!=null){
					serverSocket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	* @ClassName: ServerThread
	* @Description: 聊天线程
	* @author peace w_peace@163.com 
	* @date 15 Oct 2015 8:31:44 pm
	*
	 */
	private class ServerThread implements Runnable{
		//相应的socket
		private Socket socket=null;
		private BufferedReader in=null;
		private PrintWriter out=null;
		//每个聊天的名字
		private  String name=null;
		//聊天头
		private String meshead=null;
		//停止线程标志位
		private boolean flag=true;
		/**
		 * 
		* <p>Title:线程构造器 </p>
		* <p>Description:用来创建线程，以及初始化，和将线程加入map </p>
		* @param socket
		* @throws IOException
		 */
		public ServerThread(Socket socket) throws IOException {
			//获得socket
			this.socket=socket;
			//获得输出和输入
			out=new PrintWriter(socket.getOutputStream(),true);
			//获得用户名字
			in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			name=in.readLine();
			System.out.println(name+"上线了");
			//制作聊天头
			meshead=name+"["+socket.getInetAddress().getHostAddress()+":"+socket.getPort()+"]";
			//将线程加入map：key为名字；value为线程
			map.put(name, this);
			//提醒所有用户
			send(meshead+"上线了");
		}
		/**
		 * 
		* @Title: send 
		* @Description: 发送消息
		* @param msg 
		* @return:void   
		* @throws 
		* @author peace w_peace@163.com
		 */
		public void send(String msg){
			for(ServerThread thread:map.values()){
				thread.out.println(msg);
			}
		}
		/**
		 * 
		* @Title: Receiver 
		* @Description: 接收消息，并转发
		* @throws IOException 
		* @return:void   
		* @author peace w_peace@163.com
		 */
		
		public void Receiver() throws IOException{
			String str=null;
			ServerThread qq=null;
			//接收消息
			while((str=in.readLine())!=null){
				//如果消息为quit则退出
				if("quit".equalsIgnoreCase(str)){
					stop();
					//给客户端发送关闭链接命令
					out.println("disconnect");
					break;
				}
				if(sin==false)
				{
				   if(str.equals("single")){
					if((str=in.readLine())!=null){
						 qq=map.get(str);
						if(qq!=null)
							sin=true;
					}
				    }
				   else
				   {
					 //转发消息
						send(meshead+str);	
				   }
				
				}
				else{
					if(str.equals("q")){
						sin=false;
						qq.out.println(name+"对话结束");
					}
					else
					   qq.out.println(name+"对你说："+str);
					}
			
			}
		}
		/**
		 * 
		* @Title: stop 
		* @Description: 停止函数
		* @return:void   
		* @throws 
		* @author peace w_peace@163.com
		 */
		public void stop(){
			flag=false;
			//下线移去码map;
			map.remove(name);
			send(meshead+"已经下线了");
		}
		/**
		 * run方法
		 */
		@Override
		public void run() {
		
			try {
				while(flag){
				    //不停接收消息
					Receiver();
				}
			}
			catch(SocketException e){
				stop();//客户端直接关闭引发的错误；
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				//释放资源
				try {
					if(socket!=null){
						socket.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}
