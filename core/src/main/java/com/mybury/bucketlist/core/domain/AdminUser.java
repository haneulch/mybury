package com.mybury.bucketlist.core.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mt_admin_user", uniqueConstraints = @UniqueConstraint(name = "username", columnNames = "username"))
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class AdminUser {
	
	@Id
	private String username;
	
	@Column(name = "otp_key")
	private String otpKey;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "last_login_dt")
	private Date lastLoginDt;
	
	@Column(name = "created_dt")
	private Date createdDt;
}
