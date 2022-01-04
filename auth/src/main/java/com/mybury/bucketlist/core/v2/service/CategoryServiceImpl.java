package com.mybury.bucketlist.core.v2.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybury.bucketlist.core.domain.Category;
import com.mybury.bucketlist.core.domain.CategoryInfo;
import com.mybury.bucketlist.core.domain.User;
import com.mybury.bucketlist.core.repository.CategoryRepository;
import com.mybury.bucketlist.core.v2.vo.CategoryPriorityRequest;
import com.mybury.bucketlist.core.v2.vo.CategoryRequest;

@Service
public class CategoryServiceImpl implements CategoryService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private CategoryRepository categoryRepository;

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

	@Override
	public void updateCategoryName(CategoryRequest request) {
		Category category = em.getReference(Category.class, request.getId());
		category.setName(request.getName());
		categoryRepository.save(category);
	}

	@Override
	public void delete(String id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public String save(CategoryRequest request) {
		
		User user = em.getReference(User.class, request.getUserId());
		
		Category category = new Category();
		category.setName(request.getName());
		int priority = categoryRepository.getLastPriorityCategory(request.getUserId());
		category.setPriority(priority + 1); 
		category.setUser(user);
		
		categoryRepository.saveAndFlush(category);
		
		return category.getId();
	}
}
