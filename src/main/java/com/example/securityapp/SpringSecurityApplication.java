package com.example.securityapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

//    @Autowired
//    private OrderService orderService;
//
//    @Override
//    public void run(String...a) {
//        for (int i = 0; i <= 10; i++) {
//            Order order = new Order();
//            order.setNameSender("Pham V Huan");
//            order.setPhoneSender("0981729501");
//            order.setAddressSender("Hanoi");
//            order.setEmailSender("abc@gmail.com");
//            order.setNameReceiver("Nguyen V Hiệu");
//            order.setPhoneReceiver("0924342343");
//            order.setAddressReceiver("HCm");
//            order.setEmailReceiver("hhhhhh@gmail.com");
//            order.setLongitude(44);
//            order.setLatitude(55);
//            orderService.save(order);
//        }
//    }

}
