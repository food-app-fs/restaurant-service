package com.example.restaurantsservice.service;

import com.example.restaurantsservice.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class CartService {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String KEY = "CART_TABLE";

    public Boolean saveCart(Cart cart){

        Long productID = cart.getProductID();
        String cartID = cart.getCartID().toString();

        try{

            Map<Object,Object> cartMap =
                    redisTemplate.opsForHash().entries(KEY);
            for(Map.Entry<Object,Object>entry:cartMap.entrySet()){
                String cID = entry.getKey().toString();
                Cart eItem = (Cart) entry.getValue();

                if(eItem.getProductID().equals(productID)){
                    eItem.setQty(eItem.getQty()+cart.getQty());
                    redisTemplate.opsForHash().put(KEY,cID,eItem);
                    return true;
                }
            }

            redisTemplate.opsForHash().put(KEY,cartID,cart);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public List<Cart> fetchalluser() {
        List<Cart> cart  = redisTemplate.opsForHash().values(KEY);
        return cart;
    }

    public boolean updateCart(Cart cart) {
        // Assuming cartID is not null
        try{
            redisTemplate.opsForHash().put(KEY,cart.getCartID().toString(),cart);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean clearCart(Cart cart) {

        try{

            redisTemplate.opsForHash().delete(KEY,cart.getCartID().toString());
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void clearAllCart(List<Long> id) {
        try {
            Map<Object, Object> cartMap = redisTemplate.opsForHash().entries(KEY);

            for (Long i : id) {
                for (Iterator<Map.Entry<Object, Object>> iterator = cartMap.entrySet().iterator(); iterator.hasNext();) {
                    Map.Entry<Object, Object> entry = iterator.next();
                    Cart eItem = (Cart) entry.getValue();

                    if (eItem.getCartID().equals(i)) {
                        iterator.remove();
                        redisTemplate.opsForHash().delete(KEY, eItem.getCartID().toString());
                    }
                }
            }

            // Update the modified cartMap in Redis
            redisTemplate.opsForHash().putAll(KEY, cartMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
