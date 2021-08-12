package com.mybury.bucketlist.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybury.bucketlist.core.domain.PushMessage;

public interface PushMessageRepository extends JpaRepository<PushMessage, String>, PushMessageRepositoryCustom{
}
