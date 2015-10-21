package com.rlovep.serlvet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

/**
 * Servlet implementation class SessionDemo1
 */
@WebServlet("/SessionDemo1")
public class SessionDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//创建session对象
		HttpSession session = req.getSession();
		
		//得到session编号
		System.out.println("id:"+session.getId());
		//修改session的有效时间
		session.setMaxInactiveInterval(60);
		
	}
}
