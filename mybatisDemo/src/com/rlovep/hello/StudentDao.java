package com.rlovep.hello;

import org.apache.ibatis.session.SqlSession;

import com.rlovep.util.MyBatisUtil;

/**
 * 
* @ClassName: StudentDao
* @Description: 数据库操作类
* @author peace w_peace12@163.com 
* @date 27 Mar 2016 10:34:06 pm
*
 */
public class StudentDao {
      public void add1()throws Exception{
    	  SqlSession sqlSession=null;
    	  try{
    		  //获得sqlsession
    		  sqlSession=MyBatisUtil.getSqlsession();
    		  //默认事务开始
    		  //调用在映射文件中声明的方法
    		  int i=sqlSession.insert("com.rlovep.hello.Student.add1");
    		  System.out.println("影响了"+i+"行");
    		  //事务提交
    		  sqlSession.commit();
    		  
    	  }catch(Exception e)
    	  {
    		  e.printStackTrace();
    		  //事务回滚
    		  sqlSession.rollback();
    		  throw e;
    	  }finally {
			MyBatisUtil.closeSqlSession();
		}
      }
      public static void main(String[] args) throws Exception{
		StudentDao dao=new StudentDao();
		dao.add1();
	}
}
