package com.tomtom.asignment.onlineshop.controllers;

import com.tomtom.asignment.onlineshop.entities.*;
import com.tomtom.asignment.onlineshop.services.CartService;
import com.tomtom.asignment.onlineshop.services.MobileService;
import com.tomtom.asignment.onlineshop.services.OrderService;
import com.tomtom.asignment.onlineshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private MobileService mobileService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;


    @GetMapping("/user/products")
    public List<Product> getAllProducts(){
       return mobileService.getAllMobiles();
    }

    @GetMapping("/user/products/{id}")
    public Product getProduct(@PathVariable int id){
        return mobileService.getMobile(id);
    }

    @PostMapping("/user/products/{id}")
    public List<Mobile> addToCart(@RequestBody int productId, @PathVariable int id){
        User user = userService.findById(id);
        Mobile mobile = mobileService.getMobile(productId);
        return cartService.addToCart(mobile, user.getShoppingCart());
    }


    @GetMapping("/user/products/placeOrder/{id}")
    public String placeOrder(@PathVariable Integer id){
        User user = userService.findById(id);
        double billToPay = cartService.getTotalPrice(user.getShoppingCart());
        ShoppingOrder shoppingOrder = orderService.createOrder(user.getShoppingCart().getProducts(), billToPay, user);
        return "OrderId: "+ shoppingOrder.getOrderId()+" |  bill amount: "+ billToPay;
    }

    @PostMapping("/user/products/checkOut")
    public String checkOut(@RequestBody CheckOutDetails checkOutDetails){
        if(checkOutDetails.getBillAmount() != null && checkOutDetails.getBillAmount() != 0.0){
            return orderService.completeOrder(checkOutDetails.getOrderId(), checkOutDetails.getBillAmount());
        }else
            return "Payment is not done successfully";
    }


}
