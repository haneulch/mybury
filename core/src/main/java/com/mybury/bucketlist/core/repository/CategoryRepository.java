package com.mybury.bucketlist.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybury.bucketlist.core.domain.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, String>, CategoryRepositoryCustom {

}
