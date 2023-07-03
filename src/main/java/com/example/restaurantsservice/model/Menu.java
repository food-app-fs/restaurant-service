package com.example.restaurantsservice.model;

public record Menu (
     Long productId,
     Long restaurantId,
     String name,
     String description,
     String price
){}
