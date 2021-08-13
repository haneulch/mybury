package com.mybury.bucketlist.core.v2.vo;

import javax.annotation.Nullable;

import com.mybury.bucketlist.core.domain.Badge;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponse {
	private String nickname;
	
	@Nullable
	private String profileImg;
	
//	@Nullable
//	private String badgeType;
//	
//	@Nullable
//	private String badgeTitle;
	
	private Badge badge;
	
	@Nullable
	private String bio;
}