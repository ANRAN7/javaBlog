package com.rlovep.one2many;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

/**
 * 
* @ClassName: App_hql
* @Description: HQL查询
* @author peace w_peace@163.com 
* @date 11 Jan 2016 7:17:10 pm
*
 */
public class App_hql {
	private static SessionFactory sf=null;
	static{
		Configuration config=new Configuration().configure().addClass(Dept.class).addClass(Employee.class);
		//创建serviceRegistry  代替buildSessionFactory()方法
   		ServiceRegistry serviceRegistry =new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
   		// 创建session的工厂对象
   		sf = config.buildSessionFactory(serviceRegistry);
	}
	/*
	 * 1)	Get/load主键查询
		2)	对象导航查询
		3)	HQL查询，  Hibernate Query language  hibernate 提供的面向对象的查询语言。
		4)	Criteria 查询，   完全面向对象的查询（Query By Criteria  ,QBC）
		5)	SQLQuery， 本地SQL查询

	 */
	@Test
	public void testAll(){
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		//1主键查询
		Dept dept=(Dept)session.get(Dept.class, 1);
		Employee employee=(Employee)session.get(Employee.class, 1);
		System.out.println(dept.getDname()+":"+employee.getEname());
		System.out.println("------------------");
		//2.对象导航查询：其实就是使用set，list，map等的懒加载原理
		System.out.println(dept.getEmps());
		//3.HQL查询：返回的是对象list
		// 注意：使用hql查询的时候 auto-import="true" 要设置true，默认为true
				//  如果是false，写hql的时候，要指定类的全名
		Query query = session.createQuery("from Employee");
		List<Employee> list = query.list();
		System.out.println(list.get(0).getEname());
		System.out.println("--------------");
		//4.criteria查询,返回的是对象list
		Criteria criteria = session.createCriteria(Employee.class);
		//构建条件
		criteria.add(Restrictions.eq("id", 1));
		//criteria.add(Restrictions.idEq(1));  // 主键查询
		System.out.println(criteria.list().get(0));
		System.out.println(criteria.list().get(0));
		//5.sql查询，原生查询，不能跨数据库平台： 如果换了数据库，sql语句有必须要改。
		//也是返回list对象
		SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM one_employee LIMIT 3;").addEntity(Employee.class);
		 list = sqlQuery.list();
		System.out.println(list.get(0).getEname());
		System.out.println("--------------");
		tx.commit();
		session.close();
	}
	//Hql查询详解之列：
	@Test
	public void testHqlColumn(){
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		//a.查询全部列：返回的是对象的list
		Query q1 = session.createQuery("from Employee");
		System.out.println(q1.list());
        //使用别名查询：不能使用sql这样的查询：select * from Dept
	  	Query q2 = session.createQuery("select d from Dept d");// 在hql中 Dept空格+d是给表取别名的意思
	  	System.out.println(q2.list().get(0));
	    // b. 查询指定的列  【返回对象数据Object[] 】,注意：此处要求列名和属性名字一致
	  	Query q3 = session.createQuery("select d.id,d.Dname from Dept d");
	  	List<Object[]> list = q3.list();
	  	System.out.println(list.get(0)[0]+":"+list.get(0)[1]);//输出id,和部门名
      // c. 查询指定的列, 自动封装为对象  【必须要提供带参数构造器】	
	  	Query q4 = session.createQuery("select new Dept(d.id,d.Dname) from Dept d");
	  	System.out.println(q4.list().get(0));
		tx.commit();
		session.close();
	}

	// Hql查询详解之条件查询: 一个条件/多个条件and or/between and/模糊查询
	@Test
	public void testHqlCond() {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		// 条件查询：占位符
		Query query = session.createQuery("from Dept where Dname=?");
		query.setString(0, "ptoo1项目");
		// query.setParameter(0, "ptoo1项目");直接设置参数
		System.out.println(query.list().get(0));
		//条件查询：命名参数
		query=session.createQuery("from Dept where id=:did or Dname=:dname");
		query.setParameter("did", 2);
		query.setParameter("dname", "ptoo1项目");
		System.out.println(query.list().get(1));
		//范围查询
		query=session.createQuery("from Dept where id between ? and ?");
		query.setParameter(0, 1);
		query.setParameter(1, 3);
		System.out.println(query.list().get(2));
		//模糊查询
		query=session.createQuery("from Dept where Dname like ?");
		query.setParameter(0, "%ptoo%");
		System.out.println(query.list().get(2));
		//聚合函数查询
		query=session.createQuery("select count(*) from Dept");
		Long num = (Long)query.uniqueResult();
		System.out.println(num);
		//分组查询：
		//--统计employee表中，每个部门的人数
		//SQL写法：SELECT id,COUNT(*) FROM one_employee GROUP BY id;
		//Hql写法
		query=session.createQuery("select e.dept,count(*) from Employee e group by e.dept");
		List<Object[]> list = query.list();
	  	System.out.println(list.get(0)[0]+":"+list.get(0)[1]);//输出部门id 对应的人数
		tx.commit();
		session.close();
	}

	//测试连接查询，内连接，左右外连接,迫切连接
	@Test
	public void testUnion() {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		// 1.内连接【映射已经配置好了关系，关联的时候，直接写对象的属性即可】不需要像sql那样那么复杂；
		Query query = session.createQuery("from Dept d inner join d.emps");
		//返回为OBject[]数组对象
		List<Object[]> list = query.list();
	  	System.out.println(list.get(0)[0]+":"+list.get(0)[1]);
	  	//2.左外连接
	  	query = session.createQuery("from Dept d left join d.emps");
		//返回为OBject[]数组对象
		 list = query.list();
	  	System.out.println(list.get(1)[0]+":"+list.get(0)[1]);
	  	//3.右外连接：
		query = session.createQuery("from Dept d right join d.emps");
		//返回为OBject[]数组对象
		 list = query.list();
	  	System.out.println(list.get(2)[0]+":"+list.get(0)[1]);
	  	//4.迫切内连接【使用fetch, 会把右表的数据，填充到左表对象中！】
	  	query = session.createQuery("from Dept d inner join fetch d.emps");
		//返回为Dept对象，且emps值保存了emps的值 
		 List<Dept>list1 = query.list();
	  	System.out.println(list1.get(1)+":"+list1.get(1).getEmps());
	  	//迫切右连接
		query = session.createQuery("from Dept d right join fetch d.emps");
		//返回为Dept对象，且emps值保存了emps的值 
		list1 = query.list();
	  	System.out.println(list1.get(1)+":"+list1.get(1).getEmps());
	  	
		tx.commit();
		session.close();
	}
	 
	// HQL 分页查询
	@Test
	public void testpage() {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		 Query q = session.createQuery("from Employee");
		// 总记录数
		ScrollableResults scroll = q.scroll(); // 得到滚动的结果集
		scroll.last(); // 滚动到最后一行
		int totalCount = scroll.getRowNumber() + 1;// 得到滚到的记录数，即总记录数
		 // 设置分页参数
		 q.setFirstResult(0);
		 q.setMaxResults(3);
		 
		 // 查询
		 System.out.println(q.list());
		 System.out.println("总记录数：" + totalCount);
		tx.commit();
		session.close();
	}
	//使用保存的Hql语句
	// HQL 放到映射文件中
	@Test
	public void test_extern() {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.getNamedQuery("getAllDept");
		query.setParameter(0, 3);
		System.out.println(query.list());
		tx.commit();
		session.close();
	}
}
