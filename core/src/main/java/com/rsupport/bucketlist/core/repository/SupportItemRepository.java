package com.rsupport.bucketlist.core.repository;

import com.rsupport.bucketlist.core.domain.SupportHistory;
import com.rsupport.bucketlist.core.domain.SupportItem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SupportItemRepository extends JpaRepository<SupportItem, String>, SupportItemRepositoryCustom{
	
	List<SupportItem> findAllByOrderByItemPrice();
}
