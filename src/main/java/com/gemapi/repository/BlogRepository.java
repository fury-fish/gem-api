package com.gemapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gemapi.entity.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
} 