package com.example.blog.integration;

import com.example.blog.dto.EmailNotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "email-service")
public interface EmailServiceClient {

    @PostMapping("/api/v1/emails/send")
    Map<String, Object> sendEmail(@RequestBody EmailNotificationRequest request);
}