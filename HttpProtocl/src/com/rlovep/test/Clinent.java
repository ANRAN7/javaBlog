package com.rlovep.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Clinent {
	public static void main(String args[])
	{
	try {
		Socket s = new Socket("localhost",8888);
		PrintWriter out = new PrintWriter(s.getOutputStream(),true);
		BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
		out.println("GET HTTP/1.1");
		out.println("Host: localhost:8888");
		out.println("contentType:text/html");
		out.println();
		String str = null;
		while((str=reader.readLine())!=null) {
			System.out.println(str);
		}
	} catch (UnknownHostException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}

}
