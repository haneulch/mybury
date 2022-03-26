package com.mybury.bucketlist.core.vo;

import com.mybury.bucketlist.core.base.BaseRequestVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupportItemRequestVO extends BaseRequestVO {
	private String userId;
}