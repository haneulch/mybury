package com.mybury.bucketlist.core.repository;

import java.util.List;
import com.mybury.bucketlist.core.domain.Bucketlist;
import com.mybury.bucketlist.core.vo.BucketlistVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketlistRepository extends JpaRepository<Bucketlist, String>, BucketlistRepositoryCustom {
	List<Bucketlist> findBydDateIsNotNullAndUser_Id(String userId);
	List<BucketlistVO> findByTitleContainingAndUser_Id(String searchText, String userId);
	List<Bucketlist> findByCategory_IdIn(List<String> categoryIds);
	List<Bucketlist> findByCategory_NameContainingAndUser_Id(String categoryName, String userId);
	List<Bucketlist> findByCategory_NameContainingAndUser_IdOrTitleContainingAndUser_Id(String categoryName,
		String userId1, String title, String userId);
	int countByCategory_Id(String categoryId);
}
