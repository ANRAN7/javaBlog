package com.rlovep.contact.jspservlet;

import com.rlovep.contact.entity.Contact;
import com.rlovep.contact.exception.NameRepeatException;
import com.rlovep.contacrt.service.ContactService;
import com.rlovep.contacrt.service.impl.ContactServiceImpl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 添加联系人的逻辑
 * @author APPle
 *
 */
@WebServlet("/AddContactServlet")
public class AddContactServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//1.接收参数
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String qq = request.getParameter("qq");
		
		//封装成Contact对象
		Contact contact = new Contact();
		contact.setName(name);
		contact.setGender(gender);
		contact.setAge(Integer.parseInt(age));
		contact.setPhone(phone);
		contact.setEmail(email);
		contact.setQq(qq);
		
		ContactService service = new ContactServiceImpl();
		//2.调用dao类的添加联系人的方法
		try {
			service.addContact(contact);
		} catch (NameRepeatException e) {
			//处理自定义业务异常
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/addContact.jsp").forward(request, response);
			return;
		} 		
		
		//3.跳转到查询联系人的页面
		response.sendRedirect(request.getContextPath()+"/ListContactServlet");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
