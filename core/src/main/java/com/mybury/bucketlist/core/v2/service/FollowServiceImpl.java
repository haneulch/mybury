package com.mybury.bucketlist.core.v2.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.mybury.bucketlist.core.domain.Follow;
import com.mybury.bucketlist.core.domain.User;
import com.mybury.bucketlist.core.domain.UserSummary;
import com.mybury.bucketlist.core.repository.UserRepository;
import com.mybury.bucketlist.core.v2.repository.FollowRepository;
import com.mybury.bucketlist.core.v2.vo.FollowRequest;
import com.mybury.bucketlist.core.v2.vo.StateResponse;
import com.mybury.bucketlist.core.v2.vo.UserRequest;

@Service
public class FollowServiceImpl implements FollowService {
	@PersistenceContext
	private EntityManager em;
	
	private final FollowRepository repository;
	private final UserRepository userRepository;
	
	FollowServiceImpl(FollowRepository repository, UserRepository userRepository) {
		this.repository = repository;
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public void save(FollowRequest request) {
		User user = em.getReference(User.class, request.getFollowingId());
		
		Follow follow = Follow.builder()
							.user(user)
							.userId(request.getUserId())
							.build();
		
		em.persist(follow);
	}

	@Override
	@Transactional
	public StateResponse getFollowInfo(UserRequest request) {
		int followCount = repository.countByUserId(request.getUserId());
		int followingCount = repository.countByUser_Id(request.getUserId());
		
		User user = userRepository.getOne(request.getUserId());
		
		return StateResponse.builder()
								.followCount(followCount)
								.followingCount(followingCount)
								.hasAlarm(user.getAlarmYn())
								.build();
	}
}