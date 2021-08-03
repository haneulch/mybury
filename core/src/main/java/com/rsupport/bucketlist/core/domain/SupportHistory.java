package com.rsupport.bucketlist.core.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Getter
@Setter
@Entity
@Table(name = "mt_support_history", uniqueConstraints = @UniqueConstraint(columnNames = { "seq" }))
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY
				, getterVisibility = JsonAutoDetect.Visibility.NONE
				, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class SupportHistory extends BaseTimestampEntity<String> {

	@Id
	@Column(name = "seq")
	private int seq;
	
	@Column(name = "item_id")
	private String itemId;

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
	
	@OneToOne
	@JoinColumn(name="item_id", insertable=false, updatable=false)
	private SupportItem item;
}

