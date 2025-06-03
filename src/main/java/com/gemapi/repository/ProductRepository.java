package com.gemapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gemapi.entity.Product;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCategory_Id(Long categoryId, Pageable pageable);
    boolean existsByNameAndCategoryId(String name, Long categoryId);

    @Query(value = "SELECT DISTINCT p.*, se.*, COALESCE(MAX(se.discount_percent), 0) as max_discount " +
           "FROM products p " +
           "LEFT JOIN product_sales ps ON p.id = ps.product_id " +
           "LEFT JOIN sale_events se ON ps.sale_event_id = se.id " +
           "AND ps.active = true " +
           "AND se.active = true " +
           "AND CURRENT_TIMESTAMP BETWEEN se.start_date AND se.end_date " +
           "GROUP BY p.id, p.name, p.description, p.price, p.stock, p.category_id, p.image_url, p.video_url, " +
           "se.id, se.name, se.description, se.discount_percent, se.start_date, se.end_date, se.active " +
           "ORDER BY p.id",
           countQuery = "SELECT COUNT(DISTINCT p.id) FROM products p",
           nativeQuery = true)
    Page<Object[]> findAllWithMaxDiscount(Pageable pageable);

    @Query(value = "SELECT DISTINCT p.*, se.*, COALESCE(MAX(se.discount_percent), 0) as max_discount " +
           "FROM products p " +
           "LEFT JOIN product_sales ps ON p.id = ps.product_id " +
           "LEFT JOIN sale_events se ON ps.sale_event_id = se.id " +
           "AND ps.active = true " +
           "AND se.active = true " +
           "AND CURRENT_TIMESTAMP BETWEEN se.start_date AND se.end_date " +
           "WHERE p.id = ?1 " +
           "GROUP BY p.id, p.name, p.description, p.price, p.stock, p.category_id, p.image_url, p.video_url, " +
           "se.id, se.name, se.description, se.discount_percent, se.start_date, se.end_date, se.active",
           nativeQuery = true)
    Optional<Object[]> findByIdWithMaxDiscount(Long productId);

    @Query(value = "SELECT DISTINCT p.*, se.*, COALESCE(MAX(se.discount_percent), 0) as max_discount " +
           "FROM products p " +
           "LEFT JOIN product_sales ps ON p.id = ps.product_id " +
           "LEFT JOIN sale_events se ON ps.sale_event_id = se.id " +
           "AND ps.active = true " +
           "AND se.active = true " +
           "AND CURRENT_TIMESTAMP BETWEEN se.start_date AND se.end_date " +
           "WHERE p.category_id = ?1 " +
           "GROUP BY p.id, p.name, p.description, p.price, p.stock, p.category_id, p.image_url, p.video_url, " +
           "se.id, se.name, se.description, se.discount_percent, se.start_date, se.end_date, se.active " +
           "ORDER BY p.id",
           countQuery = "SELECT COUNT(DISTINCT p.id) FROM products p WHERE p.category_id = ?1",
           nativeQuery = true)
    Page<Object[]> findByCategoryIdWithMaxDiscount(Long categoryId, Pageable pageable);
} 