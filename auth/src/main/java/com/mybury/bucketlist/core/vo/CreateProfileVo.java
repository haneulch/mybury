package com.mybury.bucketlist.core.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProfileVo {
	private String userId;
	private String name;
	private boolean defaultImg;
}
