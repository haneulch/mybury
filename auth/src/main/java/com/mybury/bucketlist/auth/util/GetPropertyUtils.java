package com.mybury.bucketlist.auth.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class GetPropertyUtils {
	private static Environment environment;
	
	@Autowired
	public void setEnvironment(Environment env) {
		environment = env;
	}
	public static String getProperty(String key) {
		return environment == null ? null : environment.getProperty(key);
	}
}