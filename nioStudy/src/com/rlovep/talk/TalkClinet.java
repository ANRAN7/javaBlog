package com.rlovep.talk;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;


public class TalkClinet {
	  //通道选择器
			private Selector selector;
			/**
			 * 
			* @Title: initServer 
			* @Description:客户端初始化，获得client通道 注册到通道选择器 
			* @author peace w_peace12@163.com
			 */
			public void initServer(String ip,int port) throws IOException{
				// 获得一个Socket通道 
				SocketChannel client = SocketChannel.open();
				// 设置通道为非阻塞  
				client.configureBlocking(false);
				 // 获得一个通道选择器  
				this.selector=Selector.open();
				// 客户端连接服务器需要调用channel.finishConnect();才能完成连接  
				client.connect(new InetSocketAddress(ip, port));
				 //将通道选择器该通道绑定，并为该通道注册SelectionKey.OP_CONNECT事件。  
				client.register(selector, SelectionKey.OP_CONNECT);
			}
			/**
			 * 
			* @Title: AllServer 
			* @Description: 采用循环访问select上是否有需要处理时间，如果有则进行处理，
			*  包括：连接完成和建立连接通道
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
				    	//connect
				    	if(key.isConnectable()){
				    		 SocketChannel channel = (SocketChannel) key  
			                            .channel();  
			                    // 如果正在连接，则完成连接  
			                    if(channel.isConnectionPending()){  
			                        channel.finishConnect();  
			                          
			                    }  
			                    // 设置成非阻塞  
			                    channel.configureBlocking(false);  
			                    //在这里可以给服务端发送信息哦  
			                    channel.write(ByteBuffer.wrap(new String("我是peace，我要上线").getBytes()));  
			                    //在和服务端连接成功之后，为了可以接收到服务端的信息，需要给通道设置读的权限。  
			                    channel.register(this.selector, SelectionKey.OP_READ);  
			                      
				    	}
				    	//获得read事件
				    	if(key.isReadable())
				    	{
				    		//获得连接通道
				    		SocketChannel channel = (SocketChannel)key.channel();
				    		//创建缓存区
				    		ByteBuffer buffer=ByteBuffer.allocate(128);
				    		//获得消息
				    		channel.read(buffer);
				    		//去掉头尾空字符
				    		String msg=new String(buffer.array()).trim();
				    		//显示消息
				    		System.out.println("客户端收到消息为："+msg);
				    		/*//发送收到消息
				    		channel.write(buffer);*/
				    	}
				    	//删除已处理事件
				    	iterator.remove();
				    }
				}
			}
			/**
			 * 启动客户端程序：
			 */
			public static void main(String[] args) {
				try {
					TalkClinet client=new TalkClinet();
					client.initServer("192.168.1.101", 6666);
					client.AllServer();
				} catch (IOException e) {
					System.out.println("错误");
				}
			}
}
