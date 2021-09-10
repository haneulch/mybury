package com.mybury.bucketlist.core.v2.service;

import org.springframework.stereotype.Service;

import com.mybury.bucketlist.core.repository.CategoryRepository;
import com.mybury.bucketlist.core.repository.UserRepository;
import com.mybury.bucketlist.core.v2.vo.UserRequest;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	private CategoryRepository categoryRepository;
	
	UserServiceImpl(
			UserRepository userRepository
			, CategoryRepository categoryRepository) {
		this.userRepository = userRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public void removeUserInfo(UserRequest request) {
		userRepository.deleteById(request.getUserId());
		categoryRepository.deleteByUserId(request.getUserId());
	}

}
