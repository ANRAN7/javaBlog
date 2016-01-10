package com.rlovep.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rlovep.dao.impl.EmployeeDao;
import com.rlovep.entity.Employee;
import com.rlovep.entity.PageBean;
import com.rlovep.service.IEmployeeService;
import com.rlovep.service.impl.EmployeeService;

/**
 *控制层开发
 *@author peace
 */
@WebServlet(name = "index", urlPatterns = { "/index" })
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//创建service实例
	private IEmployeeService employeeService=new EmployeeService();
	// 跳转资源
	private String uri;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		    //获取“当前页”参数；  (第一次访问当前页为null) 
			String curpage=request.getParameter("curpage");
			//为空则进行判断
			if(curpage==null || "".equals(curpage)){
				curpage="1";
			}
			//转换为整数
			int current=Integer.parseInt(curpage);
			//创建pageBean对象
			PageBean<Employee> page=new PageBean<>();
	         //设置当前页参数
			page.setCurpage(current);
			//调用service
			employeeService.getPage(page);
			//将对象保存到request域中
			request.setAttribute("pageBean", page);
			//uri
			uri= "/WEB-INF/list.jsp";
		} catch (Exception e) {
			// 出现错误，跳转到错误页面；给用户友好提示
			uri = "/common/500.jsp";
		}
		//转发
		request.getRequestDispatcher(uri).forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
