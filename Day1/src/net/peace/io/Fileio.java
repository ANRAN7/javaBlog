package net.peace.io;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.regex.Pattern;

public class Fileio {
	public static FilenameFilter filte(final String arg0) {
		return new FilenameFilter() {
			private Pattern pattern=Pattern.compile(arg0);
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				
				return pattern.matcher(name).matches();
			}
		};
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       File path=new File("./src/net/peace/io/1.txt");
       String[] list ;
       try {
    	   //创建文件
		path.createNewFile();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       //删除文件
      // path.delete();
       //判断文件是否存在
       System.out.println(path.exists());
       //获得文件名
       System.out.println(path.getName());
       //获得文件位置
       System.out.println(path.getParent());
       //获取文件加的父类文件夹对象
       File pf =path.getParentFile();
       //判断是否为文件夹
       System.out.println(pf.isDirectory());
       File path1=new File("./src/net/peace/io/D");
       //创建一个目录
       path1.mkdir();
       File path2=new File("./src/net/peace/io/e/f/g");
       //父目录与子目录都创建；
       path2.mkdirs();
       //重命名 
       path.renameTo(new File("./src/net/peace/io/D.txt"));
      /* if(args.length==0)
          list=path.list();
       else*/
    
       //System.out.println(args[0]);
     
	}

}
