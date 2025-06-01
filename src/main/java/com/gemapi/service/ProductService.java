package com.gemapi.service;

import java.util.List;

import com.gemapi.entity.Product;

public interface ProductService {
    Product save(Product product);
    List<Product> findAll();
    Product findById(Long id);
    List<Product> findByCategoryId(Long categoryId);
    void deleteById(Long id);
} 