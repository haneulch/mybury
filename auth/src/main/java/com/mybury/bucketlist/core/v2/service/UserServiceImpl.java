package com.mybury.bucketlist.core.v2.service;

import com.mybury.bucketlist.core.repository.CategoryRepository;
import com.mybury.bucketlist.core.repository.UserRepository;
import com.mybury.bucketlist.core.v2.vo.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;
	private CategoryRepository categoryRepository;

	@Override
	public void removeUserInfo(UserRequest request) {
		userRepository.deleteById(request.getUserId());
		categoryRepository.deleteByUserId(request.getUserId());
	}

}
