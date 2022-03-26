package com.mybury.bucketlist.core.domain;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mt_block_user")
public class BlockUser extends BaseTimestampEntity<String> {
	
	@Id
	@Column(length = 255)
	private String email;
	
	@Column(length = 255)
	private String reason;
}
