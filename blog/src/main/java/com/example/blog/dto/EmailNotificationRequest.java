package com.example.blog.dto;

public record EmailNotificationRequest(
    String to,
    String subject,
    String body
) {}