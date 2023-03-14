package com.mybury.bucketlist.core.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import com.mybury.bucketlist.core.domain.Bucketlist;
import com.mybury.bucketlist.core.domain.Category;
import com.mybury.bucketlist.core.domain.CategoryInfo;
import com.mybury.bucketlist.core.repository.BucketlistRepository;
import com.mybury.bucketlist.core.repository.CategoryRepository;
import com.mybury.bucketlist.core.vo.ModifyCategoryNameRequestVO;
import com.mybury.bucketlist.core.vo.ModifyCategoryPriorityRequestVO;
import com.mybury.bucketlist.core.vo.RemoveCategoryRequestVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("categoryManager")
@AllArgsConstructor
public class CategoryManagerImpl implements CategoryManager {
  @PersistenceContext
  private EntityManager em;

  private final CategoryRepository categoryRepository;
  private final BucketlistRepository bucketlistRepository;

  @Override
  public void save(Category category) {
    categoryRepository.save(category);
  }

  @Override
  public int getLastPriorityCategory(String userId) {
    Category category = categoryRepository.findTopByUser_IdOrderByPriorityDesc(userId);
    if (category == null) {
      return 0;
    }
    return category.getPriority();
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
}
