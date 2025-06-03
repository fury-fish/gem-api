package com.gemapi.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gemapi.dto.ProductRequestDTO;
import com.gemapi.dto.ProductWithSaleDTO;
import com.gemapi.entity.Category;
import com.gemapi.entity.Partner;
import com.gemapi.entity.Product;
import com.gemapi.repository.CategoryRepository;
import com.gemapi.repository.PartnerRepository;
import com.gemapi.repository.ProductRepository;
import com.gemapi.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PartnerRepository partnerRepository;

    @Override
    @Transactional
    public ProductWithSaleDTO save(ProductRequestDTO request) {
        Product product = request.toEntity();
        
        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + request.getCategoryId()));
            product.setCategory(category);
        }

        if (request.getPartnerId() != null) {
            Partner partner = partnerRepository.findById(request.getPartnerId())
                    .orElseThrow(() -> new RuntimeException("Partner not found with id: " + request.getPartnerId()));
            product.setPartner(partner);
        }

        return ProductWithSaleDTO.fromEntity(productRepository.save(product));
    }

    @Override
    @Transactional
    public ProductWithSaleDTO update(Long id, ProductRequestDTO request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setImageUrl(request.getImageUrl());
        product.setHideIfOutOfStock(request.isHideIfOutOfStock());

        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + request.getCategoryId()));
            product.setCategory(category);
        }

        if (request.getPartnerId() != null) {
            Partner partner = partnerRepository.findById(request.getPartnerId())
                    .orElseThrow(() -> new RuntimeException("Partner not found with id: " + request.getPartnerId()));
            product.setPartner(partner);
        }

        return ProductWithSaleDTO.fromEntity(productRepository.save(product));
    }

    @Override
    public ProductWithSaleDTO findById(Long id) {
        return ProductWithSaleDTO.fromEntity(productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id)));
    }

    @Override
    public Page<ProductWithSaleDTO> findAll(Pageable pageable) {
        return productRepository.findAll(pageable).map(ProductWithSaleDTO::fromEntity);
    }

    @Override
    public Page<ProductWithSaleDTO> findByCategoryId(Long categoryId, Pageable pageable) {
        return productRepository.findByCategory_Id(categoryId, pageable).map(ProductWithSaleDTO::fromEntity);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ProductWithSaleDTO updateVisibility(Long id, boolean hideIfOutOfStock) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setHideIfOutOfStock(hideIfOutOfStock);
        return ProductWithSaleDTO.fromEntity(productRepository.save(product));
    }
} 