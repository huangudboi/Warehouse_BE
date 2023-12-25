package com.example.securityapp.controller;

import com.example.securityapp.model.Order;
import com.example.securityapp.service.OrderService;
import com.example.securityapp.utils.ExcelGenerator;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private static final Logger logger = LogManager.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @GetMapping("/getAllOrder")
    public ResponseEntity<List<Order>> findAllOrders() {
        logger.info("=== Start call api get all orders ===");
        List<Order> orders = orderService.findAll();
        ResponseEntity<List<Order>> response;
        try {
            response = new ResponseEntity<>(orders, HttpStatus.OK);
        }catch (IndexOutOfBoundsException ex) {
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("=== End call api get all orders ===");
        return response;
    }

    @PostMapping(value = "/createOrder")
    public ResponseEntity<Order> createOrder(@RequestBody @Valid Order order) {
        logger.info("=== Start call api create order ===");
        ResponseEntity<Order> response;
        try {
            Order oderSave= orderService.save(order);
            response = new ResponseEntity<>(oderSave, HttpStatus.CREATED);
        }catch (Exception ex){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("=== End call api create order ===");
        return response;
    }

    @DeleteMapping(value = "/deleteOrder/{order_id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable("order_id") Long orderId) {
        logger.info("=== Start call api delete order id: "+orderId+" ===");
        Optional<Order> order = orderService.findByOrderId(orderId);
        ResponseEntity<Order> response;
        try {
            orderService.remove(order.get());
            response = new ResponseEntity<>(HttpStatus.OK);
            Order actualOrder = order.orElseThrow(() -> new NoSuchElementException("Order with id:" +orderId+" don't found"));
        }catch (NoSuchElementException ex) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("=== End call api delete order id: "+orderId+" ===");
        return response;
    }

    @GetMapping("/export-to-excel")
    public void exportIntoExcelFile(HttpServletResponse response){
        logger.info("=== Export excel file ===");
        List <Order> listOfOrders = orderService.findAll();
        try {
            ExcelGenerator generator = new ExcelGenerator(listOfOrders);
            generator.generateExcelFile(response);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
