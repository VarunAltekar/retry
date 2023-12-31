package com.varun.retrycallingservice;

import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GreetingController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/greeting")
    //@Retry(name = "greetingRetry")
    public ResponseEntity greeting(@RequestParam(value = "name", defaultValue = "World") String name){
        ResponseEntity entity = restTemplate.getForEntity("http://localhost:8081/serviceBgreeting?name="+name,String.class);
        return entity;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
