package com.mybury.bucketlist.core.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mybury.bucketlist.core.domain.SupportHistory;

public interface SupportHistoryRepository extends JpaRepository<SupportHistory, Integer>, SupportHistoryRepositoryCustom{
	@Query(value = "select sum(msi.item_price) as total_price from mt_support_history msh, mt_support_item msi where msh.user_id = :userId and msh.item_id = msi.id and msh.sus_yn = 'Y'", nativeQuery=true)
	String getSupportTotalPrice(@Param("userId") String userId);
	
	SupportHistory findFirstByUserIdOrderByCreatedDtDesc(String userId);
	
	SupportHistory findOneByUserIdAndToken(String userId, String token);

	Page<SupportHistory> findByUserIdContainsOrTokenContainsOrderByCreatedDt(String userId, String token, Pageable page);

	List<SupportHistory> findByUserIdOrderBySeqDesc(String userId);
	
	long countByUserIdAndToken(String userId, String token);

	List<SupportHistory> findByUserIdAndSusYnOrderByCreatedDtDesc(String userId, Character susYn);
}
