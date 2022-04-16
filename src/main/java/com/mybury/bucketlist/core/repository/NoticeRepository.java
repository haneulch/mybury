package com.mybury.bucketlist.core.repository;

import java.util.List;
import com.mybury.bucketlist.notice.NoticeListResult;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mybury.bucketlist.core.domain.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
	List<Notice> findByStartDtLessThanEqualAndEndDtGreaterThanEqualAndDpYn(String today, String today2, Character dpYn);
	List<NoticeListResult> findByDpYnOrderByCreatedDtDesc(Character dpYn);
}
