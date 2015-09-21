package net.peace.ref;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Test {

	/**
	 * 
	* @Title: setValues 
	* @Description: 实现把map的值动态赋值到一个类的实例（由传入的类实例化）中。并返回这个实例
	* @param map   用来包含数据值（key-属性名称，value-对应的值）
	* @param cls   传入的类型
	* @return  :Object   已经赋值的对象
	* @throws ：Exception
	* @author peace w_peace@163.com
	 */
	public static Object setValues(Map map,Class cls)throws Exception{
		     //获得传入类的实例
             Object obj=cls.newInstance();
             //获得类所有的属性
             Field fs[]=cls.getDeclaredFields();
             for(Field f:fs){
            	 //判断map中是否有对应的属性
            	 if(map.containsKey(f.getName()))
            	 { 
            		 //将可访问设置为true
            		 f.setAccessible(true);
            		 //赋予相应的值，哪怕是private的属性这边也可以赋值，表现出了反射的强大；
            		 f.set(obj, map.get(f.getName()));
            	 }
             }
		return obj;
	}
   
    public static void main(String[] args) {
    	//建立相应的map
		Map map=new HashMap();
		map.put("id", 123);
		map.put("name","peace");
		
		try {
			//调用属性赋值方法
			User user =(User)setValues(map, User.class);
			System.out.println(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
