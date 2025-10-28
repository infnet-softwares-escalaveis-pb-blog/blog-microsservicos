package com.example.postservice.repository;

import com.example.postservice.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findBySlug(String slug);
}
