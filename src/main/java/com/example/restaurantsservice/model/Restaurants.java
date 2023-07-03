package com.example.restaurantsservice.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Restaurants {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long restaurantID;

    private String restaurantName;

    private String contact;

    private String postcode;

    private String address;


    private String image;

    private String timing;

    @Transient
    private List<Menu> menu = new ArrayList<>();



}
