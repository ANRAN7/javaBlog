package net.peace.io;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.jar.Attributes.Name;

public class TestFile2 {
	///过滤文件用FileFileFilter
	private class DicFile implements FileFilter{

		@Override
		public boolean accept(File arg0) {
			// TODO Auto-generated method stub
			if(arg0.isDirectory()) return true;
			return false;
		}
		
	}
public void run(){
   File file=new File("/home/peace/workspace/javaThink");
   File[] fd=file.listFiles(new DicFile());
   for(File fs1:fd){
		System.out.println(fs1.getName()+":"+fs1.length());
	}
}
public static void main(String[] args) {
	File f= new File("/home/peace/workspace/javaThink");
	//匿名类只建议一个方法的时候使用；不建议多个方法的时候使用；
	File[] fs=f.listFiles(new FilenameFilter() {
		@Override
		public boolean accept(File arg0, String arg1) {
			// TODO Auto-generated method stub
			if(arg1.endsWith(".java")) return true ;
			return false;
		}
	});
	for(File fs1:fs){
		System.out.println(fs1.getName()+":"+fs1.length());
	}
	new TestFile2().run();
}
}
//通过文件名过滤
class JavaFileFilter implements FilenameFilter{

	@Override
	public boolean accept(File arg0, String arg1) {
		// TODO Auto-generated method stub
		//返回为true的不被过滤留下来；
		if(arg1.endsWith(".java")) return true;
		return false;
	}
	
}
