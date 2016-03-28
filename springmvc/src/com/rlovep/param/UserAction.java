package com.rlovep.param;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *  控制器
 * @author AdminTC
 */
@Controller
@RequestMapping(value="/user")
public class UserAction {
	/**
	 * 用户注册，只能接收POST请求
	 * 如果不书写method=RequestMethod.POST的话，GET和POST请求都支持
	 */
	@RequestMapping(method=RequestMethod.POST,value="/register")
	public String registerMethod(Model model,String username,String salary) throws Exception{
		System.out.println("用户注册-->" + username + ":" + salary);
		model.addAttribute("message","员工注册成功"+ username);
		return "hello";
	}
	/**
	 * 用户登录，即能接收POST请求，又能接收GET请求
	 */
	@RequestMapping(value="/login")
	public String loginMethod(Model model,String username) throws Exception{
		
		System.out.println("用户登录-->" + username);
		model.addAttribute("message","员工登录成功"+ username);
		return "hello";
	}
	@RequestMapping(value="/add")
	public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("在业务控制方法中写入Request，Response等传统web参数");
		// 获取用户名和薪水
		String username = request.getParameter("username");
		String salary = request.getParameter("salary");
		System.out.println("用户注册-->" + username + ":" + salary);

		// 绑定到session域对象中
		request.getSession().setAttribute("username", username);
		request.getSession().setAttribute("salary", salary);

		// 转发/jsp/ok.jsp页面
		request.getRequestDispatcher("/jsp/ok.jsp").forward(request, response);
		return "hello";
	}
}




