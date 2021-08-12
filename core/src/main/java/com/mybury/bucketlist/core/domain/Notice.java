package com.mybury.bucketlist.core.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mybury.bucketlist.core.constants.ColumnEncryptionConstants;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Getter
@Setter
@Entity
@Table(name = "mt_notice", uniqueConstraints = @UniqueConstraint(columnNames = { "seq" }))
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY
				, getterVisibility = JsonAutoDetect.Visibility.NONE
				, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@NoArgsConstructor
public class Notice extends BaseTimestampEntity<String> {

	@Id
	@Column
	private int seq;

	@Column(name="title")
	private String title;
	
	@Column(name="content")
	private String content;
	
	@Column(name="start_dt")
	private String startDt;
	
	@Column(name="end_dt")
	private String endDt;
	
	@Column(name="dp_yn")
	private Character dpYn;
	
	@Builder
	public Notice(int seq, String title, String content, String startDt, String endDt, Character dpYn) {
		this.seq = seq;
		this.title = title;
		this.content = content;
		this.startDt = startDt;
		this.endDt = endDt;
		this.dpYn = dpYn;
	}
}