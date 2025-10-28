package com.example.postservice.service;

import com.example.postservice.domain.*;
import com.example.postservice.dto.*;
import com.example.postservice.repository.*;
import com.example.postservice.web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepo;
    private final AuthorRepository authorRepo;
    private final CategoryRepository categoryRepo;

    public PostResponse create(PostCreateRequest req) {
        var author = authorRepo.findById(req.authorId())
                .orElseThrow(() -> new NotFoundException("Author not found"));
        var category = categoryRepo.findById(req.categoryId())
                .orElseThrow(() -> new NotFoundException("Category not found"));

        var slugBase = slugify(req.title());
        var slug = ensureUniqueSlug(slugBase);

        var post = Post.builder()
                .title(req.title())
                .slug(slug)
                .content(req.content())
                .status(req.status())
                .author(author)
                .category(category)
                .build();

        try {
            post = postRepo.save(post);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Unique constraint violation", e);
        }
        return map(post);
    }

    public PostResponse update(String slug, PostUpdateRequest req) {
        var post = postRepo.findBySlug(slug).orElseThrow(() -> new NotFoundException("Post not found"));
        var category = categoryRepo.findById(req.categoryId())
                .orElseThrow(() -> new NotFoundException("Category not found"));

        post.setTitle(req.title());
        post.setContent(req.content());
        post.setStatus(req.status());
        post.setCategory(category);

        post = postRepo.save(post);
        return map(post);
    }

    public void delete(String slug) {
        var post = postRepo.findBySlug(slug).orElseThrow(() -> new NotFoundException("Post not found"));
        postRepo.delete(post);
    }

    public Post findBySlugEntity(String slug) {
        return postRepo.findBySlug(slug).orElseThrow(() -> new NotFoundException("Post not found"));
    }

    public PostResponse getBySlug(String slug) {
        return map(findBySlugEntity(slug));
    }

    private PostResponse map(Post p) {
        return new PostResponse(
                p.getId(), p.getTitle(), p.getSlug(), p.getContent(),
                p.getAuthor().getName(), p.getCategory().getName(),
                p.getStatus() != null ? p.getStatus().name() : null
        );
    }

    private String ensureUniqueSlug(String base) {
        String candidate = base;
        int suffix = 1;
        while (postRepo.findBySlug(candidate).isPresent()) {
            candidate = base + "-" + suffix++;
        }
        return candidate;
    }

    public static String slugify(String input) {
        String nowhitespace = input.trim().replaceAll("[\\s]+", "-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return normalized.toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9-]", "-")
                .replaceAll("-{2,}", "-")
                .replaceAll("^-|-$", "");
    }
}