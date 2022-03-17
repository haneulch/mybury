package com.mybury.bucketlist.auth.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mybury.bucketlist.core.base.BaseResponseVO;
import com.mybury.bucketlist.core.constants.ApiReturnCodes;
import com.mybury.bucketlist.core.domain.Bucketlist;
import com.mybury.bucketlist.core.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BucketlistViewResponseVO extends BaseResponseVO {

	private static String authServerAddress = "https://www.my-bury.com";

	@JsonProperty
	private String title;

	@JsonProperty
	private boolean open;

	@JsonProperty
	private String category;

	@JsonProperty
	@JsonFormat(pattern = "yyyy/MM/dd")
	private Date dDate;

	@JsonProperty
	@JsonFormat(pattern = "yyyy/MM/dd")
	private Date completedDt;

	@JsonProperty
	private Integer dDay;

	@JsonProperty
	private int userCount;

	@JsonProperty
	private int goalCount;

	@JsonProperty
	private String memo;

	@JsonProperty
	private String imgUrl1;

	@JsonProperty
	private String imgUrl2;

	@JsonProperty
	private String imgUrl3;

	public BucketlistViewResponseVO(Bucketlist bucketlist) {
		super(ApiReturnCodes.OK);
		this.title = bucketlist.getTitle();
		this.open = bucketlist.isOpen();
		this.category = bucketlist.getCategory().getName();
		this.dDate = bucketlist.getDDate();
		if (bucketlist.getDDate() != null)
			this.dDay = DateUtil.getDday(bucketlist.getDDate());
		this.completedDt = bucketlist.getCompletedDt();
		this.userCount = bucketlist.getUserCount();
		this.goalCount = bucketlist.getGoalCount();
		this.memo = bucketlist.getMemo();
		this.imgUrl1 = bucketlist.getImgUrl1() != null ? authServerAddress + bucketlist.getImgUrl1() : "";
		this.imgUrl2 = bucketlist.getImgUrl2() != null ? authServerAddress + bucketlist.getImgUrl2() : "";
		this.imgUrl3 = bucketlist.getImgUrl3() != null ? authServerAddress + bucketlist.getImgUrl3() : "";
	}
}
