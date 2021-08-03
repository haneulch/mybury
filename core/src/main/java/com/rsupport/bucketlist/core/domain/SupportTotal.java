package com.rsupport.bucketlist.core.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.rsupport.bucketlist.core.constants.ColumnEncryptionConstants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

//@Getter
//@Setter
//@Entity
//@Table(name = "mt_support_history")
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY
//				, getterVisibility = JsonAutoDetect.Visibility.NONE
//				, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class SupportTotal extends BaseTimestampEntity<String> {

//	@Id
//	@Column
//	private String seq;
//	
//	@Column(name = "item_id")
//	private String itemId;
//
//	@Column(name = "user_id")
//	private String userId;
}