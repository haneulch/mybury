package com.mybury.bucketlist.auth.config;

import java.util.Arrays;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CommonConfig {
	private final String[] urls = {"/profile", "/home", "/dDay", "/complete", "/cancel", "/redo"
		, "/pin", "/beforeWrite", "/write", "/bucketlist/{id}", "/bucketlist/{id}","/bucketlist/{id}",
		"/mypage", "/category", "/category/*", "/withdrawal", "/support", "/support_items", "/support_edit",
		"/change_order", "/notice"};
	@Bean
	public FilterRegistrationBean<Filter> reReadableRequestFilter() {
		FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<Filter>(new RequestFilter());
		bean.setUrlPatterns(Arrays.asList(urls));
		return bean;
	}
}