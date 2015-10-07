package net.peace.ref;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

public class TestRef {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Stack<E>
       try {
    	   //装载指定的类
		Class<Stack> c=(Class<Stack>) Class.forName("java.util.Stack");
		//获得指定类名的方法；
		Method ms[]=c.getDeclaredMethods();
		//判断是否属于特定的类
		System.out.println(c.isInstance(new Stack<>()));
		for(Method m:ms){
			//获得方法的一些信息；
			System.out.println(m.getModifiers()+" "+m.getReturnType()+" "+m.getName());
			//获得参数属性类
			Class[] cc=m.getParameterTypes();
			for(Class p:cc){
				//获得类名
				System.out.println(p.getName());
			}
			//获得异常类
			Class[] ce=m.getExceptionTypes();
			for(Class p:ce){
				System.out.println(p.getName());
			}
		}
		System.out.println("******************************");
		//获得构造器方法
		Constructor<Stack>[] con=(Constructor<Stack>[]) c.getDeclaredConstructors();
		for(Constructor p:con){
			System.out.println(p);
		}
		System.out.println("******************************");
		//获取域
		Field[] field=c.getDeclaredFields();
		for(Field f:field){
			System.out.println(f);
		}
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       System.out.println("******************************");
       try {
    	   //通过反射创建不是默认对象的构造器
		Class list=Class.forName("java.util.ArrayList");
		//参数类型设置
	    Class ps[]=new Class[1];
	    ps[0]=Integer.TYPE;
	    //获得对应参数的构造器
	    Constructor cons=list.getConstructor(ps);
	    //通过调用构造器构建对象
	    Integer[] os={3};	  
	    ArrayList t=( ArrayList)cons.newInstance(os);
	    t.add(1);
	    System.out.println(t.size());
	    //利用反射调用方法
	    //创建参数
	    Class ps3[]= new Class[1];
	    ps3[0]=Object.class;
	    //获得想要的方法 add
	    Method method1=list.getDeclaredMethod("add", ps3);
	    //进行调用  添加一个元素2；
	    method1.invoke(t, 2);//第一个参数是对象，第二个参数是方法参数；
	  ///调用另外一个构造方法
	    Class ps2[]=new Class[1];
	    ps2[0]=Collection.class;
	    Constructor cons2=list.getConstructor(ps2);
	    ArrayList t2=( ArrayList)cons2.newInstance(t);
	    System.out.println(t2);
	    
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoSuchMethodException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalArgumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
	}

}
