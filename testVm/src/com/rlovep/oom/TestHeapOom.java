package com.rlovep.oom;

import java.util.ArrayList;
import java.util.List;
/**
 * @vm Args:-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * -Xms20m -Xmx20m控制java堆大小最大值和最小值为20M一样为避免扩展；
* @ClassName: TestHeapOom
* @Description: 测试java堆溢出
* @author peace w_peace@163.com 
* @date 20 Oct 2015 2:12:45 pm
*
 */
public class TestHeapOom {

	static class OOmObject{
		
	}
	public static void main(String[] args) {
		List<OOmObject> list=new ArrayList<>();
		/**
		 * 循环创建对象并禁止回收
		 */
		while(true)
		{
			list.add(new OOmObject());
		}
	}
}
/*
 * 输出结果：
 * java.lang.OutOfMemoryError: Java heap space
Dumping heap to java_pid8782.hprof ...
Heap dump file created [27529207 bytes in 0.113 secs]
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 */