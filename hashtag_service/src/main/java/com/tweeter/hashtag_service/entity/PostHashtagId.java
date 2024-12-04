package com.tweeter.hashtag_service.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
public class PostHashtagId implements Serializable {
    private long postId;
    private long hashtagId;
}
