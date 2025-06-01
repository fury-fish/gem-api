package com.gemapi.service;

import java.util.List;

import com.gemapi.entity.Order;

public interface OrderService {
    Order save(Order order);
    List<Order> findAll();
    Order findById(Long id);
    List<Order> findByUserId(Long userId);
    void deleteById(Long id);
} 