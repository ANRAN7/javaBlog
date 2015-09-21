package net.peace.ref;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.IntPredicate;

public class TestArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class c=Class.forName("java.lang.String");
			//数组对对象的构建
			Object str=Array.newInstance(c, 10);//第一个参数建立的类别。第二个参数建立的数组长度以及维数；多维在下面演示
			//打印看是否得到想要的数组
			Class cstr=str.getClass();
			System.out.println(cstr.getName());
			//给数组给定的索引赋值，此去索引为0
			Array.set(str, 0, "peace");
			//获得数组给定索引的值
			String ss=(String)Array.get(str, 0);
			System.out.println(ss);
			//可以转换为真正的数组；
			String[] string=(String[])str;
			System.out.println(string[0]);
			/*
			 * 下面进行array的多维演示
			 */
			//建立维数参数，数组的长度为维数，数组的值为建立的单个维数长度
			int[] inta=new int[]{5,6,9};
			//将数组当为第二个参数传入；获得三维数组 相当于a[5][6][9]
			Object threeInt=Array.newInstance(Integer.TYPE, inta);
			//打印看是否得到想要的数组
			Class cint=threeInt.getClass().getComponentType();
			System.out.println(cint.getName());
			//获得二维数组相当于 a1[6][9]=a[3]
			Object twoInt=Array.get(threeInt, 3);
			//获得一维数组相当于  a2=a1[5]
			Object oneInt=Array.get(twoInt, 5);
			//对一维数组进行赋值  a2[3]=10
			Array.set(oneInt,3,10);
			//打印验证
			int[][][] aint=(int[][][])threeInt;
			System.out.println("[3][5][3]="+aint[3][5][3]);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
