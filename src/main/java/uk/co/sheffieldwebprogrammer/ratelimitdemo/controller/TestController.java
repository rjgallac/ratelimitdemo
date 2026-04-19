package uk.co.sheffieldwebprogrammer.ratelimitdemo.controller;

import java.time.Duration;

import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Logger;
import io.github.bucket4j.Bucket;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TestController {
        
    private static Bucket bucket = Bucket.builder()
        .addLimit(limit -> limit.capacity(1000).refillGreedy(100, Duration.ofSeconds(1)))
        .build();


    @GetMapping("/test")
    public String test() {
        log.info("Test endpoint called");    
        return "Hello, World!";
    }

    @GetMapping("/testratelimit")
    public ResponseEntity<String> testratelimit() {
        log.info("Test rate limit endpoint called");   
        if (bucket.tryConsume(1)) {
             return ResponseEntity.ok("Hello, World!");    
        } else {
            log.warn("Rate limit exceeded");
             return ResponseEntity.status(429).body("Rate limit exceeded");
        }       
    }

}
