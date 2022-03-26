package com.mybury.bucketlist.auth.vo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mybury.bucketlist.core.base.BaseResponseVO;
import com.mybury.bucketlist.core.constants.ApiReturnCodes;
import com.mybury.bucketlist.core.domain.Notice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoticeResponseVO extends BaseResponseVO {
	@JsonProperty
	private List<Notice> notice;

	public NoticeResponseVO(List<Notice> notice) {
	    super(ApiReturnCodes.OK);
	    this.notice = notice;
	}
}