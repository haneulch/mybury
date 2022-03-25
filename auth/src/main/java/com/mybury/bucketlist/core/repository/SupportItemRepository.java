package com.mybury.bucketlist.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybury.bucketlist.core.domain.SupportItem;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportItemRepository extends JpaRepository<SupportItem, String> {
	List<SupportItem> findByOrderByItemPrice();
}
