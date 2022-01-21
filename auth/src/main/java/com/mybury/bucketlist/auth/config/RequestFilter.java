package com.mybury.bucketlist.auth.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

public class RequestFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		if(request != null && request.getContentType() != null
			&& !request.getContentType().contains(MediaType.MULTIPART_FORM_DATA_VALUE)) {
			RereadableRequestWrapper wrapper
				= new RereadableRequestWrapper((HttpServletRequest) request);
			filterChain.doFilter(wrapper, response);
		}
		filterChain.doFilter(request, response);
	}
}