package com.rlovep.file;

import java.io.File;
import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;




/**
 * Servlet implementation class FileUpload02
 */
@WebServlet("/upload1")
public class FileUpload02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文件上传工厂
		FileItemFactory factory=new DiskFileItemFactory();
		//创建上传核心工具类
		ServletFileUpload upload=new ServletFileUpload(factory);
		//设置单个文件允许的大小：50M
		upload.setFileSizeMax(50*1024*1024);
	    //设置文件上传表单允许的总大小：100M
		upload.setSizeMax(100*1024*1024);
		//设置上传表单文件名的编码：
		upload.setHeaderEncoding("utf-8");
		//文件名前缀
		String prix=null;
		/*
		 * 文件核心处理逻辑
		 */
		//判断当前表单是否为文件上传表单
		if(ServletFileUpload.isMultipartContent(request))
		{
			   
			try {
				 //把请求数据转换为一个个FileItem对象存入List
				List<FileItem> list = upload.parseRequest(new ServletRequestContext(request));
				for(FileItem item :list){
					//判断是否为普通文本数据
					if(item.isFormField())
					{
						// 普通文本数据
						String fieldName = item.getFieldName();	// 表单元素名称
						prix = item.getString();		// 表单元素名称,应的数据
						//item.getString("UTF-8");  指定编码
						System.out.println(fieldName + " " + prix);
					}
					//上传文件
					else{
						// 普通文本数据
						String fieldName = item.getFieldName();	// 表单元素名称
						String name = item.getName();	// 文件名				
						String content = item.getString();		// 文件内容；这个不建议使用；
						//String type = item.getContentType();	// 文件类型
						//InputStream in = item.getInputStream();// 上传文件流
						System.out.println(fieldName+"名字："+name+"content"+content);
						//组装文件名
						name=prix+name;
						//获取存放路径
						String path=getServletContext().getRealPath("/upload");
						System.out.println(path);
						//创建目标文件
						File file=new File(path,name);
						//工具类，文件上传
						item.write(file);
						item.delete();//删除垃圾文件；
					
					}
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
