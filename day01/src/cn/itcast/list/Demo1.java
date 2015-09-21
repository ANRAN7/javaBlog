package cn.itcast.list;

import java.util.HashSet;

import javax.print.attribute.HashAttributeSet;

/*
集合： 存储对象数据 的集合容器。

单例集合

----------| Collection  单例集合 的根接口
----------------| List 如果是实现了List接口的集合类，具备的特点： 有序，可重复。
-------------------| ArrayList ArrayList底层是使用了Object数组实现 的。 特点： 查询速度快，增删慢。
-------------------| LinkedList LinkedList底层是使用了链表数据结构实现的。特点： 查询速度慢，增删快
-------------------| Vector 底层是使用了Object数组实现 的， 实现原理与ArrayList 是一致的，但是是线程安全的，操作效率低。
----------------| Set  如果是实现了Set接口的集合类，具备的特点： 无序，不可重复。
-------------------| HashSet 底层是使用了哈希表实现 的。 特点： 存取速度快。

HashSet存储元素的原理：
 	往hashSet添加元素的时候，首先会调用元素的hashCode方法得到元素的哈希码值，然后把哈希码值经过运算算出该元素存在哈希表中的位置。
	
	情况1：如果算出的位置目前还没有存在任何的元素，那么该元素可以直接添加到哈希表中。
		
	情况2： 如果算出的位置目前已经存在其他的元素，那么还会调用元素 的equals方法再与这个位置上 的元素比较一次。
	如果equals方法返回的是true，那么该元素被视为重复元素，不允许添加。如果equals方法返回 的是false，那么该元素也可以被添加。
------------------| TreeSet  底层是使用了红黑树（二叉树）数据结构实现的。 特点： 对集合中的元素进行排序存储、。

TreeSet要注意的事项：
	1. 往TreeSet添加元素 的时候，如果元素具备自然顺序的特点，那么TreeSet会根据元素 的自然顺序特性进行排序 存储。
	2. 往TreeSet添加元素 的时候，如果元素不具备自然顺序的特点,那么元素所属的类就必须要实现Comparable接口，把比较的规则定义在CompareTo方法上。
	3. 往TreeSet添加元素 的时候，如果元素不具备自然顺序的特点,那么元素所属的类就也没有实现Comparable接口，那么在创建TreeSet对象的时候必须要传入比较器对象。
	
	比较器 的定义格式:
		
			class 类名 implements  Comparator{
			}

双列集合
	----------| Map 存储的数据都是以键值对的形式存在的，键可以不重复，值可重复。
	-------------| HashMap 底层也是使用了哈希表实现的。
	-------------| TreeMap 底层也是使用了红黑树数据结构实现的。 


练习： 






 */

class Person{
	
	int id;
	
	
	String name;


	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
	@Override
	public int hashCode() {
		return this.id;
	}
	
	@Override
	public boolean equals(Object obj) {
		Person p = (Person)obj;
		return this.id == p.id;
	}
	
	@Override
	public String toString() {
		return "编号："+ this.id +" 姓名： "+ this.name;
	}
}

public class Demo1 {
	
	public static void main(String[] args) {
		HashSet<Person> set = new HashSet<Person>();
		set.add(new Person(110,"狗娃"));
		set.add(new Person(110,"狗娃"));
		System.out.println("集合的元素："+ set);
		
		
		
	}
	
}
