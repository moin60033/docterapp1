package com.example.myblog3.payload;

import lombok.Data;

import javax.persistence.Column;
@Data
public class PostDto {
    private long id;
    private String title;
    private String description;
    private String content;

}
