package com.example.restaurantsservice.client;


import com.example.restaurantsservice.model.Menu;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface Client {

    @GetExchange("menu/{id}")
    public List<Menu> getMenuForRestaurantByID(@PathVariable("id")Long id);


    @GetExchange("menu/search/{s}")
    public List<Menu> getItemsByMenuForRestro(@PathVariable("s")String s);
}
