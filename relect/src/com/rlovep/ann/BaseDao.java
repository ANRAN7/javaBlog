package com.rlovep.ann;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.ResultSetHandler;

import com.peace.generic.Jdbc_Utils;
import com.rlovep.ann.util.Column;
import com.rlovep.ann.util.Id;
import com.rlovep.ann.util.Table;



/**
 * 解决优化的问题：
 * 	  1. 当数据库表名与类名不一致、 
 *    2. 字段与属性不一样、
 *    3. 主键不叫id 
 *    
 */
public class BaseDao<T> {
   //当前运行的类型
	private Class<T>clazz;
	//表名
	private String tableName;
    //主键
	private String id;
	public BaseDao() {
		// 获得父类type 此处为参数类型 BaseDao<Admin>
		Type type = this.getClass().getGenericSuperclass();
		// 强制转换为“参数化类型” 【BaseDao<Admin>】
		ParameterizedType pType = (ParameterizedType) type;
		// 获取参数化类型中，实际类型的定义 【new Type[]{Admin.class}】
		Type[] aTypes = pType.getActualTypeArguments();
		// 获取数据的第一个元素：Admin.class
		clazz = (Class) aTypes[0];
		//获得表名注解
		Table annotation = clazz.getAnnotation(Table.class);
		tableName=annotation.tableName();
		//获取当前运行类的所有字段、遍历、获取每一个字段上的id注解
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			// 设置强制访问
			f.setAccessible(true);
			// 获取每一个字段上的id注解
			Id anno_id = f.getAnnotation(Id.class);

			// 判断
			if (anno_id != null) {
				// 如果字段上有id注解，当前字段(field)是主键； 再获取字段名称
				Column column = f.getAnnotation(Column.class);
				// 主键
				id = column.columnName();
				// 跳出循环
				break;
			}
		}
        System.out.println("表名"+tableName);
        System.out.println("ID:"+id);
	}
	public T findById(int sid){
		try {
			String sql = "select * from " + tableName + " where " + id +"=?";
			/*
			 * DbUtils的已经封装好的工具类：BeanHandler?   属性=字段
			 */
			return Jdbc_Utils.getQurrRunner().query(sql, new BeanHandler<T>(clazz), sid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public List<T> getAll(){
		try {
			String sql = "select * from " + tableName;
			return Jdbc_Utils.getQurrRunner().query(sql, new BeanListHandler<T>(clazz));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
/**
 * 自定义结果集：封装单个Bean对象
 */
class BeanHandler<T> implements ResultSetHandler<T>{
    private Class<T> clazz;
    public BeanHandler(Class<T> clazz){
    	this.clazz=clazz;
    }
	@Override
	public T handle(ResultSet rs) throws SQLException {
       try {
		   //创建要封装的对象
    	   T t=clazz.newInstance();
		   //判断是否返回结果
    	   if(rs.next())
    	   {
    		   //获得class的field
    		   Field[] fields = clazz.getDeclaredFields();
    		   //遍历所有fields
    		   for(Field f:fields)
    		   {
    			   //获得属性名
    			   String fieldName=f.getName();
    			   //获得属性的cloume注释
    			   Column column = f.getAnnotation(Column.class);
    			   //从rs中获得相应的结果
    			   Object object = rs.getObject(column.columnName());
    			   //设置对象的属性值
    			   BeanUtils.copyProperty(t, fieldName, object);
    		   }
    	   }
    	   return t;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
/**
 * 自定义结果集：封装多个Bean对象到List集合
 */
class BeanListHandler<T> implements ResultSetHandler<List<T>>{
	
	// 要封装的单个对象
	private Class<T> clazz;
	public BeanListHandler(Class<T> clazz){
		this.clazz = clazz;
	}
	@Override
	public List<T> handle(ResultSet rs) throws SQLException {
		List<T> list=new ArrayList<>();
		try {
			while(rs.next())
			{
				// 创建要封装的对象  ‘1’
				T t = clazz.newInstance(); 
				
				// a. 获取类的所有的Field字段数组
				Field[] fs = clazz.getDeclaredFields();
				
				// b. 遍历， 得到每一个字段类型：Field
				for (Field f : fs) {
				
					// c. 获取”属性名称“
					String fieldName = f.getName();
					
					// e. 获取Field字段上注解   【@Column(columnName = "a_userName")】
					Column column =  f.getAnnotation(Column.class);
					
					// f. ”字段名“
					String columnName = column.columnName();        // 数据库中字段 a_userName
					
					// g. 字段值
					Object columnValue = rs.getObject(columnName);
					
					// 设置（BeanUtils组件）
					BeanUtils.copyProperty(t, fieldName, columnValue);
				}
				// 对象添加到集合
				list.add(t);
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
