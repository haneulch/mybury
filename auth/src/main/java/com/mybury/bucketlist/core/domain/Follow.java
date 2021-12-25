package com.mybury.bucketlist.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mybury.bucketlist.core.domain.id.FollowId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mt_follow")
@IdClass(FollowId.class)
public class Follow extends BaseTimestampEntity<String> {
	
	@Id
	@Column(name = "user_id", columnDefinition = "VARCHAR(255) NOT NULL")
	private String userId;
	
	@Id
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "following_id", referencedColumnName = "id")
	private User user;
}