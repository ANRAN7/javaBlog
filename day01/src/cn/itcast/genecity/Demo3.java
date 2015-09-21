package cn.itcast.genecity;

import java.util.ArrayList;
import java.util.Comparator;

/*
 泛型类
 
 泛型类的定义格式：
 	
 	class 类名<声明自定义的泛型>{
 	
 	} 
 
注意的事项： 
 	1. 在类上自定义的泛型的具体数据类型是在创建对象的时候指定的。
	2. 在类上自定义了泛型，如果创建该类的对象时没有指定泛型的具体类型，那么默认是Object类型。
 	
 	
 */

class Worker implements Comparator<Worker>{

	@Override
	public int compare(Worker o1, Worker o2) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	
}



//自定义 一个集合对象
class MyList<T>{
	
	Object[]  arr = new Object[10];
	
	int index = 0;
	
	public MyList(){
	
	}

	public void add(T o){
		arr[index++] = o;
	} 
	
}


public class Demo3 {
	
	public static void main(String[] args) {
		MyList<String> list=  new MyList<String>();
		list.add("123");
		
		MyList list2 = new MyList();
		
		new ArrayList<String>();
		
	}
	
}
