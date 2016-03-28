package com.netease.yunyin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Friend {
       public static void main(String[] args) {
    	   //获得输入
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		   //存储所有的朋友圈
		List<Set<Integer>>list=new ArrayList<>();
		try {
			 //获得行数
			int len=Integer.parseInt(br.readLine());
			//循环
			while(len-->0){
				
				//获得每行的整数
				String string=br.readLine();
				if(string==null||"".equals(string))break;
				String[] strings = string.split("\\s");
				int a[]=new int[]{Integer.parseInt(strings[0]),
					     Integer.parseInt(strings[1])};
                /*count
                 * 进行判断：
                 * 0 :带表集合中没有这一对好友中的任意一个，新建set集合
                 * 1 ：代表只有一个与该对好友组成好友关系，
                 * 2 ：代表有两个集合，与该对好友构成关系，将两个集合合成一个
                 * 不会大雨2 
                 */
				int count=0;
				//索引：存储list中的索引，该索引对应的集合与该对好友存在关系
				int index[]=new int[2];
				//索引计数
				int n=0;
				//遍历存在的朋友圈
				for(Set<Integer>s:list){
					//判断关系
					if(s.contains(a[0])||s.contains(a[1]))
						{
						  //有关系则加入
						  s.add(a[0]);
						  s.add(a[1]);
						  //保存索引
						  index[count++]=n;
						}
					n++;
					//为2的话即可以跳出循环
					if(count==2)break;
				}
				//不存在关系，则新建集合
				if(count==0)
				{
					Set<Integer>s=new HashSet<>();
					s.add(a[0]);
					s.add(a[1]);
					list.add(s);
				}
				//存在两个，则合并
				else if(count==2){
					Set<Integer> set1 = list.get(index[0]);
					Set<Integer> set2 = list.get(index[1]);
					set1.addAll(set2);
					list.remove(index[1]);
				}
				
				
	}
			//按集合的规模排序
			Collections.sort(list, new Comparator<Set<Integer>>() {

				@Override
				public int compare(Set<Integer> o1, Set<Integer> o2) {
					if(o1.size()>o2.size())
						return -1;
					return 0;
				}
			});
			//遍历输出规模
			for(Set<Integer>s:list){
				System.out.println(s.size());
			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
