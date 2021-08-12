package com.mybury.bucketlist.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybury.bucketlist.core.domain.Bucketlist;

public interface BucketlistRepository extends JpaRepository<Bucketlist, String>, BucketlistRepositoryCustom {
}
