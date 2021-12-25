package com.mybury.bucketlist.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mt_seq")
@NoArgsConstructor
@AllArgsConstructor
public class Sequence {
	
	@Id
	private String seqName;
	
	@Column(name = "nextVal", columnDefinition = "INT(12) DEFAULT 1")
	private int nextVal;
}