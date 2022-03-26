package com.mybury.bucketlist.core.v2.service;

import com.mybury.bucketlist.core.domain.Badge;
import com.mybury.bucketlist.core.domain.BadgeUser;

public interface BadgeUserService {

	void save(Badge badge);

	BadgeUser findByUseYnAndUserId(String userId);
}