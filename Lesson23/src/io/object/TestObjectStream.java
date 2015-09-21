package io.object;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TestObjectStream {
	public static void main(String[] args) {
		writeStu();
		readStu();
	}
	
	public static void readStu() {
		ObjectInputStream osi = null;
		try {
			//创建一个对象输入流，并且从一个文件中读取对象
			osi = new ObjectInputStream(new FileInputStream("e:/kh/stu.save"));
			//读取文件中的对象
			Student stu = (Student)osi.readObject();
			System.out.println(stu.getId()+","+stu.getUsername()+","+stu.getMoeny());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(osi!=null) osi.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void writeStu() {
		Student stu = new Student(1,"张三");
		stu.setMoeny(10000);
		ObjectOutputStream oos = null;
		try {
			//创建一个文件输出流，并且把这个对象输出到文件stu.save中去
			oos = new ObjectOutputStream(new FileOutputStream("e:/kh/stu.save"));
			//直接将对象写到文件中
			oos.writeObject(stu);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(oos!=null) oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
