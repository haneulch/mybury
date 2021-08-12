package com.mybury.bucketlist.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mybury.bucketlist.core.domain.User;
import com.mybury.bucketlist.core.domain.UserMapping;

public interface UserRepository extends JpaRepository<User, String>, UserRepositoryCustom {

	Page<UserMapping> findByIdContainsOrEmailContainsOrNameContainsOrderByName(String id, String email, String name, Pageable page);
	
	UserMapping findOneById(String userId);
}
