package com.example.securityapp.controller;

import com.example.securityapp.model.Order;
import com.example.securityapp.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Order>> findAllOrders() {
        List<Order> orders = orderService.findAll();
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    private static final Logger logger = LoggerFactory
            .getLogger(OrderController.class);

    @PostMapping(value = "/createOrder")
    public ResponseEntity<Order> createOrder(@RequestBody @Valid Order order, UriComponentsBuilder builder) {
        orderService.save(order);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/{order_id}").buildAndExpand(order.getOrderId()).toUri());
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/deleteOrder/{order_id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable("order_id") Long orderId) {
        Optional<Order> order = orderService.findByOrderId(orderId);
        if (!order.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderService.remove(order.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
