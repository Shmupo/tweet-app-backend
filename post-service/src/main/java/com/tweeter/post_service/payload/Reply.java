package com.tweeter.post_service.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
    Long id;
    Long userId;
    Long postId;
    String content;
    String tag;
    private LocalDateTime createdAt;
}
