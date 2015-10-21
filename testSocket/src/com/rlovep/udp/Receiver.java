package com.rlovep.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Receiver {
	public static void main(String[] args) {
	DatagramSocket ds = null;
	try {
		//UDP接受端连接
		ds = new DatagramSocket(9999);
		//定义将UDP的数据包接收到什么地方
		byte[] buf = new byte[1024];
		//定义UDP的数据接收包
		DatagramPacket dp = new DatagramPacket(buf, buf.length);
		while(true) {
			//接收数据包
			ds.receive(dp);
			String str = new String(dp.getData(),0,dp.getLength());
			System.out.println(str);
		}
	} catch (SocketException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if(ds!=null) ds.close();
	}
}
}
