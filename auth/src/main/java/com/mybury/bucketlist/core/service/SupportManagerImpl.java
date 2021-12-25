package com.mybury.bucketlist.core.service;

import com.mybury.bucketlist.core.domain.SupportHistory;
import com.mybury.bucketlist.core.domain.SupportItem;
import com.mybury.bucketlist.core.repository.SupportHistoryRepository;
import com.mybury.bucketlist.core.repository.SupportItemRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupportManagerImpl implements SupportManager {

	private final SupportHistoryRepository supportHistoryRepository;
	private final SupportItemRepository supportItemRepository;

	@Override
	public List<SupportItem> getSupportItems() {
		return supportHistoryRepository.getSupportItems();
	}
	
	@Override
	public void saveSupportHistory(SupportHistory vo) {
		long count = supportHistoryRepository.countByUserIdAndToken(vo.getUserId(), vo.getToken());
		if( count == 0) {
			supportHistoryRepository.save(vo);
		}
	}
	
	@Override
	public void updateSupportHistory(SupportHistory vo) {
		supportHistoryRepository.save(vo);
	}

	@Override
	public String getSupportTotalPrice(String userId) {
		return supportHistoryRepository.getSupportTotalPrice(userId);
	}

	@Override
	public void saveSupportItem(List<SupportItem> list) {
		supportItemRepository.deleteAll();
		supportItemRepository.saveAll(list);
		
	}

	@Override
	public List<SupportItem> findAllByOrderByItemPrice() {
		return supportItemRepository.findAllByOrderByItemPrice();
	}

	@Override
	public SupportHistory findFirstByUserIdOrderByCreatedDtDesc(String userId) {
		return supportHistoryRepository.findFirstByUserIdOrderByCreatedDtDesc(userId);
	}
	
	@Override
	public SupportHistory findOneByUserIdAndToken(String userId, String token) {
		return supportHistoryRepository.findOneByUserIdAndToken(userId, token);
	}

	@Override
	public Page<SupportHistory> getSupportHistory(String search, Pageable page) {
		return supportHistoryRepository.findByUserIdContainsOrTokenContainsOrderByCreatedDt(search, search, page);
	}

	@Override
	public List<SupportHistory> getSupportHistoryByUserId(String userId) {
		return supportHistoryRepository.findByUserIdOrderBySeqDesc(userId);
	}
	
	@Override
	public void updateSusYn(int seq, String susYn) {
		SupportHistory supportHistory = supportHistoryRepository.getOne(seq);
		supportHistory.setSusYn(susYn.charAt(0));
		
		supportHistoryRepository.save(supportHistory);
	}

	@Override
	public List<SupportHistory> findByUserIdAndSusYnOrderByCreatedDtDesc(String userId, Character susYn) {
		return supportHistoryRepository.findByUserIdAndSusYnOrderByCreatedDtDesc(userId, susYn);
	}
}
