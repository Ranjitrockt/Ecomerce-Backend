package com.ecomerce_backend.ecomerce_backend.reposatori;

import com.ecomerce_backend.ecomerce_backend.entityes.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRaposatory  extends JpaRepository<OrderItem,Long> {

}
