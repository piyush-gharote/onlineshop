package com.tomtom.asignment.onlineshop.repositories;

import com.tomtom.asignment.onlineshop.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository  extends CrudRepository<User, Integer> {
}
