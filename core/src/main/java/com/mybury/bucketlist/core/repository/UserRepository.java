package com.mybury.bucketlist.core.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mybury.bucketlist.core.domain.User;
import com.mybury.bucketlist.core.domain.UserMapping;
import com.mybury.bucketlist.core.domain.UserSummary;

public interface UserRepository extends JpaRepository<User, String>, UserRepositoryCustom {

	Page<UserMapping> findByIdContainsOrEmailContainsOrNameContainsOrderByName(String id, String email, String name, Pageable page);
	
	UserMapping findOneById(String userId);
	
	@Query(value = ""
			+ "select "
			+ "		mu.id"
			+ "		, mu.img_url"
			+ "		, mu.name "
			+ "from mt_user mu "
			+ "where mu.id in ("
			+ "		select "
			+ "			mf.following_id "
			+ "		from mt_follow mf "
			+ "		where mf.user_id = :userId)", nativeQuery = true)
	List<UserSummary> findFollowings(@Param("userId") String userId);
	
	@Query(value = ""
			+ "select "
			+ "		mu.id"
			+ "		, mu.img_url"
			+ "		, mu.name "
			+ "from mt_user mu "
			+ "where mu.id in ("
			+ "		select "
			+ "			mf.user_id "
			+ "		from mt_follow mf "
			+ "		where mf.following_id = :userId)", nativeQuery = true)
	List<UserSummary> findFollowers(@Param("userId") String userId);
}
