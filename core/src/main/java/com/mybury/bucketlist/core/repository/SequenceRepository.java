package com.mybury.bucketlist.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybury.bucketlist.core.domain.Sequence;

public interface SequenceRepository extends JpaRepository<Sequence, String> {
	
}
