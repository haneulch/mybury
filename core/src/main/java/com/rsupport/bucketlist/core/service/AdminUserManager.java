package com.rsupport.bucketlist.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;

import com.rsupport.bucketlist.core.domain.AdminUser;

public interface AdminUserManager {

	AdminUser findOneByUsername(String username);
	Page<AdminUser> findByUsername(String username, Pageable page);
	void save(AdminUser adminUser);
	String initializeOtpKey(String username);
	void deleteById(String username);
}
