package com.tweeter.hashtag_service.service;

import com.tweeter.hashtag_service.entity.Hashtag;
import com.tweeter.hashtag_service.entity.PostHashtag;

import java.util.List;

public interface HashtagService {
    List<Long> getAllPostsOfHashtag(Long id);
    PostHashtag addPostIdToHashtag(String hashtagName, Long postId);
    Hashtag createHashtag(String hashtagName);
    Hashtag updateHashtag(Long id, Hashtag hashtag);
    void deleteHashtag(Long id);
}
