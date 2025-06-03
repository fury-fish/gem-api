package com.gemapi.controller.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.gemapi.dto.ApiResponse;
import com.gemapi.dto.ReviewDTO;
import com.gemapi.dto.PageResponse;
import com.gemapi.service.ReviewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/admin/reviews")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Admin Review Management", description = "Admin APIs for review management")
public class AdminReviewController {

    private final ReviewService reviewService;

    @GetMapping
    @Operation(summary = "Get all reviews", description = "Get all reviews with pagination and sorting")
    public ResponseEntity<ApiResponse<PageResponse<ReviewDTO>>> getAllReviews(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(sortDirection, sortBy));
        Page<ReviewDTO> reviewPage = reviewService.findAll(pageRequest);
        return ResponseEntity.ok(ApiResponse.success(PageResponse.of(reviewPage, page)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a review by ID", description = "Get a review by its ID")
    public ResponseEntity<ApiResponse<ReviewDTO>> getReviewById(@PathVariable Long id) {
        ReviewDTO review = reviewService.findById(id);
        return ResponseEntity.ok(ApiResponse.success(review));
    }

    @GetMapping("/product/{productId}")
    @Operation(summary = "Get reviews by product", description = "Get all reviews for a specific product")
    public ResponseEntity<ApiResponse<PageResponse<ReviewDTO>>> getReviewsByProduct(
            @PathVariable Long productId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(sortDirection, sortBy));
        Page<ReviewDTO> reviewPage = reviewService.findByProductId(productId, pageRequest);
        return ResponseEntity.ok(ApiResponse.success(PageResponse.of(reviewPage, page)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a review", description = "Delete a review by its ID")
    public ResponseEntity<ApiResponse<Void>> deleteReview(@PathVariable Long id) {
        reviewService.deleteById(id);
        return ResponseEntity.ok(ApiResponse.success("Review deleted successfully", null));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a review", description = "Update an existing review")
    public ResponseEntity<ApiResponse<ReviewDTO>> updateReview(
            @PathVariable Long id,
            @Valid @RequestBody ReviewDTO reviewDTO) {
        ReviewDTO updatedReview = reviewService.updateReview(id, reviewDTO);
        return ResponseEntity.ok(ApiResponse.success("Review updated successfully", updatedReview));
    }
} 