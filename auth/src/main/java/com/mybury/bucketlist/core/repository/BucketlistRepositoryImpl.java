package com.mybury.bucketlist.core.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import com.mybury.bucketlist.auth.dto.BucketlistResDTO;
import com.mybury.bucketlist.auth.dto.QBucketlistResDTO;
import com.mybury.bucketlist.core.constants.CommonCodes;
import com.mybury.bucketlist.core.domain.Bucketlist;
import com.mybury.bucketlist.core.domain.QBucketlist;
import com.mybury.bucketlist.core.util.DateUtil;
import com.mybury.bucketlist.core.vo.HomeRequestVO;
import com.mysema.query.jpa.impl.JPAQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

@Repository
public class BucketlistRepositoryImpl implements BucketlistRepositoryCustom {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<BucketlistResDTO> getBucketlistResDTO(HomeRequestVO requestVO) {
    JPAQuery query = new JPAQuery(entityManager);
    QBucketlist bucketlist = QBucketlist.bucketlist;
    QBucketlistResDTO result = new QBucketlistResDTO(bucketlist.id, bucketlist.title, bucketlist.pin,
      bucketlist.status, bucketlist.dDate, bucketlist.userCount, bucketlist.goalCount, bucketlist.completedDt,
      bucketlist.orderSeq, bucketlist.category().name);

    query.from(bucketlist).where(bucketlist.user().id.eq(requestVO.getUserId()));

    String filter = requestVO.getFilter();

    if (filter != null) {
      switch (filter) {
        case "started":
          query.where(bucketlist.status.eq("1"));
          break;
        case "completed":
          query.where(bucketlist.status.eq("2"));
        case "all":
          query.where(bucketlist.status.eq("1").or(bucketlist.status.eq("2")));
        default:
          break;
      }
    }

    String sort = requestVO.getSort();

    if (sort != null) {
      switch (sort) {
        case "updatedDt" :
          query.orderBy(bucketlist.updatedDt.desc());
          break;
        case "createdDt" :
          query.orderBy(bucketlist.createdDt.asc());
          break;
        case "custom" :
          query.orderBy(bucketlist.orderSeq.asc());
          break;
        default:
          break;
      }
    }

    return query.list(result);
  }

  @Override
  public List<Bucketlist> getBucketlists(HomeRequestVO requestVO) {
    JPAQuery query = new JPAQuery(entityManager);
    QBucketlist bucketlist = QBucketlist.bucketlist;

    query.from(bucketlist).where(bucketlist.user().id.eq(requestVO.getUserId()));

    String filter = requestVO.getFilter();

    if (StringUtils.isNotBlank(filter)) {
      if (filter.equals("started"))
        query.where(bucketlist.status.eq("1"));

      if (filter.equals("completed"))
        query.where(bucketlist.status.eq("2"));

      if (filter.equals("all"))
        query.where(bucketlist.status.eq("1").or(bucketlist.status.eq("2")));
    }

    String sort = requestVO.getSort();

    if (sort.equals("updatedDt")) {
      query.orderBy(bucketlist.updatedDt.desc());
    }

    if (sort.equals("createdDt")) {
      query.orderBy(bucketlist.createdDt.asc());
    }

    if (sort.equals("custom")) {
      query.orderBy(bucketlist.orderSeq.asc());
    }

    return query.list(bucketlist);
  }

  @Override
  public boolean existsPopupBucketlist(String userId, int popupPeriod) {
    JPAQuery query = new JPAQuery(entityManager);
    QBucketlist bucketlist = QBucketlist.bucketlist;

    query.from(bucketlist).where(bucketlist.user().id.eq(userId)
      .and(bucketlist.dDate.eq(DateUtil.addDays(DateUtil.getToday(), popupPeriod))));

    return query.exists();
  }

  @Override
  public List<Bucketlist> getDDayBucketlist(String userId, String filter) {
    JPAQuery query = new JPAQuery(entityManager);
    QBucketlist bucketlist = QBucketlist.bucketlist;

    query.from(bucketlist).where(bucketlist.user().id.eq(userId)
      .and(bucketlist.status.eq(CommonCodes.BucketlistStatus.STARTED)).and(bucketlist.dDate.isNotNull()));

    if (StringUtils.isNotBlank(filter)) {
      if (StringUtils.equals(filter, "minus"))
        query.where(bucketlist.dDate.goe(DateUtil.getDate()));

      if (StringUtils.equals(filter, "plus"))
        query.where(bucketlist.dDate.lt(DateUtil.getDate()));
    }

    query.orderBy(bucketlist.dDate.asc());

    return query.list(bucketlist);
  }

  @Override
  public List<Bucketlist> getBucketlistsByDDate(Date date, String userId) {
    JPAQuery query = new JPAQuery(entityManager);
    QBucketlist bucketlist = QBucketlist.bucketlist;

    query.from(bucketlist).where(bucketlist.dDate.eq(date).and(bucketlist.user().id.eq(userId))
      .and(bucketlist.status.eq(CommonCodes.BucketlistStatus.STARTED)));

    return query.list(bucketlist);
  }

  @Override
  public String getLastBucketlistId() {
    JPAQuery query = new JPAQuery(entityManager);
    QBucketlist bucketlist = QBucketlist.bucketlist;

    query.from(bucketlist);

    return query.singleResult(bucketlist.id);
  }

  @Override
  public int getStartedBucketlistCount(String userId) {
    JPAQuery query = new JPAQuery(entityManager);
    QBucketlist bucketlist = QBucketlist.bucketlist;

    query.from(bucketlist)
      .where(bucketlist.user().id.eq(userId).and(bucketlist.status.eq(CommonCodes.BucketlistStatus.STARTED)));

    return (int) query.count();
  }

  @Override
  public int getCompletedBucketlistCount(String userId) {
    JPAQuery query = new JPAQuery(entityManager);
    QBucketlist bucketlist = QBucketlist.bucketlist;

    query.from(bucketlist).where(
      bucketlist.user().id.eq(userId).and(bucketlist.status.eq(CommonCodes.BucketlistStatus.COMPLETED)));

    return (int) query.count();
  }

  @Override
  public List<Bucketlist> getBucketlistByCategoryId(String categoryId) {
    JPAQuery query = new JPAQuery(entityManager);
    QBucketlist bucketlist = QBucketlist.bucketlist;

    query.from(bucketlist).where(bucketlist.category().id.eq(categoryId)).orderBy(bucketlist.createdDt.asc());

    return query.list(bucketlist);
  }
}
