package com.mybury.bucketlist.core.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HostSignUpRequestVO {

  @JsonProperty
  private String email;
  
  @JsonProperty
  private int accountType;
}