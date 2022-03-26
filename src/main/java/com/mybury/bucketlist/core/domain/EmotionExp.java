package com.mybury.bucketlist.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mt_emotion_exp")
public class EmotionExp extends BaseTimestampEntity<String> {
	
	@EmbeddedId
	private EmotionExpId id;

	@Column(name = "emot_id", length = 255, nullable = false)
	private String emotId;
}

@Data
@Embeddable
class EmotionExpId implements Serializable {
	
	@Column(name = "user_id", length = 255)
	private String userId;
	
	@Column(name = "buk_id", length = 255)
	private String bukId;
}
