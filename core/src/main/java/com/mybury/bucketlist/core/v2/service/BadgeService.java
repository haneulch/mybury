package com.mybury.bucketlist.core.v2.service;

import com.mybury.bucketlist.core.domain.Badge;

public interface BadgeService {

	void save(Badge badge);

	Object findByUserId(String userId);
}