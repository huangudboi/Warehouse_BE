package com.example.securityapp.controller;

import com.example.securityapp.model.Order;
import com.example.securityapp.service.OrderService;
import com.example.securityapp.utils.ExcelGenerator;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private Logger logger = Logger.getLogger(OrderController.class);

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
    public ResponseEntity<Order> createOrder(@RequestBody @Valid Order order, UriComponentsBuilder builder) {
        logger.info("=== Start call api create order ===");
        ResponseEntity<Order> response;
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/{order_id}").buildAndExpand(order.getOrderId()).toUri());
        try {
            orderService.save(order);
            response = new ResponseEntity<>(order, HttpStatus.CREATED);
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
    public void exportIntoExcelFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=ListOrders-" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List <Order> listOfOrders = orderService.findAll();
        ExcelGenerator generator = new ExcelGenerator(listOfOrders);
        generator.generateExcelFile(response);
    }
}
