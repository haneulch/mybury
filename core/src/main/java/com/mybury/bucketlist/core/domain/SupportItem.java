package com.mybury.bucketlist.core.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mybury.bucketlist.core.constants.ColumnEncryptionConstants;

import lombok.AllArgsConstructor;
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
import javax.persistence.GenerationType;
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
@Table(name = "mt_support_item", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }))
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY
				, getterVisibility = JsonAutoDetect.Visibility.NONE
				, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupportItem extends BaseTimestampEntity<String> {

	@Id
	@Column(nullable = false, columnDefinition = "INT(12) NOT NULL")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="item_price")
	private int itemPrice;
	
	@Column(name="item_img")
	private String itemImg;
	
	@Column(name="item_name")
	@ColumnTransformer(read = ColumnEncryptionConstants.DEC_ITEM_NAME, write = ColumnEncryptionConstants.ENC_COLUMN)
	private String itemName;
	
	@Column(name="google_key")
	private String googleKey;
	
	@Column(name="dp_yn")
	private Character dpYn;
}