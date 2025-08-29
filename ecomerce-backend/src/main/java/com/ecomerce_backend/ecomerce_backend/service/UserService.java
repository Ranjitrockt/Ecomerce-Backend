package com.ecomerce_backend.ecomerce_backend.service;

import com.ecomerce_backend.ecomerce_backend.entityes.User;

import java.util.List;

public interface UserService {
   User createUser(User user);
   User getUserById(Long id);
   List<User> getUserAll();
   void deleteUser(Long id);
   User updateUser(Long id ,User user);
}
