package com.tweeter.hashtag_service.repository;

import com.tweeter.hashtag_service.entity.PostHashtag;
import com.tweeter.hashtag_service.entity.PostHashtagId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostHashtagRepository extends JpaRepository<PostHashtag, PostHashtagId> {
    @Query("SELECT ph.id.postId FROM PostHashtag ph WHERE ph.id.hashtagId = :hashtagId")
    List<Long> findPostIdsByHashtagId(@Param("hashtagId") long hashtagId);
}
