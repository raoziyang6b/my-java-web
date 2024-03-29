package com.yinhe.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yinhe.bean.User;

public class UserLoginPrivilegeFilter implements Filter {

   
	public void destroy() {
		
	}

	//过滤 没有登录的用户
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		 HttpServletRequest req= (HttpServletRequest) request;
		 HttpServletResponse res= (HttpServletResponse) response;
		 HttpSession session=req.getSession();
		 User user=(User) session.getAttribute("user");
		 if(user==null){
			  res.sendRedirect(req.getContextPath()+"/login.jsp");
			  return;
			 
		 }
		chain.doFilter(request, response);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
