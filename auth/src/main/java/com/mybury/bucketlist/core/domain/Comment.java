package com.mybury.bucketlist.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mt_comment")
public class Comment extends BaseTimestampEntity<String> {
	
	@Id
	private int id;

	@Column(name = "buk_id", length = 255, nullable = false)
	private String bukId;
	
	@Column(name = "user_id", length = 255, nullable = false)
	private String userId;
	
	@Column(length = 500)
	private String content;
	
	@Column(length = 1)
	@ColumnDefault("'N'")
	private Character reported;
}
