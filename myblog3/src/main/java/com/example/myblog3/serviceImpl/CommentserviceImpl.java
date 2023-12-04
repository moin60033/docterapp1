package com.example.myblog3.serviceImpl;

import com.example.myblog3.entity.Comment;
import com.example.myblog3.entity.Post;
import com.example.myblog3.payload.CommentDto;
import com.example.myblog3.repository.CommentRepository;
import com.example.myblog3.repository.PostRepository;
import com.example.myblog3.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentserviceImpl implements CommentService {


    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentserviceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    private ModelMapper mapper;

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFound("Post not found with id: " + postId)
        );

        comment.setPost(post);

        Comment savedComment = commentRepository.save(comment);

        CommentDto dto = mapToDto(savedComment);
        return dto;
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFound("Post not found with id: " + postId)
        );

        List<Comment> comments = commentRepository.findByPostId(postId);
        List<CommentDto> commentDtos = comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());

        return commentDtos;
    }


    @Override
    public CommentDto getCommentById(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFound("post not found with Id: " + postId)
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFound("comment not found with Id: " + commentId)
        );
        CommentDto dto = mapToDto(comment);
        return dto;
    }

    @Override
    public List<CommentDto> getAllCommentsById() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentDto> dtos = comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public void deleteCommentById(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFound("post not found with Id: " + postId)
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFound("comment not found with Id: " + commentId)
        );
        commentRepository.deleteById(commentId);
    }


    private CommentDto mapToDto(Comment savedComment) {
        CommentDto dto = mapper.map(savedComment, CommentDto.class);
        return dto;
    }

    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = mapper.map(commentDto, Comment.class);
        return comment;
    }
}
