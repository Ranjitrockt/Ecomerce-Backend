package com.ecomerce_backend.ecomerce_backend.service;

import com.ecomerce_backend.ecomerce_backend.entityes.Product;

import java.util.List;

public interface ProductService {
    Product create(Product product);
    Product update(Long id, Product product);
    Product getById(Long id);
    List<Product> getAll();
    void delete(Long id);

}
