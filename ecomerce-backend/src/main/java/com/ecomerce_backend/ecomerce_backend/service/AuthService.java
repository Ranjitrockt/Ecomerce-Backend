package com.ecomerce_backend.ecomerce_backend.service;

import com.ecomerce_backend.ecomerce_backend.DTO.AuthResponse;
import com.ecomerce_backend.ecomerce_backend.DTO.LoginRequest;

import com.ecomerce_backend.ecomerce_backend.DTO.RegisterRequest;

// AuthService.java
public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
    boolean validateToken(String token);
}

