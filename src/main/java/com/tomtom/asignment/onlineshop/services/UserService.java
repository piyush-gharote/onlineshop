package com.tomtom.asignment.onlineshop.services;

import com.tomtom.asignment.onlineshop.entities.Cart;
import com.tomtom.asignment.onlineshop.entities.User;
import com.tomtom.asignment.onlineshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(int userId){
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }else
            throw new RuntimeException("user with Id"+ userId +" doesn't exist");
    }

    @Transactional
    public User createUser(long phoneNo, String emailId){
        User user = new User();
        user.setEmailId(emailId);
        user.setPhoneNo(phoneNo);
        Cart cart = new Cart();
        user.setShoppingCart(cart);
        return userRepository.save(user);
    }
}
