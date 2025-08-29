package com.ecomerce_backend.ecomerce_backend.reposatori;

import com.ecomerce_backend.ecomerce_backend.entityes.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductReposatory extends JpaRepository<Product, Long> {

}
