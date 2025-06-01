package com.gemapi.service;

import com.gemapi.entity.SaleEvent;
import java.util.List;

public interface SaleEventService {
    SaleEvent save(SaleEvent saleEvent);
    SaleEvent findById(Long id);
    List<SaleEvent> findAll();
    List<SaleEvent> findActiveEvents();
    void deleteById(Long id);
    void applySaleToProduct(Long productId, Long saleEventId);
    void removeSaleFromProduct(Long productId, Long saleEventId);
    void enableSaleEvent(Long id);
    void disableSaleEvent(Long id);
} 