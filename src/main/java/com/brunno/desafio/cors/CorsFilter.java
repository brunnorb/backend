package com.brunno.desafio.cors;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;



@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		//bloqueia acesso
		//resp.setHeader("Access-Control-Allow-Origin",siwcencobApiProperty.getOriginPermitida());
		resp.setHeader("Access-Control-Allow-Origin",req.getHeader("Origin"));
		
		//resp.addHeader("Access-Control-Allow-Origin","http://192.168.0.169:4200");
		
		
		
		//resp.setHeader("Access-Control-Allow-Origin","*");
		resp.setHeader("Access-Control-Allow-Credentials","true"); // Cookie ser enviado
		
		
		if ("OPTIONS".equals(req.getMethod()) ) {
			resp.setHeader("Access-Control-Allow-Methods","POST,GET,DELETE,PUT,OPTIONS");
			resp.setHeader("Access-Control-Allow-Headers","Authorization,Content-Type,Accept");
			resp.setHeader("Access-Control-Max-Age","3600");
			resp.setStatus(HttpServletResponse.SC_OK);
		}else 
		{
			chain.doFilter(request, response);
		}
	}

	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
