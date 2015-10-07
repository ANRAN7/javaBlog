package net.peace.reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Jgrep {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
         if(args.length<2){
        	 System.out.println("请输入文件，以及正则表达式");
        	 System.exit(0);
         }
         Pattern p=Pattern.compile(args[1]);
         int index=0;
         int nline=0;
         Matcher m=p.matcher(" ");
         for(String line : new TestFile(args[0]))
         { 		
        	 m.reset(line);
        	 while(m.find()){
        		 System.out.println(index++ +":"+m.group()+" Start:"+m.start()+" n:"+nline);
        	 }
        	 nline++;
         }
         		
	}

}

