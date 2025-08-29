package com.ecomerce_backend.ecomerce_backend.service;

import jakarta.persistence.criteria.Order;

import java.util.List;

public interface OrderService {
    Order PlaceOrder (Long userId);
    List<Order> GetUserOders(Long userId);
    void cancelOder(Long orderId);
}
