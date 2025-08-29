package com.ecomerce_backend.ecomerce_backend.service;

import com.ecomerce_backend.ecomerce_backend.entityes.CartItem;

import java.util.List;
public interface CartItemService {

 void addCart(Long userId, Long productId,int quality);
    void removeFromCart(Long userId, Long productId);
    List<CartItem> getUserCart(Long userId);
    void cleanCart(long userId);


    }
