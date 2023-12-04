package com.example.myblog3.service;

import com.example.myblog3.payload.PostDto;
import com.example.myblog3.payload.PostResponse;

public interface Postservice {
    PostDto savePost(PostDto postDto);

    PostDto getPostBYId(long id);

    void deletePostById(long id);

    PostDto updatePostById(long id, PostDto postDto);

    PostResponse getPost(int pageNo, int pageSize, String sortBy, String sortDir);
}
