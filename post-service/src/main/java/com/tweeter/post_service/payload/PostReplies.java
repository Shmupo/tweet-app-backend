package com.tweeter.post_service.payload;

import com.tweeter.post_service.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostReplies {
    Post post;
    List<Reply> replies;
}
