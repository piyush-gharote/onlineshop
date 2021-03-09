package com.tomtom.asignment.onlineshop.controllers;

import com.tomtom.asignment.onlineshop.entities.Cart;
import com.tomtom.asignment.onlineshop.entities.Mobile;
import com.tomtom.asignment.onlineshop.entities.Product;
import com.tomtom.asignment.onlineshop.entities.User;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.List;

public class BaseTest {

    static Mobile product1;
    static Mobile product2;
    static Mobile product3;
    static List<Product> products = new ArrayList<>();
    static User user;

    @BeforeAll
    public static void init(){
        product1 = new Mobile();
        product1.setProductId(1);
        product1.setBrand("Apple");
        product1.setPrice(1200);

        product2 = new Mobile();
        product1.setProductId(2);
        product2.setPrice(1300);
        product2.setBrand("Samsung");

        product3 = new Mobile();
        product1.setProductId(3);
        product3.setBrand("OnePlus");
        product3.setPrice(1500);

        products.add(product1);
        products.add(product2);
        products.add(product3);

        user = new User();
        user.setUserId(1);
        user.setShoppingCart(new Cart());
        user.setEmailId("piyush@gmail.com");
        user.setPhoneNo(8600296413l);

        products.add(product1);
        products.add(product2);
        products.add(product3);
    }
}
