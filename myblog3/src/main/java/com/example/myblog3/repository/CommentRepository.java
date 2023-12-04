package com.example.myblog3.repository;

import com.example.myblog3.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(long postId);
}
