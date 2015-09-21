package net.peace;
//import java.awt.TextField;
import java.util.*;
import net.mindview.util.*;
public class UniqueWords {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
      Set<String> words =new TreeSet<String>( new TextFile("Test.java","\\W+"));
      System.out.println(words);
     
	}

}
