package com.mybury.bucketlist.core.v2.vo;

import com.mybury.bucketlist.core.domain.Badge;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "My > 프로필")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponse {

	@Schema(description = "이름")
	private String nickname;

	@Schema(description = "프로필 이미지")
	private String profileImage;

	@Schema(description = "뱃지 정보")
	private Badge badge;

	@Schema(description = "bio(한줄설명)")
	private String bio;

	@Schema(description = "팔로워 수")
	private int followerCount;

	@Schema(description = "팔로잉 수")
	private int followingCount;
}
