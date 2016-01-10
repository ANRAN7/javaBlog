package com.rlovep.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.rlovep.entity.FoodType;
import com.rlovep.service.IFoodTypeService;
import com.rlovep.utils.BeanFactory;

/**
 * Servlet implementation class FoodTypeServlet
 */
@WebServlet(name="FoodTypeServlet",value="/foodType",loadOnStartup=2)
public class FoodTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //获得service
	private IFoodTypeService service1=BeanFactory.getInstance("foodTypeService", IFoodTypeService.class);
	//转发和重定向uri
    private Object uri;
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	//放置菜系列表
    	List<FoodType>list=service1.query();
    	config.getServletContext().setAttribute("foodtype",list);
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method=request.getParameter("method");
        
        switch (method) {
		case "add":
			add(request, response);
			break;
		case "list":
			list(request, response);
			break;
		case "update":
			update(request, response);
			break;
		case "delete":
			delete(request, response);
			break;
		case "search":
			search(request, response);
			break;
		case "show":
			show(request, response);
			break;
		default:
			break;
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	IFoodTypeService service=BeanFactory.getInstance("foodTypeService", IFoodTypeService.class);
    	try {
			String name=request.getParameter("name");
			FoodType foodType=new FoodType();
			foodType.setTypeName(name);
			service.add(foodType);
			list(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			uri="/error/error.jsp";
			goTo(request, response, uri);
		}
    }
    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	IFoodTypeService service=BeanFactory.getInstance("foodTypeService", IFoodTypeService.class);
    	try {
			List<FoodType> list = service.query();
			request.setAttribute("list", list);
			request.getServletContext().setAttribute("foodtype", list);
			uri=request.getRequestDispatcher("/sys/foodtype/cuisineList.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			uri="/error/error.jsp";
		}finally{
			goTo(request, response, uri);
		}
    }
    /**
     * 
    * @Title: goTo 
    * @Description: TODO
    * @param request
    * @param response
    * @param uri
    * @throws ServletException
    * @throws IOException 
    * @return:转发和重定向的处理函数，通过判断类型进行不同的转发   
    * @author peace w_peace@163.com
     */
    private void goTo(HttpServletRequest request,HttpServletResponse response,Object uri) throws ServletException,IOException{
    	IFoodTypeService service=BeanFactory.getInstance("foodTypeService", IFoodTypeService.class);
    	if(uri instanceof RequestDispatcher)
    	{
    		((RequestDispatcher) uri).forward(request, response);
    	}else{
    		response.sendRedirect(request.getContextPath()+(String)uri);//重定向时需要加上context的路径，因为给浏览器用；
    	}
    }
    private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	IFoodTypeService service=BeanFactory.getInstance("foodTypeService", IFoodTypeService.class);
    	try {
			String id=request.getParameter("id");
			FoodType foodType=service.findById(Integer.parseInt(id));
		    request.setAttribute("type", foodType);
			uri=request.getRequestDispatcher("/sys/foodtype/updateCuisine.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			uri="/error/error.jsp";
		}finally{
			goTo(request, response, uri);
		}
    }
    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	IFoodTypeService service=BeanFactory.getInstance("foodTypeService", IFoodTypeService.class);
    	try {
    		FoodType type = new FoodType();
			Map<String, String[]> map = request.getParameterMap();
			BeanUtils.populate(type, map);
			service.updata(type);
			list(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			uri="/error/error.jsp";
			goTo(request, response, uri);
		}
    }
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	IFoodTypeService service=BeanFactory.getInstance("foodTypeService", IFoodTypeService.class);
    	try {
    		String id = request.getParameter("id");
    		service.delete(Integer.parseInt(id));
			list(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			uri="/error/error.jsp";
			goTo(request, response, uri);
		}
    }
    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	IFoodTypeService service=BeanFactory.getInstance("foodTypeService", IFoodTypeService.class);
    	try {
    		String keyword = request.getParameter("keyword");
    		if(keyword!=null){
    			List<FoodType> list = service.query(keyword);
    			request.setAttribute("list", list);
    	        uri = request.getRequestDispatcher("/sys/foodtype/cuisineList.jsp");
    		}
		} catch (Exception e) {
			e.printStackTrace();
			uri="/error/error.jsp";
		}
    	finally{
    		goTo(request, response, uri);
    	}
    }
}
