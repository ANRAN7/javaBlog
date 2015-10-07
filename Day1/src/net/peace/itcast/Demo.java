package net.peace.itcast;

import java.util.ArrayList;

class Mylist<T>{
	T a;
	public T peace(T a) {
		this.a=a;
		return this.a;
	}
}
public class Demo {
	/*
	 * 需求定义一个函数，可以接收认可类型的参数，返回一致
	 * 
	 */
	public static <T> T Print(T a) {
		Mylist<T> aMylist=new Mylist<T>();
		
		return a;
		
	}
	public static void main(String[] args){
		ArrayList list=new ArrayList();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		list.add("ddd");
		for(int i=0;i<list.size();i++){
			String str=(String)list.get(i);
			System.out.println(str.toUpperCase());
		}
		ArrayList<String> strings =new ArrayList<String>();
		strings.add("aaa");
		strings.add("aaa");
		strings.add("aaa");
		for(String i:strings){
			System.out.println(i);
		}
	}

}

