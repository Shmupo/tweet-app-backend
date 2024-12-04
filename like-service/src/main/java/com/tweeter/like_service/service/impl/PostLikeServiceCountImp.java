package com.tweeter.like_service.service.impl;

import com.tweeter.like_service.entity.PostLikeCount;
import com.tweeter.like_service.repository.PostLikeCountRepository;
import com.tweeter.like_service.service.PostLikeCountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

// Post like counts are ONLY created when a user first likes a post
// The idea is to reduce the amount of data used for 'dead' posts that nobody likes

@Service
public class PostLikeServiceCountImp implements PostLikeCountService {
    @Autowired
    private PostLikeCountRepository postLikeCountRepository;

    @Override
    public PostLikeCount getPostLikeCountByPostId(Long postId) {
        PostLikeCount postLikeCount = postLikeCountRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found."));

        return postLikeCount;
    }

    @Override
    public PostLikeCount addPostLikeCount(Long postId) {
        if (postLikeCountRepository.existsById(postId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post already has a like count entry.");
        }

        PostLikeCount postLikeCount = new PostLikeCount(postId, 0L);
        return postLikeCountRepository.save(postLikeCount);
    }

    @Override
    @Transactional
    public PostLikeCount updatePostLikeCount(Long postId, PostLikeCount postLikeCount) {
        PostLikeCount existingPostLikeCount = postLikeCountRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found."));

        existingPostLikeCount.setLikeCount(postLikeCount.getLikeCount());

        return postLikeCountRepository.save(existingPostLikeCount);
    }

    @Override
    public void deletePostLikeCount(Long postId) {
        if (!postLikeCountRepository.existsById(postId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found.");
        }

        // Delete the like count for the post
        postLikeCountRepository.deleteById(postId);
    }
}
