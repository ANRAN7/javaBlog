package com.rlovep.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.rlovep.entity.Dept;
import com.rlovep.entity.Employee;
import com.rlovep.service.IDeptService;
import com.rlovep.service.IEmployeeService;

public class EmployeeAction extends ActionSupport implements ModelDriven<Employee>{
     /**
	* @Fields serialVersionUID : TODO
	*/
	
	private static final long serialVersionUID = -2490183480746643120L;
	//封装请求Employee
	private Employee employee=new Employee();
	//部门id值
	private int deptId;
	//属性注入
	private IEmployeeService employeeService;
	private IDeptService deptService;
	 //日志文件：
	   private static Logger log=Logger.getLogger(EmployeeAction.class);
	
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public void setDeptService(IDeptService deptService) {
		this.deptService = deptService;
	}
    //模型驱动实现的方法，自动封装属性为对象
	@Override
	public Employee getModel() {
		return employee;
	}
	/**
	 * 
	* @Title: list 
	* @Description: 员工展示
	* @author peace w_peace12@163.com
	 */
	public String list(){
		//获得所有员工，放到request域
		List<Employee> list = employeeService.getAll();
		ActionContext.getContext().getContextMap().put("listEmp", list);
		return "list";
	}
	/**
	 * 
	* @Title: viewAdd 
	* @Description: 添加员工，进入添加页面
	* @author peace w_peace12@163.com
	 */
	public String viewAdd(){
		//将部门信息加入request域，用于添加员工
		List<Dept> list = deptService.getAll();
		ActionContext.getContext().put("listDept", list);
		
		return "add";
	}
	/**
	 * 
	* @Title: add 
	* @Description: 添加员工方法
	* @author peace w_peace12@163.com
	 */
	public String save(){
		//先根据部门主键查询：
		Dept dept=deptService.findById(deptId);
		employee.setDept(dept);
		//保存员工到数据库
		employeeService.save(employee);
		log.warn("添加成功");
		return "listAction";
	}
	/**
	 * 
	* @Title: viewupdate 
	* @Description: 修改员工信息 - 进入修改视图
	* @author peace w_peace12@163.com
	 */
	public String viewUpdate(){
		//获取要修改的记录id
		int id=employee.getId();
		//根据员工id查到员工信息
		Employee emp = employeeService.findById(id);
		//查询所有dept
		List<Dept> list = deptService.getAll();
		ActionContext.getContext().put("listDept", list);
		//数据回显：也可以用域对象，此去为了演示值栈
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.pop();
		//将需要更新emp的压入栈顶
		vs.push(emp);
		return "edit";
	}
	/**
	 * 
	* @Title: update
	* @Description: 修改员工信息 - 确认修改
	* @author peace w_peace12@163.com
	 */
	public String update(){
		//先根据部门主键查询：
		Dept dept=deptService.findById(deptId);
		employee.setDept(dept);
		//保存员工到数据库
		employeeService.update(employee);
		log.warn("修改成功");
		return "listAction";
	}
	/**
	 * 
	* @Title: delete
	* @Description: 修改员工信息 - 删除
	* @author peace w_peace12@163.com
	 */
	public String delete(){
		System.out.println(11);
		//通过id信息删除
		employeeService.delete(employee.getId());
		log.warn("删除成功");
		return "listAction";
	}
}
