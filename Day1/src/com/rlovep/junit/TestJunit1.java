package com.rlovep.junit;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
/*
junit(单元测试框架)  需要拷贝junit的jar
1.目前存在的问题：
	1. 目前的方法如果需要测试，都需要在main方法上调用。
	2. 目前的结果都需要我们人工对比。可以使用断言进行对比：

2.junit要注意的细节：
	1. 如果使用junit测试一个方法的时候，在junit窗口上显示绿条那么代表测试正确，
		如果是出现了红条，则代表该方法测试出现了异常不通过。
	2. 如果点击方法名、 类名、包名、 工程名运行junit分别测试的是对应的方法，类、 包中 的所有类的test方法，工程中的所有test方法。
	3.  @Test测试的方法不能是static修饰与不能带有形参。
	4. 如果测试一个方法的时候需要准备测试的环境或者是清理测试的环境，那么可以@Before、 @After 、@BeforeClass、 @AfterClass这四个注解。
	@Before、 @After 是在每个测试方法测试的时候都会调用一次， @BeforeClass、 @AfterClass是在所有的测试方法测试之前与测试之后调用一次而已。


 */

public class TestJunit1 {
	//使用测试类进行测试
	public static int getMax(){
		int a = 3;
		int b  =5; 
		int max = a>b?a:b;
		return max;
	}
	//使用测试类进行测试
	public static int getMin(){
		int a = 3;
		int b = 5; 
		int min = a<b?a:b;
		return min;
	}

	@Test //注解  此去不能测试
	public	 void getMax(int a, int b){
	/*	int a = 3;
		int b = 5 ;*/
		int max = a>b?a:b;
		System.out.println("最大值："+max);
	}
	@Test
      public void sort(){
    	  int arr[]={9,4,5,8};
    	  for(int j=0;j<arr.length;j++)
    		  for(int i=j;i<arr.length;i++){
    			  if(arr[i]<arr[j]){
    				  int temp=arr[j];
    				  arr[j]=arr[i];
    				  arr[i]=temp;
    				
    			  }
    		  }
      System.out.println(Arrays.toString(arr));     
      }
	//准备测试的环境  比如准备一个文件输入流
		//@Before
		@BeforeClass
		public static void beforeRead(){
			System.out.println("准备测试环境成功...");
		}
		//清理测试环境的方法  清楚创建的文件
//		@After 
		@AfterClass
		public static void afterRead(){
			System.out.println("清理测试环境..");
		}
	public static void main(String[] args) {
		new TestJunit1().sort();
	}
}
/*
  3.junit使用规范：
	1. 一个类如果需要测试，那么该类就应该对应着一个测试类，测试类的命名规范 ： 被测试类的类名+ Test.
	2. 一个被测试的方法一般对应着一个测试的方法，测试的方法的命名规范是： test+ 被测试的方法的方法名
 */
//被测试类的类名+ Test.
class ToolTest {
	
	@Test//test+ 被测试的方法的方法名
	public void testGetMax(){
		int max = TestJunit1.getMax();
		if(max!=5){
			throw new RuntimeException();
		}else{
			System.out.println("最大值："+ max);
		}
		
		//断言 进行对比看是否运行正常；
		//Assert.assertSame(5, max); // expected 期望   actual  真实     ==
//		Assert.assertSame(new String("abc"), "abc");
//		Assert.assertEquals(new String("abc"), "abc"); //底层是使用Equals方法比较的
//		Assert.assertNull("aa");
//		Assert.assertTrue(true);
	}
	
	@Test
	public void  testGetMin(){
		int min =TestJunit1.getMin(); 
		if(min!=3){
			throw new RuntimeException();
		}else{
			System.out.println("最小值："+ min);
		}
	}
	
	

}
