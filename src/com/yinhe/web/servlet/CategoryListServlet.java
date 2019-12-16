package com.yinhe.web.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yinhe.bean.Category;
import com.yinhe.service.CategoryService;
import com.yinhe.utils.JedisPoolUtils;
import redis.clients.jedis.Jedis;
public class CategoryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	private CategoryService categoryService = new CategoryService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		
		//1.��ȡjedis���� ����redis���ݿ�
	      //Jedis jedis= JedisPoolUtils.getJedis();
	     //String categoryListJSon= jedis.get("categoryListJson");
	      //if(categoryListJSon==null){
	    	  //׼������
	    	  List<Category> categoryList=categoryService.findAllCategory();
	    	//����gson����
	 		 Gson gson=new Gson();
			 //������ת��Ϊjson����
			 String categoryListJSon= gson.toJson(categoryList);
			 //��json���ݷ���jedis������
			 //jedis.set("categoryListJson", categoryListJSon);
	      //}
	
		 response.getWriter().println(categoryListJSon);
	}

}
