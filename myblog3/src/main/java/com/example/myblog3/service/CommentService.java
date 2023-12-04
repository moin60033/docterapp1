package com.example.myblog3.service;

import com.example.myblog3.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto getCommentById(long postId, long commentId);

    List<CommentDto> getAllCommentsById();


    void deleteCommentById(long postId, long commentId);

}
