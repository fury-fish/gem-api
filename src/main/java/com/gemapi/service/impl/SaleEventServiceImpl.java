package com.gemapi.service.impl;

import com.gemapi.entity.SaleEvent;
import com.gemapi.entity.ProductSale;
import com.gemapi.repository.SaleEventRepository;
import com.gemapi.repository.ProductSaleRepository;
import com.gemapi.service.SaleEventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleEventServiceImpl implements SaleEventService {

    private final SaleEventRepository saleEventRepository;
    private final ProductSaleRepository productSaleRepository;

    public SaleEventServiceImpl(SaleEventRepository saleEventRepository, ProductSaleRepository productSaleRepository) {
        this.saleEventRepository = saleEventRepository;
        this.productSaleRepository = productSaleRepository;
    }

    @Override
    public SaleEvent save(SaleEvent saleEvent) {
        return saleEventRepository.save(saleEvent);
    }

    @Override
    public SaleEvent findById(Long id) {
        return saleEventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale event not found with id: " + id));
    }

    @Override
    public List<SaleEvent> findAll() {
        return saleEventRepository.findAll();
    }

    @Override
    public List<SaleEvent> findActiveEvents() {
        return saleEventRepository.findActiveEvents(LocalDateTime.now());
    }

    @Override
    public void deleteById(Long id) {
        saleEventRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void applySaleToProduct(Long productId, Long saleEventId) {
        ProductSale productSale = productSaleRepository.findByProductIdAndSaleEventId(productId, saleEventId);
        if (productSale == null) {
            productSale = new ProductSale();
            productSale.setProductId(productId);
            productSale.setSaleEventId(saleEventId);
            productSale.setActive(true);
            productSaleRepository.save(productSale);
        } else if (!productSale.isActive()) {
            productSale.setActive(true);
            productSaleRepository.save(productSale);
        }
    }

    @Override
    @Transactional
    public void removeSaleFromProduct(Long productId, Long saleEventId) {
        ProductSale productSale = productSaleRepository.findByProductIdAndSaleEventId(productId, saleEventId);
        if (productSale != null && productSale.isActive()) {
            productSale.setActive(false);
            productSaleRepository.save(productSale);
        }
    }

    @Override
    @Transactional
    public void enableSaleEvent(Long id) {
        SaleEvent saleEvent = findById(id);
        saleEvent.setActive(true);
        saleEventRepository.save(saleEvent);
    }

    @Override
    @Transactional
    public void disableSaleEvent(Long id) {
        SaleEvent saleEvent = findById(id);
        saleEvent.setActive(false);
        saleEventRepository.save(saleEvent);
    }
} 