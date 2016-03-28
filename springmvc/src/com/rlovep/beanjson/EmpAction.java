package com.rlovep.beanjson;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/emp")
public class EmpAction {
	/**
     * @ResponseBody Emp 表示让springmvc将Emp对象转成json文本
	 */
	@ResponseBody
	@RequestMapping(value="/bean2json")
	public  Emp bean2json() throws Exception{
		//创建Emp对象
		Emp emp = new Emp();
		emp.setId(1);
		emp.setUsername("哈哈");
		emp.setSalary(7000D);
		emp.setHiredate(new Date());
		return emp;
	}
	
	
	@RequestMapping(value="/listbean2json")
	public @ResponseBody List<Emp> listbean2json() throws Exception{
		//创建List对象
		List<Emp> empList = new ArrayList<Emp>();
		//向List对象中添加三个Emp对象
		empList.add(new Emp(1,"哈哈",7000D,new Date()));
		empList.add(new Emp(2,"呵呵",8000D,new Date()));
		empList.add(new Emp(3,"嘻嘻",9000D,new Date()));
		//返回需要转JSON文本的对象
		return empList;
	}

	
	@RequestMapping(value="/map2json")
	public @ResponseBody Map<String,Object> map2json() throws Exception{
		//创建List对象
		List<Emp> empList = new ArrayList<Emp>();
		//向List对象中添加三个Emp对象
		empList.add(new Emp(1,"哈哈",7000D,new Date()));
		empList.add(new Emp(2,"呵呵",8000D,new Date()));
		empList.add(new Emp(3,"嘻嘻",9000D,new Date()));
		//创建Map对象
		Map<String,Object> map = new LinkedHashMap<String,Object>();
		//向Map对象中绑定二个变量
		map.put("total",empList.size());
		map.put("rows",empList);
		//返回需要转JSON文本的对象
		return map;
	}
	
	
}
