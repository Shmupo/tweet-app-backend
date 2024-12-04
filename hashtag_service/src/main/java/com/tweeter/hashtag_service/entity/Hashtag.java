package com.tweeter.hashtag_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "hashtags"
)
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "hashtag_name", unique = true, nullable = false, length = 64)
    private String hashtagName;

    public Hashtag(String hashtagName) {
        this.hashtagName = hashtagName;
    }
}
