package com.gemapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gemapi.entity.BlogComment;

@Repository
public interface BlogCommentRepository extends JpaRepository<BlogComment, Long> {
} 