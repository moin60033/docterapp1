package com.example.myblog3.payload;

import lombok.Data;

import java.util.List;

@Data
public class PostResponse {
    List<PostDto> postDtos;
    private int pageNo;
    private int pageSize;
    private Long totalElements;
    private int totalpages;
    private boolean last;
}
