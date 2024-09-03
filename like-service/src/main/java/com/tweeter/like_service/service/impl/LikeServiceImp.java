package com.tweeter.like_service.service.impl;

import com.tweeter.like_service.entity.Like;
import com.tweeter.like_service.entity.LikeId;
import com.tweeter.like_service.exception.ResourceNotFoundException;
import com.tweeter.like_service.repository.LikeRepository;
import com.tweeter.like_service.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImp implements LikeService {
    @Autowired
    private LikeRepository likeRepository;

    @Override
    public List<Like> getLikesByPostId(Long postId) {
        return likeRepository.findAllLikesByLikeIdPostId(postId);
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
