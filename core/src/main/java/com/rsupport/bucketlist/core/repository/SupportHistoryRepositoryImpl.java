package com.rsupport.bucketlist.core.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;
import com.mysema.query.jpa.impl.JPAQuery;
import com.rsupport.bucketlist.core.domain.QSupportHistory;
import com.rsupport.bucketlist.core.domain.QSupportItem;
import com.rsupport.bucketlist.core.domain.SupportItem;
import com.rsupport.bucketlist.core.domain.SupportTotal;

@Repository("supportHistoryRepository")
public class SupportHistoryRepositoryImpl implements SupportHistoryRepositoryCustom{

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
