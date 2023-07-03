package com.example.restaurantsservice.repository;

import com.example.restaurantsservice.model.Restaurants;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Restaurants,Long> {

    Restaurants findByRestaurantID(Long i);
}
