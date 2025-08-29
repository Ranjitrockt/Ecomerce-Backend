package com.ecomerce_backend.ecomerce_backend.entityes;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import  java.util.*;

@Entity
@Data
@Table(name="user")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Long id;

    private String name;

    private  String email;

    private  String  password;

    private String role;

    private boolean enabled =  true;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)

    private  List<Order> order = new ArrayList<>();
}
