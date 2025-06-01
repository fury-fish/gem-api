package com.gemapi.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.gemapi.entity.Product;

public interface ProductService {
    Product save(Product product);
    Page<Product> findAll(Pageable pageable);
    Product findById(Long id);
    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);
    void deleteById(Long id);
} 