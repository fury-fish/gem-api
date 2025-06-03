package com.gemapi.dto;

import com.gemapi.entity.Product;
import com.gemapi.entity.SaleEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductWithSaleDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private Long categoryId;
    private Long partnerId;
    private String imageUrl;
    private Double averageRating;
    private Integer reviewCount;
    private boolean active;
    private boolean hideIfOutOfStock;
    private BigDecimal salePrice;
    private Integer discountPercent;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ProductWithSaleDTO fromEntity(Product product) {
        ProductWithSaleDTO dto = new ProductWithSaleDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());
        dto.setCategoryId(product.getCategory() != null ? product.getCategory().getId() : null);
        dto.setPartnerId(product.getPartner() != null ? product.getPartner().getId() : null);
        dto.setImageUrl(product.getImageUrl());
        dto.setAverageRating(product.getAverageRating());
        dto.setReviewCount(product.getReviewCount());
        dto.setActive(product.isActive());
        dto.setHideIfOutOfStock(product.isHideIfOutOfStock());
        dto.setSalePrice(product.getSalePrice());
        dto.setDiscountPercent(product.getDiscountPercent());
        dto.setCreatedAt(product.getCreatedAt());
        dto.setUpdatedAt(product.getUpdatedAt());
        return dto;
    }
} 