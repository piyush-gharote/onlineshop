package com.tomtom.asignment.onlineshop.services;

import com.tomtom.asignment.onlineshop.entities.Cart;
import com.tomtom.asignment.onlineshop.entities.Mobile;
import com.tomtom.asignment.onlineshop.entities.Product;
import com.tomtom.asignment.onlineshop.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CartService {

    @Autowired
    private ShoppingCartRepository cartRepository;

    public Cart save(Cart cart){
        return cartRepository.save(cart);
    }

    public Cart findById(int id){
        Optional<Cart> cartOptional = cartRepository.findById(id);
        if(cartOptional.isPresent())
            return cartOptional.get();
        else
            throw new RuntimeException("no cart is available by Id: "+ id);
    }

    public List<Mobile> addToCart(Mobile mobile, Cart cart){
        cart.getProducts().add(mobile);
        cart = save(cart);
        return cart.getProducts();
    }

    public double getTotalPrice(Cart cart) {
        double totalPrice = 0;
        for(Product p : cart.getProducts()){
            totalPrice += p.getPrice();
        }
        return totalPrice;
    }

    public void removeFromCart(Product product, int cartId){
        Cart cart = findById(cartId);
        if(cart.getProducts().contains(product)) {
            cart.getProducts().remove(product);
            save(cart);
        }
        else
            throw new RuntimeException("product is not present in cart");
    }
}
