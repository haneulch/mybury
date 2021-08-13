package com.mybury.bucketlist.core.domain.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.mybury.bucketlist.core.domain.Badge;
import com.mybury.bucketlist.core.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class BadgeUserId implements Serializable {
	@Column(name = "user_id", length = 255, nullable = false)
	private String userId;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "badge_id", referencedColumnName = "id")
	private Badge badge;
}