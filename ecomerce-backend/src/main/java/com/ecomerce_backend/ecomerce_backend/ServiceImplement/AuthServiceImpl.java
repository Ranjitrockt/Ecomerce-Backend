package com.ecomerce_backend.ecomerce_backend.ServiceImplement;

import com.ecomerce_backend.ecomerce_backend.DTO.AuthResponse;

import com.ecomerce_backend.ecomerce_backend.Security.JwtUtil;

import org.springframework.security.core.Authentication;

import com.ecomerce_backend.ecomerce_backend.DTO.LoginRequest;
import com.ecomerce_backend.ecomerce_backend.DTO.RegisterRequest;
import com.ecomerce_backend.ecomerce_backend.entityes.Role;
import com.ecomerce_backend.ecomerce_backend.entityes.User;
import com.ecomerce_backend.ecomerce_backend.reposatori.UserReposatory;
import com.ecomerce_backend.ecomerce_backend.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.*;


@Service
@RequiredArgsConstructor
@Transactional

public  class AuthServiceImpl implements AuthService {

    private final UserReposatory userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return new AuthResponse(null, "Email already in use");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(String.valueOf(Role.USER));

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token, "Registration successful");
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtUtil.generateToken(request.getEmail());

            return new AuthResponse(token, "Login successful");
        } catch (Exception e) {
            return new AuthResponse(null, "Invalid email or password");
        }
    }

    @Override
    public boolean validateToken(String token) {
        return jwtUtil.validateToken(token);
    }
}

