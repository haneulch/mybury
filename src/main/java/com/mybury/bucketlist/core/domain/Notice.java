package com.mybury.bucketlist.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mt_notice", uniqueConstraints = @UniqueConstraint(columnNames = {"seq"}))
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY
  , getterVisibility = JsonAutoDetect.Visibility.NONE
  , isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@NoArgsConstructor
public class Notice extends BaseTimestampEntity<String> {

  @Id
  @Column
  private int seq;

  @Column
  private String tag;

  @Column
  private String title;

  @Column
  private String content;

  @Column(name = "start_dt")
  private String startDt;

  @Column(name = "end_dt")
  private String endDt;

  @Column(name = "dp_yn")
  private Character dpYn;

  @Column(name = "faq_yn")
  private Character faqYn;

  @Builder
  public Notice(int seq, String title, String content, String startDt, String endDt, Character dpYn, Character faqYn) {
    this.seq = seq;
    this.title = title;
    this.content = content;
    this.startDt = startDt;
    this.endDt = endDt;
    this.dpYn = dpYn;
    this.faqYn = faqYn;
  }
}
