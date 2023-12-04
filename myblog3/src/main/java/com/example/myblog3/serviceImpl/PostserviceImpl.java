package com.example.myblog3.serviceImpl;

import com.example.myblog3.entity.Post;
import com.example.myblog3.payload.PostDto;
import com.example.myblog3.payload.PostResponse;
import com.example.myblog3.repository.PostRepository;
import com.example.myblog3.service.Postservice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class PostserviceImpl implements Postservice {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public PostDto savePost(PostDto postDto) {
        Post post = maptoEntity(postDto);
        Post savedPost = postRepository.save(post);
        PostDto dto = mapToDto(savedPost);
        return dto;
    }

    @Override
    public PostDto getPostBYId(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("post not found with id:+Id")
        );
        PostDto dto = mapToDto(post);
        return dto;
    }

    @Override
    public void deletePostById(long id) {
        postRepository.findById(id);
    }

    @Override
    public PostDto updatePostById(long id, PostDto postDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("post not found with id:+Id")
        );
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post updatePost = postRepository.save(post);
        PostDto dto = mapToDto(updatePost);
        return dto;
    }

    @Override
    public PostResponse getPost(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);
        Page<Post> pagePost = postRepository.findAll(pageable);
        List<Post> posts = pagePost.getContent();
        List<PostDto> postDtos = posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setPostDtos(postDtos);
        postResponse.setPageNo(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalpages(pagePost.getTotalPages());
        postResponse.setLast(pagePost.isLast());
        return postResponse;
    }

    Post maptoEntity(PostDto postDto){
        Post post = mapper.map(postDto, Post.class);
        //Post post = new Post();
        //post.setTitle(postDto.getTitle());
        //post.setDescription(postDto.getDescription());
        //post.setContent(postDto.getContent());
        return post;
    }
    PostDto mapToDto(Post post){
        PostDto dto = mapper.map(post, PostDto.class);
        //PostDto dto = new PostDto();
        //dto.setId(post.getId());
        //dto.setTitle(post.getTitle());
        //dto.setDescription(post.getDescription());
        //dto.setContent(post.getContent());
        return dto;
    }
}
