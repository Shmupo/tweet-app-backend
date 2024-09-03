package com.tweeter.like_service.controller;

import com.tweeter.like_service.entity.Like;
import com.tweeter.like_service.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/likes")
@RestController
public class LikeController {
    @Autowired
    private LikeService likeService;

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Like>> getLikesByPostId(@PathVariable("postId") Long postId) {
        return ResponseEntity.ok(likeService.getLikesByPostId(postId));
    }

    @GetMapping("/post/{postId}/count")
    public ResponseEntity<Integer> getLikesCountByPostId(@PathVariable("postId") Long postId) {
        return ResponseEntity.ok(likeService.getLikesByPostId(postId).size());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Like>> getLikesByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(likeService.getLikesByUserId(userId));
    }

    @GetMapping("/user+post/{userId}/{postId}")
    public ResponseEntity<Like> getLikeByUserIdAndPostId(@PathVariable("userId") Long userId,
                                                         @PathVariable("postId") Long postId) {
        return ResponseEntity.ok(likeService.getLikeByUserIdAndPostId(userId, postId));
    }

    @GetMapping("/user+post/{userId}/{postId}/exists")
    public ResponseEntity<Boolean> getLikeByUserIdAndPostIdExists(@PathVariable("userId") Long userId,
                                                                  @PathVariable("postId") Long postId) {
        boolean exists = likeService.getLikeByUserIdAndPostId(userId, postId) != null;
        return ResponseEntity.ok(exists);
    }


    @PostMapping("/user+post/{userId}/{postId}")
    public ResponseEntity<Like> createLike(@PathVariable("userId") Long userId,
                                           @PathVariable("postId") Long postId) {
        return ResponseEntity.ok(likeService.createLike(userId, postId));
    }

    @DeleteMapping("/user+post/{userId}/{postId}")
    public HttpStatus deleteLikeByUserIdAndPostId(@PathVariable("userId") Long userId,
                                                  @PathVariable("postId") Long postId) {
        likeService.deleteLike(userId, postId);
        return HttpStatus.OK;
    }
}
