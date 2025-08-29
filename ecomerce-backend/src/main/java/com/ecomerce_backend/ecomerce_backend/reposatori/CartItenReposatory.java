package com.ecomerce_backend.ecomerce_backend.reposatori;

import com.ecomerce_backend.ecomerce_backend.entityes.CartItem;
import com.ecomerce_backend.ecomerce_backend.entityes.Product;
import com.ecomerce_backend.ecomerce_backend.entityes.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItenReposatory  extends JpaRepository<CartItem, Long> {



    Optional<CartItem> findByUserAndProduct(User user, Product product);

    List<CartItem> findByUser(User user);
}
