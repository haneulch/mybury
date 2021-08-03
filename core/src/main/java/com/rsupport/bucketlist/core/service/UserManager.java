package com.rsupport.bucketlist.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.rsupport.bucketlist.core.domain.User;
import com.rsupport.bucketlist.core.domain.UserMapping;
import com.rsupport.bucketlist.core.vo.CreateProfileRequestVO;
import com.rsupport.bucketlist.core.vo.HostSignInRequestVO;
import com.rsupport.bucketlist.core.vo.HostSignUpRequestVO;

public interface UserManager {

  User getUserById(String userId);

  User getUserByEmail(String email);

  User signup(HostSignUpRequestVO requestVO);

  User signin(HostSignInRequestVO requestVO);

  void createProfile(CreateProfileRequestVO requestVO);

  void remove(String userId);

  Page<UserMapping> getUser(String search, Pageable page);
  
  UserMapping getUserMappingById(String userId);
}
