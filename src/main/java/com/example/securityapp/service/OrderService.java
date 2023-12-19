package com.example.securityapp.service;

import com.example.securityapp.model.Message;
import com.example.securityapp.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> findAll();
    void save(Order order);
    void remove(Order order);
    Optional<Order> findByOrderId(Long orderId);

}
