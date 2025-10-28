package com.example.postservice.repository;

import com.example.postservice.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
    Optional<Post> findBySlug(String slug);
}
