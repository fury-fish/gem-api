package com.gemapi.dto;

import com.gemapi.entity.Product;
import com.gemapi.entity.SaleEvent;
import java.math.BigDecimal;

public class ProductWithSaleDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal salePrice;
    private Integer stock;
    private Long categoryId;
    private String imageUrl;
    private String videoUrl;
    private SaleEvent saleEvent;
    private Integer discountPercent;

    public ProductWithSaleDTO(Product product, SaleEvent saleEvent, Integer discountPercent) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.categoryId = product.getCategoryId();
        this.imageUrl = product.getImageUrl();
        this.videoUrl = product.getVideoUrl();
        this.saleEvent = saleEvent;
        this.discountPercent = discountPercent;
        
        if (discountPercent > 0) {
            BigDecimal discountMultiplier = BigDecimal.ONE.subtract(
                BigDecimal.valueOf(discountPercent).divide(BigDecimal.valueOf(100))
            );
            this.salePrice = product.getPrice().multiply(discountMultiplier);
        } else {
            this.salePrice = null;
        }
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public BigDecimal getPrice() { return price; }
    public BigDecimal getSalePrice() { return salePrice; }
    public Integer getStock() { return stock; }
    public Long getCategoryId() { return categoryId; }
    public String getImageUrl() { return imageUrl; }
    public String getVideoUrl() { return videoUrl; }
    public SaleEvent getSaleEvent() { return saleEvent; }
    public Integer getDiscountPercent() { return discountPercent; }
} 