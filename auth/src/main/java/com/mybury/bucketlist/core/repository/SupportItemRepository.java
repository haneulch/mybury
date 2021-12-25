package com.mybury.bucketlist.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybury.bucketlist.core.domain.SupportItem;

public interface SupportItemRepository extends JpaRepository<SupportItem, String> {
	
	List<SupportItem> findAllByOrderByItemPrice();
}
