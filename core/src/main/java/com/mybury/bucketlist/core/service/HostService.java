package com.mybury.bucketlist.core.service;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mybury.bucketlist.core.domain.Bucketlist;
import com.mybury.bucketlist.core.vo.ChangeOrderListDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HostService {
	
	private final EntityManager em;
	
	@Transactional
	public void changeOrder(ChangeOrderListDTO dto) {
		if(!dto.getOrders().isEmpty()) {
			int i = 1;
			for(String id : dto.getOrders()) {
				Bucketlist bucketlist = em.find(Bucketlist.class, id);
				bucketlist.setOrderSeq(i);
				i ++;
			}
		}
	}
}
