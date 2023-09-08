package com.hurbanlab.catalog.controller;

import com.hurbanlab.catalog.error.DefaultErrorCodes;
import com.hurbanlab.catalog.error.ErrorDescription;
import com.hurbanlab.catalog.error.ResourceNotFoundError;
import com.hurbanlab.catalog.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    public void getProductsShouldThrowError() throws Exception {
        when(productService.getProductHello()).thenThrow(new RuntimeException("Crash"));
        ResponseEntity<ErrorDescription> response = this.restTemplate.getForEntity("http://localhost:" + port + "/catalog/products", ErrorDescription.class);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(DefaultErrorCodes.GENERIC_ERROR, response.getBody().getCode());
    }

    @Test
    public void getErrorShouldThrowError() throws ResourceNotFoundError {
        ResponseEntity<ErrorDescription> response = this.restTemplate.getForEntity("http://localhost:" + port + "/catalog/error", ErrorDescription.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(DefaultErrorCodes.RESOURCE_NOT_FOUND_ERROR, response.getBody().getCode());
    }
}
