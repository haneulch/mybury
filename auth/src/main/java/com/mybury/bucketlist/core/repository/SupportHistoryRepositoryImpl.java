package com.mybury.bucketlist.core.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mybury.bucketlist.core.domain.QSupportItem;
import com.mybury.bucketlist.core.domain.SupportItem;
import com.mysema.query.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

@Repository
public class SupportHistoryRepositoryImpl implements SupportHistoryRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;
	  
	@Override
	public List<SupportItem> getSupportItems() {
		JPAQuery query = new JPAQuery(entityManager);
		QSupportItem supportItem = QSupportItem.supportItem;

		query.from(supportItem).orderBy(supportItem.itemPrice.asc());

		return query.list(supportItem);
	}
}
