package com.gemapi.service;

import java.util.List;

import com.gemapi.entity.Category;

public interface CategoryService {
    Category save(Category category);
    List<Category> findAll();
    Category findById(Long id);
    boolean existsByName(String name);
    void deleteById(Long id);
} 