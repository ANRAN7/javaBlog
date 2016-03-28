package com.netease.yunyin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class WordCount {
      public static void main(String[] args) {
    	//获得输入
    	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    	//将单词和次数存成键值对
    	Map<String, Integer>words=new HashMap<>();
    	//行数
  		int n=0;
  		
		try {
			//行数获得
			n= Integer.parseInt(br.readLine());
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
  		  //处理输入：
    	  while(true){
    		   try {
    			   //按行处理
				String lin=br.readLine();
				n--;
				//对每一行的单词单独处理
				String[] strings = lin.split("\\s");
				for(String k:strings){
					//判断计数
					if(!"".equals(k)&&words.get(k)!=null){
						int wordCount=words.get(k);
						words.put(k,wordCount+1);
					}else if(k!=null&&!"".equals(k)){
						words.put(k, 1);
					}
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
    		   //处理完所有行时，跳出
     		  if(n<1)break;
    	  }
    	    //获得Map的键值对并存到Entry
    	List<Entry<String, Integer>> sortList=new ArrayList<>(words.entrySet());
    	   //进行排序，实现Comparator接口
    	Collections.sort(sortList, new Comparator<Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				//次数大的放前面
				if(o1.getValue()>o2.getValue())
					return -1;
				//次数相同的按，单词排序
				else if(o1.getValue()==o2.getValue())
					return o1.getKey().compareTo(o2.getKey());
				return 0;
			}
		});
    	//循环输出：
    	for(Entry<String, Integer> e:sortList){
    		
    		System.out.println(e.getKey()+" "+e.getValue());
    	}
      }
}
