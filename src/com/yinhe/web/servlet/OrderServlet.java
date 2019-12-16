package com.yinhe.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import com.yinhe.bean.Cart;
import com.yinhe.bean.CartItem;
import com.yinhe.bean.Orderitem;
import com.yinhe.bean.Orders;
import com.yinhe.bean.User;
import com.yinhe.service.OrderService;
import com.yinhe.utils.CommonsUtils;
import com.yinhe.utils.PaymentUtil;


public class OrderServlet extends BaseServlet {

	private OrderService orderService=new OrderService();
	
	private int currentPage=1;
	
	private int pageSize=5;
	//提交订单
	public void submitOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		//1.判断用户是否存在
		HttpSession session= request.getSession();
		User user=(User) session.getAttribute("user");
		if(user==null){
			response.sendRedirect("login.jsp");
			return;
		}
		
		Orders order=new Orders();
		order.setOid(CommonsUtils.getUUID());
		order.setOrdertime(new Date());
		//订单支付状态 1代表已付款 0代表未付款
		order.setState(0);
		Cart cart =(Cart) session.getAttribute("cart");
		order.setTotal(cart.getTotal());
		order.setUid(user.getUid());
		orderService.addOrder(order);
		
	    ArrayList<Orderitem> orderitemList=new ArrayList<Orderitem>();
		HashMap<String,CartItem> cartItems=cart.getCartItems();
		for(Map.Entry<String, CartItem> entry :cartItems.entrySet()){
			CartItem cartItem=  entry.getValue();
			Orderitem orderItem=new Orderitem();
			orderItem.setOid(order.getOid());
			orderItem.setCount(cartItem.getBuyNum());
			orderItem.setItemid(CommonsUtils.getUUID());
			orderItem.setPid(cartItem.getProduct().getPid());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setSubtotal(cartItem.getSubTotal());
			orderService.addOrderItem(orderItem);
			orderitemList.add(orderItem);
		}
		order.setItems(orderitemList);
		request.setAttribute("order", order);
		request.getRequestDispatcher("../order_info.jsp").forward(request, response);
	
	}
	//确认订单
	public void confirmSubmitOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		  Map<String,String[]> properties=  request.getParameterMap();
		  Orders order=new Orders();
		  try {
			BeanUtils.populate(order, properties);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  order.setState(1);
		orderService.updateOrder(order);
		
		  //进入我的订单，下面代码可以不要，可以直接重定向到myOrder
		  
		//1.判断用户是否存在
		HttpSession session= request.getSession();
		User user=(User) session.getAttribute("user");
		if(user==null){
			response.sendRedirect("login.jsp");
			return;
		}
		if(request.getParameter("currentPage")!=null){
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		}
		int start=(currentPage-1)*pageSize;
		List<Orders> orderList=orderService.findOrderByUid(user.getUid(),start,pageSize);
		int totalCount=orderService.findOrderCountByUid(user.getUid());
		int totalPage=totalCount%pageSize>0 ?totalCount/pageSize+1:totalCount/pageSize;
		for(Orders order1 : orderList){
			
			ArrayList<Orderitem> orderitemList=orderService.findAllOrderItemByOid(order1.getOid());
			order1.setItems(orderitemList);
		}
		request.setAttribute("orderList", orderList);
		request.setAttribute("currentPage", currentPage);
	    request.setAttribute("totalPage", totalPage);
		request.getRequestDispatcher("../order_list.jsp").forward(request, response);
		
	}
	//我的订单
	public void myOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		//1.判断用户是否存在
		HttpSession session= request.getSession();
		User user=(User) session.getAttribute("user");
		if(user==null){
			response.sendRedirect("login.jsp");
			return;
		}
		if(request.getParameter("currentPage")!=null){
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		}
		int start=(currentPage-1)*pageSize;
		List<Orders> orderList=orderService.findOrderByUid(user.getUid(),start,pageSize);
		int totalCount=orderService.findOrderCountByUid(user.getUid());
		int totalPage=totalCount%pageSize>0 ?totalCount/pageSize+1:totalCount/pageSize;
		for(Orders order : orderList){
			
			ArrayList<Orderitem> orderitemList=orderService.findAllOrderItemByOid(order.getOid());
			order.setItems(orderitemList);
		}
		request.setAttribute("orderList", orderList);
		request.setAttribute("currentPage", currentPage);
	    request.setAttribute("totalPage", totalPage);
		request.getRequestDispatcher("../order_list.jsp").forward(request, response);
		
	}
	
	public void deleteOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String oid = request.getParameter("oid");
		orderService.deleteOrder(oid);
		
		//1.判断用户是否存在
				HttpSession session= request.getSession();
				User user=(User) session.getAttribute("user");
				if(user==null){
					response.sendRedirect("login.jsp");
					return;
				}
				if(request.getParameter("currentPage")!=null){
					currentPage=Integer.parseInt(request.getParameter("currentPage"));
				}
				int start=(currentPage-1)*pageSize;
				List<Orders> orderList=orderService.findOrderByUid(user.getUid(),start,pageSize);
				int totalCount=orderService.findOrderCountByUid(user.getUid());
				int totalPage=totalCount%pageSize>0 ?totalCount/pageSize+1:totalCount/pageSize;
				for(Orders order : orderList){
					
					ArrayList<Orderitem> orderitemList=orderService.findAllOrderItemByOid(order.getOid());
					order.setItems(orderitemList);
				}
				request.setAttribute("orderList", orderList);
				request.setAttribute("currentPage", currentPage);
			    request.setAttribute("totalPage", totalPage);
				request.getRequestDispatcher("../order_list.jsp").forward(request, response);
	}

}
