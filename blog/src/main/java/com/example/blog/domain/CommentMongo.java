package com.example.blog.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "comments")
@Getter
@Setter

public class CommentMongo {

    @Id
    private String id;

    @JsonIgnore
    private Long postId;

    @NotBlank
    @Size(max = 80)
    private String authorName;

    @Email
    @NotBlank
    @Size(max = 120)
    private String authorEmail;

    @NotBlank
    private String content;

    private LocalDateTime createdAt = LocalDateTime.now();
}
