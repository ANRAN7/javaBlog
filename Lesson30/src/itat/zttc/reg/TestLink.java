package itat.zttc.reg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestLink {
	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("d:/test/01.htm"));
			String str = null;
			StringBuilder sb = new StringBuilder();
			while((str=br.readLine())!=null) {
				sb.append(str);
			}
			List<String> es = getEmail(sb.toString());
			for(String e:es) {
				System.out.println(e);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(br!=null) br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static List<String> getEmail(String str) {
		List<String> es = new ArrayList<String>();
		Pattern p = Pattern.compile("<a.*?\\s+href=['\"]([^\"'>]*?)['\"].*?>(.*?)</a>");
		Matcher m = p.matcher(str);
		while(m.find()) {
			es.add(m.group(1));
		}
		return es;
	}
}
