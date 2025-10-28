package com.example.postservice.dto;

import com.example.postservice.domain.PostStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostUpdateRequest(
        @NotBlank String title,
        String content,
        @NotNull Long categoryId,
        @NotNull PostStatus status
) {}
