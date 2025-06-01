package com.gemapi.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.gemapi.entity.Category;

public interface CategoryService {
    Category save(Category category);
    Page<Category> findAll(Pageable pageable);
    Category findById(Long id);
    boolean existsByName(String name);
    void deleteById(Long id);
} 