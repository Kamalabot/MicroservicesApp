package com.example.microservices.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("/products")
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @PostMapping
    public String addProduct(@RequestBody String product) {
        return "Product added: " + product;
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable String id) {
        return "Product with ID: " + id;
    }
}
