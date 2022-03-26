package com.mybury.bucketlist.core.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {
  private String message;
  private T data;
}
