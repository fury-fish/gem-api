package com.gemapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gemapi.dto.ProductRequestDTO;
import com.gemapi.dto.ProductWithSaleDTO;

public interface ProductService {
    ProductWithSaleDTO save(ProductRequestDTO request);
    ProductWithSaleDTO update(Long id, ProductRequestDTO request);
    ProductWithSaleDTO findById(Long id);
    Page<ProductWithSaleDTO> findAll(Pageable pageable);
    Page<ProductWithSaleDTO> findByCategoryId(Long categoryId, Pageable pageable);
    void deleteById(Long id);
    ProductWithSaleDTO updateVisibility(Long id, boolean hideIfOutOfStock);
} 