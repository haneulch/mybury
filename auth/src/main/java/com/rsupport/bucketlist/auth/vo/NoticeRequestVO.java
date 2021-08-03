package com.rsupport.bucketlist.auth.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeRequestVO {
	private int seq;
	private String title;
	private String content;
	private String startDt;
	private String endDt;
	private String dpYn;
}
