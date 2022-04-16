package com.mybury.bucketlist.core.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.mybury.bucketlist.auth.dto.BucketlistResDTO;
import com.mybury.bucketlist.core.domain.Bucketlist;
import com.mybury.bucketlist.core.domain.Category;
import com.mybury.bucketlist.core.domain.User;
import com.mybury.bucketlist.core.repository.BucketlistRepository;
import com.mybury.bucketlist.core.repository.CategoryRepository;
import com.mybury.bucketlist.core.repository.UserRepository;
import com.mybury.bucketlist.core.util.DateUtil;
import com.mybury.bucketlist.core.vo.BucketlistModifyRequestVO;
import com.mybury.bucketlist.core.vo.BucketlistWriteRequestVO;
import com.mybury.bucketlist.core.vo.HomeRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BucketlistManagerImpl implements BucketlistManager {
  @Autowired
  private BucketlistRepository bucketlistRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private FileUploadService fileUploadService;

  @Override
  public List<Bucketlist> getBucketlists(HomeRequestVO requestVO) {
    List<Bucketlist> bucketlists = bucketlistRepository.getBucketlists(requestVO);
    for (Bucketlist bucketlist : bucketlists) {
      if (bucketlist.getDueDate() != null) {
        bucketlist.setDDay(DateUtil.getDday(bucketlist.getDueDate()));
      }
    }

    return bucketlists;
  }

  @Override
  public List<Bucketlist> getBucketlistByCategoryId(String categoryId) {
    List<Bucketlist> bucketlists = bucketlistRepository.getBucketlistByCategoryId(categoryId);
    for (Bucketlist bucketlist : bucketlists) {
      if (bucketlist.getDueDate() != null) {
        bucketlist.setDDay(DateUtil.getDday(bucketlist.getDueDate()));
      }
    }

    return bucketlists;
  }

  @Override
  public boolean existsPopupBucketlist(String userId, int popupPeriod) {
    return bucketlistRepository.existsPopupBucketlist(userId, popupPeriod);
  }

  @Override
  public List<Bucketlist> getDDayBucketlist(String userId, String filter) {
    List<Bucketlist> bucketlists = bucketlistRepository.getDDayBucketlist(userId, filter);

    for (Bucketlist bucketlist : bucketlists) {
      bucketlist.setDDay(DateUtil.getDday(bucketlist.getDueDate()));
    }

    return bucketlists;
  }

  @Override
  public List<Bucketlist> getBucketlistsByDDate(Date date, String userId) {
    return bucketlistRepository.getBucketlistsByDDate(date, userId);
  }

  @Override
  public Bucketlist getBucketlistById(String bucketlistId) {
    return bucketlistRepository.getOne(bucketlistId);
  }

  @Override
  public void writeBucketlist(BucketlistWriteRequestVO requestVO) {
    Bucketlist bucketlist = new Bucketlist();
    bucketlist.setTitle(requestVO.getTitle());
    bucketlist.setOpen(requestVO.isOpen());
    bucketlist.setStatus("1");
    bucketlist.setGoalCount(requestVO.getGoalCount());
    bucketlist.setDueDate(requestVO.getDDate());
    bucketlist.setMemo(requestVO.getMemo());

    if (requestVO.getImage1() != null) {
      String imgUrl1 = fileUploadService.upload(requestVO.getImage1());
      bucketlist.setImgUrl1(imgUrl1);
    }

    if (requestVO.getImage2() != null) {
      String imgUrl2 = fileUploadService.upload(requestVO.getImage2());
      bucketlist.setImgUrl2(imgUrl2);
    }

    if (requestVO.getImage3() != null) {
      String imgUrl3 = fileUploadService.upload(requestVO.getImage3());
      bucketlist.setImgUrl3(imgUrl3);
    }

    Category category = categoryRepository.getOne(requestVO.getCategoryId());
    bucketlist.setCategory(category);

    User user = userRepository.getOne(requestVO.getUserId());
    bucketlist.setUser(user);

    bucketlistRepository.save(bucketlist);
  }

  @Override
  public void saveBucketlist(Bucketlist bucketlist) {
    bucketlistRepository.save(bucketlist);
  }

  @Override
  public void modifyBucketlist(BucketlistModifyRequestVO requestVO) {
    Bucketlist bucketlist = bucketlistRepository.getOne(requestVO.getId());
    bucketlist.setTitle(requestVO.getTitle());
    bucketlist.setOpen(requestVO.isOpen());
    bucketlist.setGoalCount(requestVO.getGoalCount());
    bucketlist.setDueDate(requestVO.getDDate());
    bucketlist.setMemo(requestVO.getMemo());

    if (requestVO.isRemoveImg3())
      bucketlist.setImgUrl3(null);

    if (requestVO.isRemoveImg2()) {
      if (bucketlist.getImgUrl3() != null) {
        bucketlist.setImgUrl2(bucketlist.getImgUrl3());
        bucketlist.setImgUrl3(null);
      } else {
        bucketlist.setImgUrl2(null);
      }
    }

    if (requestVO.isRemoveImg1()) {
      if (bucketlist.getImgUrl2() != null) {
        bucketlist.setImgUrl1(bucketlist.getImgUrl2());
        bucketlist.setImgUrl2(null);
        if (bucketlist.getImgUrl3() != null) {
          bucketlist.setImgUrl2(bucketlist.getImgUrl3());
          bucketlist.setImgUrl3(null);
        }
      } else {
        bucketlist.setImgUrl1(null);
      }
    }

    if (requestVO.getImage1() != null) {
      String imgUrl1 = fileUploadService.upload(requestVO.getImage1());
      bucketlist.setImgUrl1(imgUrl1);
    }

    if (requestVO.getImage2() != null) {
      String imgUrl2 = fileUploadService.upload(requestVO.getImage2());
      bucketlist.setImgUrl2(imgUrl2);
    }

    if (requestVO.getImage3() != null) {
      String imgUrl3 = fileUploadService.upload(requestVO.getImage3());
      bucketlist.setImgUrl3(imgUrl3);
    }

    Category category = categoryRepository.getOne(requestVO.getCategoryId());
    bucketlist.setCategory(category);

    bucketlistRepository.save(bucketlist);
  }

  @Override
  public void deleteBucketlist(String bucketlistId) {
    bucketlistRepository.deleteById(bucketlistId);
  }

  @Override
  public int getStartedBucklistCount(String userId) {
    return bucketlistRepository.getStartedBucketlistCount(userId);
  }

  @Override
  public int getCompletedBucketlistCount(String userId) {
    return bucketlistRepository.getCompletedBucketlistCount(userId);
  }

  @Override
  public String getLastBucketlistId() {
    return bucketlistRepository.getLastBucketlistId();
  }

  @Override
  public Map<String, List<Bucketlist>> findBydDateIsNotNullAndUser_Id(String userId) {

    List<Bucketlist> list = bucketlistRepository.findByDueDateIsNotNullAndUser_Id(userId);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    Map<String, List<Bucketlist>> map = list
      .stream()
      .collect(Collectors.groupingBy(n -> sdf.format(n.getDueDate())));
    return map;
  }

  @Override
  public List<BucketlistResDTO> getBucketlistResDTO(HomeRequestVO requestVO) {
    return bucketlistRepository.getBucketlistResDTO(requestVO);
  }
}
