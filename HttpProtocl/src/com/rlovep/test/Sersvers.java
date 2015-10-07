package com.rlovep.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
//import org.apache.tomcat.jni.Socket;

public class Sersvers {
	public static void main(String[] args) throws Exception{
		//建立serversocket
		ServerSocket serverSocket=new ServerSocket(8888);
		System.out.println("服务器启动。。。");
		//建立连接
		Socket socket=serverSocket.accept();
		System.out.println("开始》》》");
		//读取本地的test.html
		FileInputStream in=new FileInputStream(new File("./src/peace.html"));
		//构建输出通道"
		OutputStream out=socket.getOutputStream();
		//发送数据
		byte[] read=new byte[1024];
		int len=0;
		while((len=in.read(read))!=-1){
			out.write(read,0,len);
			System.out.println("开始》》》");
		}
		//关闭资源
		in.close();
      	out.close();
	}

}
