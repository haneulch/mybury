package com.mybury.bucketlist.core.v2.vo;

import javax.annotation.Nullable;

import com.mybury.bucketlist.core.domain.Badge;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "My > 프로필")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponse {
	
	private String nickname;
	
	@Nullable
	private String profileImg;
	
	private Badge badge;
	
	@Nullable
	private String bio;
}
