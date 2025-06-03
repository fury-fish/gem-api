package com.gemapi.controller.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.gemapi.dto.PageResponse;
import com.gemapi.dto.ProductRequestDTO;
import com.gemapi.dto.ProductWithSaleDTO;
import com.gemapi.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/admin/products")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Admin Product Management", description = "Admin APIs for product management")
public class AdminProductController {

    private final ProductService productService;

    @PostMapping
    @Operation(summary = "Create a new product", description = "Create a new product with the provided details")
    public ResponseEntity<ProductWithSaleDTO> createProduct(@Valid @RequestBody ProductRequestDTO request) {
        return ResponseEntity.ok(productService.save(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a product", description = "Update an existing product with the provided details")
    public ResponseEntity<ProductWithSaleDTO> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDTO request) {
        return ResponseEntity.ok(productService.update(id, request));
    }

    @GetMapping
    @Operation(summary = "Get all products", description = "Get all products with pagination and sorting")
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
    @Operation(summary = "Get a product by ID", description = "Get a product by its ID")
    public ResponseEntity<ProductWithSaleDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product", description = "Delete a product by its ID")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/visibility")
    @Operation(summary = "Update product visibility", description = "Update product visibility based on stock")
    public ResponseEntity<ProductWithSaleDTO> updateVisibility(
            @PathVariable Long id,
            @RequestParam boolean hideIfOutOfStock) {
        return ResponseEntity.ok(productService.updateVisibility(id, hideIfOutOfStock));
    }
} 