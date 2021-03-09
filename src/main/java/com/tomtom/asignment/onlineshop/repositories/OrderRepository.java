package com.tomtom.asignment.onlineshop.repositories;

import com.tomtom.asignment.onlineshop.entities.ShoppingOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<ShoppingOrder, Integer> {
}
