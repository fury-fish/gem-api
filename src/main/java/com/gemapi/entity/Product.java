package com.gemapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer stock;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "video_url")
    private String videoUrl;

    @Transient
    private BigDecimal salePrice;

    @Transient
    private Integer discountPercent;

    public void calculateSalePrice(Integer discountPercent) {
        if (discountPercent != null && discountPercent > 0 && discountPercent <= 100) {
            this.discountPercent = discountPercent;
            BigDecimal discount = price.multiply(BigDecimal.valueOf(discountPercent))
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
            this.salePrice = price.subtract(discount);
        } else {
            this.discountPercent = null;
            this.salePrice = null;
        }
    }
} 