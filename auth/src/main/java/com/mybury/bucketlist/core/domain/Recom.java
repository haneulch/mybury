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
@Table(name = "mt_recom")
public class Recom extends BaseTimestampEntity<String> {
	
	@Id
	private int id;

	@Column(length = 100, nullable = false)
	private String keyword;
	
	@Column(name = "dp_yn", length = 1, nullable = false)
	@ColumnDefault("'Y'")
	private Character dpYn;
	
	@Column(name = "start_dt", length = 8)
	private String startDt;
	
	@Column(name = "end_dt", length = 8)
	private String endDt;
	
	@Column(length = 1)
	private Character type;
}