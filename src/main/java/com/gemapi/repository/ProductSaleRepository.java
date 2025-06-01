package com.gemapi.repository;

import com.gemapi.entity.ProductSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductSaleRepository extends JpaRepository<ProductSale, Long> {
    List<ProductSale> findByProductIdAndActive(Long productId, boolean active);
    List<ProductSale> findBySaleEventIdAndActive(Long saleEventId, boolean active);
    ProductSale findByProductIdAndSaleEventId(Long productId, Long saleEventId);
} 