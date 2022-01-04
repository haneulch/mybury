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
@Table(name = "mt_challenge_join")
public class ChallengeJoin extends BaseTimestampEntity<String> {

	@Id
	private int id;
	
	@Column(name = "chel_id", length = 255, nullable = false)
	private String chelId;
	
	@Column(name = "user_id", length = 255, nullable = false)
	private String userId;
	
	@Column(name = "goal_count", length = 11)
	private int goalCount;
}
