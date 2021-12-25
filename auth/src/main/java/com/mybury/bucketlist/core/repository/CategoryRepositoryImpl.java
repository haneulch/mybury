package com.mybury.bucketlist.core.repository;

import com.mybury.bucketlist.core.domain.Category;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mybury.bucketlist.core.domain.QCategory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepositoryCustom {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Category getDefaultCategory(String userId) {
    JPAQuery query = new JPAQuery(entityManager);
    QCategory category = QCategory.category;

    query.from(category)
            .where(category.user().id.eq(userId)
                    .and(category.name.eq("없음")));

    return query.uniqueResult(category);
  }

  @Override
  public int getLastPriorityCategory(String userId) {
    JPAQuery query = new JPAQuery(entityManager);
    QCategory category = QCategory.category;

    query.from(category).where(category.user().id.eq(userId));

    return query.uniqueResult(category.priority.max());
  }

  @Override
  public List<Category> getCategoryListByUserId(String userId) {
    JPAQuery query = new JPAQuery(entityManager);
    QCategory category = QCategory.category;

    query.from(category)
            .where(category.user().id.eq(userId))
            .orderBy(category.priority.asc());

    return query.list(category);
  }
}
