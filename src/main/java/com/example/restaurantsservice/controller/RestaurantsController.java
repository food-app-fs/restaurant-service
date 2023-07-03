package com.example.restaurantsservice.controller;


import com.example.restaurantsservice.client.Client;
import com.example.restaurantsservice.model.Cart;
import com.example.restaurantsservice.model.Restaurants;
import com.example.restaurantsservice.repository.Repository;
import com.example.restaurantsservice.service.CartService;
import com.example.restaurantsservice.service.RestaurantsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RequestMapping("/restaurants/")
@RestController
@Slf4j
public class RestaurantsController {

    @Autowired
    RestaurantsService service;

    @Autowired
    CartService cartService;

    @Autowired
    Client client;

    @Autowired
    Repository repository;


    @GetMapping("/all")
    public List<Restaurants> ListOfRestaurants(){
        log.info("LIST OF RESTAURANTS");
        List<Restaurants> restaurants =
                repository.findAll();
        restaurants.forEach(r->
                r.setMenu(client.getMenuForRestaurantByID(r.getRestaurantID())));


        return restaurants;
    }

    @PostMapping("/add-new")
    public Restaurants AddNewRestaurant(@RequestBody Restaurants restaurants){
        log.info("ADD NEW RESTAURANT");
        return repository.save(restaurants);
    }

//    @PostMapping(value = "/add-new/restro", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE })
//    public Restaurants AddNewRestaurant(
//            @RequestParam("file") MultipartFile file,
//            @RequestParam("restaurantName") String restaurantName,
//            @RequestParam("contact") String contact,
//            @RequestParam("postcode") String postcode,
//            @RequestParam("address") String address,
//            @RequestParam("timing") String timing
//    ){
//        log.info("ADD NEW RESTAURANT");
//        return service.includeNewRestaurant(file,restaurantName,contact,postcode,address,timing);
//    }


    // work here

    @GetMapping("/search/{s}")
    public Restaurants getRestautantsByMenuContains(@PathVariable("s")Long s){
        log.info("SEARCH RESTAURANT BY: " + s);

        Restaurants restaurants =
                service.getRestaurantsByID(s);
        restaurants.setMenu(client.getMenuForRestaurantByID(restaurants.getRestaurantID()));
        return restaurants;
    }


    @PostMapping("/add-cart")
    public ResponseEntity<String> saveCart(@RequestBody Cart cart){
        boolean result = cartService.saveCart(cart);
        if(result) return ResponseEntity.ok("ADDED TO THE BASKET");
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/cart-read")
    public List<Cart> fetchCart(){
        return cartService.fetchalluser();
    }

    @PostMapping("/cart-update/qty")
    public ResponseEntity<String> update(@RequestBody Cart cart){
        boolean result = cartService.updateCart(cart);
        if(result) return ResponseEntity.ok("ADDED TO THE BASKET");
        else return ResponseEntity.badRequest().build();
    }

    @PostMapping("/cart/clear")
    public ResponseEntity<String> deletecart(@RequestBody Cart cart){
        boolean result = cartService.clearCart(cart);
        if(result) return ResponseEntity.ok("Clear" + cart.getCartID());
        else return ResponseEntity.badRequest().build();
    }

    @PostMapping("/cart/clear-all")
    public void deletecart(@RequestBody List<Long> id){
        cartService.clearAllCart(id);
    }



}
