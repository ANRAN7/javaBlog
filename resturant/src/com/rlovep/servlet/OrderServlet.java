package com.rlovep.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rlovep.entity.DinnerTable;
import com.rlovep.entity.Food;
import com.rlovep.entity.OrderDetail;
import com.rlovep.entity.Orders;
import com.rlovep.service.IDinnerTableService;
import com.rlovep.service.IFoodService;
import com.rlovep.service.IOrderDetailService;
import com.rlovep.service.IOrdersService;
import com.rlovep.utils.BeanFactory;
import com.rlovep.utils.PageBean;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;



@WebServlet(name="OrdereServlet",value="/order",loadOnStartup=4)
public class OrderServlet extends BaseServlet {
	
     /**
	* @Fields serialVersionUID : TODO
	*/
	
	private static final long serialVersionUID = 1L;
	@Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	List<Orders> list = ordersService1.query();
    	List<OrderDetail> list2 = orderDetailService1.query();
    	config.getServletContext().setAttribute("orders", list);
    	config.getServletContext().setAttribute("orderDetail", list2);
    }
   public Object putInCar(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		Object uri = null;
		IFoodService foodService = BeanFactory.getInstance("foodService", IFoodService.class);
		HttpSession session = request.getSession();
		Map<Food, Integer> map = new LinkedHashMap<Food, Integer>();
		try {
			// 获取实物id
			String id = request.getParameter("food_id");
			Food food = foodService.findById(Integer.parseInt(id));
			// 从session获得订单数据
			Map<Food, Integer> m = (Map<Food, Integer>) session.getAttribute("foods");
			if (m != null) {
				if (m.containsKey(food)) {
					Integer integer = m.get(food);
					m.put(food, integer + 1);
				} else {
					m.put(food, 1);
					session.setAttribute("foods", m);
				}
			} else {
				map.put(food, 1);
				session.setAttribute("foods", map);
			}
			uri = "/app/detail/clientCart.jsp";
			return uri;
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
			return uri;
		}
	}
   public Object removeOrder(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		Object uri = null;
		IFoodService foodService = BeanFactory.getInstance("foodService", IFoodService.class);
		HttpSession session = request.getSession();
		try {
			// 获取实物id
			String id = request.getParameter("gid");
			Food food = foodService.findById(Integer.parseInt(id));
			//获取加入餐车时的食物数据
			Map<Food, Integer> m = (Map<Food, Integer>) session.getAttribute("foods");
			m.remove(food);
			session.setAttribute("foods", m);
			uri = "/app/detail/clientCart.jsp";
			return uri;
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
			return uri;
		}
	}
   public Object alterSorder(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		Object uri = null;
		IFoodService foodService = BeanFactory.getInstance("foodService", IFoodService.class);
		HttpSession session = request.getSession();
		try {
			// 获取实物id
			String id = request.getParameter("gid");
			Food food = foodService.findById(Integer.parseInt(id));
			//获得数量
			String num = request.getParameter("snumber");
			//获取加入餐车时的食物数据
			Map<Food, Integer> m = (Map<Food, Integer>) session.getAttribute("foods");
			m.put(food, Integer.parseInt(num));
			session.setAttribute("foods", m);
			uri = "/app/detail/clientCart.jsp";
			return uri;
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
			return uri;
		}
	}
   public Object takeOrder(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		Object uri = null;
		IOrdersService ordersService = BeanFactory.getInstance("ordersService",
				IOrdersService.class);
		IOrderDetailService orderDetailService= BeanFactory.getInstance("orderDetailService",
				IOrderDetailService.class);
		HttpSession session = request.getSession();
		try {
			//获取加入餐车时的食物数据
			Map<Food, Integer> m = (Map<Food, Integer>) session.getAttribute("foods");
			String table_id = (String) session.getAttribute("table_id");
			//新建订单对象
			Orders order=new Orders();
			order.setTable_id(Integer.parseInt(table_id));
			//创建订单详细对象
			OrderDetail detail=new OrderDetail();
			//使订单可以迭代：
			Set<Entry<Food,Integer>> entrySet = m.entrySet();
			//定义总价
			double sum=0;
			int orderId=ordersService.getCount()+1;
			for(Entry<Food,Integer> entry:entrySet){
				Food food = entry.getKey();
				Integer count = entry.getValue();
				sum+=food.getPrice()*count;
			}
			//增加订单
			order.setOrderDate(new Date());
			order.setTotalPrice(sum);
			ordersService.add(order);
			//增加详细订单:
			for (Entry<Food, Integer> entry : entrySet) {
				Food food = entry.getKey();
				Integer count = entry.getValue();
				detail.setFood_id(food.getId());
				detail.setOrderId(orderId);
				detail.setFoodCount(count);
				orderDetailService.add(detail);
			}
			//将最新的订单写入context：
			List<Orders> orders = ordersService.query();
			List<OrderDetail> orderDetail = orderDetailService.query();
			this.getServletContext().setAttribute("orders", orders);
			this.getServletContext().setAttribute("orderDetail", orderDetail);
			uri = "/app/detail/clientOrderList.jsp";
			return uri;
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
			return uri;
		}
	}
   public Object getOrderDetail(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		IOrderDetailService orderDetailService= BeanFactory.getInstance("orderDetailService",
				IOrderDetailService.class);
	     Object uri = null;
		 String id = request.getParameter("orderId");
		 List<OrderDetail> list=null;
		try {
		    if(id!=null&&!id.isEmpty()){
		    	list=orderDetailService.findByOrderId(Integer.parseInt(id));
		    	request.setAttribute("orderDetail", list);
		    	uri = request.getRequestDispatcher("/sys/order/orderDetail.jsp");
		    }
		    return uri;
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
			return uri;
		}
	}
   public Object pay(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
	   IDinnerTableService tableService=BeanFactory.getInstance("dinnerTableService", IDinnerTableService.class);
	   IOrdersService ordersService= BeanFactory.getInstance("ordersService",
				IOrdersService.class);
	   IOrderDetailService orderDetailService= BeanFactory.getInstance("orderDetailService",
				IOrderDetailService.class);
 	     Object uri = null;
 	    try {
			String oid = request.getParameter("orderId");
			Orders o = new Orders();
			o.setOrderStatus(1);
			o.setId(Integer.parseInt(oid));

			ordersService.update(o);
			String tid = request.getParameter("tableId");
			if (tid != null) {
				tableService.quitTable(Integer.parseInt(tid));
			}
			// 将最新的订单写入context：
			List<Orders> orders = ordersService.query();
			List<OrderDetail> orderDetail = orderDetailService.query();
			this.getServletContext().setAttribute("orders", orders);
			this.getServletContext().setAttribute("orderDetail", orderDetail);
			List<DinnerTable> tables = tableService.query();// 更新前台首页的桌子
			this.getServletContext().setAttribute("table", tables);
			
			DinnerTable table = tableService.findById(Integer.parseInt(tid));// 用于后台点击结账时消除通知
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) this.getServletContext().getAttribute("tn");
			if (list != null) {
				list.remove(table.getTableName());//已结账
			}
			this.getServletContext().setAttribute("tn", list);//已结账
			//
			// 5. 跳转
			uri = getOrderList(request, response);
			return uri;
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
			return uri;
 		}
 	}
   public Object call(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
	   IDinnerTableService tableService=BeanFactory.getInstance("dinnerTableService", IDinnerTableService.class);
 	     Object uri = null;
		try {
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("table_id");
			DinnerTable table = tableService.findById(Integer.parseInt(id));
			String tableName = table.getTableName();
			@SuppressWarnings("unchecked")
			List<String> tab = (List<String>) this.getServletContext()
					.getAttribute("tn");
			if (tab == null) {
				tab = new ArrayList<String>();
			}
			tab.add(tableName);//没有结账的桌子
			this.getServletContext().setAttribute("tn", tab);
			//删除数据：
			session.removeAttribute("table_id");
			session.removeAttribute("foods");
			//session.removeAttribute("");
			uri = "/app/index.jsp";
			return uri;
 		} catch (Exception e) {
 			e.printStackTrace();
 			uri = "/error/error.jsp";
 			return uri;
 		}
 	}
   public Object getOrderList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	   IOrdersService ordersService= BeanFactory.getInstance("ordersService",
				IOrdersService.class);
		Object uri = null;
		// 1. 获取“当前页”参数； (第一次访问当前页为null)
		String currPage = request.getParameter("currentPage");
		// 判断
		if (currPage == null || "".equals(currPage.trim())) {
			currPage = "1"; // 第一次访问，设置当前页为1;
		}
		// 转换
		int currentPage = Integer.parseInt(currPage);

		// 2. 创建PageBean对象，设置当前页参数； 传入service方法参数
		PageBean<Orders> pageBean = new PageBean<Orders>();
		pageBean.setCurrentPage(currentPage);
		pageBean.setPageCount(6);

		// 3. 调用service
		ordersService.getAll(pageBean); // 【pageBean已经被dao填充了数据】
		// 4. 保存pageBean对象，到request域中
		request.setAttribute("pageBean", pageBean);

		// 5. 跳转
		uri = request.getRequestDispatcher("sys/order/orderList.jsp");

		return uri;

	}
}
