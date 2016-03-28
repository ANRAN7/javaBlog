package com.netease.yunyin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Othre_SplitIp {
	  private static List<String> results = new ArrayList<String>();

	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        String input = sc.nextLine();
	        convert2Address(input);
	        for (String ip : results) {
	            System.out.println(ip);
	        }

	    }

	    public static void convert2Address(String input) {

	        if (input == null && input.length() > 12) {
	            return;
	        }
	        if (input.length() == 0) {
	            return;
	        }
	        search(input, 0, "");

	    }

	    public static void search(String input, int number, String address) {
	        if (number == 3 && isValidIp(input)) {
	            results.add(address + input);
	            return;
	        }

	        for (int i = 0; i < 3 && i < input.length() - 1; i++) {
	            String sub = input.substring(0, i + 1);
	            if (isValidIp(sub))
	                search(input.substring(i + 1, input.length()), number + 1, address + sub + '.');
	        }
	    }

	    public static boolean isValidIp(String s) {
	        if (s.charAt(0) == '0')
	            return s.equals("0");
	        int num = Integer.parseInt(s);

	        if (num <= 255 && num > 0)
	            return true;
	        else
	            return false;
	    }
}
