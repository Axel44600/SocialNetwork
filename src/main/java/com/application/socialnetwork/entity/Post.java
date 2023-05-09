package com.application.socialnetwork.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Post extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    private String message;

    private LocalDateTime dateOfPost;

}
