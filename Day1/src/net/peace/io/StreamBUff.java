package net.peace.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Date;
/*
 *此类用来测试过滤流，包括buff和data流，以及reader和writer
 */
public class StreamBUff {
	/*
	*********************************************************************************************************
	*                                        testbuff()
	*
	* Description ： This function test BufferedInputStream and BufferedOutputStream.
	*
	* Arguments   : None.
	*
	* Returns     : None
	*
	* Note(s)     : None
	* 
	* Author      ：peace 
	*********************************************************************************************************
	*/
	public void testbuff(){
		System.out.println("test buff");
		long starttime=new java.util.Date().getTime();
		BufferedInputStream Bio=null;
		BufferedOutputStream Bos=null;
		try {
			//Arguments InputStream
			Bio=new BufferedInputStream(new FileInputStream("./1.txt"));
			//Arguments  OutputStream
			Bos=new BufferedOutputStream(new FileOutputStream("./2.txt"));
			//Buffer
			byte[] buf=new byte[1024];
			int len=0;
			//read
			while((len=Bio.read(buf))>=0){
				//copy
				Bos.write(buf,0,len);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//releases any system resources 
			if(Bio!=null){
				try {
					Bio.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(Bos!=null){
				try {
					Bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//test time
		long endtime=new java.util.Date().getTime();
		System.out.println((endtime-starttime));
	}
	/*
	*********************************************************************************************************
	*                                        testData()
	*
	* Description ： This function test DataInputStream and DataOutputStream.
	*
	* Arguments   : None.
	*
	* Returns     : None
	*
	* Note(s)     : None
	* 
	* Author      ：peace 
	*********************************************************************************************************
	*/
	public void testData(){
		System.out.println("test Data");
		//定义输出输入流
		DataInputStream dio=null;
		DataOutputStream dos=null;
		//先创建需要操作的文件
		File f=new File("./1.data");
		try {
			f.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			
			
			dos=new DataOutputStream(new FileOutputStream("./1.data"));
			dio=new DataInputStream(new FileInputStream("./1.data"));
			//向指定文件写入整数值
			dos.writeInt(11);
			dos.writeInt(22);
			dos.writeInt(66);
			dos.writeInt(55);
			dos.writeInt(44);
			dos.writeInt(33);
			
			int len=0;
			/*
			 * 读出整数值，读到最后会产生EOFException异常。
			 */
			while((len=dio.readInt())>=0){
				System.out.println(len);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * 扑捉EOFException异常，必须在io异常前面，因为EOFException继承自io异常；
		 */
		catch (EOFException e){

			System.out.println("end");
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			//releases any system resources 
			if(dio!=null){
				try {
					dio.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(dos!=null){
				try {
					dos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	/*
	*********************************************************************************************************
	*                                        testReader()
	*
	* Description ： This function test FileWriter and FileReader.
	*
	* Arguments   : None.
	*
	* Returns     : None
	*
	* Note(s)     : None
	* 
	* Author      ：peace 
	*********************************************************************************************************
	*/
	public void testReader(){
		//
		System.out.println("测试字符流");
		/*
		 * 一般字符输入选用BufferedReader，因为他带有readline方法；非常使用。是过滤流
		 */
		BufferedReader bf=null;
		/*
		 * 一般字符输出选用PrintWriter，因为他带有println方法；非常使用。是过滤流
		 * BufferedWriter流使用write方法时，必须加上newLine进行换行
		 */
		PrintWriter pw=null;
		try {
			pw=new PrintWriter(new BufferedWriter(new FileWriter("./writer.txt")));
			pw.println("peace 22 170");
			pw.println("rong 21 165");
			//输出与输入使用同一个文件，这里必须刷新以供后面的使用；
			pw.flush();
			bf=new BufferedReader(new FileReader("./writer.txt"));
			String str;
			
			while((str=bf.readLine())!=null){
				System.out.println(str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//releases any system resources 
			if(pw!=null){
				pw.close();
			}
			if(bf!=null){
				try {
					bf.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	/*
	*********************************************************************************************************
	*                                        testStreamReader()
	*
	* Description ： This function test  InputStreamReader and OutputStreamWriter
	*
	* Arguments   : None.
	*
	* Returns     : None
	*
	* Note(s)     : None
	* 
	* Author      ：peace 
	*********************************************************************************************************
	*/
    public void testStreamReader(){
    	System.out.println("测试转换流");
    	BufferedReader bf=null;
    	PrintWriter pw=null;
    	try {
    		//字节流转换为字符流。使用的是系统输入和输出
    		bf=new BufferedReader(new InputStreamReader(System.in));
    		pw=new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        	String str=null;
			while((str=bf.readLine())!=null){
				if(str.equals("exit"))break;
				pw.println(str);
				//写的时候要刷新生效，不然得关闭后才生效；
				pw.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//releases any system resources
			if(bf!=null){
				try {
					bf.close();
					pw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    	
    }
    /*
	*********************************************************************************************************
	*                                       writeStu()
	*
	* Description ： This function test ObjectOutputStream
	*
	* Arguments   : None.
	*
	* Returns     : None
	*
	* Note(s)     : None
	* 
	* Author      ：peace 
	*********************************************************************************************************
	*/
    public void writeStu(){
    	ObjectOutputStream ojo=null;
    	//创建要写入的对象；
    	Studet stu=new Studet(11,"peace");
        //private transient int money; money不能写入修改的值；
    	stu.setMoney(1000);
    	try {
    		//将对象写入指定的文件
			ojo=new ObjectOutputStream(new FileOutputStream("./student.txt"));
			ojo.writeObject(stu);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//releases any system resources
			if(ojo!=null){
				try {
					ojo.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    	
    }
    /*
	*********************************************************************************************************
	*                                       readStu()
	*
	* Description ： This function test ObjecINputStream
	*
	* Arguments   : None.
	*
	* Returns     : None
	*
	* Note(s)     : None
	* 
	* Author      ：peace 
	*********************************************************************************************************
	*/
    public void readStu(){
    	//创建输入对象的引用
    	Studet stu=null;
    	ObjectInputStream osi=null;
    	try {
    		//从指定文件中输入对象
			osi=new ObjectInputStream(new FileInputStream("./student.txt"));
			stu=(Studet)osi.readObject();
			System.out.println(stu.getId()+":"+stu.getName()+":"+stu.getMoney());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//releases any system resources
			if(osi!=null){
				try {
					osi.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    	
    }
    
	public static void main(String[] args) {
		StreamBUff streamBUff=new StreamBUff();
		//test buff
		streamBUff.testbuff();
		//test data
		streamBUff.testData();
		//test 字符流
		streamBUff.testReader();
		// test 转换流
		//streamBUff.testStreamReader();
		//对象流
		streamBUff.writeStu();
		streamBUff.readStu();
	}

}
