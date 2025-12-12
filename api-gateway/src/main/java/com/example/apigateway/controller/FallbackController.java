package com.example.apigateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/blog-service")
    public ResponseEntity<Map<String, Object>> blogServiceFallback() {
        Map<String, Object> fallbackResponse = new HashMap<>();
        fallbackResponse.put("message", "Blog Service is currently unavailable");
        fallbackResponse.put("status", "FALLBACK_ACTIVE");
        fallbackResponse.put("timestamp", LocalDateTime.now());
        fallbackResponse.put("service", "blog-service");
        fallbackResponse.put("suggestion", "Please try again later");
        
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                           .body(fallbackResponse);
    }

    @PostMapping("/blog-service")
    public ResponseEntity<Map<String, Object>> blogServiceFallbackPost() {
        Map<String, Object> fallbackResponse = new HashMap<>();
        fallbackResponse.put("message", "Blog Service is currently unavailable - Cannot create new posts");
        fallbackResponse.put("status", "FALLBACK_ACTIVE");
        fallbackResponse.put("timestamp", LocalDateTime.now());
        fallbackResponse.put("service", "blog-service");
        fallbackResponse.put("suggestion", "Please try to create the post again later");
        
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                           .body(fallbackResponse);
    }

    @PutMapping("/blog-service")
    public ResponseEntity<Map<String, Object>> blogServiceFallbackPut() {
        Map<String, Object> fallbackResponse = new HashMap<>();
        fallbackResponse.put("message", "Blog Service is currently unavailable - Cannot update posts");
        fallbackResponse.put("status", "FALLBACK_ACTIVE");
        fallbackResponse.put("timestamp", LocalDateTime.now());
        fallbackResponse.put("service", "blog-service");
        fallbackResponse.put("suggestion", "Please try to update the post again later");
        
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                           .body(fallbackResponse);
    }

    @DeleteMapping("/blog-service")
    public ResponseEntity<Map<String, Object>> blogServiceFallbackDelete() {
        Map<String, Object> fallbackResponse = new HashMap<>();
        fallbackResponse.put("message", "Blog Service is currently unavailable - Cannot delete posts");
        fallbackResponse.put("status", "FALLBACK_ACTIVE");
        fallbackResponse.put("timestamp", LocalDateTime.now());
        fallbackResponse.put("service", "blog-service");
        fallbackResponse.put("suggestion", "Please try to delete the post again later");
        
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                           .body(fallbackResponse);
    }

    @GetMapping("/email-service")
    public ResponseEntity<Map<String, Object>> emailServiceFallback() {
        Map<String, Object> fallbackResponse = new HashMap<>();
        fallbackResponse.put("message", "Email Service is currently unavailable");
        fallbackResponse.put("status", "FALLBACK_ACTIVE");
        fallbackResponse.put("timestamp", LocalDateTime.now());
        fallbackResponse.put("service", "email-service");
        fallbackResponse.put("suggestion", "Email will be queued and sent when service is restored");
        
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                           .body(fallbackResponse);
    }

    @PostMapping("/email-service")
    public ResponseEntity<Map<String, Object>> emailServiceFallbackPost() {
        Map<String, Object> fallbackResponse = new HashMap<>();
        fallbackResponse.put("message", "Email Service is currently unavailable");
        fallbackResponse.put("status", "FALLBACK_ACTIVE");
        fallbackResponse.put("timestamp", LocalDateTime.now());
        fallbackResponse.put("service", "email-service");
        fallbackResponse.put("suggestion", "Email will be queued and sent when service is restored");
        
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                           .body(fallbackResponse);
    }

    @GetMapping("/login-service")
    public ResponseEntity<Map<String, Object>> loginServiceFallback() {
        Map<String, Object> fallbackResponse = new HashMap<>();
        fallbackResponse.put("message", "Authentication Service is currently unavailable");
        fallbackResponse.put("status", "FALLBACK_ACTIVE");
        fallbackResponse.put("timestamp", LocalDateTime.now());
        fallbackResponse.put("service", "login-service");
        fallbackResponse.put("suggestion", "Please try to login again in a few minutes");
        
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                           .body(fallbackResponse);
    }

    @PostMapping("/login-service")
    public ResponseEntity<Map<String, Object>> loginServiceFallbackPost() {
        Map<String, Object> fallbackResponse = new HashMap<>();
        fallbackResponse.put("message", "Authentication Service is currently unavailable - Cannot process login");
        fallbackResponse.put("status", "FALLBACK_ACTIVE");
        fallbackResponse.put("timestamp", LocalDateTime.now());
        fallbackResponse.put("service", "login-service");
        fallbackResponse.put("suggestion", "Please try to login again in a few minutes");
        
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                           .body(fallbackResponse);
    }

}