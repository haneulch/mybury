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
@Table(name = "mt_recent_keyword")
public class RecentKeyword extends BaseTimestampEntity<String>{

	@Id
	@Column(name = "user_id", columnDefinition = "VARCHAR(255)")
	private String userId;
	
	@Column(columnDefinition = "VARCHAR(255) NOT NULL")
	private String keyword;
}