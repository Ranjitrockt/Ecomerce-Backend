package com.ecomerce_backend.ecomerce_backend.entityes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
   @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        private Order order;

        @ManyToOne
        private Product product;

        private int quantity;

        private double priceAtPurchase;

    public void setPrice(double totalPrice) {
    }
}


