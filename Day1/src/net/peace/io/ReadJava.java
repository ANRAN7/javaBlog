package net.peace.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReadJava {

	public static void main(String[] args) {
		/*
		 * 1文件流的经典写法
		 *  2将文件定义定在try外面，将实例化放在中间，在finally中关闭 
		 *  3文件字节读入可以一个个读，也可以按数组读；
		 * 字节读：b = in.read();数组读：len=in.read(buf)
		 * 4文件的输出流，同文件的输入流一样。只是方向不同；
		 */
		// TODO Auto-generated method stub
		String path = "/home/peace/workspace/python/if.py";
		FileInputStream in = null;
		FileOutputStream fos = null;
		try {
			// 打开流
			in = new FileInputStream(path);
			fos = new FileOutputStream("/home/peace/if.py");
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = in.read(buf)) >= 0) {
				// System.out.write(buf,0,len);
				fos.write(buf, 0, len);
			}
			// 使用流读文件内容
			/*
			 * int b = in.read(); while (b != -1) { System.out.print((char) b);
			 * b = in.read(); }
			 */
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// 释放资源
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
