package com.mybury.bucketlist.core.repository;

import com.mybury.bucketlist.core.domain.User;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mybury.bucketlist.core.domain.QUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UserRepositoryImpl implements UserRepositoryCustom {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public User getUserByEmail(String email) {
    JPAQuery query = new JPAQuery(entityManager);
    QUser user = QUser.user;
    query.from(user).where(user.email.eq(email));
    return query.uniqueResult(user);
  }
}
