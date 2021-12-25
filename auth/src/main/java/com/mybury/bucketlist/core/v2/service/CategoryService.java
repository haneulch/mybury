package com.mybury.bucketlist.core.v2.service;

import java.util.List;

import com.mybury.bucketlist.core.domain.CategoryInfo;
import com.mybury.bucketlist.core.v2.vo.CategoryPriorityRequest;
import com.mybury.bucketlist.core.v2.vo.CategoryRequest;

public interface CategoryService {
	List<CategoryInfo> findCategoryInfo(String userId);
	void updateCategoryPriority(CategoryPriorityRequest request);
	void updateCategoryName(CategoryRequest request);
	void delete(String id);
	String save(CategoryRequest request);
}
