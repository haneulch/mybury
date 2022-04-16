package com.mybury.bucketlist.core.v2.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import com.mybury.bucketlist.core.domain.Category;
import com.mybury.bucketlist.core.domain.CategoryInfo;
import com.mybury.bucketlist.core.domain.User;
import com.mybury.bucketlist.core.repository.CategoryRepository;
import com.mybury.bucketlist.core.v2.vo.CategoryPriorityRequest;
import com.mybury.bucketlist.core.v2.vo.CategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
	@PersistenceContext
	private EntityManager em;

	private final CategoryRepository categoryRepository;
	public List<CategoryInfo> findCategoryInfo(String userId) {
		return categoryRepository.findCategoryInfo(userId);
	}

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

	public void updateCategoryName(CategoryRequest request) {
		Category category = em.getReference(Category.class, request.getId());
		category.setName(request.getName());
		categoryRepository.save(category);
	}

	public void delete(String id) {
		categoryRepository.deleteById(id);
	}

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
