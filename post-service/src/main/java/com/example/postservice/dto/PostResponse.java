package com.example.postservice.dto;

public record PostResponse(
        Long id,
        String title,
        String slug,
        String content,
        String authorName,
        String categoryName,
        String status
) {}
