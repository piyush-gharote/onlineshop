package com.tomtom.asignment.onlineshop.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cartId;

    @OneToMany
    private List<Mobile> products;

    @OneToOne(mappedBy = "shoppingCart", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private User user;
}
