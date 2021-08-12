package com.mybury.bucketlist.core.repository;

import java.util.List;

import com.mybury.bucketlist.core.domain.Category;

public interface CategoryRepositoryCustom {

  Category getDefaultCategory(String userId);

  int getLastPriorityCategory(String userId);

  List<Category> getCategoryListByUserId(String userId);
}
