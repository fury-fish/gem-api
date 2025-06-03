package com.gemapi.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gemapi.dto.ReviewDTO;
import com.gemapi.entity.Product;
import com.gemapi.entity.Review;
import com.gemapi.entity.User;
import com.gemapi.repository.ProductRepository;
import com.gemapi.repository.ReviewRepository;
import com.gemapi.repository.UserRepository;
import com.gemapi.service.ReviewService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        Review review = new Review();
        updateReviewFromDTO(review, reviewDTO);
        Review savedReview = reviewRepository.save(review);
        updateProductRating(savedReview.getProduct().getId());
        return ReviewDTO.fromEntity(savedReview);
    }

    @Override
    public ReviewDTO updateReview(Long id, ReviewDTO reviewDTO) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
        Long productId = review.getProduct().getId();
        updateReviewFromDTO(review, reviewDTO);
        Review savedReview = reviewRepository.save(review);
        updateProductRating(productId);
        return ReviewDTO.fromEntity(savedReview);
    }

    @Override
    public ReviewDTO findById(Long id) {
        return ReviewDTO.fromEntity(reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id)));
    }

    @Override
    public Page<ReviewDTO> findAll(Pageable pageable) {
        return reviewRepository.findAll(pageable).map(ReviewDTO::fromEntity);
    }

    @Override
    public Page<ReviewDTO> findByProductId(Long productId, Pageable pageable) {
        return reviewRepository.findByProductId(productId, pageable).map(ReviewDTO::fromEntity);
    }

    @Override
    public void deleteById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
        Long productId = review.getProduct().getId();
        reviewRepository.deleteById(id);
        updateProductRating(productId);
    }

    private void updateReviewFromDTO(Review review, ReviewDTO reviewDTO) {
        review.setRating(reviewDTO.getRating());
        review.setComment(reviewDTO.getComment());

        if (reviewDTO.getProductId() != null) {
            Product product = productRepository.findById(reviewDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + reviewDTO.getProductId()));
            review.setProduct(product);
        }

        if (reviewDTO.getUserId() != null) {
            User user = userRepository.findById(reviewDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + reviewDTO.getUserId()));
            review.setUser(user);
        }
    }

    private void updateProductRating(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        
        Double averageRating = reviewRepository.calculateAverageRating(productId);
        Integer reviewCount = reviewRepository.countByProductId(productId);
        
        product.setAverageRating(averageRating);
        product.setReviewCount(reviewCount);
        productRepository.save(product);
    }
} 