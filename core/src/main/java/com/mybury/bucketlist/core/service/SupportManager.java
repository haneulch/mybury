package com.mybury.bucketlist.core.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;

import com.mybury.bucketlist.core.domain.SupportHistory;
import com.mybury.bucketlist.core.domain.SupportItem;

public interface SupportManager {
	List<SupportItem> getSupportItems();
	void saveSupportHistory(SupportHistory vo);
	void updateSupportHistory(SupportHistory vo);
	String getSupportTotalPrice(String userId);
	void saveSupportItem(List<SupportItem> list);
	
	List<SupportItem> findAllByOrderByItemPrice();

	SupportHistory findFirstByUserIdOrderByCreatedDtDesc(String userId);
	
	SupportHistory findOneByUserIdAndToken( String userId, String token);
	
	Page<SupportHistory> getSupportHistory(String search, Pageable page);
	
	List<SupportHistory> getSupportHistoryByUserId(String userId);
	
	void updateSusYn(int seq, String susYn);
	
	List<SupportHistory> findByUserIdAndSusYnOrderByCreatedDtDesc(String userId, Character susYn);
}