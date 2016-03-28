package com.netease.yunyin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Others_WordCount{
    public static void main(String[] args){
        int rowCount = 0;
        Scanner sc = new Scanner(System.in);
        rowCount = sc.nextInt();
        int i=0;
        StringBuilder sb = new StringBuilder();
        while(i<=rowCount){
            sb.append(sc.nextLine()+" ");
            i++;
        }
        //单词匹配
        Pattern pattern= Pattern.compile("[a-zA-Z]+");
        Matcher matcher=pattern.matcher(sb.toString());
        Map<String,Integer> words=new HashMap<String,Integer>();
        String word="";
        int wordCount;
        while(matcher.find()){
            word=matcher.group();
            if(words.containsKey(word)){
                wordCount=words.get(word);
                words.put(word, wordCount+1);
            }else{
                words.put(word, 1);
            }
        }

        List<Map.Entry<String,Integer>> list=new ArrayList<Map.Entry<String,Integer>>(words.entrySet());
        //对集合进行排序
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue() > o2.getValue()) {
                    return -1;
                } else if (o1.getValue() == o2.getValue()) {
                    return o1.getKey().compareTo(o2.getKey());
                } else {
                    return 1;
                }

            }
        });
        //输出结果
        StringBuilder output = new StringBuilder();
        for(Map.Entry<String,Integer> bean : list){
            output.append(bean.getKey()).append(" ").append(bean.getValue()).append("\n");
        }
        System.out.println(output.toString());

    }

}
