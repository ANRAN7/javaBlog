/*
*********************************************************************************************************
*                                        TestFile()
*
* Description ： 这个实现了unix下的grep功能
*
* Arguments   : "./test.java" "\\b[Ssct]\\w+"  第一个参数为文件路径，第二个参数为正则表达式
*
* Returns     : 打印出符合的文件名：按次数，开始位置，以及哪一行
*
* Note(s)     : 使用了自建的TestFile。功能将文件打开后将文件的每一行读入存入在Arraylist中；
* 
* Author      ：peace 
*********************************************************************************************************
*/
package net.peace.reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Jgrep {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
         if(args.length<2){
        	 System.out.println("请输入文件，以及正则表达式");
        	 System.exit(0);
         }
         Pattern p=Pattern.compile(args[1]);
         int index=0;
         int nline=0;
         Matcher m=p.matcher(" ");
         for(String line : new TestFile(args[0]))
         { 		
        	 nline++;
        	 m.reset(line);
        	 while(m.find()){
        		 System.out.println(index++ +":"+m.group()+" Start:"+m.start()+" n:"+nline);
        	 }
        	
         }
         		
	}

}
