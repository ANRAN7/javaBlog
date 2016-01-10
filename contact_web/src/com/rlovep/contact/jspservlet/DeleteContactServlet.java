package com.rlovep.contact.jspservlet;


import com.rlovep.contacrt.service.ContactService;
import com.rlovep.contacrt.service.impl.ContactServiceImpl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 删除联系人的逻辑
 * @author APPle
 *
 */
@WebServlet("/DeleteContactServlet")
public class DeleteContactServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//在火狐浏览器中以Get方式提交带参数的数据，会重复提交两次。
		System.out.println("删除联系人");
		//1.接收id
		String id = request.getParameter("id");
		
		//2.调用service删除联系人的方法
		ContactService service = new ContactServiceImpl();
		service.deleteContact(id);
		
		//3.跳转到查询联系人的页面
		response.sendRedirect(request.getContextPath()+"/ListContactServlet");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
