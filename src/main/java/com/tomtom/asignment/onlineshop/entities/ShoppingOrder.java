package com.tomtom.asignment.onlineshop.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class ShoppingOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int orderId;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    protected Date orderDate;

    @OneToMany(mappedBy = "productId", cascade = {CascadeType.ALL})
    protected List<Mobile> items;

    double billToPay;

    boolean isAmountPaid;
    boolean readyToShip;
}
