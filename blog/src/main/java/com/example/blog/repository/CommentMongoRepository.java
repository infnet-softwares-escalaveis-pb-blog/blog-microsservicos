package com.example.blog.repository;

import com.example.blog.domain.CommentMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentMongoRepository extends MongoRepository<CommentMongo, String> {
    List<CommentMongo> findByPostId(Long postId);
}
