package cn.itcsat.junit;

import junit.framework.Assert;

import org.junit.Test;

//测试类
public class ToolTest {
	
	@Test
	public void testGetMax(){
		int max = Tool.getMax();
		if(max!=5){
			throw new RuntimeException();
		}else{
			System.out.println("最大值："+ max);
		}
		
		//断言
		//Assert.assertSame(5, max); // expected 期望   actual  真实     ==
//		Assert.assertSame(new String("abc"), "abc");
//		Assert.assertEquals(new String("abc"), "abc"); //底层是使用Equals方法比较的
//		Assert.assertNull("aa");
//		Assert.assertTrue(true);
		
		
		
	}
	
	@Test
	public void  testGetMin(){
		int min = Tool.getMin(); 
		if(min!=3){
			throw new RuntimeException();
		}else{
			System.out.println("最小值："+ min);
		}
	}
	
	

}
