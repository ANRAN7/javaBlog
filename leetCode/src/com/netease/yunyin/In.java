package com.netease.yunyin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class In {
	 public static void main(String[] args) {
    	 
			/*Scanner in=new Scanner(System.in);
			String s=" ";
			int n=0;
			while(in.hasNextLine()){
				n++;
				Scanner t=new Scanner(in.nextLine());
				String k=in.nextLine();
				String[] strings = k.split("\\s");
				while(t.hasNext()){
					s+="-"+t.next();
				}
				String[] strings = in.nextLine().split("\\s");
				for(String k:strings){
					s+="-"+k;
				}
				if(n>4)break;
			}
			System.out.println(s);
		}*/
	    	  BufferedReader rd=new BufferedReader(new InputStreamReader(System.in));
	    	  String s=" ";
	  		  int n=0;
	    	  while(true){
	    		   try {
					String lin=rd.readLine();
					n++;
					String[] strings = lin.split("\\s");
					for(String k:strings){
						s+="-"+k;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
	    		   
	     		  if(n>4)break;
	    	  }
	    	  System.out.println(s);
	      }
}
