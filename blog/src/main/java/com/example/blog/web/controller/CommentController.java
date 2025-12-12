package com.example.blog.web.controller;

import com.example.blog.domain.Comment;
import com.example.blog.domain.CommentMongo;
import com.example.blog.dto.CommentCreateRequest;
import com.example.blog.service.CommentService;
import com.example.blog.service.CommentServiceMongo;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
class CommentController {

    //private final CommentService service;
    private final CommentServiceMongo service;
    public CommentController(CommentServiceMongo service){ this.service = service; }

    @GetMapping("/posts/{slug}/comments")
    public List<CommentMongo> findByPostSlug(@PathVariable String slug){ return service.listByPostSlug(slug); }

    @PostMapping("/posts/{slug}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentMongo add(@PathVariable String slug, @Valid @RequestBody CommentCreateRequest req){
        return service.create(slug, req.authorName(), req.authorEmail(), req.content());
    }

    @DeleteMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id){ service.delete(id); }
}
