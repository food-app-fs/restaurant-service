package com.example.restaurantsservice.service;

import com.example.restaurantsservice.model.Restaurants;
import com.example.restaurantsservice.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class RestaurantsService {
    @Autowired
    Repository repository;

    public List<Restaurants> getAllRestaurants(){
        return repository.findAll();
    }


    public Restaurants getRestaurantsByID(Long i){
        return repository.findByRestaurantID(i);
    }


//    //contains
//    public List<Restaurants> sortByFindMenuItems(String value){
//
//    }







    //future update
//    public Restaurants includeNewRestaurant(
//            MultipartFile file,
//            String restaurantName,
//            String contact,
//            String postcode,
//            String address,
//            String timing
//    ){
//
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        Restaurants restaurants = new Restaurants();
//        restaurants.setRestaurantName(restaurantName);
//        restaurants.setPostcode(postcode);
//        restaurants.setAddress(address);
//        restaurants.setTiming(timing);
//        restaurants.setContact(contact);
//
//        try{
//            if(file.isEmpty()){
//                System.out.println("No Image Found");
//            }else{
//                byte[] imageData = file.getBytes();
//                restaurants.setImage(imageData);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        return repository.save(restaurants);
//    }

}
