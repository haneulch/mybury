package com.mybury.bucketlist.core.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.mybury.bucketlist.core.domain.id.BadgeUserId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mt_badge_user")
@IdClass(BadgeUserId.class)
public class BadgeUser {

	@Id
	@Column(name = "user_id", length = 255, nullable = false)
	private String userId;
	
	@Id
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "badge_id", referencedColumnName = "id")
	private Badge badge;
	
	@Column(name = "count", length = 11)
	private int count;
	
	@Column(name = "sus_yn", length = 1)
	@ColumnDefault("'N'")
	private Character susYn;
	
	@Column(name = "use_yn", length = 1)
	@ColumnDefault("'N'")
	private Character useYn;
}
