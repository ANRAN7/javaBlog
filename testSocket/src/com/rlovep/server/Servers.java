package com.rlovep.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
* @ClassName: Servers
* @Description: socket服务器创建
* @author peace w_peace@163.com 
* @date 15 Oct 2015 3:21:09 pm
*
 */
public class Servers {
	public static void main(String[] args) {
		
		ServerSocket serverSocket=null;
		PrintWriter out=null;
		BufferedReader in=null;
		Socket s=null;
		int i=0;
		try {
			//服务器的创建:创建相应的serversocket
			serverSocket=new ServerSocket(8888);
			while(true){
				try {
					//进行监听，当客户端没数据发送过来时，停在这里；
					s=serverSocket.accept();
					//获得本机分配的当地socket端口
					System.out.println("当地端口："+s.getLocalPort());
					//获得本机分配的当地socket端口
					System.out.println("远程端口："+s.getPort());
					//获得socketAddress
					System.out.println("远程adress:"+s.getRemoteSocketAddress());
					System.out.println("本地adress:"+s.getLocalSocketAddress());
					//获得inetAddress
					System.out.println("远程inetAddress:"+s.getInetAddress());
					System.out.println("本地inetAddress:"+s.getLocalAddress());
					//获得socket的输出流，关闭socket时，会自动关闭socket的输出输入留
					out=new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
					//获得socket的输出流，关闭socket时，会自动关闭socket的输出输入留
					in=new BufferedReader(new InputStreamReader(s.getInputStream()));
					//将客户端发过来的数据输出：
					String str=null;
					 str=in.readLine();
						System.out.println("收到"+str);
					
					out.println("hello"+i++);
					
					out.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					if(s!=null){
						s.close();
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(serverSocket!=null){
				try {
					serverSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

}
