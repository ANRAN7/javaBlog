package com.rlovep.talk;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class TalkServer {
	    //通道选择器
		private Selector selector;
		private Map<String, SocketChannel> map=new HashMap<>();
		//常量定义：
		private static final String login="login";
		private static final String single="single";
		/**
		 * 
		* @Title: initServer 
		* @Description:服务端初始化，获得serverSocker通道 注册到通道选择器 
		* @author peace w_peace12@163.com
		 */
		public void initServer(int port) throws IOException{
			//获得一个ServerSocket通道
			ServerSocketChannel server = ServerSocketChannel.open();
			//设置为通道非组塞
			server.configureBlocking(false);
			//将服务器监听port端口
			server.socket().bind(new InetSocketAddress(port));
			//获得通道选择器
			this.selector=Selector.open();
			//将服务通道绑定到通道选择器，并为该通道注册SelectionKey.OP_ACCEPT事件，当accept事件到达时，selector.select()会返回事件
			server.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("服务端初始化成功");
		}
		/**
		 * 
		* @Title: AllServer 
		* @Description: 采用循环访问select上是否有需要处理时间，如果有则进行处理，
		*  包括：server端accept和建立连接客户端的socket通道
		* @author peace w_peace12@163.com
		 */
		public void AllServer()throws IOException{
			while(true){
				//阻塞调用线程，直到有某个Channel的某个感兴趣的事件准备好了
				int i=selector.select();
				if(i==0)continue;
				//获得selector中存在的事件
			    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			    while(iterator.hasNext())
			    {
			    	//获得该键值
			    	SelectionKey key=iterator.next();
			    	//accept
			    	if(key.isAcceptable()){
			    		//获得产生事件的通道
			    		ServerSocketChannel server = (ServerSocketChannel)key.channel();
			    		//获得和客户端连接的通道
			    	    SocketChannel channel = server.accept(); 
			    	    //设置成非阻塞
			    	    channel.configureBlocking(false);
			    	    //向客户端发送一条连接成功消息
			    	    channel.write(ByteBuffer.wrap(new String("连接成功").getBytes()));
			    	    //将客户端连接通道绑定到选择器，并注册read事件
			    	    channel.register(selector, SelectionKey.OP_READ);
			    	}
			    	//获得read事件
			    	if(key.isReadable())
			    	{
			    		//获得客户端连接通道
			    		SocketChannel channel = (SocketChannel)key.channel();
			    		//创建缓存区
			    		ByteBuffer buffer=ByteBuffer.allocate(128);
			    		//获得消息
			    		channel.read(buffer);
			    		//去掉头尾空字符
			    		String msg=new String(buffer.array()).trim();
 			    		String[] mStrings=msg.split("_");
 			    		//显示消息
			    		System.out.println("服务器接收到消息为："+msg);
			    		switch (mStrings[0]) {
						case login:
							map.put(mStrings[1], channel);
				    		//将消息发送回去
				    		channel.write(ByteBuffer.wrap(new String("登陆成功").getBytes()));
							break;
						case single:
							SocketChannel channel2 = map.get(mStrings[1]);
							channel2.write(ByteBuffer.wrap(mStrings[2].getBytes()));
							break; 
						default:
							break;
						}
			    		
			    		
			    	}
			    	//删除已处理事件
			    	iterator.remove();
			    }
			}
		}
		/**
		 * 启动服务端程序：
		 */
		public static void main(String[] args) {
			try {
				TalkServer servier=new TalkServer();
				servier.initServer(6666);
				servier.AllServer();
			} catch (IOException e) {
				System.out.println("错误");
			}
		     
			
			}
}
