package com.hurbanlab.catalog.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private RestTemplate restTemplate;

    private String host = "http://localhost";
    private String uri = "/product/hello";
    @InjectMocks
    private ProductService productService = new ProductService(host, uri, restTemplate);

    @Test
    public void getProductsTest() {
        String greetings = "Hello product";
        when(restTemplate.getForEntity(host + uri, String.class))
                .thenReturn(new ResponseEntity<String>(greetings, HttpStatus.OK));
        String response = productService.getProductHello();

        assertNotNull(response);
        assertEquals(greetings, response);
    }
}
