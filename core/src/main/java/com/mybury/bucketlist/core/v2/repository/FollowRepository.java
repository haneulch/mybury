package com.mybury.bucketlist.core.v2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mybury.bucketlist.core.domain.Follow;

@Repository
public interface FollowRepository extends JpaRepository<Follow, String> {
	List<Follow> findByUserId(String userId);

	int countByUserId(String userId);
	
	int countByUser_Id(String userId);
}