package com.varun.retryCalledService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class GreetingController {

    @GetMapping("/serviceBgreeting")
    public ResponseEntity<String> greet(@RequestParam(name = "name", defaultValue = "World") String name){
        // 1. error immediately
        //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Service generating error");

        // 2. error with occasional delay
        Random random = new Random();
        if(random.nextInt(2) == 1){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error("Thread Interupted", e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.OK).body("Hello !");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Service generating error");
    }
}
