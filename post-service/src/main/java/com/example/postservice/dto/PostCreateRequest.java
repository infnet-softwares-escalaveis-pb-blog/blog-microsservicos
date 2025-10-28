package com.example.postservice.dto;

import com.example.postservice.domain.PostStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostCreateRequest(
        @NotBlank String title,
        String content,
        @NotNull Long authorId,
        @NotNull Long categoryId,
        @NotNull PostStatus status
        ) {}
