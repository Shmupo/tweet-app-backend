package com.tweeter.post_service.service;

import com.tweeter.post_service.entity.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    Post getPostById(Long postId);
    Post createPost(Post post);
    Post updatePost(Long postId, Post post);
    List<Post> getAllPostsOfUser(Long userId);
    void deletePost(Long id);
}
