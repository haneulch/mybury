package com.mybury.bucketlist.core.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mybury.bucketlist.core.domain.Notice;

public interface NoticeRepository extends JpaRepository<Notice, String> {

	List<Notice> findByStartDtLessThanEqualAndEndDtGreaterThanEqualAndDpYn(String today, String today2, Character dpYn);

}
