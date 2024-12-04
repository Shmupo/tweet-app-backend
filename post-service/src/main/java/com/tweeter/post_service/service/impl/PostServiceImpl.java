package com.tweeter.post_service.service.impl;

import com.tweeter.post_service.entity.Post;
import com.tweeter.post_service.exception.ResourceNotFoundException;
import com.tweeter.post_service.repository.PostRepository;
import com.tweeter.post_service.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(Long postId) {
        return postRepository.findById(postId).
                orElseThrow(() -> new ResourceNotFoundException("post", "postId", + postId));
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Long postId, Post post) {
        Post foundPost = postRepository.findById(postId).
                orElseThrow(() -> new ResourceNotFoundException("post", "postId", + postId));
        foundPost.setUserId(post.getUserId());
        foundPost.setTitle(post.getTitle());
        foundPost.setContent(post.getContent());
        return foundPost;
    }

    @Override
    public List<Post> getAllPostsOfUser(Long userId) {
        return postRepository.findAllPostsByUserId(userId);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
