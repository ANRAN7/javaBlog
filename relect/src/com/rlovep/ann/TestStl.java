package com.rlovep.ann;

import java.util.List;

public class TestStl {
     @Override//重写父类符号
    public String toString() {
    	// TODO Auto-generated method stub
    	return super.toString();
    }
     @SuppressWarnings(value={"unused","unchecked"})//拟制编译器警告
     private void save(){
    	 List list=null;
    	 String peace="王和平";
    			 System.out.println(peace);
     }
     @Deprecated//标记方法已经过时
     private void save1(){
    	 String peace="王和平";
    			 System.out.println(peace);
     }
     
}
