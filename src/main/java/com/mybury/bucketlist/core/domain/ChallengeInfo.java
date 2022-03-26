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
@Table(name = "mt_challenge_info")
public class ChallengeInfo {
	
	@Id
	private String id;
	
	@Column(length = 500)
	private String content;
}
