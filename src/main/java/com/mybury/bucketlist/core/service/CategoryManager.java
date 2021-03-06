package com.mybury.bucketlist.core.service;

import java.util.List;

import com.mybury.bucketlist.core.domain.Category;
import com.mybury.bucketlist.core.domain.CategoryInfo;
import com.mybury.bucketlist.core.v2.vo.CategoryPriorityRequest;
import com.mybury.bucketlist.core.vo.ModifyCategoryNameRequestVO;
import com.mybury.bucketlist.core.vo.ModifyCategoryPriorityRequestVO;
import com.mybury.bucketlist.core.vo.RemoveCategoryRequestVO;

public interface CategoryManager {

	void save(Category category);

	int getLastPriorityCategory(String userId);

	void modifyName(ModifyCategoryNameRequestVO requestVO);

	void modifyPriority(ModifyCategoryPriorityRequestVO requestVO);

	void remove(RemoveCategoryRequestVO requestVO);

	List<Category> getCategoryListByUserId(String userId);
	
	List<CategoryInfo> findCategoryInfo(String userId);

	void updateCategoryPriority(CategoryPriorityRequest request);
}
