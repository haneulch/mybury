package com.rsupport.bucketlist.core.repository;

import com.rsupport.bucketlist.core.domain.User;
import com.rsupport.bucketlist.core.domain.UserMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String>, UserRepositoryCustom {

	Page<UserMapping> findByIdContainsOrEmailContainsOrNameContainsOrderByName(String id, String email, String name, Pageable page);
	
	UserMapping findOneById(String userId);
}
