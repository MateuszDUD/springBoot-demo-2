package com.example.demo.service;

import com.example.demo.model.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class QuoteService {

    private RestTemplate restTemplate;

    @Value("${myrest.url}")
    private String restUrl;

    @Autowired
    public QuoteService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<Quote> getQuote() {
        return Optional.of(restTemplate.getForObject(restUrl, Quote.class));
    }
}
