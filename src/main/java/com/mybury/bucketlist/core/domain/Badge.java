package com.mybury.bucketlist.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mt_badge")
public class Badge {
	
	@Id
	@Column(length = 255, nullable = false)
	private String id;
	
	@Column(length = 255, nullable = false)
	private String title;
	
	@Column(name = "goal_count", length = 11)
	@ColumnDefault("0")
	private int goalCount;
	
	@Column(name = "type", length = 255)
	private String type;
}
