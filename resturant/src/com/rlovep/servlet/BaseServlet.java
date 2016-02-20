package com.rlovep.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rlovep.service.IDinnerTableService;
import com.rlovep.utils.BeanFactory;
import com.rlovep.utils.WebUtils;
import com.rlovep.service.IFoodService;
import com.rlovep.service.IFoodTypeService;
import com.rlovep.service.IOrderDetailService;
import com.rlovep.service.IOrdersService;

public abstract class BaseServlet extends HttpServlet {
   /**
	* @Fields serialVersionUID : TODO
	*/
	
	private static final long serialVersionUID = 1L;
	//创建service，子类需要能够访问，采用protected
	protected IDinnerTableService tableService=BeanFactory.getInstance("dinnerTableService", IDinnerTableService.class);
	protected IFoodTypeService foodTypeService = BeanFactory.getInstance(
			"foodTypeService", IFoodTypeService.class);
	protected IFoodService foodService = BeanFactory.getInstance("foodService",
			IFoodService.class);
	protected IOrdersService ordersService = BeanFactory.getInstance("ordersService",
			IOrdersService.class);
	protected IOrderDetailService orderDetailService= BeanFactory.getInstance("orderDetailService",
			IOrderDetailService.class);
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   //保存跳转资源 方法返回值
		Object returnValue=null;
		//获取操作类型：对应实际servlet中的方法
		String methodName = request.getParameter("method");
		//获得运行时类
		Class<? extends BaseServlet> clazz = this.getClass();
		try {
			//获得方法值
			Method method = clazz.getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
			//调用方法
		returnValue = method.invoke(this, request,response);
		} catch (Exception e) {
			e.printStackTrace();
		returnValue="/error/error.jsp";
		} finally {
			if(returnValue!=null)
			//跳转
			WebUtils.goTo(request, response, returnValue);
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
