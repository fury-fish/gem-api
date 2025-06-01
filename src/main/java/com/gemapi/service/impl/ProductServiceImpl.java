package com.gemapi.service.impl;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gemapi.entity.Product;
import com.gemapi.entity.SaleEvent;
import com.gemapi.repository.ProductRepository;
import com.gemapi.service.ProductService;
import com.gemapi.dto.ProductWithSaleDTO;
import com.gemapi.dto.ProductRequestDTO;
import java.util.stream.Collectors;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.sql.Timestamp;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductWithSaleDTO save(ProductRequestDTO productRequest) {
        Product product = productRequest.toEntity();
        Product savedProduct = productRepository.save(product);
        return new ProductWithSaleDTO(savedProduct, null, 0);
    }

    private Product mapToProduct(Object[] row) {
        Product product = new Product();
        product.setId(((Number) row[0]).longValue());
        product.setName((String) row[1]);
        product.setDescription((String) row[2]);
        product.setPrice((BigDecimal) row[3]);
        product.setStock((Integer) row[4]);
        product.setCategoryId(((Number) row[5]).longValue());
        product.setImageUrl((String) row[6]);
        product.setVideoUrl((String) row[7]);
        return product;
    }

    private SaleEvent mapToSaleEvent(Object[] row) {
        if (row[8] == null) return null;
        
        SaleEvent saleEvent = new SaleEvent();
        saleEvent.setId(((Number) row[8]).longValue());
        saleEvent.setName((String) row[9]);
        saleEvent.setDescription((String) row[10]);
        saleEvent.setDiscountPercent((Integer) row[11]);
        
        // Convert Timestamp to LocalDateTime
        Timestamp startTimestamp = (Timestamp) row[12];
        Timestamp endTimestamp = (Timestamp) row[13];
        saleEvent.setStartDate(startTimestamp != null ? startTimestamp.toLocalDateTime() : null);
        saleEvent.setEndDate(endTimestamp != null ? endTimestamp.toLocalDateTime() : null);
        
        saleEvent.setActive((Boolean) row[14]);
        return saleEvent;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductWithSaleDTO> findAll(Pageable pageable) {
        Page<Object[]> results = productRepository.findAllWithMaxDiscount(pageable);
        List<ProductWithSaleDTO> products = results.getContent().stream()
                .map(result -> {
                    Product product = mapToProduct(result);
                    SaleEvent saleEvent = mapToSaleEvent(result);
                    Integer discountPercent = ((Number) result[15]).intValue();
                    return new ProductWithSaleDTO(product, saleEvent, discountPercent);
                })
                .collect(Collectors.toList());
        
        return new PageImpl<>(products, pageable, results.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductWithSaleDTO findById(Long id) {
        Object[] result = productRepository.findByIdWithMaxDiscount(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        
        Product product = mapToProduct(result);
        SaleEvent saleEvent = mapToSaleEvent(result);
        Integer discountPercent = ((Number) result[15]).intValue();
        return new ProductWithSaleDTO(product, saleEvent, discountPercent);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductWithSaleDTO> findByCategoryId(Long categoryId, Pageable pageable) {
        Page<Object[]> results = productRepository.findByCategoryIdWithMaxDiscount(categoryId, pageable);
        List<ProductWithSaleDTO> products = results.getContent().stream()
                .map(result -> {
                    Product product = mapToProduct(result);
                    SaleEvent saleEvent = mapToSaleEvent(result);
                    Integer discountPercent = ((Number) result[15]).intValue();
                    return new ProductWithSaleDTO(product, saleEvent, discountPercent);
                })
                .collect(Collectors.toList());
        
        return new PageImpl<>(products, pageable, results.getTotalElements());
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
} 