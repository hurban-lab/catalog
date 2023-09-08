package com.hurbanlab.catalog.controller;

import com.hurbanlab.catalog.error.DefaultErrorCodes;
import com.hurbanlab.catalog.error.ResourceNotFoundError;
import com.hurbanlab.catalog.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalog")
@Log4j2
public class HelloController {

    @Value("${spring.application.name:catalog}")
    private String appName;

    private ProductService productService;
    public HelloController(@Autowired ProductService productService){
        this.productService = productService;
    }

    @RequestMapping("/hello")
    public ResponseEntity<String> hello() {
        log.info("Retrieving greetings");
        return new ResponseEntity<>("Hello " + appName, HttpStatus.OK);
    }


    @RequestMapping("/products")
    public ResponseEntity<String> getProducts() {
        log.info("Retrieving products");
        return new ResponseEntity<>(productService.getProductHello(), HttpStatus.OK);
    }

    @RequestMapping("/error")
    public ResponseEntity<String> error() throws ResourceNotFoundError {
        log.warn("Retrieving error");
        throw new ResourceNotFoundError(DefaultErrorCodes.RESOURCE_NOT_FOUND_ERROR, "Error resource not found");
    }
}
