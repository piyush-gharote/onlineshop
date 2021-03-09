package com.tomtom.asignment.onlineshop.services;

import com.tomtom.asignment.onlineshop.entities.Mobile;
import com.tomtom.asignment.onlineshop.entities.ShoppingOrder;
import com.tomtom.asignment.onlineshop.entities.User;
import com.tomtom.asignment.onlineshop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public ShoppingOrder getOrder(int id){
        return orderRepository.findById(id).get();
    }

    public List<ShoppingOrder> getOrders(){
        ArrayList<ShoppingOrder> mobiles = new ArrayList<>();
        orderRepository.findAll().forEach(mobiles :: add);
        return mobiles;
    }

    public ShoppingOrder updateOrder(ShoppingOrder shoppingOrderUpdated, int id){
        Optional<ShoppingOrder> orderOptional = orderRepository.findById(id);

        if(orderOptional.isPresent()){
            ShoppingOrder shoppingOrder = orderOptional.get();
            shoppingOrder.setItems(shoppingOrderUpdated.getItems());
            shoppingOrder.setOrderDate(shoppingOrderUpdated.getOrderDate());
            shoppingOrder.setUser(shoppingOrderUpdated.getUser());

            return orderRepository.save(shoppingOrder);
        }else
            return null;
    }

    public ShoppingOrder addProduct(ShoppingOrder shoppingOrder){
        return orderRepository.save(shoppingOrder);
    }

    public void deleteAllOrders(){
        orderRepository.deleteAll();
    }

    public void deleteOrder(int id){
        orderRepository.deleteById(id);
    }

    public ShoppingOrder createOrder(List<Mobile> items, double bill, User user){
        ShoppingOrder shoppingOrder = new ShoppingOrder();
        shoppingOrder.setUser(user);
        shoppingOrder.setOrderDate(new Date());
        List<Mobile> list = new ArrayList<>();
        list.addAll(items);
        shoppingOrder.setItems(list);
        shoppingOrder.setBillToPay(bill);

       return orderRepository.save(shoppingOrder);
    }


    public String completeOrder(int orderId, double amount){
        ShoppingOrder shoppingOrder = getOrder(orderId);
        if(amount == shoppingOrder.getBillToPay()) {
            shoppingOrder.setAmountPaid(true);
            shoppingOrder.setReadyToShip(true);
            orderRepository.save(shoppingOrder);
            return "Amount is paid and ShoppingOrder is shipped";
        }else {
            return "amount is not paid";
        }
    }
}
