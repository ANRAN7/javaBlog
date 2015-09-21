import java.util.*;
public class TestContainers{
static Collection fill(Collection<String> collection){
      collection.add("peace");
      collection.add("rong");
      collection.add("wang");
      collection.add("liu");
      return collection;
 }
static Map fill(Map<String,String>map){
     map.put("boy","peace");
     map.put("wirl","sisi");
     map.put("lover","rong");
     return map;
 }
public static void main(String[]args){
   System.out.println(fill(new ArrayList<String>()));
    System.out.println(fill(new LinkedList<String>()));
    System.out.println(fill(new HashSet<String>()));
    System.out.println(fill(new TreeSet<String>()));
    System.out.println(fill(new LinkedHashSet<String>()));
    System.out.println(fill(new HashMap<String,String>()));
    System.out.println(fill(new TreeMap<String,String>()));
    System.out.println(fill(new LinkedHashMap<String,String>()));

}
}

