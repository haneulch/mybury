package com.mybury.bucketlist.core.vo;

import java.util.List;
import com.mybury.bucketlist.core.domain.Bucketlist;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "검색 Response")
public class SearchResponseVO {
  @Schema(description = "버킷리스트 목록")
  List<BucketlistVO> bucketlists;

  @Schema(description = "카테고리 목록")
  List<CategoryVO> categories;
}
