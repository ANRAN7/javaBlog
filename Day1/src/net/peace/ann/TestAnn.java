package net.peace.ann;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Documented//包含到文档中
@Target(ElementType.METHOD)//设置放在长发上
@Retention(RetentionPolicy.RUNTIME)//设置在运行时可用
@interface Myanno{
	 int k=1;//注意接口中的属性是 static 和final的
	public String name();//name没有默认值，在写注解时必须赋值
	public int id() default 1;//设置默认值
}
public class TestAnn {
	private int id;
	private String name;
	//设置注解，并赋值
	@Myanno(name="peace1",id=1)
	public int getId() {
		return id;
	}
	//设置注解，并赋值
	@Myanno(name="peace2",id=2)
	public void setId(int id) {
		this.id = id;
	}
	//设置注解，并赋值
	@Myanno(name="peace3",id=3)
	public String getName() {
		return name;
	}
	//设置注解，并赋值
	@Myanno(name="peace4",id=4)
	public void setName(String name) {
		this.name = name;
	}
	@Override//覆盖注解，标准注解；
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	public static void main(String[] args) {
		try {
			//获得方法数组
			Class cls=Class.forName("net.peace.ann.TestAnn");
			Method[] met=cls.getDeclaredMethods();
			for(Method m:met){
				//获得方法对应的注解
				Myanno myanno=m.getAnnotation(Myanno.class);
				if(myanno!=null)
				System.out.println(m.getName()+":"+myanno.name()+":"+myanno.id()+myanno.k);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
