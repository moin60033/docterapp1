package com.example.myblog3.controller;

import com.example.myblog3.payload.CommentDto;
import com.example.myblog3.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    private CommentService commentService;

        //http://localhost:8080/api/posts/2/comments
        @PostMapping("/posts/{postId}/comments")
        public ResponseEntity<CommentDto> createComment(@PathVariable(value =
                "postId") long postId,
                                                        @RequestBody CommentDto commentDto) {
            return new ResponseEntity<>(commentService.createComment(postId,
                    commentDto), HttpStatus.CREATED);
        }
    //http://localhost:8080/api/posts/1/comments
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentByPostId(@PathVariable(value = "postId") long postId){
        return commentService.getCommentsByPostId(postId);
    }
       //http://localhost:8080/api/posts/2/comments/1
       @GetMapping("/posts/{postId}/comments/{commentId}")
       public ResponseEntity<CommentDto> getcommentById(@PathVariable(value = "postId") long postId,
                                                        @PathVariable(value = "commentId") long commentId){
           CommentDto dto = commentService.getCommentById(postId, commentId);
           return new ResponseEntity<>(dto,HttpStatus.OK);
       }
       //http://localhost:8080/api/commments
       @GetMapping("/comments")
       public List<CommentDto> getAllCommentsById(){
           List<CommentDto> dtos = commentService.getAllCommentsById();
           return dtos;
       }
       @DeleteMapping("/posts/{postId}/comments/{commentId}")
       public ResponseEntity<String> deleteCommentById(@PathVariable(value = "postId") long postId,
                                                           @PathVariable(value = "commentId") long commentId){
            commentService.deleteCommentById(postId,commentId);
           return new ResponseEntity<>("comment is deleted",HttpStatus.OK);
       }

}
