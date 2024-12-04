package com.tweeter.hashtag_service.repository;

import com.tweeter.hashtag_service.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    Hashtag findHashtagByHashtagName(String hashtagName);
}
