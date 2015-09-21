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
			//����һ�����������������Ҵ�һ���ļ��ж�ȡ����
			osi = new ObjectInputStream(new FileInputStream("e:/kh/stu.save"));
			//��ȡ�ļ��еĶ���
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
		Student stu = new Student(1,"����");
		stu.setMoeny(10000);
		ObjectOutputStream oos = null;
		try {
			//����һ���ļ�����������Ұ��������������ļ�stu.save��ȥ
			oos = new ObjectOutputStream(new FileOutputStream("e:/kh/stu.save"));
			//ֱ�ӽ�����д���ļ���
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
