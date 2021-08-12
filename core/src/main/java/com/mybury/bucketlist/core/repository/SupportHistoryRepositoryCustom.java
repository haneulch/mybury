package com.mybury.bucketlist.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mybury.bucketlist.core.domain.SupportItem;

public interface SupportHistoryRepositoryCustom{
	List<SupportItem> getSupportItems();
}
