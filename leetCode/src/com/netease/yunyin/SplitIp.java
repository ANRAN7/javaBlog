package com.netease.yunyin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SplitIp {
    public static void main(String[] args) {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		try {
			String input=br.readLine();
			String ip="";
			split(0, input.length(), input, ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    //划分函数
   static void split(int start,int length,String input,String ip){
	     //回溯时重新赋值
	     String back=ip;
    	for(int i=1;i<4&&(start+i)<=length;i++){
    		//回溯
    		ip=back;
    		//判断每一字节是否符合规范
    		String a=input.substring(start, start+i);
    		if(isValidIp(a))
    		{
    			//符合规范加上
    			ip+=a;
    			//判断是否达到了四个字符：0.0.0.0
    			String[] strings = ip.split("\\.");
    			//输出
    			if(strings.length==4&&(start+i)==length){System.out.println(ip);return;}
    			//加上.号递归
    			ip+=".";
    			split(start+i, length,input, ip);
    		}
    	}
    }
   
   //判断是否为ip地址
   public static boolean isValidIp(String subIp) {
	   //如果第一个字符为0，则一定为0
       if (subIp.charAt(0) == '0')
           return subIp.equals("0");
       //0<subIp<=255
       int num = Integer.parseInt(subIp);
       if (num <= 255 && num > 0)
           return true;
       else
           return false;
   }
}
/*//划分函数
static void split(int start,int length,String s,String ip){
	     //回溯时重新赋值
	     String back=ip;
 	for(int i=1;i<4;i++){
 		ip=back;
 		//如果长度到了输入字符串的长度就可以输出了
 		if((start+i)==length){
 			//判断是否符合ip地址规范
 			String a=s.substring(start, length);
     		if(!isValidIp(a))return;
     		//符合则加上
     		ip+=a;
     		String[] strings = ip.split("\\.");
     		//判断是否达到了四个字符：0.0.0.0
     		if(strings.length==4)System.out.println(ip);
 			return;
 		}else if((start+i)>length) return;
 		String a=s.substring(start, start+i);
 		if(!isValidIp(a))return;
 		else{
 		    //接着递归
 			ip+=a+".";
 			String[] strings = ip.split("\\.");
 			if(strings.length>=5){return;}
 			split(start+i, length, s, ip);
 		}
 	}
 }*/