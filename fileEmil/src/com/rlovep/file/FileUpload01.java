package com.rlovep.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileUpload01
 */
@WebServlet("/upload")
public class FileUpload01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获得post提交的输入流
		BufferedReader in=new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
		String string=null;
		while((string=in.readLine())!=null)
		{
			//将每行输出
			System.out.println(string);
		}
		in.close();
	}
	/**
	 * ------WebKitFormBoundarycRBIugzvLWow3g2h
Content-Disposition: form-data; name="filename"

peace
------WebKitFormBoundarycRBIugzvLWow3g2h
Content-Disposition: form-data; name="file"; filename="1.txt"
Content-Type: text/plain

sisiisisiisisisiissiisisisisiisis
我爱你

------WebKitFormBoundarycRBIugzvLWow3g2h--

	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
