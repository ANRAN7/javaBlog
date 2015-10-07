package com.rlovep.bean;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
/*
 * 通过配置文件更改对象的属性，比如注册的时候，服务器可以通过接收配置文件进行更改；
 * 此去用反射进行更改，直接更改实现域的值；比较麻烦
 */
public class CofigRef {
	public static void main(String[] args) {
		try {
			//获得更改后的对象；
			Person p=(Person)getInstance();
			System.out.println(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//根据配置文件的内容生产对象的对象并且要把对象的属性值封装到对象中。
		public static Object getInstance() throws Exception{
			//通过字符流进行输入；
			BufferedReader bufferedReader = new BufferedReader(new FileReader("obj.txt"));
			String className =  bufferedReader.readLine(); //读取配置文件获取到完整的类名。
			Class clazz = Class.forName(className);
			//通过class对象获取到无参的构造方法
			Constructor constructor = clazz.getConstructor(null);
			//创建对象
			Object o  = constructor.newInstance(null);
			//读取属性值
			String line = null;
			while((line = bufferedReader.readLine())!=null){
				String[] datas = line.split("=");
				//通过属性名获取到对应的Field对象。
				Field field = 	clazz.getDeclaredField(datas[0]);
				field.setAccessible(true);
				if(field.getType()==int.class){
					//更改属性内容；
					field.set(o, Integer.parseInt(datas[1]));
				}else{
					field.set(o, datas[1]);
				}
				
			}
			bufferedReader.close();
			return o;
			
		}

}
