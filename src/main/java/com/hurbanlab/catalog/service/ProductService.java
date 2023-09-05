package com.hurbanlab.catalog.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {

    @Value("${product.host:http://localhost:9090}")
    private String productHost;

    @Value("${product.get-hello-uri:/hello}")
    private String uri;

    private RestTemplate restTemplate = new RestTemplate();


    public String getProductHello() {
        String fooResourceUrl = productHost + uri;
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl, String.class);
        return response.getBody();
    }
}
