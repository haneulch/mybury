package com.mybury.bucketlist.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mybury.bucketlist.core.domain.Category;
import com.mybury.bucketlist.core.domain.CategoryInfo;

public interface CategoryRepository extends JpaRepository<Category, String>, CategoryRepositoryCustom {

	@Query(value = ""
			+ "select " + 
			"		mc.id" + 
			"		, mc.name" + 
			"		, (select count(1) from mt_bucketlist mb where mb.category_id = mc.id) as categoryCount" + 
			"	from mt_category mc" + 
			"	where mc.user_id = :userId", nativeQuery = true)
	List<CategoryInfo> findCategoryInfo(@Param("userId") String userId);
	
	void deleteByUserId(String userId);
}
