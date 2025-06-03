package com.gemapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gemapi.dto.ReviewDTO;

public interface ReviewService {
    ReviewDTO createReview(ReviewDTO reviewDTO);
    ReviewDTO updateReview(Long id, ReviewDTO reviewDTO);
    ReviewDTO findById(Long id);
    Page<ReviewDTO> findAll(Pageable pageable);
    Page<ReviewDTO> findByProductId(Long productId, Pageable pageable);
    void deleteById(Long id);
} 