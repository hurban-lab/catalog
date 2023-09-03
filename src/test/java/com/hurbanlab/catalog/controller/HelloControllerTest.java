package com.hurbanlab.catalog.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerTest {

    @Value(value="${local.server.port}")
    private int port;

    @Value("${spring.application.name:there}")
    private String appName;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void helloShouldReturnDefaultMessage() throws Exception {
        assertTrue(this.restTemplate.getForObject("http://localhost:" + port + "/hello",
                String.class).contains("Hello " + appName));
    }
}
