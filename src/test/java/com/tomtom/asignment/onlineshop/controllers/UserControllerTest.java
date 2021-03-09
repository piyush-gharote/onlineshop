package com.tomtom.asignment.onlineshop.controllers;

import com.tomtom.asignment.onlineshop.entities.Mobile;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest extends BaseTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserController userController;

    @Order(1)
    @Test
    void getAllProducts() {
        given(userController.getAllProducts()).willReturn(products);
        try {
            mvc.perform(get("http://localhost:8080/user/products"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("[0].brand").value(product1.getBrand()))
                    .andExpect(jsonPath("[1].brand").value("Samsung"))
                    .andExpect(jsonPath("[2].brand").value("OnePlus"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Order(2)
    @Test
    void getProduct() {
        given(userController.getProduct(product1.getProductId())).willReturn(product1);
        try {
            int id = product1.getProductId();
            mvc.perform(get("http://localhost:8080/user/products/"+ id))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("brand").value(product1.getBrand()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Order(3)
    @Test
    void addToCart() {
        try {
            List<Mobile> list = new ArrayList<>();
            list.add(product3);
            given(userController.addToCart(3,1)).willReturn(list);
            mvc.perform(post("http://localhost:8080/user/products/" + 1)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("3")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("[0].brand").value("OnePlus"))
                    .andExpect(jsonPath("[0].price").value(1500));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void placeOrder() {
        try {
            mvc.perform(get("http://localhost:8080/user/products/placeOrder/1"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void checkOut() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{")
                .append("\"").append("billAmount").append("\"").append(":").append(15000).append(",")
                .append("\"").append("orderId").append("\"").append(":").append(5)
                .append("}");
        try {

            mvc.perform(post("http://localhost:8080/user/products/checkOut")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(stringBuilder.toString())
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}