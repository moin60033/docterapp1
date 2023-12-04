package com.example.myblog3.controller;

import com.example.myblog3.payload.PostDto;
import com.example.myblog3.payload.PostResponse;
import com.example.myblog3.service.Postservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private Postservice postservice;

    //http://localhost:8080/api/post
    @PostMapping("/post")
    public ResponseEntity<?> savePost(@Valid @RequestBody PostDto postDto, BindingResult result){
        if (result.hasErrors()){
            new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto dto = postservice.savePost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/post/1
    @GetMapping({"/id"})
    public ResponseEntity<PostDto> getPostById(@PathVariable ("id") long Id){
        PostDto dto = postservice.getPostBYId(Id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    //http://localhost:8080/api/post/1
    @DeleteMapping({"/id"})
    public ResponseEntity<String> deletePostById(@PathVariable ("id") long Id){
        postservice.deletePostById(Id);
        return null;
    }
    //http://localhost:8080/api/post/1
    @PutMapping({"/id"})
    public ResponseEntity<PostDto> updatePostById(@PathVariable ("id") long Id,
                                                  @RequestBody PostDto postDto){
        PostDto dto = postservice.updatePostById(Id, postDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    //http://localhost:8080/api/post?pageNo=1&pageSize=5&sortBy
    @GetMapping
    public PostResponse getPost(
            @RequestParam(name = "pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(name = "pageSize",defaultValue = "0",required = false) int pageSize,
            @RequestParam(name = "sortBy",defaultValue = "0",required = false) String sortBy,
            @RequestParam(name = "sortDir",defaultValue = "0",required = false) String sortDir
    ){
        PostResponse postResponse = postservice.getPost(pageNo,pageSize,sortBy,sortDir);
        return postResponse;
    }
}
