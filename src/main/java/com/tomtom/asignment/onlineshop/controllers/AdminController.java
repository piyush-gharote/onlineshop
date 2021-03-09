package com.tomtom.asignment.onlineshop.controllers;

import com.tomtom.asignment.onlineshop.entities.User;
import com.tomtom.asignment.onlineshop.services.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    private UserService userService;

    @PostMapping("/admin/user/create")
    public User createUser(@RequestBody UserData userData){
        return userService.createUser(userData.getPhoneNo(), userData.getEmailId());
    }

}
