package com.hurbanlab.catalog.controller;

import com.hurbanlab.catalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalog")
public class HelloController {

    @Value("${spring.application.name:catalog}")
    private String appName;

    private ProductService productService;
    public HelloController(@Autowired ProductService productService){
        this.productService = productService;
    }

    @RequestMapping("/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello " + appName, HttpStatus.OK);
    }


    @RequestMapping("/products")
    public ResponseEntity<String> getProducts() {

        return new ResponseEntity<>(productService.getProductHello(), HttpStatus.OK);
    }
}
