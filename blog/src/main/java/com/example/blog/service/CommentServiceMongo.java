package com.example.blog.service;

import com.example.blog.domain.CommentMongo;
import com.example.blog.domain.Post;
import com.example.blog.repository.CommentMongoRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.web.exception.NotFoundException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceMongo {
    private final CommentMongoRepository commentRepo;
    private final PostRepository postRepo;

    public CommentServiceMongo(CommentMongoRepository commentRepo, PostRepository postRepo) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
    }

    @Transactional(readOnly = true)
    public List<CommentMongo> listByPostSlug(String postSlug){
        Post p = postRepo.findBySlug(postSlug).orElseThrow(() -> new NotFoundException("Post não encontrado"));

        return commentRepo.findByPostId(p.getId());
    }

    @Transactional
    public CommentMongo create(String postSlug,
                             @NotBlank String authorName,
                             @Email String authorEmail,
                             @NotBlank String content){

        Post p = postRepo.findBySlug(postSlug).orElseThrow(() -> new NotFoundException("Post não encontrado"));

        CommentMongo c = new CommentMongo();
        c.setPostId(p.getId());
        c.setAuthorName(authorName);
        c.setAuthorEmail(authorEmail);
        c.setContent(content);
        c.setCreatedAt(LocalDateTime.now());
        return commentRepo.save(c);
    }

    @Transactional
    public void delete(String commentId){
        if (!commentRepo.existsById(commentId)) {
            throw new IllegalArgumentException("Comentário não encontrado: " + commentId);
        }
        commentRepo.deleteById(commentId);
    }
}
