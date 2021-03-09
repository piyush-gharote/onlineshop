package com.tomtom.asignment.onlineshop.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private Long phoneNo;
    private String emailId;

    @OneToMany(mappedBy = "user")
    private List<ShoppingOrder> shoppingOrders;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartId", referencedColumnName = "cartId")
    private Cart shoppingCart;

}
