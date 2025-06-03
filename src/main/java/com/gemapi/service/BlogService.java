package com.gemapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gemapi.dto.BlogDTO;

public interface BlogService {
    BlogDTO createBlog(BlogDTO blogDTO);
    BlogDTO updateBlog(Long id, BlogDTO blogDTO);
    BlogDTO findById(Long id);
    Page<BlogDTO> findAll(Pageable pageable);
    void deleteById(Long id);
    BlogDTO updatePublishStatus(Long id, boolean published);
    void deleteComment(Long blogId, Long commentId);
} 