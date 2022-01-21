package com.mybury.bucketlist.core.repository;

import java.util.Date;
import java.util.List;

import com.mybury.bucketlist.auth.vo.BucketlistViewResponseVO;
import com.mybury.bucketlist.core.domain.Category;
import com.mybury.bucketlist.core.domain.CategoryId;
import com.mybury.bucketlist.core.vo.BucketlistVO;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mybury.bucketlist.core.domain.Bucketlist;
import org.springframework.data.jpa.repository.Query;

public interface BucketlistRepository extends JpaRepository<Bucketlist, String>, BucketlistRepositoryCustom {
	List<Bucketlist> findBydDateIsNotNullAndUser_Id(String userId);
	List<BucketlistVO> findProjectionByTitleContainingAndUser_Id(String searchText, String userId);
	List<Bucketlist> findByCategory_IdIn(List<String> categoryIds);
	List<Bucketlist> findByCategory_NameContainingAndUser_Id(String categoryName, String userId);
	List<Bucketlist> findByCategory_NameContainingAndUser_IdOrTitleContainingAndUser_Id(String categoryName,
		String userId1, String title, String userId);
}
