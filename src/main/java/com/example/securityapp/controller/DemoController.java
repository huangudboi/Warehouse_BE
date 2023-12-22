package com.example.securityapp.controller;

import io.jsonwebtoken.JwtException;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Demo check login lấy token để vào API
@RestController
@RequestMapping("/api/v1/demo-controller")
public class DemoController {

    private Logger logger = Logger.getLogger(DemoController.class);
    
    @GetMapping
    public ResponseEntity<String> sayHello() {
        logger.info("=== Start call api test token ===");
        ResponseEntity<String> response;
        try {
//            response = ResponseEntity.ok("Test token");
            response = new ResponseEntity<>("Test token", HttpStatus.OK);
        }catch (JwtException ex){
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception ex){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("=== End call api test token ===");
        return response;
    }
}