package com.mybury.bucketlist.core.v2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mybury.bucketlist.core.domain.Follow;

@Repository
public interface FollowRepository extends JpaRepository<Follow, String> {
	List<Follow> findByUserId(String userId);

	List<Follow> findByUser_Id(String userId);

	int countByUserId(String userId);
	
	int countByUser_Id(String userId);

	@Query(value = "select mf.following_id from mt_follow mf where mf.user_id = :userId", nativeQuery = true)
	List<String> findUser_IdByUserId(@Param("userId") String userId);

	@Query(value = "select mf.user_id from mt_follow mf where mf.following_id = :userId", nativeQuery = true)
	List<String> findUserIdByUser_Id(@Param("userId") String userId);
}