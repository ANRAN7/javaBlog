package com.rlovep.iO;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;
import org.omg.CORBA.portable.OutputStream;
/**
 * 
* @ClassName: StreamNio
* @Description: 为基于流的I/O使用Nio
* @author peace w_peace12@163.com 
* @date 22 Jan 2016 8:22:26 pm
*
 */
public class StreamNio {
    @Test
	public void testRead(){
    	//通过Files类获得输入流
	    try(InputStream in=Files.newInputStream(Paths.get("src/test.txt"), StandardOpenOption.READ)) {
	    	int i=0;
			do{
				i=in.read();
				if(i!=-1)System.out.println((char)i);
			}while(i!=-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    @Test
  	public void testWrite(){
      	//通过Files类获得输出流
  	    try(BufferedOutputStream out=new BufferedOutputStream(Files.newOutputStream(Paths.get("src/test2.txt"),StandardOpenOption.CREATE, StandardOpenOption.WRITE))) {
  	    	for(int i=0;i<26;i++)
  	    		out.write((byte)('A'+i));
  		} catch (Exception e) {
  			e.printStackTrace();
  			  		}
  	}
}
