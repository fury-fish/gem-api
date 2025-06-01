package com.gemapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gemapi.dto.ProductRequestDTO;
import com.gemapi.dto.ProductWithSaleDTO;

public interface ProductService {
    ProductWithSaleDTO save(ProductRequestDTO productRequest);
    Page<ProductWithSaleDTO> findAll(Pageable pageable);
    ProductWithSaleDTO findById(Long id);
    Page<ProductWithSaleDTO> findByCategoryId(Long categoryId, Pageable pageable);
    void deleteById(Long id);
} 