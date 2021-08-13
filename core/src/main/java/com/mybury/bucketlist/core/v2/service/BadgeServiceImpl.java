package com.mybury.bucketlist.core.v2.service;

import org.springframework.stereotype.Service;

import com.mybury.bucketlist.core.domain.Badge;
import com.mybury.bucketlist.core.v2.repository.BadgeRepository;

@Service
public class BadgeServiceImpl implements BadgeService {
	
	private final BadgeRepository badgeRepository;
	
	
	BadgeServiceImpl(BadgeRepository badgeRepository) {
		this.badgeRepository = badgeRepository;
	}

	@Override
	public void save(Badge badge) {
		badgeRepository.save(badge);
	}
}