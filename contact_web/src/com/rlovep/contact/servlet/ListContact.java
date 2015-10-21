package com.rlovep.contact.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rlovep.contact.dao.ContactDao;
import com.rlovep.contact.dao.impl.ContactDaoImpl;
import com.rlovep.contact.entity.Contact;


/**
 * Servlet implementation class ListContact
 */
@WebServlet("/ListContact")
public class ListContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.从xml中读取出联系人数据
		ContactDao dao = new ContactDaoImpl();
		List<Contact> list = dao.findAll();
		
		//2.显示到浏览器
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		String html = "";
		
		//shift+alt+A   ^(.*)$  \1";
		html += "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>";
		html += "<html xmlns='http://www.w3.org/1999/xhtml'>";
		html += "<head>";
		html += "<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />";
		html += "<title>查询所有联系人</title>";
		html += "<style type='text/css'>";
		html += "	table td{";
		html += "		/*文字居中*/";
		html += "		text-align:center;";
		html += "	}";
		html += "	";
		html += "	/*合并表格的边框*/";
		html += "	table{";
		html += "		border-collapse:collapse;";
		html += "	}";
		html += "</style>";
		html += "</head>";
		html += "";
		html += "<body>";
		html += "<center><h3>查询所有联系人</h3></center>";
		html += "<table align='center' border='1' width='800px'>";
		html += "	<tr>";
		html += "    	<th>编号</th>";
		html += "        <th>姓名</th>";
		html += "        <th>性别</th>";
		html += "        <th>年龄</th>";
		html += "        <th>电话</th>";
		html += "        <th>邮箱</th>";
		html += "        <th>QQ</th>";
		html += "        <th>操作</th>";
		html += "    </tr>";
		if(list!=null){
			for (Contact contact : list) {
				html += "    <tr>";
				html += "    	<td>"+contact.getId()+"</td>";
				html += "        <td>"+contact.getName()+"</td>";
				html += "        <td>"+contact.getGender()+"</td>";
				html += "        <td>"+contact.getAge()+"</td>";
				html += "        <td>"+contact.getPhone()+"</td>";
				html += "        <td>"+contact.getEmail()+"</td>";
				html += "        <td>"+contact.getQq()+"</td>";
				html += "        <td><a href='"+request.getContextPath()+"/QueryContact?id="+contact.getId()+"'>修改</a>&nbsp;<a href='"+request.getContextPath()+"/DeleteContact?id="+contact.getId()+"'>删除</a></td>";
				html += "    </tr>";
			}
		}
		html += "    <tr>";
		html += "    	<td colspan='8' align='center'><a href='"+request.getContextPath()+"/addContact.html'>[添加联系人]</a></td>";
		html += "    </tr>";
		html += "</table>";
		html += "</body>";
		html += "</html>";

		
		writer.write(html);
     
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
