package com.mybury.bucketlist.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mybury.bucketlist.core.common.OtpUtils;
import com.mybury.bucketlist.core.domain.AdminUser;
import com.mybury.bucketlist.core.repository.AdminUserRepository;

@Service
public class AdminUserManagerImpl implements AdminUserManager {
	
	AdminUserRepository adminUserRepository;
	
	AdminUserManagerImpl( AdminUserRepository adminUserRepository) {
		this.adminUserRepository = adminUserRepository;
	}

	@Override
	public AdminUser findOneByUsername(String username) {
		return adminUserRepository.findById(username).orElse(null);
	}
	
	@Override
	public Page<AdminUser> findByUsername(String search,  Pageable page) {
		return adminUserRepository.findByUsernameContainsOrNameContainsOrderByCreatedDt(search, search, page);
	}
	
	@Override
	public void save(AdminUser adminUser) {
		adminUserRepository.save(adminUser);
	}

	@Override
	public String initializeOtpKey(String username) {
		AdminUser adminUser = adminUserRepository.findById(username).orElse(null);
		
		if( adminUser != null) {
			String newOtpKey = OtpUtils.generateKey();
			adminUser.setOtpKey(newOtpKey);
			
			adminUserRepository.save(adminUser);
			
			return newOtpKey;
		}
		return null;
	}

	@Override
	public void deleteById(String username) {
		adminUserRepository.deleteById(username);
	}
}