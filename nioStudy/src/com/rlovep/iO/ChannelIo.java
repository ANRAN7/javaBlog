package com.rlovep.iO;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

/**
 * 
* @ClassName: ChannelIo
* @Description: 通过通道进行文件的读写
* @author peace w_peace12@163.com 
* @date 22 Jan 2016 7:17:43 pm
*
 */
public class ChannelIo {
     
	@Test
	public void testRead(){
		Path path=null;
		try {
			//获取路径
			 path= Paths.get("src/test.txt");
		} catch (InvalidPathException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   //获得对应路径的通道
		try(SeekableByteChannel fChannel=Files.newByteChannel(path)){
			//创建一个缓存区
			ByteBuffer buffer=ByteBuffer.allocate(128);
			int length=0;
			//从通道中读取字节
			while((length=fChannel.read(buffer))!=-1)
			{
				
			  System.out.println("------方法一----");
				//将缓存指向初始指针
				buffer.rewind();
				//显示字符
				for(int i=0;i<length;i++)
					System.out.println((char)buffer.get());
			}
			System.out.println("------方法二----");
             //通过映射进行独处
			//转型为FileChannel
			FileChannel fileChannel=(FileChannel)fChannel;
			//获得通道大小
			long size = fileChannel.size();
			//映射出缓存区
			MappedByteBuffer map = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, size);
			//显示字符
			for(int i=0;i<size;i++)
				System.out.println((char)map.get());
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	@Test
	public void testWrite(){
		Path path=null;
		try {
			//获取路径
			 path= Paths.get("src/test1.txt");
		} catch (InvalidPathException e) {
			e.printStackTrace();
		}
		   //获得对应路径的通道:可写，文件不存在是建立新文件,第二个方法演示时，映射文件需要读写功能，加上了可读操作
		try(SeekableByteChannel fChannel=Files.newByteChannel(path,StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE)){
			 System.out.println("------方法一----");
			//创建一个缓存区
			ByteBuffer buffer=ByteBuffer.allocate(26);
			//初始化缓存
			for(int i=0;i<26;i++)
				buffer.put((byte)('A'+i));
			//将缓存指向初始指针
			buffer.rewind();
			fChannel.write(buffer);
			 System.out.println("------方法二----");
			 //通过映射写入
			 FileChannel fileChannel=(FileChannel)fChannel;
			 //创建映射的缓存
			 MappedByteBuffer map = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0,26);
			 //写入字符到文件
				for(int i=0;i<26;i++)
					map.put((byte)('a'+i));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
