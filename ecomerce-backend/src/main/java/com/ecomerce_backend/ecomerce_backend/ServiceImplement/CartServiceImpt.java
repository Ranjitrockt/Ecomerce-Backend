package com.ecomerce_backend.ecomerce_backend.ServiceImplement;

import com.ecomerce_backend.ecomerce_backend.entityes.CartItem;
import com.ecomerce_backend.ecomerce_backend.entityes.Product;
import com.ecomerce_backend.ecomerce_backend.entityes.User;
import com.ecomerce_backend.ecomerce_backend.reposatori.CartItenReposatory;
import com.ecomerce_backend.ecomerce_backend.reposatori.ProductReposatory;
import com.ecomerce_backend.ecomerce_backend.reposatori.UserReposatory;
import com.ecomerce_backend.ecomerce_backend.service.CartItemService;
import com.ecomerce_backend.ecomerce_backend.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public  class CartServiceImpt  implements CartItemService {

    @Autowired
    private UserReposatory userReposatory;


    @Autowired
    private ProductReposatory productReposatory;

    @Autowired
   private CartItenReposatory cartItenReposatory;
    public  void addCart(Long userId ,  Long productId, int quantity ){
        User user  = userReposatory.findById(userId)
                .orElseThrow(()-> new RuntimeException("user not found "));

        Product product  = productReposatory.findById(productId).orElseThrow(()->new RuntimeException("Product not found"));

        Optional<CartItem> existingItem = cartItenReposatory .findByUserAndProduct(user, product);

        if(existingItem.isPresent()){
            CartItem item= existingItem.get();
            item.setQuantity(item.getQuantity()+ quantity);
            item.setTotalPrice(item.getQuantity()*product.getPrice());
            cartItenReposatory.save(item);

        }
        else{
            CartItem newItem = new CartItem();
            newItem.setUser(user);
            newItem.setProduct(product);
            newItem.setTotalPrice(quantity);
            cartItenReposatory.save(newItem);

        }
    }
    public void removeFromCart(Long userId, Long productId) {
        User user = userReposatory.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productReposatory.findById(productId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<CartItem> item = cartItenReposatory.findByUserAndProduct(user, product);
        item.ifPresent(cartItenReposatory::delete);

    }
    public List<CartItem>  getUserCart(Long userId){
        User user = userReposatory.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return cartItenReposatory.findAllByUser(user);
    }


    public void cleanCart(long userId) {
        User user = userReposatory.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<CartItem> cartItems = cartItenReposatory.findAllByUser(user);
        cartItenReposatory.deleteAll(cartItems);
    }








}
