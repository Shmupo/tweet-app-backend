package com.tweeter.post_service.service.impl;

import com.tweeter.post_service.entity.Post;
import com.tweeter.post_service.exception.ResourceNotFoundException;
import com.tweeter.post_service.payload.PostReplies;
import com.tweeter.post_service.payload.Reply;
import com.tweeter.post_service.repository.PostRepository;
import com.tweeter.post_service.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
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

    // api call
    @Override
    public PostReplies getPostWithReplies(Long postId) {
        Post foundPost = postRepository.findById(postId).
                orElseThrow(() -> new ResourceNotFoundException("post", "postId", + postId));

        Reply[] replyArray = restTemplate.getForObject("http://REPLY-SERVICE/api/posts/" + foundPost.getId() + "/replies", Reply[].class);
        List<Reply> replies = replyArray != null ? Arrays.asList(replyArray) : new ArrayList<>();

        return new PostReplies(foundPost, replies);
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
        foundPost.setTag(post.getTag());
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
