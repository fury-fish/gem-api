package com.gemapi.controller;

import com.gemapi.entity.SaleEvent;
import com.gemapi.service.SaleEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleEventController {

    private final SaleEventService saleEventService;

    public SaleEventController(SaleEventService saleEventService) {
        this.saleEventService = saleEventService;
    }

    @PostMapping
    public ResponseEntity<SaleEvent> createSaleEvent(@RequestBody SaleEvent saleEvent) {
        return ResponseEntity.ok(saleEventService.save(saleEvent));
    }

    @GetMapping
    public ResponseEntity<List<SaleEvent>> getAllSaleEvents() {
        return ResponseEntity.ok(saleEventService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<SaleEvent>> getActiveSaleEvents() {
        return ResponseEntity.ok(saleEventService.findActiveEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleEvent> getSaleEventById(@PathVariable Long id) {
        return ResponseEntity.ok(saleEventService.findById(id));
    }

    @PostMapping("/{id}/enable")
    public ResponseEntity<Void> enableSaleEvent(@PathVariable Long id) {
        saleEventService.enableSaleEvent(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/disable")
    public ResponseEntity<Void> disableSaleEvent(@PathVariable Long id) {
        saleEventService.disableSaleEvent(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{saleEventId}/products/{productId}")
    public ResponseEntity<Void> applySaleToProduct(
            @PathVariable Long saleEventId,
            @PathVariable Long productId) {
        saleEventService.applySaleToProduct(productId, saleEventId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{saleEventId}/products/{productId}")
    public ResponseEntity<Void> removeSaleFromProduct(
            @PathVariable Long saleEventId,
            @PathVariable Long productId) {
        saleEventService.removeSaleFromProduct(productId, saleEventId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSaleEvent(@PathVariable Long id) {
        saleEventService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
} 