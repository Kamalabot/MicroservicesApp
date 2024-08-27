package com.example.microservices.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("/orders")
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @PostMapping
    public String createOrder(@RequestBody String order) {
        return "Order created: " + order;
    }

    @GetMapping("/{id}")
    public String getOrder(@PathVariable String id) {
        return "Order with ID: " + id;
    }
}
