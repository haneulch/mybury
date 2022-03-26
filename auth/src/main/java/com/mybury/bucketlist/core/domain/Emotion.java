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
@Table(name = "mt_emotion")
public class Emotion {

	@Id
	private String id;
	
	@Column(name = "img_url", length = 255, nullable = false)
	private String imgUrl;
	
	@Column(length = 255, nullable = false)
	private String title;
	
	@Column(name = "dp_yn", length = 1)
	@ColumnDefault("'Y'")
	private Character dpYn;
}