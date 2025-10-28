package com.example.postservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthorRequest(
        @NotBlank String name,
        @Email @NotBlank String email,
        String bio
) {}
