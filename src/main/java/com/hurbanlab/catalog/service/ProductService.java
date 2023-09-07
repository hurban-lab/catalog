package com.hurbanlab.catalog.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Log4j2
public class ProductService {

    private String productHost;

    private String uri;

    private RestTemplate restTemplate;

    public ProductService(
            @Value("${product.host:http://localhost:9090}")
            String productHost,
            @Value("${product.get-hello-uri:/hello}")
            String uri,
            @Autowired RestTemplate restTemplate
    ){
        this.restTemplate = restTemplate;
        this.uri = uri;
        this.productHost = productHost;
    }

    public String getProductHello() {
        String fooResourceUrl = productHost + uri;
        log.info("Getting Products from " + fooResourceUrl);
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl, String.class);
        return response.getBody();
    }
}
