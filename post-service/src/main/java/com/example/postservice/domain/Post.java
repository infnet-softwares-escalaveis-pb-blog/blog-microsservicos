package com.example.postservice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "posts", uniqueConstraints = @UniqueConstraint(columnNames = "slug"))
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @Column(nullable = false, unique = true)
    private String slug;

    @Lob
    private String content;

    @Enumerated(EnumType.STRING)
    private PostStatus status;

    private Instant createdAt;
    private Instant updatedAt;

    @ManyToOne(optional = false)
    private Author author;

    @ManyToOne(optional = false)
    private Category category;

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }
}
