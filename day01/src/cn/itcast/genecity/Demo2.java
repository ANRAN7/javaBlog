package cn.itcast.genecity;
/*
 自定义泛型： 自定义泛型可以理解为是一个数据类型的占位符，或者是理解为是一个数据类型的变量。
 
 泛型方法：
	
	泛型方法的定义格式： 
		
		修饰符   <声明自定义泛型>返回值类型       函数名(形参列表..){
		
		}

注意：
	1. 在方法上的自定义泛型的具体数据类型是调用该方法的时候传入实参的时候确定的。
	2. 自定义泛型使用的标识符只要符合标识符的命名规则即可。



 需求： 定义一个函数可以接收任意类型的参数，要求函数的返回值类型与实参的数据类型要一致。
 */
public class Demo2 {

	public static void main(String[] args) {
		Integer i= print(12); // Integer
		String str = print("abc");
	}
	
	
	//<T> 把T字母声明为自定义 的泛型、
	public static <abc> abc print(abc o){
		
		return o;
	}
	
}
