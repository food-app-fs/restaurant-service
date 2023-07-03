package com.example.restaurantsservice.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@RedisHash("CART_TABLE")
public class Cart implements Serializable {

    private static final long serialVersionUID = 1437030937044169014L;

    @Id
    Long cartID;
    Long productID;
    Long restroID;
    String productName;
    String produtPrice;
    int qty;

}
