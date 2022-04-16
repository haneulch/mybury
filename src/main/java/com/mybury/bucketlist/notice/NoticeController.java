package com.mybury.bucketlist.notice;

import java.util.List;
import com.mybury.bucketlist.core.domain.Notice;
import com.mybury.bucketlist.core.util.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * fileName      : NoticeController
 * date         : 2022/04/02
 * description  : Notice Controller class
 */
@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {
  private final NoticeService noticeService;

  @GetMapping("/json")
  public ResponseEntity<Object> getDisplayedNotice() {
    List<NoticeListResult> notices = noticeService.getDisplayedNotice();
    return ResponseUtils.success(notices);
  }

  @GetMapping("/json/{seq}")
  public ResponseEntity<Object> getNoticeDetail(@PathVariable int seq) {
    Notice notice = noticeService.getNotice(seq);
    return ResponseUtils.success(notice);
  }

  @GetMapping("/{page}")
  public String pageMapping(@PathVariable String page) {
    return "/notice/" + page;
  }
}
