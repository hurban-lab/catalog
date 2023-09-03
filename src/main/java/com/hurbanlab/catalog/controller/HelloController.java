package com.hurbanlab.catalog.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${spring.application.name:catalog}")
    private String appName;

    @RequestMapping("/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello " + appName, HttpStatus.OK);
    }
}
