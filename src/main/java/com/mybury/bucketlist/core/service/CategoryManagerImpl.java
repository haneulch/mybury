package com.mybury.bucketlist.core.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mybury.bucketlist.core.domain.Bucketlist;
import com.mybury.bucketlist.core.domain.Category;
import com.mybury.bucketlist.core.domain.CategoryInfo;
import com.mybury.bucketlist.core.repository.BucketlistRepository;
import com.mybury.bucketlist.core.repository.CategoryRepository;
import com.mybury.bucketlist.core.v2.vo.CategoryPriorityRequest;
import com.mybury.bucketlist.core.vo.ModifyCategoryNameRequestVO;
import com.mybury.bucketlist.core.vo.ModifyCategoryPriorityRequestVO;
import com.mybury.bucketlist.core.vo.RemoveCategoryRequestVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("categoryManager")
public class CategoryManagerImpl implements CategoryManager {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private BucketlistRepository bucketlistRepository;

	@Override
	public void save(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public int getLastPriorityCategory(String userId) {
		Category category = categoryRepository.findTopByUser_IdOrderByPriorityDesc(userId);
		if(category == null) {
			return 0;
		}
		return category.getPriority();
//		return categoryRepository.getLastPriorityCategory(userId);
	}

	@Override
	@Transactional
	public void modifyName(ModifyCategoryNameRequestVO requestVO) {
		Category category = categoryRepository.getOne(requestVO.getId());
		category.setName(requestVO.getName());
		categoryRepository.save(category);
	}

	@Override
	@Transactional
	public void modifyPriority(ModifyCategoryPriorityRequestVO requestVO) {
		List<String> categoryList = requestVO.getCategoryIdList();
		for (int i = 0; i < categoryList.size(); i++) {
			Category category = categoryRepository.getOne(categoryList.get(i));
			category.setPriority(i);
			categoryRepository.save(category);
		}
	}

	@Override
	@Transactional
	public void remove(RemoveCategoryRequestVO requestVO) {
//		Category defaultCategory = categoryRepository.getDefaultCategory(requestVO.getUserId());
		Category defaultCategory = categoryRepository.findByIsDefaultAndUser_id('Y', requestVO.getUserId());

		List<String> categoryIdList = requestVO.getCategoryIdList();
		for (String categoryId : categoryIdList) {
			List<Bucketlist> bucketlists = bucketlistRepository.getBucketlistByCategoryId(categoryId);
			for (Bucketlist bucketlist : bucketlists) {
				bucketlist.setCategory(defaultCategory);
				bucketlistRepository.save(bucketlist);
			}
			categoryRepository.deleteById(categoryId);
		}
	}

	@Override
	public List<Category> getCategoryListByUserId(String userId) {
		return categoryRepository.getCategoryListByUserId(userId);
	}

	@Override
	public List<CategoryInfo> findCategoryInfo(String userId) {
		return categoryRepository.findCategoryInfo(userId);
	}

	@Override
	public void updateCategoryPriority(CategoryPriorityRequest request) {
		if(request.getCategories() != null) {
			int priority = 0;
			for(String categoryId : request.getCategories()) {
				Category category = em.getReference(Category.class, categoryId);
				category.setPriority(priority);
				categoryRepository.save(category);
				
				priority ++;
			}
		}
	}
}
