package com.tweeter.reply_service.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private Long id;
    private Long userId;
    private String title;
    private String content;
    private String tag = null;
    private LocalDateTime createdAt;
    private Set<Long> likedByUserIds = new HashSet<>();
}
