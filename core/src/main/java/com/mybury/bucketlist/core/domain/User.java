package com.mybury.bucketlist.core.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mybury.bucketlist.core.constants.ColumnEncryptionConstants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "mt_user", uniqueConstraints = @UniqueConstraint(name = "email", columnNames = "email"))
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class User extends BaseTimestampEntity<String> {

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	private String id;

	@Column
	@ColumnTransformer(read = ColumnEncryptionConstants.DEC_USER_EMAIL, write = ColumnEncryptionConstants.ENC_COLUMN)
	private String email;

	@Column(name = "account_type")
	private int accountType;

	@Column(name = "name")
	@ColumnTransformer(read = ColumnEncryptionConstants.DEC_USER_NAME, write = ColumnEncryptionConstants.ENC_COLUMN)
	private String name;

	@Column(name = "img_url")
	@ColumnTransformer(read = ColumnEncryptionConstants.DEC_USER_IMG_URL, write = ColumnEncryptionConstants.ENC_COLUMN)
	private String imgUrl;

	@Column(name = "user_seq")
	private int userSeq;
	
	@Column(length = 255)
	private String bio;
	
	@Column(length = 1)
	@ColumnDefault("'N'")
	private Character alarmYn;
	
	@JsonIgnore
	@Column(name = "last_login_dt")
	private Date lastLoginDt;

	@JsonIgnore
	@Column(columnDefinition = "bit default 1")
	private boolean enabled;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Category> categoryList = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Bucketlist> bucketlists = new ArrayList<>();
}
