package com.mybury.bucketlist.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mt_report")
public class Report extends BaseTimestampEntity<String> {
	
	@Id
	private int id;
	
	@Column(name = "comment_id", length = 10)
	private int commentId;
	
	@Column(name = "buk_id", length = 255)
	private String bukId;
	
	@Column(name = "user_id", length = 255, nullable = false)
	private String userId;
	
	@Column(length = 2, nullable = false)
	private String reason;
}
