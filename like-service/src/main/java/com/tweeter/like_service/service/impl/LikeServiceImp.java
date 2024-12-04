package com.tweeter.like_service.service.impl;

import com.tweeter.like_service.entity.Like;
import com.tweeter.like_service.entity.LikeId;
import com.tweeter.like_service.entity.PostLikeCount;
import com.tweeter.like_service.exception.ResourceNotFoundException;
import com.tweeter.like_service.repository.LikeRepository;
import com.tweeter.like_service.service.LikeService;
import com.tweeter.like_service.service.PostLikeCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImp implements LikeService {
    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private PostLikeCountService postLikeService;

    @Override
    public Long getTotalLikesByPostId(Long postId) {
        PostLikeCount foundPostlikeCount = postLikeService.getPostLikeCountByPostId(postId);

        if (foundPostlikeCount != null) {
            return foundPostlikeCount.getLikeCount();
        } else {
            return 0L;
        }
    }

    @Override
    public List<Like> getLikesByUserId(Long userId) {
        List<Like> foundLike = likeRepository.findAllLikesByLikeIdUserId(userId);
        System.out.println(foundLike);
        return foundLike;
    }

    @Override
    public Like getLikeByUserIdAndPostId(Long userId, Long postId) {
        try {
            Like foundLike = likeRepository.findById(new LikeId(userId, postId)).
                    orElseThrow(() -> new ResourceNotFoundException("like", "userId and postId", userId + " and " + postId));

            return foundLike;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Like createLike(Long userId, Long postId) {
        LikeId likeId = new LikeId(userId, postId);

        if (likeRepository.findById(likeId).isPresent()) {
            throw new IllegalStateException("Like already exists for userId: " + userId + " and postId: " + postId);
        }

        // create entry to track total post likes, or, if it exists, add to it
        PostLikeCount postLikeCount = postLikeService.getPostLikeCountByPostId(postId);
        if (postLikeCount == null) { // create new if not found
            postLikeCount = new PostLikeCount(postId, 1L);
            postLikeService.addPostLikeCount(postId);
        } else { // update if found
            postLikeCount.setLikeCount(postLikeCount.getLikeCount() + 1);
            postLikeService.updatePostLikeCount(postId, postLikeCount);
        }

        Like newLike = new Like();
        newLike.setLikeId(likeId);

        return likeRepository.save(newLike);
    }

    @Override
    public void deleteLike(Long userId, Long postId) {
        try {
            Like foundLike = likeRepository.findById(new LikeId(userId, postId)).
                    orElseThrow(() -> new ResourceNotFoundException("like", "userId and postId", userId + " and " + postId));

            if (foundLike == null) {
                throw new ResourceNotFoundException("like", "userId and postId", userId + " and " + postId);
            }

            likeRepository.delete(foundLike);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
