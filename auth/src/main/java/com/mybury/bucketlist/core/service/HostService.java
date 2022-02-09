package com.mybury.bucketlist.core.service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;
import com.mybury.bucketlist.auth.dto.BucketlistResDTO;
import com.mybury.bucketlist.auth.dto.CategoryResDTO;
import com.mybury.bucketlist.auth.dto.SearchResDTO;
import com.mybury.bucketlist.auth.vo.SearchRequestDTO;
import com.mybury.bucketlist.core.domain.Bucketlist;
import com.mybury.bucketlist.core.repository.BucketlistRepository;
import com.mybury.bucketlist.core.repository.CategoryRepository;
import com.mybury.bucketlist.core.vo.BucketlistVO;
import com.mybury.bucketlist.core.vo.CategoryVO;
import com.mybury.bucketlist.core.vo.ChangeOrderListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HostService {

	private final EntityManager em;
	private final BucketlistRepository bucketlistRepository;
	private final CategoryRepository categoryRepository;

	@Transactional
	public void changeOrder(ChangeOrderListDTO dto) {
		if (dto.getOrders().isEmpty()) {
			return;
		}
		int i = 1;
		for(String id : dto.getOrders()) {
			Bucketlist bucketlist = em.find(Bucketlist.class, id);
			bucketlist.setOrderSeq(i);
			i ++;
		}
	}

  public SearchResDTO searchBucketlist(SearchRequestDTO request) {
		SearchResDTO searchResponseVO = new SearchResDTO();
		List<CategoryVO> categoryVOS = null;
		List<CategoryResDTO> categoryResDTOS = null;
		List<BucketlistVO> bucketlistVOS = null;
		List<BucketlistResDTO> bucketlistResDTOS = null;

		switch (request.getFilter()) {
			case "category" :
				categoryVOS = categoryRepository.findByUser_IdAndNameContaining(request.getUserId(), request.getSearchText());
				categoryResDTOS = categoryVOS.stream()
					.map(c -> new CategoryResDTO(c.getId(), c.getName(), bucketlistRepository.countByCategory_Id(c.getId())))
					.collect(Collectors.toList());
				searchResponseVO.setCategories(categoryResDTOS);
				break;
			case "dday" :
				bucketlistVOS = bucketlistRepository.findBydDateNotNullAndTitleContainingAndUser_Id(request.getSearchText(), request.getUserId());
				bucketlistResDTOS = bucketlistVOS.stream()
					.map(b -> new BucketlistResDTO(b).init())
					.collect(Collectors.toList());
				searchResponseVO.setBucketlists(bucketlistResDTOS);
				break;
			default:
				categoryVOS = categoryRepository.findByUser_IdAndNameContaining(request.getUserId(), request.getSearchText());
				categoryResDTOS = categoryVOS.stream()
					.map(c -> new CategoryResDTO(c.getId(), c.getName(), bucketlistRepository.countByCategory_Id(c.getId())))
					.collect(Collectors.toList());
				searchResponseVO.setCategories(categoryResDTOS);

				bucketlistVOS = bucketlistRepository.findBydDateNotNullAndTitleContainingAndUser_Id(request.getSearchText(), request.getUserId());
				bucketlistResDTOS = bucketlistVOS.stream()
					.map(b -> new BucketlistResDTO(b).init())
					.collect(Collectors.toList());
				searchResponseVO.setBucketlists(bucketlistResDTOS);
				break;
		}
		return searchResponseVO;
  }
}
