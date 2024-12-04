package com.tweeter.hashtag_service.controller;

import com.tweeter.hashtag_service.payload.PostDTO;
import com.tweeter.hashtag_service.service.HashtagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/hashtag")
@RestController
public class HashtagController {
    @Autowired
    private HashtagService hashtagService;

    @GetMapping("/{hashtagName}")
    public ResponseEntity<List<PostDTO>> getAllPostsOfHashtag(
            @PathVariable("hashtagName") String hashtagName
    ) {
        return null;
    }

    @PostMapping("/api/hashtag/{hashtagName}/add-post/{postId}")
    public HttpStatus addPostIdToHashtag(
            @PathVariable("hashtagName") String hashtagName,
            @PathVariable("hashtagName") String postId
    ) {
        return null;
    }
}
