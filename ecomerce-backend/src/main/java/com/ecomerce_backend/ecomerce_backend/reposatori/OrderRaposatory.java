package com.ecomerce_backend.ecomerce_backend.reposatori;

import com.ecomerce_backend.ecomerce_backend.entityes.User;
import jakarta.persistence.criteria.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRaposatory extends JpaRepository<Order, Long> {

    Optional<Order> findById(User user);

    List<Order> findByUser(User user);
}
