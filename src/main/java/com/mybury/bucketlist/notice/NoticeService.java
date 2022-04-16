package com.mybury.bucketlist.notice;

import java.util.List;
import com.mybury.bucketlist.core.domain.Notice;
import com.mybury.bucketlist.core.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * fileName      : NoticeService
 * date         : 2022/04/02
 * description  : Notice Service Class
 */
@Service
@RequiredArgsConstructor
public class NoticeService {
  private final NoticeRepository noticeRepository;

  public List<NoticeListResult> getDisplayedNotice() {
    return noticeRepository.findByDpYnOrderByCreatedDtDesc('Y');
  }

  public Notice getNotice(int seq) {
    return noticeRepository.findById(seq).orElse(null);
  }
}
