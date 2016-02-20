package com.rlovep.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rlovep.entity.DinnerTable;
import com.rlovep.service.IDinnerTableService;
import com.rlovep.utils.BeanFactory;




/**
 * Servlet implementation class DinnerTableServlet
 */
@WebServlet(value="/table",loadOnStartup=1,name="DinnerTableServlet")
public class DinnerTableServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	Object uri=null;
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	List<DinnerTable> list = tableService.query();
    	config.getServletContext().setAttribute("table", list);
    }  
    public Object search(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
    	String keyword=request.getParameter("keyword");
    	try {
			if(keyword!=null){
			List<DinnerTable> list = tableService.query(keyword);
			request.setAttribute("list", list);
			uri=request.getRequestDispatcher("/sys/board/boardList.jsp");
			}
			else{
				uri="/error/error.jsp";
			}
			return uri;
		} catch (Exception e) {
			e.printStackTrace();
		return uri="/error/error.jsp";
		}
    }
    public Object delete(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
    	try {
    		String id=request.getParameter("id");
			if(id!=null){
			 tableService.delete(Integer.parseInt(id));
			uri=list(request, response);
			}
			else{
				uri="/error/error.jsp";
			}
			return uri;
		} catch (Exception e) {
			e.printStackTrace();
			return uri="/error/error.jsp";
		}
    }
public Object list(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
    	try {
    		List<DinnerTable> list = tableService.query();
    		request.setAttribute("list", list);
    		//将餐桌表存到context用与前台显示
    		request.getServletContext().setAttribute("table", list);
			uri=request.getRequestDispatcher("/sys/board/boardList.jsp");
			return uri;
		} catch (Exception e) {
			e.printStackTrace();
			return uri="/error/error.jsp";
		}
   }
public Object update(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
	try {
		String id=request.getParameter("id");
		if(id!=null){
		tableService.changeState(Integer.parseInt(id));
		uri=list(request, response);
		}
		else{
			uri="/error/error.jsp";
		}
		return uri;
	} catch (Exception e) {
		e.printStackTrace();
		return uri="/error/error.jsp";
	}
 }
public Object add(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
	try {
		String tableName=request.getParameter("tableName");
		if(tableName!=null){
		DinnerTable dt=new DinnerTable();
		dt.setTableName(tableName);
		tableService.add(dt);
		uri=list(request, response);
		}
		else{
			uri="/error/error.jsp";
		}
		return uri;
	} catch (Exception e) {
		e.printStackTrace();
		return uri="/error/error.jsp";
	}
 }
}
