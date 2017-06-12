package com.za.shadrack.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;


import com.za.shadrack.bo.UserBO;


public class AuthenticationFilter implements Filter {
	/**
	 * @author Shadrack.Mugwena
	 */
	
	private UserBO userBO;
	
	public AuthenticationFilter(UserBO userBO) {
		this.userBO = userBO;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String authorizationHeader = null;
		
		try {
			 if (request instanceof HttpServletRequest) {
				 HttpServletRequest httpRequest = (HttpServletRequest) request;
	             authorizationHeader = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
	             if (authorizationHeader != null && !authorizationHeader.isEmpty()) {
	            	 String token = authorizationHeader.substring("Bearer".length()).trim();
		             
		             this.userBO.validateToken(token);
	                }
	   
			 }
			
		} catch (Exception e) {
			throw e;
		}
		 chain.doFilter(request, response);
		
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException { }

	@Override
	public void destroy() {	}
}
