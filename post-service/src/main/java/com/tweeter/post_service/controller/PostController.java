package com.tweeter.post_service.controller;

import com.tweeter.post_service.entity.Post;
import com.tweeter.post_service.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/posts")
@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> data = postService.getAllPosts();
        return ResponseEntity.ok(data);
    }

    @GetMapping("tag/{tag}")
    public ResponseEntity<List<Post>> getAllPostsByTag(@PathVariable("tag") String tag) {
        List<Post> data = postService.getAllPostsByTag(tag);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable("postId") Long id) {
        Post data = postService.getPostById(id);
        return ResponseEntity.ok(data);
    }

    // all posts of a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getAllUserPosts(@PathVariable("userId") Long userId) {
        List<Post> data = postService.getAllPostsOfUser(userId);
        return ResponseEntity.ok(data);
    }


//  This will be handled by reply-service
//    @GetMapping("/{postId}/replies")
//    private ResponseEntity<PostReplies> getPostReplies(@PathVariable("postId") Long postId) {
//        PostReplies data = postService.getPostWithReplies(postId);
//        return ResponseEntity.ok(data);
//    }

    @PostMapping
    public ResponseEntity<Post> createPost(@Validated @RequestBody Post post) {
        Post data = postService.createPost(post);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Post> getAllUserPosts(@PathVariable("postId") Long postId,
                                                       @Validated @RequestBody Post post) {
        Post data = postService.updatePost(postId, post);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{postId}")
    public HttpStatus deletePost(@PathVariable("postId") Long postId) {
        postService.deletePost(postId);
        return HttpStatus.OK;
    }
}
