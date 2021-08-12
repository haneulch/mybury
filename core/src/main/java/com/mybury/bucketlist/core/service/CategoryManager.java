package com.mybury.bucketlist.core.service;

import com.mybury.bucketlist.core.domain.Category;
import com.mybury.bucketlist.core.vo.ModifyCategoryNameRequestVO;
import com.mybury.bucketlist.core.vo.ModifyCategoryPriorityRequestVO;
import com.mybury.bucketlist.core.vo.RemoveCategoryRequestVO;

import java.util.List;

public interface CategoryManager {

  void save(Category category);

  int getLastPriorityCategory(String userId);

  void modifyName(ModifyCategoryNameRequestVO requestVO);

  void modifyPriority(ModifyCategoryPriorityRequestVO requestVO);

  void remove(RemoveCategoryRequestVO requestVO);

  List<Category> getCategoryListByUserId(String userId);
}
