package com.rlovep.clinet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 
* @ClassName: Clinet01
* @Description: 建立socket的客户端
* @author peace w_peace@163.com 
* @date 15 Oct 2015 3:44:28 pm
*
 */
public class Clinet01 {
	public static void main(String[] args) {
		Socket socket=null;
		PrintWriter out=null;
		BufferedReader in=null;
		try {
			//建立socket客户端:第一个参数：ip地址；第二个参数：发送的端口，假如没有服务器，会停在这里，然后扔出异常；
			socket=new Socket("192.168.1.105", 8888);
			//获得本机分配的当地socket端口
			System.out.println("当地端口："+socket.getLocalPort());
			//获得本机分配的当地socket端口
			System.out.println("远程端口："+socket.getPort());
			//获得socketAddress
			System.out.println("远程adress:"+socket.getRemoteSocketAddress());
			System.out.println("本地adress:"+socket.getLocalSocketAddress());
			//获得inetAddress
			System.out.println("远程inetAddress:"+socket.getInetAddress());
			System.out.println("本地inetAddress:"+socket.getLocalAddress());
			//获得socket的输出流
			out=new PrintWriter(socket.getOutputStream());
			//获得socket的输入流
			in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.println("peace");
			out.flush();
			String str=null;
		        str=in.readLine();
				System.out.println("收到:"+str);
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(socket!=null){
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
