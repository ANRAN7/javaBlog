package net.peace.reg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/*
*********************************************************************************************************
*                                        TestFile()
*
* Description ： This class create a list to save opened file
*
* Arguments   : File path
*
* Returns     : ArrayList
*
* Note(s)     : None
* 
* Author      ：peace 
*********************************************************************************************************
*/
public class TestFile extends ArrayList<String>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1681074288964328666L;
	/*
	 * 构造函数作用，用字符输入流打开指定文件，将文件按行读入存入arraylist中；
	 */
	public TestFile(String file) {
		// TODO Auto-generated constructor stub
		BufferedReader br=null;
		try {
			br=new BufferedReader(new FileReader(file));
              String str;
			while((str=br.readLine())!=null){
				this.add(str);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		
		for(String line:new TestFile(args[0]))
		      System.out.println(line);
	}

}
