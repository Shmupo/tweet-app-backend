package com.tweeter.post_service.service;

import com.tweeter.post_service.entity.Post;
import com.tweeter.post_service.payload.PostReplies;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    List<Post> getAllPostsByTag(String tag);
    Post getPostById(Long postId);
    PostReplies getPostWithReplies(Long postId);
    Post createPost(Post post);
    Post updatePost(Long postId, Post post);
    List<Post> getAllPostsOfUser(Long userId);
    void deletePost(Long id);
}
