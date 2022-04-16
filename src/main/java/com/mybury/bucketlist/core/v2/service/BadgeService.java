package com.mybury.bucketlist.core.v2.service;

import com.mybury.bucketlist.core.domain.Badge;
import com.mybury.bucketlist.core.v2.repository.BadgeRepository;
import com.mybury.bucketlist.core.v2.repository.BadgeUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BadgeService {
	private final BadgeRepository badgeRepository;
	private final BadgeUserRepository badgeUserRepository;

	public void save(Badge badge) {
		badgeRepository.save(badge);
	}

	public Object findByUserId(String userId) {
		return badgeUserRepository.findByUserId(userId);
	}
}
