package com.gemapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gemapi.dto.PageResponse;
import com.gemapi.dto.ProductWithSaleDTO;
import com.gemapi.dto.ProductRequestDTO;
import com.gemapi.service.ProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductWithSaleDTO> createProduct(@Valid @RequestBody ProductRequestDTO productRequest) {
        return ResponseEntity.ok(productService.save(productRequest));
    }

    @GetMapping
    public ResponseEntity<PageResponse<ProductWithSaleDTO>> getAllProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(sortDirection, sortBy));
        Page<ProductWithSaleDTO> productPage = productService.findAll(pageRequest);
        return ResponseEntity.ok(PageResponse.of(productPage, page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductWithSaleDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<PageResponse<ProductWithSaleDTO>> getProductsByCategoryId(
            @PathVariable Long categoryId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(sortDirection, sortBy));
        Page<ProductWithSaleDTO> productPage = productService.findByCategoryId(categoryId, pageRequest);
        return ResponseEntity.ok(PageResponse.of(productPage, page));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
} 