package com.rlovep.serlvet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieDemo
 */
@WebServlet("/CookieDemo")
public class CookieDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*//1.创建cookie对象
		Cookie cookie=new Cookie("name", "pace");
		Cookie cookie2=new Cookie("name", "sisi");
		//2.设置cookie的有效路径，默认情况下有效路径在当前web应用下。/Httpser
		cookie2.setPath("/Httpser2");
		*//**
		 * 2)设置cookie的有效时间
		 * 正整数：表示cookie数据保存浏览器的缓存目录（硬盘中），数值表示保存的时间。
			负整数：表示cookie数据保存浏览器的内存中。浏览器关闭cookie就丢失了！！
			零：表示删除同名的cookie数据
		 *//*
		//cookie2.setMaxAge(-1);//cookie2保存在内存
		cookie.setMaxAge(20);
		response.addCookie(cookie);
		//response.addCookie(cookie2);
		//接收cookie的信息；
		Cookie[] cookies = request.getCookies();
		//注意：判断null,否则空指针
		if(cookies!=null){
			//遍历
			for(Cookie c:cookies){
				String name = c.getName();
				String value = c.getValue();
				String path=c.getPath();
				int age=c.getMaxAge();
				System.out.println(name+"="+value);
				System.out.println("path:"+path);
				System.out.println("age:"+age);
			}
		}else{
			System.out.println("没有接收cookie数据");
		}*/
		//获取当前时间
		response.setCharacterEncoding("utf-8");
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String curtime=format.format(new Date());
		//取得cookie
		Cookie[] cookies = request.getCookies();
		String lasttime=null;
		if(cookies!=null)
		{
			for(Cookie c:cookies)
			{
				//判断是否有lasttime的cookie
				if("lasttime".equals(c.getName())){
					//有就输出上次访问的时间
					lasttime=c.getValue();
					response.getWriter().println("上次访问时间："+lasttime);
					response.getWriter().println("这次访问时间："+curtime);
					//将lasttime更改为现在的时间
					c.setValue(curtime);
					//设置为一个月
					c.setMaxAge(1*30*24*60*60);
					//返回修改
					response.addCookie(c);
					break;
				}
			}
		}
		//假如没有cookies或者没有lasttime就建立对应的cookie
	if(cookies==null||lasttime==null)
	{
		//输出本次访问的时间
		response.getWriter().println("这是您第一次访问网站"+curtime);
		//创建lasttime的cookie
		Cookie cookie=new Cookie("lasttime", curtime);
		//设置保存时间为一个月
		cookie.setMaxAge(1*30*24*60*60);
		//发送给浏览器
		response.addCookie(cookie);
	}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
