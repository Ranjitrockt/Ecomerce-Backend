package com.ecomerce_backend.ecomerce_backend.reposatori;

import com.ecomerce_backend.ecomerce_backend.entityes.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserReposatory extends  JpaRepository<User,Long> {

    Optional<User> findById(String email);
    boolean existsByEmail(String email);
}
