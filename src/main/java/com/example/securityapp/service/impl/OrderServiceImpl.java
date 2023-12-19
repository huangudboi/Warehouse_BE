package com.example.securityapp.service.impl;

import com.example.securityapp.model.Order;
import com.example.securityapp.repository.OrderRepository;
import com.example.securityapp.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return (List<Order>) orderRepository.findAll();
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void remove(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public Optional<Order> findByOrderId(Long orderId) {
        return orderRepository.findByOrderId(orderId);
    }
}
