package com.mybury.bucketlist.core.v2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mybury.bucketlist.core.domain.Badge;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, String>{
}