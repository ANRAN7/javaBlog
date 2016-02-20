package com.peace.generic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 
* @ClassName: GenricDemo01
* @Description: 测试泛型
* @author peace w_peace@163.com 
* @date 3 Dec 2015 8:17:28 pm
 */
//泛型类的编写：就是在类名之后加上<T> 或者多个：<T，S>  泛型接口与之类似；
public class GenricDemo01<T> {
	//可以直接定义一个引用
	T t;
	//t=new T();但是不能实例化
	@Test
	//XML配置方式，使用C3P0连接池管理连接
	public void testXML() throws Exception {
		// 创建c3p0连接池核心工具类
		// 自动加载src下c3p0的配置文件【c3p0-config.xml】
		ComboPooledDataSource dataSource = new ComboPooledDataSource();// 使用默认的配置
		PreparedStatement pstmt = null;
		
		// ---> 从连接池对象中，获取连接对象
					Connection con = dataSource.getConnection();
					// 执行更新
					con.prepareStatement("delete from admin where id=12").executeUpdate();
					// 关闭
					con.close();
	}
    @Test
	public void testGenerc(){
		//1.集合的声明,不值名类型
		List list=new ArrayList<>();
		list.add(1);
		list.add("dddd");
		//获得字符串,当不写清楚放入的类型时，这里可以编译通过但是运行时报出异常
		//String s=(String)list.get(0);
		//System.out.println(s);
		//2.使用泛型：
		//List<int> a;不能放基础类型；
		List<String> list2=new ArrayList<>();
		list2.add("234");
		//list2.add(234);当指明了字符串类型时，编译时就会出粗
		//3.泛型只在编译时期有效，编译后的字节码文件中不存在有泛型信息！
		if(list.getClass().equals(list2.getClass())==true)
			System.out.println("两者还是属于相同的类型");
	   }
    /*
     * 当用泛型做函数参数时，一定要注意由于泛型的擦除，形如下面的两个方法不是重载会报错：
     * 编译不能通过
     */
   /* public void save(List<Integer> i){}
    public void save(List<String> s){}*/
    /**
     * 
    * @Title: generic 
    * @Description: 泛型方法的编写：
    * 要求：在返回值之前加上使用到的泛型标志：<K,T>
     */
    public static <K,F> F generic(K m1){
    	
    	return null;
    }
    /*
     * 调用泛型方法：
     * 只需要将对应的参数传入即可，不需要像泛型类一样将泛型写清楚：如List<String>
     */
    @Test
    public void testMethod(){
    	
    	//直接调用就行；方法会自己匹配型号；此时方法匹配的型号：k为Date  T为String
    	String s=generic(new Date());
    }
    //下面测试关键字：?,extend,super
    public void testKeys(){
        //list3.add(new Person());
        //测试有界类型：
        Stats<Person> person=new Stats();
        Stats<Student>student=new Stats();
        Stats<Teacher>teacher=new Stats();
        Stats<High>high=new Stats();
        //Stats<String> s=new Stats(); 放入的类型必须为Person的子类；
        String s=new String();
        //测试？
        save1(person);
        save1(student);
        save1(high);
        save1(high);
        //save1(s);
        
        //测试extends
        save2(student);
        save2(high);
        //save2(teacher);
        //save2(person);
        
        //测试super
        save3(student);
        save3(person);
        //save3(teacher);
        //save3(high);
    }
    //方法 测试用 ？号能传入任何继承自person的参数的Stats
    public void save1(Stats<?>s){
    	//能够获取
    	Person p = s.getP();
    	//不能操作 泛型定义的P引用
    	//s.setP(p);
    	//能操作其他应用
    	s.setS("peace");
    }
    //测试extends  能传入继承自Studnet的参数或者Student的Stats
    public void save2(Stats<? extends Student>s){
    	//能够获取
    	Person p = s.getP();
    	//不能操作 泛型定义的P引用
    	//s.setP(p);
    	//能操作其他应用
    	s.setS("peace");
    }
    //测试super  能传入Studnet的父类和Studnet
    public void save3(Stats<? super Student>s){
    	//能够获取
    	Student p = (Student)s.getP();
    	//能写入
    	s.setP(p);
    }
}
//泛型类型确定： 实现泛型接口的类也是抽象，那么类型在具体的实现中确定或创建泛型类的时候确定
class Test1<T> extends GenricDemo01<T>{
	
}
//泛型类型确定： 在业务实现类中直接确定接口的类型
class Test2  extends GenricDemo01<String>{
	
}
//建立四个类用于测试：
class Person{
	
}
//继承自person类
class Student extends Person{
	
}
//继承自person类
class Teacher extends Person{
	
}
//继承自Student
class High extends Student{
	
}
//限制传入类型必须为Person或者其子类，可以限定多个接口和一个类
class Stats<T extends Person>{
	T p;
    String s;
	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public T getP() {
		return p;
	}

	public void setP(T p) {
		this.p = p;
	}
}
