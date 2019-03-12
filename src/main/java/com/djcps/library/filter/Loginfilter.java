package com.djcps.library.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.djcps.library.controller.AdminController;
/** @WebFilter(urlPatterns="/admin/*",filterName="loginfilter") **/
/**
 * @author djsxs
 *
 */
public class Loginfilter implements Filter{
	@Autowired
	@Qualifier("admin")
   private AdminController adminController;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		String phone = request.getParameter("phone");
		String pwd = request.getParameter("password");
		Object issucess = adminController.adminLogin(phone, pwd, request);
		String success = "success";
		if(success.equals(issucess.toString())){	
			arg2.doFilter(request, arg1);
			request.getRequestDispatcher("admin/index").forward(request, arg1);		
		}
		  response.sendRedirect("/index"); 
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("init filter");
		
	}

}
