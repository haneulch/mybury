package com.rsupport.bucketlist.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rsupport.bucketlist.core.domain.AdminUser;

public interface AdminUserRepository  extends JpaRepository<AdminUser, String>{

	Page<AdminUser> findByUsernameContainsOrNameContainsOrderByCreatedDt(String username, String name, Pageable page);

}
