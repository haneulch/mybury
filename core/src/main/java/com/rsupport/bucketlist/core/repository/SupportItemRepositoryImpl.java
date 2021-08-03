package com.rsupport.bucketlist.core.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository("supportItemRepository")
public class SupportItemRepositoryImpl implements SupportItemRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager; 
}
