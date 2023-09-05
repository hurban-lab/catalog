package com.hurbanlab.catalog.controller;

import com.hurbanlab.catalog.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerTest {

    @Value(value="${local.server.port}")
    private int port;

    @Value("${spring.application.name:there}")
    private String appName;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ProductService productService;

    @Test
    public void helloShouldReturnDefaultMessage() throws Exception {
        assertTrue(this.restTemplate.getForObject("http://localhost:" + port + "/catalog/hello",
                String.class).contains("Hello " + appName));
    }

    @Test
    public void getProductsShouldReturnAMessage() throws Exception {
        String greetings = "Hello product";
        when(productService.getProductHello()).thenReturn(greetings);
        assertTrue(this.restTemplate.getForObject("http://localhost:" + port + "/catalog/products",
                String.class).contains(greetings));
    }
}
