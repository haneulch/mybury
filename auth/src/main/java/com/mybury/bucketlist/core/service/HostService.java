package com.mybury.bucketlist.core.service;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.mybury.bucketlist.auth.vo.BucketlistViewResponseVO;
import com.mybury.bucketlist.auth.vo.SearchRequestDTO;
import com.mybury.bucketlist.core.domain.Category;
import com.mybury.bucketlist.core.domain.CategoryId;
import com.mybury.bucketlist.core.domain.CategoryInfo;
import com.mybury.bucketlist.core.repository.BucketlistRepository;
import com.mybury.bucketlist.core.repository.CategoryRepository;
import com.mybury.bucketlist.core.vo.BucketlistVO;
import com.mybury.bucketlist.core.vo.SearchResponseVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mybury.bucketlist.core.domain.Bucketlist;
import com.mybury.bucketlist.core.vo.ChangeOrderListDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HostService {
	
	private final EntityManager em;
	private final BucketlistRepository bucketlistRepository;
	private final CategoryRepository categoryRepository;
	
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

  public SearchResponseVO searchBucketlist(SearchRequestDTO request) {
		SearchResponseVO searchResponseVO = new SearchResponseVO();
		switch (request.getFilter()) {
			case "category" :
				searchResponseVO.setCategories(categoryRepository.findByUser_IdAndNameContaining(request.getUserId(),
					request.getSearchText()));
				break;
			case "dday" :
				List<BucketlistVO> bucketlists =
					bucketlistRepository.findProjectionByTitleContainingAndUser_Id(request.getSearchText(),
				request.getUserId());
				searchResponseVO.setBucketlists(bucketlists.stream().filter(v -> v.getDDate() != null).collect(Collectors.toList()));
				break;
			default:
				searchResponseVO.setCategories(categoryRepository.findByUser_IdAndNameContaining(request.getUserId(),
					request.getSearchText()));
				searchResponseVO.setBucketlists(bucketlistRepository.findProjectionByTitleContainingAndUser_Id(request.getSearchText(),
					request.getUserId()));
				break;
		}
		return searchResponseVO;
  }
}
