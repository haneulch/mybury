package com.mybury.bucketlist.core.v2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mybury.bucketlist.core.domain.BadgeUser;

@Repository
public interface BadgeUserRepository extends JpaRepository<BadgeUser, String>{
	BadgeUser findByUserIdAndUseYn(String userId, Character useYn);
	List<BadgeUser> findByUserId(String userId);
}
