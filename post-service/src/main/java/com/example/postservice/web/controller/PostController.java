package com.example.postservice.web.controller;

import com.example.postservice.dto.*;
import com.example.postservice.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService service;

    @PostMapping
    public ResponseEntity<PostResponse> create(@RequestBody @Valid PostCreateRequest req) {
        var body = service.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @PutMapping("/{slug}")
    public PostResponse update(@PathVariable String slug, @RequestBody @Valid PostUpdateRequest req) {
        return service.update(slug, req);
    }

    @DeleteMapping("/{slug}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String slug) {
        service.delete(slug);
    }

    @GetMapping("/{slug}")
    public PostResponse bySlug(@PathVariable String slug) {
        return service.getBySlug(slug);
    }
}