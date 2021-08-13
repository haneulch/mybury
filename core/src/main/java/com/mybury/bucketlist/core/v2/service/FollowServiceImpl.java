package com.mybury.bucketlist.core.v2.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.mybury.bucketlist.core.domain.Follow;
import com.mybury.bucketlist.core.domain.User;
import com.mybury.bucketlist.core.repository.UserRepository;
import com.mybury.bucketlist.core.v2.repository.FollowRepository;
import com.mybury.bucketlist.core.v2.vo.FollowRequest;

@Service
public class FollowServiceImpl implements FollowService {
	@PersistenceContext
	private EntityManager em;
	
	private final FollowRepository repository;
	
	FollowServiceImpl(FollowRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Follow> findByUserId(String userId) {
//		FollowId id = FollowId.builder().userId(userId).build();
		return repository.findByUserId(userId);
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
}