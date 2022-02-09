package com.mybury.bucketlist.auth.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CommonInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String servletPath = request.getServletPath();

		HttpSession session = request.getSession();
		boolean isAdmin = session.getAttribute("isAdmin") == null ? false : (boolean) session.getAttribute("isAdmin");

		if( servletPath != null && ( servletPath.equals("/admin") || servletPath.equals("/admin/otpCheck"))) {
			if( isAdmin) {
				response.sendRedirect("/admin/page/main");
				return false;
			} else {
				session.invalidate();
			}
		}

//		response.sendRedirect("/admin");
		return true;
	}
}
