package com.mybury.bucketlist.core.repository;

import com.mybury.bucketlist.core.domain.PushMessage;
import com.mysema.query.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mybury.bucketlist.core.domain.QPushMessage;

public class PushMessageRepositoryImpl implements PushMessageRepositoryCustom {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public PushMessage getPushMessage() {
    JPAQuery query = new JPAQuery(entityManager);
    QPushMessage pushMessage = QPushMessage.pushMessage;

    query.from(pushMessage);
    return query.singleResult(pushMessage);
  }
}
