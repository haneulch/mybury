package com.mybury.bucketlist.core.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mt_support_history", uniqueConstraints = @UniqueConstraint(columnNames = { "seq" }))
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY
				, getterVisibility = JsonAutoDetect.Visibility.NONE
				, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupportHistory extends BaseTimestampEntity<String> {

	@Id
	@Column(name = "seq")
	private int seq;

	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "token")
	private String token;
	
	@Column(name="sus_yn")
	private Character susYn;
	
	@Column(name="del_yn")
	private Character delYn;
	
	@Column(name="created_dt")
	private Date createdDt;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "item_id", referencedColumnName = "id")
	SupportItem item;
}

