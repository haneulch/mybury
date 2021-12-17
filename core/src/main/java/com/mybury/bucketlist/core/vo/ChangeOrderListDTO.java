package com.mybury.bucketlist.core.vo;

import java.util.List;

import lombok.Data;

@Data
public class ChangeOrderListDTO {
	String userId;
	List<String> orders;
}

