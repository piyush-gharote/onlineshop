package com.tomtom.asignment.onlineshop.repositories;

import com.tomtom.asignment.onlineshop.entities.Cart;
import org.springframework.data.repository.CrudRepository;

public interface ShoppingCartRepository extends CrudRepository<Cart, Integer> {

}
