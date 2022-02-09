package com.mybury.bucketlist.auth.config;

import javax.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {
	@Bean
	public FilterRegistrationBean<Filter> reReadableRequestFilter() {
		return new FilterRegistrationBean<Filter>(new RequestFilter());
	}
}
