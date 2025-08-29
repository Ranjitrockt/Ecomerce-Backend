package com.ecomerce_backend.ecomerce_backend.entityes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="product")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Long productId;

    private String title;

    private String description;

    private int  quality;

    private  String imageurl;

    private double  price;

    private  String category;



}
