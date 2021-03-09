package com.tomtom.asignment.onlineshop.controllers;

import com.tomtom.asignment.onlineshop.entities.Mobile;
import com.tomtom.asignment.onlineshop.services.MobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SellerController {

    @Autowired
    private MobileService mobileService;

    @PostMapping("/seller/products")
    public Mobile addProduct(@RequestBody Mobile mobile){
        return mobileService.addMobile(mobile);
    }

    @PostMapping("/seller/products/addAll")
    public String addAllProducts(@RequestBody List<Mobile> mobiles){
        mobileService.addAll(mobiles);
        return "products are posted";
    }

    @PutMapping("/seller/products/{id}")
    public void updateProduct(@RequestBody Mobile mobile, @PathVariable int id){
        mobileService.updateMobile(mobile, id);
    }

    @DeleteMapping("/seller/products/{id}")
    public void removeProduct(@PathVariable int id){
        mobileService.deleteMobile(id);
    }
}
