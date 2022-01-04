package com.mybury.bucketlist.core.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class HistoryRevisionRepositoryImpl implements HistoryRevisionRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public long getPreviousRevisionNumber(Class<?> clazz, String id) {
		AuditReader reader = AuditReaderFactory.get(entityManager);
		List<Number> revisionNumbers = reader.getRevisions(clazz, id);
		for (Number revisionNumber : revisionNumbers) {
			log.info("revisionNumber : " + revisionNumber);
		}

		return 0;
	}

	@Override
	public long getNextRevisionNumber() {
		return 0;
	}

	@Override
	public Date getRevisionDate() {
		return null;
	}

	@Override
	public void getHistoryListWithJoinField(Class<?> clazz) {
		AuditQuery query = AuditReaderFactory.get(entityManager).createQuery().forRevisionsOfEntity(clazz, false, true);
		query.addProjection(AuditEntity.revisionNumber().distinct()).addProjection(AuditEntity.revisionType());
	}
}
