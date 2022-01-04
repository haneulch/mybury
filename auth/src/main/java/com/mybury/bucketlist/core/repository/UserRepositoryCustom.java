package com.mybury.bucketlist.core.repository;


import com.mybury.bucketlist.core.domain.User;

public interface UserRepositoryCustom {

    User getUserByEmail(String email);
}
