package com.ecomerce_backend.ecomerce_backend.ServiceImplement;

import com.ecomerce_backend.ecomerce_backend.entityes.CartItem;
import com.ecomerce_backend.ecomerce_backend.entityes.OrderItem;
import com.ecomerce_backend.ecomerce_backend.entityes.User;
import com.ecomerce_backend.ecomerce_backend.reposatori.CartItenReposatory;
import com.ecomerce_backend.ecomerce_backend.reposatori.OrderRaposatory;
import com.ecomerce_backend.ecomerce_backend.reposatori.UserReposatory;

import com.ecomerce_backend.ecomerce_backend.service.OrderService;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public  class OrderServiceImpt  implements OrderService {

        @Autowired
        private OrderRaposatory orderRaposatory;

        @Autowired
        private UserReposatory userReposatory;

        @Autowired
        private CartItenReposatory cartItemReposatory;

        @Autowired
        private CartServiceImpt cartServiceImpt;

        @Override
        public Order PlaceOrder(Long userId) {

            User user = userReposatory.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            List<CartItem> cartItems = cartItemReposatory.findByUser(user);
            if (cartItems.isEmpty()) {
                throw new RuntimeException("Cart is empty");
            }
           Order order = new Order() {
               @Override
               public Order reverse() {
                   return null;
               }

               @Override
               public boolean isAscending() {
                   return false;
               }

               @Override
               public Expression<?> getExpression() {
                   return null;
               }
           };
            order.equals(user);
            order.setOrderDate(LocalDateTime.now());
            order.setStatus("PLACED");


            double total = 0;
            List<OrderItem> orderItems = new ArrayList<>();

            for (CartItem cartItem : cartItems) {
                OrderItem orderItem = new OrderItem();
                orderItem.setProduct(cartItem.getProduct());
                orderItem.setQuantity(cartItem.getQuantity());
                orderItem.setPrice(cartItem.getTotalPrice());
                com.ecomerce_backend.ecomerce_backend.entityes.Order Order;
                orderItem.setOrder(Order);
                orderItems.add(orderItem);
                total += cartItem.getTotalPrice();
            }

            order.setOrderItems(orderItems);
            order.setTotalAmount(total);

            Order savedOrder = orderRaposatory.save(order);
            cartItemReposatory.deleteAll(cartItems); // clear cart

            return savedOrder;
        }

        @Override
        public List<Order> GetUserOders(Long userId) {
            User user = userReposatory.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            return orderRaposatory.findByUser(user);
        }

        @Override
        public void cancelOder(Long orderId) {
            Order order = orderRaposatory.findById(orderId)
                    .orElseThrow(() -> new RuntimeException("Order not found"));
            order.setStatus("CANCELLED");
            orderRaposatory.save(order);
        }

    public static class ProductServiceImpt {
    }
}





