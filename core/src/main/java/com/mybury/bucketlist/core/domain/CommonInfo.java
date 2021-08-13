package com.mybury.bucketlist.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mt_common_info")
public class CommonInfo {

	@EmbeddedId
	private CommonInfoId id;
	
	@Column(length = 255, nullable = false)
	private String content;
	
	@Column(name = "code_des", length = 255)
	private String codeDes;
}

@Data
@Embeddable
class CommonInfoId implements Serializable {
	@Column(name = "parent_code", length = 4)
	private String parentCode;
	
	@Column(length = 2)
	private String code;
}
